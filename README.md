# CaptureScreen
Its used to take screen shot for any webpage online

Project Set Up:-
- donwload project from git : 
- imort project as a existing maven project
- install Eclispe Java Developemnt tools (if required)
- Maven update
- Make Maven build in run configuration

Chorme dirver setup:
- download chrome drive: https://chromedriver.storage.googleapis.com/index.html?path=2.38/
- extract siz and save it on "C:\\chromedriver_win32\\chromedriver.exe"
- you can reset above path as per your location.

Two ways to run app 

1. As a spring boot java application (run internal tomcat server) - Prefered
- select project and right click on it
- select 'Run AS'
- select 'java application'
- select 'CaptureScreenApplication'
- in browser - localhost/8080/CaptureScreen


2. Adding externa tomcat server 9 and make deployment

- add tomcat server 9 (java EE 8)
- add project to tomcat server
- Clean/Build - server/project
- right click on server and Run server 
- in browser - localhost/8080/CaptureScreen
 

Make API call

1. To test application is working
- http://localhost:8080/testService

output:
data : "application working fine" 

2. To test screen shot API
- http://localhost:8080/getScreenShot?data=https://en.wikipedia.org/wiki/Java_(programming_language)

output: image
