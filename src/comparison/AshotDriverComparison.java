package comparison;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.annotations.Test;

public class AshotDriverComparison {

	
	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://thinking-tester-contact-list.herokuapp.com/");
		driver.findElement(By.id("signup")).click();
		
	}

	//Error thrown with selenium 4.0.0 and Ashot 1.5.2

	
//	@Test
//	public void loadAndSignUp() {
//		
//		Webdriver driver = new ChromeDriver();
//		driver.manage().window().maximize();
//		driver.get("https://thinking-tester-contact-list.herokuapp.com/");
//		driver.findElement(By.id("signup")).click();
//		
//	}

	
}
