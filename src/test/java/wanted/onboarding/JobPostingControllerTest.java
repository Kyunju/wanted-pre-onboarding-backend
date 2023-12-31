package wanted.onboarding;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import wanted.onboarding.jobposting.controller.JobPostingController;
import wanted.onboarding.jobposting.dto.JobPostingPatchDto;
import wanted.onboarding.jobposting.dto.JobPostingPostDto;
import wanted.onboarding.jobposting.entity.JobPosting;
import wanted.onboarding.jobposting.service.JobPostingService;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.google.gson.Gson;

@WebMvcTest(JobPostingController.class)
@AutoConfigureMockMvc
@DisplayName("채용공고 컨트롤러 테스트")
public class JobPostingControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private Gson gson;
	@MockBean
	private JobPostingService jobPostingService;

	@Test
	@DisplayName("[API][POST] 채용공고 등록 테스트")
	void jobPostingPost() throws Exception {
		// given
		Long jobPostingId = 1L;
		JobPosting jobPosting = JobPosting.from(jobPostingId);
		// post 요청시 requestBody 를 위한 Dto 객체
		JobPostingPostDto jobPostingPostDto =
			JobPostingPostDto.builder()
				.companyId(1L)
				.position("주니어 Java 개발자")
				.jobDescription("Java 개발자 구인합니다")
				.compensation(300000)
				.requiredSkills("JAVA, Spring")
				.build();
		String requestBody = gson.toJson(jobPostingPostDto);
		given(jobPostingService.createJobPosting(Mockito.any(JobPostingPostDto.class))).willReturn(jobPosting);

		// when
		ResultActions actions =
			mockMvc.perform(
				post("/jobs")
					.contentType(MediaType.APPLICATION_JSON)
					.content(requestBody)
					.accept(MediaType.APPLICATION_JSON)
			);

		// then
		actions
			.andExpect(status().isCreated())
			.andExpect(header().string("Location", is("/jobs/" + jobPostingId)));
	}

	@Test
	@DisplayName("[API][PATCH] 채용공고 수정 테스트")
	void jobPostingPatch() throws Exception {
		//given
		Long jobPostingId = 1L;
		JobPostingPostDto jobPostingPostDto =
			JobPostingPostDto.builder()
				.companyId(1L)
				.position("주니어 Java 개발자")
				.jobDescription("Java 개발자 구인합니다")
				.compensation(300000)
				.requiredSkills("JAVA, Spring")
				.build();
		JobPostingPatchDto jobPostingPatchDto =
			JobPostingPatchDto.builder()
				.position("주니어 Java 개발자")
				.jobDescription("수정")
				.compensation(300)
				.requiredSkills("JAVA, Spring")
				.build();
		JobPosting jobPosting = JobPosting.from(jobPostingPostDto);
		jobPosting.setJobPostingId(jobPostingId);
		jobPosting.updateDataFrom(jobPostingPatchDto);
		given(jobPostingService.updateJobPosting(Mockito.any(JobPostingPatchDto.class), Mockito.anyLong())).willReturn(
			jobPosting);
		String requestBody = gson.toJson(jobPostingPatchDto);

		//when
		ResultActions actions =
			mockMvc.perform(
				patch("/jobs/{jobPosting-id}", jobPostingId)
					.contentType(MediaType.APPLICATION_JSON)
					.content(requestBody)
					.accept(MediaType.APPLICATION_JSON)
			);

		//then
		actions.andExpect(status().isOk())
			.andExpect(jsonPath("jobDescription").value(jobPostingPatchDto.getJobDescription()))
			.andExpect(jsonPath("compensation").value(jobPostingPatchDto.getCompensation()));
	}

	@Test
	@DisplayName("[API][DELETE]채용공고 삭제 테스트")
	void jobPostingDelete() throws Exception {
		//given
		Long jobPostingId = 1L;
		doNothing().when(jobPostingService).deleteJobPosting(jobPostingId);

		//when, then
		mockMvc.perform(
				delete("/jobs/{jobPosting-id}", jobPostingId)
					.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isNoContent());
	}
}
