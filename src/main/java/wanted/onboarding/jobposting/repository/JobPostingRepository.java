package wanted.onboarding.jobposting.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import wanted.onboarding.jobposting.entity.JobPosting;

public interface JobPostingRepository extends JpaRepository<JobPosting, Long> {
}
