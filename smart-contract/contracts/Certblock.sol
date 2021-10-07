// SPDX-License-Identifier: MIT
pragma solidity ^0.6.0;
pragma experimental ABIEncoderV2;

import "./Ownable.sol";

contract Certblock is Ownable {

    struct Certificate {
        string fileHash;
        string fileName;
        uint256 expiryDate;
        bool flag;
    }

    uint constant availableDays = 365 days;

    mapping (address => bool) users;
    mapping (address => uint16) userCertificateCount;
    mapping (address => Certificate[6]) userCertificates;

    event AddCertificate(string fileHash, string fileName, address user, uint16 certificateNumber);

    enum TypeOfCertificate {Graduate, Transcript}

    constructor () public {
        owner = msg.sender;
    }

    /**
     * @dev Find one of certificate by User, CertificateNumber
     * @param _user, _certificateNumber
     */
    function getCertificate(address _user, uint16 _certificateNumber) public view returns(string memory, string memory, uint256) {
        Certificate memory certificate = userCertificates[_user][_certificateNumber];
        return (certificate.fileHash, certificate.fileName, certificate.expiryDate);
    }

    /**
     * @dev Find all of certificate by User
     * @param {_user}
     */
    function getCertificate(address _user) public view returns(Certificate[6] memory) {
        return userCertificates[_user];
    }

    /**
     * @dev Get ExpiryDate
     * @param _user, _certificateNumber
     */
    function getExpiryDate(address _user, uint16 _certificateNumber) public view returns(uint256) {
        return userCertificates[_user][_certificateNumber].expiryDate;
    }

    /**
     * @dev Add Certificate by User
     * @param _fileHash, _fileName, _userAddress, _certificateNumber
     */
    function addCertificate(string memory _fileHash, string memory _fileName, address _user, uint16 _certificateNumber) public onlyOwner {
        emit AddCertificate(_fileHash, _fileName, _user, _certificateNumber);

        if (!containsUser(_user)) {
            users[_user] = true;
        }
        // Update expiryDate
        if (userCertificates[_user][_certificateNumber].flag) {
            updateExpiryDate(_user, _certificateNumber);
            return;
        }
        increaseCertificate(_user);
        userCertificates[_user][_certificateNumber] = Certificate(_fileHash, _fileName, now + availableDays, true);
    }

    /**
     * @dev Update ExpiryDate : now date + 1 years
     */
    function updateExpiryDate(address _user, uint16 _certificateNumber) internal {
        userCertificates[_user][_certificateNumber].expiryDate = now + availableDays;
    }

    /**
     * @dev Remove one of certificate
     * @param _user, _certificateNumber
     */
    function removeCertificate(address _user, uint16 _certificateNumber) public {
        userCertificates[_user][_certificateNumber].flag = false;
        decreaseCertificate(_user);
    }

    /**
     * @dev Check if the user is already registred
     */
    function containsUser(address _user) public view returns (bool) {
        return users[_user];
    }

    /**
     * @dev Increase certificate count
     */
    function increaseCertificate(address _user) internal {
        userCertificateCount[_user]++;
    }

    /**
     * @dev Decrease certificate count
     */
    function decreaseCertificate(address _user) internal {
        userCertificateCount[_user]--;
    }
}

