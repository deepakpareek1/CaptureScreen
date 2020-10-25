package com.ajackus.CaptureScreen.Services;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Service;

import com.ajackus.CaptureScreen.BrowserDrivers.ChromeBrowserDriver;
import com.ajackus.CaptureScreen.Controllers.ScreenShotController;

/*
 * Service layer : Screen Shot 
 */
@Service
public class ScreenShotService {

	/** The Constant log. */
	private static final Logger log = LogManager.getLogger(ScreenShotController.class);
	
	/**
	 * return screen shot
	 * @return
	 */
	public HttpServletResponse getScreenShot(HttpServletResponse response, String webLink){ 
		try {
		
			// Get chrome driver
			ChromeBrowserDriver chromeDriver = ChromeBrowserDriver.getInstance();
			WebDriver webDriver = chromeDriver.getDriver();
			
			// Get screen shot 
			webDriver.get(webLink);
			File scrFile = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE); 
			  
			// send screen shot in response as a stream from 
			InputStream is = new FileInputStream(scrFile);
			IOUtils.copy(is, response.getOutputStream());
			
			log.debug("Took Screenshot for "+webLink+" and saved as "+"screenShot.png");
        
        } catch (Exception ex) {
        	log.error(ex);
        }
		return response;
	}
	
	/**
	 * Check url is valid
	 * @return isvalid
	 */
	public boolean isURL(String url) {
	  try {
	     (new java.net.URL(url)).openStream().close();
	     return true;
	  } catch (Exception ex) { }
	  return false;
	}
}
