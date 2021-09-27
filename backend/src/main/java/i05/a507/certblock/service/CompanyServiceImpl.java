package i05.a507.certblock.service;

import i05.a507.certblock.domain.Company;
import i05.a507.certblock.repository.CompanyRepository;
import org.springframework.stereotype.Service;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("companyService")
public class CompanyServiceImpl implements CompanyService {

	CompanyRepository companyRepository;

	@Override
	public Company getCompany(int userId) {
		Company company = companyRepository.findById(userId);
		return company;
	}
}
