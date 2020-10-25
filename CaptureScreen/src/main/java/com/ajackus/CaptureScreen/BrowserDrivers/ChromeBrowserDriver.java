package com.ajackus.CaptureScreen.BrowserDrivers;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeBrowserDriver {

	private static ChromeBrowserDriver instance=null;
    private WebDriver driver=null;


    // Constructor
    private ChromeBrowserDriver(){
    	System.setProperty("webdriver.chrome.driver","C:\\chromedriver_win32\\chromedriver.exe");
		driver= new ChromeDriver();
		driver.manage().window().setPosition(new Point(-2000, 0));
    }


	//TO create instance of class
    public static ChromeBrowserDriver getInstance(){
        if(instance==null){
            instance = new ChromeBrowserDriver();
        }
        return instance;
    }
    
 // To get driver
    public WebDriver getDriver()
    {
    	return driver;
    } 
}
