package com.ajackus.CaptureScreen.Controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ajackus.CaptureScreen.Entities.ResponseBody;
import com.ajackus.CaptureScreen.Entities.ScreenShotDTO;
import com.ajackus.CaptureScreen.Services.ScreenShotService;
import com.ajackus.CaptureScreen.Utility.Constants;

/**
 * Controller Class : Handle REST request for Screen shots
 *
 */
@RestController
public class ScreenShotController extends BaseController {

	/** The Constant log. */
	private static final Logger log = LogManager.getLogger(ScreenShotController.class);
	
	@Autowired
	ScreenShotService screenShotService;
	
	/**
	 * Return application working status
	 * @return
	 */
	@RequestMapping(value ="/testService", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseBody>  testService(HttpServletRequest request, HttpServletResponse response) throws IOException {
		log.debug("Inside testService");
		HttpStatus status = HttpStatus.OK;
		ScreenShotDTO screenShot = new ScreenShotDTO();
		try {
			screenShot.setMessage("application working fine");
		} catch (Exception ex) {
			log.error("Exception in " +request.getRequestURI() , ex);
			status = HttpStatus.NOT_FOUND;
			return prepareResponse(null, status, Boolean.FALSE);
		}
		
		return prepareResponse(screenShot, status, Boolean.TRUE);
	}
	
	/**
	 * Return screen shot
	 * @return
	 */
	@RequestMapping(value ="/getScreenShot", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseBody>  getScreenShot(@RequestParam("data") String weblink, HttpServletRequest request, HttpServletResponse response) throws IOException {
		log.debug("Inside getScreenShot");
		HttpStatus status = HttpStatus.OK;
		ScreenShotDTO screenShot = new ScreenShotDTO();
		try {
			if(screenShotService.isURL(weblink)) {
				response = (HttpServletResponse) screenShotService.getScreenShot(response, weblink);
				response.flushBuffer();
			}
			else {
				screenShot.setMessage(Constants.MSG_INCORRECT_URL);
			}
		} catch (Exception ex) {
			log.error("Exception in " +request.getRequestURI() , ex);
			status = HttpStatus.NOT_FOUND;
			return prepareResponse(Constants.MSG_SERVER_ERROR, status, Boolean.FALSE);
		}
		
		return prepareResponse(screenShot, status, Boolean.TRUE);
	}
}
