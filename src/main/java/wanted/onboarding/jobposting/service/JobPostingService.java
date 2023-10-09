package wanted.onboarding.jobposting.service;

import wanted.onboarding.jobposting.dto.JobPostingPostDto;
import wanted.onboarding.jobposting.entity.JobPosting;

public interface JobPostingService {
	public JobPosting createJobPosting(JobPostingPostDto jobPostingPostDto);
}
