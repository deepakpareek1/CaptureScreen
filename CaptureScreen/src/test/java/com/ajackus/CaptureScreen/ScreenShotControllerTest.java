package com.ajackus.CaptureScreen;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.ajackus.CaptureScreen.BrowserDrivers.ChromeBrowserDriver;
import com.ajackus.CaptureScreen.Utility.Constants;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ScreenShotControllerTest extends CaptureScreenApplicationTests {

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private MockMvc mockMVC;
	
	@Before
	public void setup(){
		mockMVC = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		ChromeBrowserDriver chromeDriver = ChromeBrowserDriver.getInstance();
	}
	
	@Test
	public void testService() throws Exception{
		mockMVC.perform(MockMvcRequestBuilders.get("/testService")).andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(content().contentType("application/json"))
		.andExpect(jsonPath("$.isSuccess").value(Boolean.TRUE))
		.andExpect(jsonPath("$.data").value("application working fine"));
	}
	
	@Test
	public void testScreenShotWithCorrectURL() throws Exception{
		mockMVC.perform(MockMvcRequestBuilders.get("/getScreenShot?data=https://en.wikipedia.org/wiki/Java_(programming_language)")).
		andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testScreenShotWithInCorrectURL() throws Exception{
		mockMVC.perform(MockMvcRequestBuilders.get("/getScreenShot?data=https://en.wikipediaEEEEE.org/wiki/Java_(programming_language)")).andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(content().contentType("application/json"))
		.andExpect(jsonPath("$.isSuccess").value(Boolean.TRUE))
		.andExpect(jsonPath("$.data").value(Constants.MSG_INCORRECT_URL));
	}
	
	@AfterClass
    public static void cleanUp(){
        
    }

}
