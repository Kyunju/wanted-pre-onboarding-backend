package wanted.onboarding.jobposting.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.boot.autoconfigure.batch.BatchProperties;

import lombok.Getter;
import wanted.onboarding.company.entity.Company;
import wanted.onboarding.jobposting.dto.JobPostingPostDto;

@Entity
public class JobPosting {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long jobPostingId;
	private String position;
	private int compensation;
	private String jobDescription;
	private String requiredSkills;
	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company;

	protected JobPosting() {
	}

	private JobPosting(Long jobPostingId) {
		this.jobPostingId = jobPostingId;
	}

	private JobPosting(String position, int compensation, String jobDescription, String requiredSkills) {
		this.position = position;
		this.compensation = compensation;
		this.jobDescription = jobDescription;
		this.requiredSkills = requiredSkills;
	}

	public static JobPosting from(long jobPostingId) {
		return new JobPosting(jobPostingId);
	}

	public static JobPosting from(JobPostingPostDto jobPostingPostDto) {
		return new JobPosting(
			jobPostingPostDto.getPosition(),
			jobPostingPostDto.getCompensation(),
			jobPostingPostDto.getJobDescription(),
			jobPostingPostDto.getRequiredSkills());
	}

	public Long getJobPostingId() {
		return jobPostingId;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
}
