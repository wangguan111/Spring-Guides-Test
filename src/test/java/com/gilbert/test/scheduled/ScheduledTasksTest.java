package com.gilbert.test.scheduled;

import org.awaitility.Duration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ScheduledTasksTest {

	//为测试主体类部分打桩考虑使用SpyBean,
	// 为外部依赖打桩，考虑使用MockBean
	@SpyBean
	ScheduledTasks tasks;

	@Autowired
	MockMvc mockMvc;

	@Test
	public void reportCurrentTime() {
		await().atMost(Duration.TWO_SECONDS).untilAsserted(() -> {
			verify(tasks, atLeast(1)).reportCurrentTime();
		});
	}

	@Test
	public void restController() throws Exception {

		this.mockMvc.perform(get("/scheduledTasks")).andDo(print()).
				andExpect(status().isOk());
	}
}
