package com.example.restcontroller;

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
		return System.getenv("GOOGLE_CHROME_PATH") +" / "+System.getenv("CHROMEDRIVER_PATH");
	}
}
