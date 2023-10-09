package wanted.onboarding.company.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import wanted.onboarding.company.entity.Company;
import wanted.onboarding.company.repository.CompanyRepository;

@Service
public class CompanyService {
	private final CompanyRepository companyRepository;

	public CompanyService(CompanyRepository companyRepository) {
		this.companyRepository = companyRepository;
	}

	// 실제 데이터베이스에 회사가 존재하는지 확인하고 Company 엔티티를 리턴하는 메소드
	public Company getVerifiedCompanyFromId(long companyId) {
		Optional<Company> optionalCompany = companyRepository.findById(companyId);
		return optionalCompany.orElseThrow();
	}
}
