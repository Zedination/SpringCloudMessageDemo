package com.example.restcontroller;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.FCMService;

@RestController
public class ThongBao {
	@Autowired
    private FCMService fcmService;
	@PostMapping("test-notification")
	public String sendThongBao(@RequestParam("fcmtoken") String fcmtoken, @RequestParam("content") String content) {
		System.out.println(fcmtoken);
		return fcmService.pushNotification(fcmtoken, content);
	}
	@GetMapping("/get-env")
	public String getEnv() {
		System.setProperty("webdriver.chrome.driver",System.getenv("CHROMEDRIVER_PATH"));
		ChromeOptions options = new ChromeOptions();
		//options.addArguments("--remote-debugging-port=9222");
		options.addArguments("--headless");
		options.addArguments("--disable-gpu");
		options.addArguments("--no-sandbox");
		WebDriver driver = new ChromeDriver();
		String url = "https://vnexpress.net/";
		driver.get(url);
		return System.getenv("GOOGLE_CHROME_PATH") +" / "+System.getenv("CHROMEDRIVER_PATH")+"/n"+"Tiêu để: "+
		driver.getTitle();
	}
	@GetMapping("/demo-selenium")
	public String semoSelenium() {
		return "aaaa";
	}
}
