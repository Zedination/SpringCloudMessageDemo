package com.example.restcontroller;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
		String result =  System.getenv("GOOGLE_CHROME_PATH") +" / "+System.getenv("CHROMEDRIVER_PATH")+"/n"+"Tiêu để: "+
		driver.getTitle();
		driver.close();
		return result;
	}
	@GetMapping("/demo-selenium")
	public String semoSelenium() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",System.getenv("CHROMEDRIVER_PATH"));
		ChromeOptions options = new ChromeOptions();
		//options.addArguments("--remote-debugging-port=9222");
		options.addArguments("--headless");
		options.addArguments("--disable-gpu");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-dev-shm-usage");
		WebDriver driver = new ChromeDriver();
		String url = "http://sis.utc.edu.vn/";
		driver.get(url);
		driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/a")).click();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement inputUsername = driver.findElement(By.xpath("//*[@id=\"i0116\"]"));
		inputUsername.sendKeys("duc171201239@st.utc.edu.vn");
		WebElement buttonNext = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"idSIButton9\"]")));
		buttonNext.click();
		WebElement inputPass = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"i0118\"]")));
		inputPass.sendKeys("dagevjul14");
		WebElement buttonSubmit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"idSIButton9\"]")));
		buttonSubmit.click();
		WebElement buttonConfirm =wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//*[@id=\"idBtn_Back\"]"))));
		buttonConfirm.click();
		String result = driver.getTitle();
		driver.manage().deleteAllCookies();
		driver.close();
		return result;
	}
}
