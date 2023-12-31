package wanted.onboarding.jobposting.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import wanted.onboarding.jobposting.dto.JobPostingMultiResponseDto;
import wanted.onboarding.jobposting.dto.JobPostingPatchDto;
import wanted.onboarding.jobposting.dto.JobPostingPostDto;
import wanted.onboarding.jobposting.entity.JobPosting;
import wanted.onboarding.jobposting.service.JobPostingService;

@RestController
@RequestMapping("/jobs")
public class JobPostingController {
	private final JobPostingService jobPostingService;

	public JobPostingController(JobPostingService jobPostingService) {
		this.jobPostingService = jobPostingService;
	}

	@PostMapping
	public ResponseEntity postJobPosting(@RequestBody JobPostingPostDto jobPostingPostDto) {
		JobPosting jobPosting = jobPostingService.createJobPosting(jobPostingPostDto);
		URI uri = UriComponentsBuilder.newInstance().path("/jobs/" + jobPosting.getJobPostingId()).build().toUri();
		return ResponseEntity.created(uri).build();
	}

	@PatchMapping("/{jobPosting-id}")
	public ResponseEntity patchJobPosting(@RequestBody JobPostingPatchDto jobPostingPatchDto,
		@PathVariable("jobPosting-id") Long jobPostingId) {
		JobPosting updatedJobPosting = jobPostingService.updateJobPosting(jobPostingPatchDto, jobPostingId);
		return new ResponseEntity(updatedJobPosting, HttpStatus.OK);
	}

	@DeleteMapping("/{jobPosting-id}")
	public ResponseEntity deleteJobPosting(@PathVariable("jobPosting-id") Long jobPostingId) {
		jobPostingService.deleteJobPosting(jobPostingId);
		return ResponseEntity.noContent().build();
	}

	@GetMapping
	public ResponseEntity getJobPostings() {
		return new ResponseEntity(jobPostingService.findAllJobPostings(), HttpStatus.OK);
	}
}
