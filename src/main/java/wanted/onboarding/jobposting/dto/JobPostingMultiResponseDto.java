package wanted.onboarding.jobposting.dto;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Builder;
import lombok.Getter;
import wanted.onboarding.jobposting.entity.JobPosting;
@Getter
public class JobPostingMultiResponseDto {
	private Long jobPostingId;
	private String companyName;
	private String country;
	private String province;
	private String position;
	private int compensation;
	private String requiredSkills;

	public JobPostingMultiResponseDto() {
	}

	public JobPostingMultiResponseDto(Long jobPostingId, String companyName, String country, String province,
		String position, int compensation, String requiredSkills) {
		this.jobPostingId = jobPostingId;
		this.companyName = companyName;
		this.country = country;
		this.province = province;
		this.position = position;
		this.compensation = compensation;
		this.requiredSkills = requiredSkills;
	}

	public static List<JobPostingMultiResponseDto> createResponseDtoList(List<JobPosting> jobPostings) {
		return jobPostings.stream()
			.map(jobPosting -> new JobPostingMultiResponseDto(
				jobPosting.getJobPostingId(),
				jobPosting.getCompany().getName(),
				jobPosting.getCompany().getCountry(),
				jobPosting.getCompany().getProvince(),
				jobPosting.getPosition(),
				jobPosting.getCompensation(),
				jobPosting.getRequiredSkills()
			))
			.collect(Collectors.toList());
	}
}
