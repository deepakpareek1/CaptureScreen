package com.ajackus.CaptureScreen.Controllers;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.ajackus.CaptureScreen.Entities.ResponseBody;

/**
 * BaseController base/helper class which can be used across all the Rest
 * Controllers for performing common functionalities. Bases controller has helper
 * methods to prepare Response object with various attributes using overloaded
 * methods.
 * 
 */
@CrossOrigin
public class BaseController {
	
private static Map<String, String> urlVsClassMap = new LinkedHashMap<String, String>();
	
	
	@Autowired
	private RequestMappingHandlerMapping requestMappingHandlerMapping;
	
	@PostConstruct
	public void init() {
		Map<RequestMappingInfo, HandlerMethod> handlerMethods = this.requestMappingHandlerMapping.getHandlerMethods();
		for (Entry<RequestMappingInfo, HandlerMethod> item : handlerMethods.entrySet()) {
			RequestMappingInfo mapping = item.getKey();
			HandlerMethod method = item.getValue();
			for (String urlPattern : mapping.getPatternsCondition().getPatterns()) {
				urlVsClassMap.put(urlPattern, method.getBeanType().getName() + "#" + method.getMethod().getName());
			}
		}
	}
	
	/**
	 * prepareResponse method prepares response Entity object with given
	 * response object and status codes.
	 *
	 * @param responseObject the response object
	 * @param httpStatus the http status
	 * @param httpStatus the isSucess
	 * @return ResponseEntity<ResponseBody>
	 */
	public ResponseEntity<ResponseBody> prepareResponse(Object responseObject, HttpStatus httpStatus, Boolean isSuccess) {
		ResponseBody responseBody = new ResponseBody();
		responseBody.setIsSuccess(isSuccess);
		responseBody.setData(responseObject);
		return new ResponseEntity<>(responseBody, httpStatus);
	}


}
