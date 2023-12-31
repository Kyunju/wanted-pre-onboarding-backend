package wanted.onboarding.jobposting.service;

import java.util.List;

import wanted.onboarding.jobposting.dto.JobPostingMultiResponseDto;
import wanted.onboarding.jobposting.dto.JobPostingPatchDto;
import wanted.onboarding.jobposting.dto.JobPostingPostDto;
import wanted.onboarding.jobposting.entity.JobPosting;

public interface JobPostingService {
	public JobPosting createJobPosting(JobPostingPostDto jobPostingPostDto);

	public JobPosting updateJobPosting(JobPostingPatchDto jobPostingPatchDto, Long jobPostingId);

	public void deleteJobPosting(Long jobPostingId);

	public List<JobPostingMultiResponseDto> findAllJobPostings();
}
