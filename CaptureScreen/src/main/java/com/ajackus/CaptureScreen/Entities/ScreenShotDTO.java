package com.ajackus.CaptureScreen.Entities;

public class ScreenShotDTO {

	private String message;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getBase64() {
		return base64;
	}
	public void setBase64(String base64) {
		this.base64 = base64;
	}
	private String base64;
}
