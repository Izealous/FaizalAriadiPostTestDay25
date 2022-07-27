import org.testng.annotations.Test;


import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.ui.Select;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNotSame;
import static org.testng.Assert.assertSame;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;

public class KitaLulus {
	WebDriver driver;
	ChromeOptions options;
	String direktoriFile = System.getProperty("user.dir")+"\\\\test-output\\\\myfile.png";
	
	@BeforeSuite
	public void setupDriver() {
		System.setProperty("webdriver.chrome.driver", "C:\\webdriver\\chromedriver.exe");
		this.options = new ChromeOptions();
		this.options.setExperimentalOption("debuggerAddress", "localhost:9988");
		this.driver = new ChromeDriver(options);	
		this.driver.get("https://kerja.kitalulus.com/id");
		
	}
	
	@Test (priority = 1)
	public void vacancyTest() {
		Boolean state = true;
		int expect = 0;
		while (state) {
			try {
				driver.findElement(By.xpath("//*[@id=\"app-layout\"]/div[3]/div[1]/div/input")).sendKeys("developer", Keys.ENTER);
				
				state = false;
			}catch (NoSuchElementException e) {
				state = true;
			}
		}
		
		state = true;
		
		while (state) {
			try {
				String jumlahLowongan = driver.findElement(By.xpath("//*[@id=\"app-layout\"]/div[3]/div[2]/p/strong[1]")).getAttribute("innerHTML");
				String tampung  = jumlahLowongan.replaceAll("\\D+", "");
				int intJumlahLowongan = Integer.parseInt(tampung);
				System.out.println(tampung);
				assertNotEquals(tampung, expect);
				
				state = false;
			}catch (NoSuchElementException e) {
				state = true;
			}
		}				
	}
	
	@Test (priority = 2)
	public void bookmarkTest() {
		Boolean state = true;
		while (state) {
			try {
				driver.findElement(By.xpath("//*[@id=\"app-layout\"]/div[3]/div[3]/div[1]/div[1]/a/div/div/p")).click();
				driver.manage().timeouts().implicitlyWait(9, TimeUnit.SECONDS);
				state = false;
			}catch (NoSuchElementException e) {
				state = true;
			}
		}
		
		state = true;
		
		while (state) {
			try {
				String expect = "https://kerja.kitalulus.com/icons/heart-bookmark-active.svg";
				driver.findElement(By.xpath("//img[@alt='bookmark default']")).click();
				WebElement src = driver.findElement(By.xpath("//*[@id=\"app-layout\"]/div[2]/div[3]/div[1]/div[1]/div/button/img"));
				String actual = src.getAttribute("src");
				System.out.println(actual);
				assertEquals(actual, expect);
						
				state = false;
			}catch (Exception e) {
				state = true;
			}
		}
	}
	
}
