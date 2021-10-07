// SPDX-License-Identifier: MIT
pragma solidity ^0.6.0;

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


    enum TypeofCertificate {Graduate, Transcript}

    constructor () public {
        owner = msg.sender;
    }

    /**
     * @dev Find All Certificate by User
     * @param user
     */
    // function getCertificates(address _user) public view returns(Certificate[] memory) {
    //     return userCertificates[_user];
    // }

    /**
     * @dev Find one of certificate by User, CertificateNumber
     * @param _user, certificateNumber
     */
    function getCertificate(address _user, uint16 _certificateNumber) public view returns(string memory, string memory, uint256) {
        Certificate memory certificate = userCertificates[_user][_certificateNumber];
        return (certificate.fileHash, certificate.fileName, certificate.expiryDate);
    }

    /**
     * @dev Add Certificate by User
     * @param _user, certificateNumber
     */
    function getExpiryDate(address _user, uint16 _certificateNumber) public view returns(uint256) {
        return userCertificates[_user][_certificateNumber].expiryDate;
    }

    /**
     * @dev Add Certificate by User
     * @param _fileHash, fileName, userAddress, certificateNumber
     */
    function addCertificate(string memory _fileHash, string memory _fileName, address _user, uint16 _certificateNumber) public onlyOwner {
        emit AddCertificate(_fileHash, _fileName, _user, _certificateNumber);

        if (!containsUser(_user)) {
            users[_user] = true;
        }
        // Update expiryDate
        if (userCertificates[_user][_certificateNumber].flag) {
            userCertificates[_user][_certificateNumber].expiryDate = now + availableDays;
            return;
        }
        userCertificateCount[_user]++;
        userCertificates[_user][_certificateNumber] = Certificate(_fileHash, _fileName, now + availableDays, true);
    }

    // function getCertificateImage(address _user, uint16 _certificateNumber) public returns(bytes32) {
    //     return getCertificate(_user, _certificateNumber).fileName;
    // }

    // function getCertificateExpiryDate(address _user, uint16 _certificateNumber) public returns(uint256) {
    //     return getCertificate(_user, _certificateNumber).expiryDate;
    // }

    /**
     * @dev Remove one of certificate
     * @param _user, certificateNumber
     */
    function removeCertificate(address _user, uint16 _certificateNumber) public {
        userCertificates[_user][_certificateNumber].flag = false;
        userCertificateCount[_user]--;
    }

    /**
     * @dev Check if the user is already registred
     * @param {user}
     */
    function containsUser(address _user) public view returns (bool) {
        return users[_user];
    }

}

