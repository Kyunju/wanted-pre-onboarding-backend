package wanted.onboarding.jobposting.dto;

import lombok.Builder;

@Builder
public class JobPostingPatchDto {
	private String position;
	private int compensation;
	private String jobDescription;
	private String requiredSkills;

	public String getPosition() {
		return position;
	}

	public int getCompensation() {
		return compensation;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public String getRequiredSkills() {
		return requiredSkills;
	}
}
