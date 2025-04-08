package comparison;

import static org.testng.Assert.assertEquals;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.github.romankh3.image.comparison.ImageComparison;
import com.github.romankh3.image.comparison.ImageComparisonUtil;
import com.github.romankh3.image.comparison.model.ImageComparisonResult;
import com.github.romankh3.image.comparison.model.ImageComparisonState;

public class ImageComparisonTest {

	WebDriver driver;
	
	@BeforeTest()
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://thinking-tester-contact-list.herokuapp.com/");
	}
	
	@Test(priority=1)
	public void differentPage() throws IOException, InterruptedException {
		// Code added to check compatibility with Selenium
		String sourceFilePath = "resources/screenshots/v1/source.png";
		String targetFilePath = "resources/screenshots/v2/target.png";
		
		
		driver.findElement(By.id("signup")).click();
		
		driver.findElement(By.id("firstName")).sendKeys("Testing");
		driver.findElement(By.id("email")).sendKeys("Testing@gmail.com");
		File ss1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(ss1, new File(sourceFilePath));
		
		driver.findElement(By.id("cancel")).click();
		Thread.sleep(2000);
		File ss2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(ss2, new File(targetFilePath));
		
		//Calling utility
		imageComp(sourceFilePath, targetFilePath,"DifferentPage");
	}
	
	
	@Test(priority=2)
	public void samePageDiffData() throws IOException, InterruptedException {
		// Code added to check compatibility with Selenium
		String sourceFilePath = "resources/screenshots/v1/source2.png";
		String targetFilePath = "resources/screenshots/v2/target2.png";
		
		
		driver.findElement(By.id("signup")).click();
		
		driver.findElement(By.id("firstName")).sendKeys("Testing");
		driver.findElement(By.id("email")).sendKeys("Testing@gmail.com");
		File ss1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(ss1, new File(sourceFilePath));
		
		driver.findElement(By.id("firstName")).clear();
		driver.findElement(By.id("email")).clear();
		Thread.sleep(2000);
		File ss2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(ss2, new File(targetFilePath));
		
		//Calling utility
		imageComp(sourceFilePath, targetFilePath,"samePageDiffData");
	}
	
	@Test(priority=3)
	public void differentDimensions() throws IOException, InterruptedException {
		// Code added to check compatibility with Selenium
		String sourceFilePath = "resources/screenshots/v1/source3.png";
		String targetFilePath = "resources/screenshots/v2/target3.png";
		
		
		driver.findElement(By.id("signup")).click();
		
		//maximized
		File ss1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(ss1, new File(sourceFilePath));
		
		Dimension dimension = new Dimension(300, 500);
		driver.manage().window().setSize(dimension);
		driver.findElement(By.id("firstName")).clear();
		driver.findElement(By.id("email")).clear();
		Thread.sleep(1000);
		File ss2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(ss2, new File(targetFilePath));
		
		//Calling utility
		imageComp(sourceFilePath, targetFilePath,"differentDimension");
	}
	
	
	@Test(priority=4)
	public void differentDimensions2() throws IOException, InterruptedException {
		// Code added to check compatibility with Selenium
		String sourceFilePath = "resources/screenshots/v1/Source-Samsung.png";
		String targetFilePath = "resources/screenshots/v2/Target-Samsung.png";
				
		//Calling utility
		imageComp(sourceFilePath, targetFilePath,"samsungDiff");
	}
	
	@Test(priority=5)
	public void sameImage() throws IOException, InterruptedException {
		// Code added to check compatibility with Selenium
		String sourceFilePath = "resources/screenshots/v1/Source-Samsung.png";
		
				
		//Calling utility
		imageComp(sourceFilePath, sourceFilePath,"sameImg");
	}
	
	
	public static void imageComp(String sourceFile, String targetFile, String fileName) {
		// load images to be compared:
				BufferedImage expectedImage = ImageComparisonUtil
						.readImageFromResources(sourceFile);
				BufferedImage actualImage = ImageComparisonUtil
						.readImageFromResources(targetFile);

				// Create ImageComparison object with result destination and compare the images.
				ImageComparisonResult imageComparisonResult = new ImageComparison(expectedImage, actualImage).compareImages();

				File resultDestination = new File(fileName+".png");
				// Image can be saved after comparison, using ImageComparisonUtil.
				ImageComparisonUtil.saveImage(resultDestination, imageComparisonResult.getResult());
				// Check the result
				assertEquals(ImageComparisonState.MATCH, imageComparisonResult.getImageComparisonState(), "Mismatch");
	}

	@AfterTest
	public void tearDown() {
		driver.close();
		driver.quit();
	}

}