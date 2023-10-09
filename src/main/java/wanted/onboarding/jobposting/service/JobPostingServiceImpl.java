package wanted.onboarding.jobposting.service;

import org.springframework.stereotype.Service;

import wanted.onboarding.company.entity.Company;
import wanted.onboarding.company.service.CompanyService;
import wanted.onboarding.jobposting.dto.JobPostingPostDto;
import wanted.onboarding.jobposting.entity.JobPosting;
import wanted.onboarding.jobposting.repository.JobPostingRepository;

@Service
public class JobPostingServiceImpl implements JobPostingService {
	private final JobPostingRepository jobPostingRepository;
	private final CompanyService companyService;

	public JobPostingServiceImpl(JobPostingRepository jobPostingRepository, CompanyService companyService) {
		this.jobPostingRepository = jobPostingRepository;
		this.companyService = companyService;
	}

	@Override
	public JobPosting createJobPosting(JobPostingPostDto jobPostingPostDto) {
		JobPosting jobPosting = JobPosting.from(jobPostingPostDto);
		Company company = companyService.getVerifiedCompanyFromId(jobPostingPostDto.getCompanyId());
		jobPosting.setCompany(company);

		return jobPostingRepository.save(jobPosting);
	}
}
