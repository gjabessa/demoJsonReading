package com.example.demo;

import com.example.demo.controller.demoController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class DemoApplicationTests {

	@Autowired
	MockMvc mockMvc;

	@Mock
	demoController dc;

	public static final ObjectMapper om = new ObjectMapper();

	@Before
	public void setup(){

	}

	@Test
	void testHappyPath() throws Exception{
		CustomObject co = om.readValue(mockMvc.perform(get("/")).andExpect(status().is(200)).andReturn().getResponse().getContentAsString(), CustomObject.class);
		Assert.assertNotNull(co);
		Assert.assertNotNull(co.getMessage());
		Assert.assertEquals("success", co.getStatus());
	}

	@Test
	void testFailurePath() throws Exception {

		when(dc.fetchFileMap()).thenThrow(new IOException());
		mockMvc.perform(get("/")).andExpect(status().is(404)).andReturn().getResponse().getContentAsString();


	}


}
