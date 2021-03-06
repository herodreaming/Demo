package main.java.com.dbyl.tests;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.os.WindowsUtils;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import main.java.com.dbyl.libarary.utils.DriverFactory;

public class getAlert {
	private WebDriver driver;

	@Test(groups = { "ChromeDriver" })
	public void FireFoxDriver() throws InterruptedException {
		WindowsUtils.getProgramFilesPath();
//		System.setProperty("webdriver.gecko.driver", "webDriver//geckodriver.exe");
		driver =DriverFactory.getFirefoxDriver();
		driver.get("http://www.baidu.com");
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		WebElement target1=driver.findElement(By.xpath("//a[@id='target1']"));
		String currentWindow=driver.getWindowHandle();
		String target1Window = null;
		target1.click();
		Set<String> windows = driver.getWindowHandles();
		for(String window:windows)
		{
			 if(!currentWindow.equals(window))
			 {
				 target1Window=window;
				 driver.switchTo().window(window);
			 }
		}
		WebElement target2 = driver.findElement(By.id("target2"));
		target2.click();
		
		 windows = driver.getWindowHandles();
			for(String window:windows)
			{
				 if(!currentWindow.equals(window)&& !target1Window.equals(window) )
				 {
					 driver.switchTo().window(window);
				 }
			}
	Alert  alert=driver.switchTo().alert();
	System.out.println(alert.getText());
	alert.accept();

	}

	/**
	 * @author Young
	 * @param driver
	 * @param by
	 * @return
	 */
	public static boolean isElementPresent(WebDriver driver, final By by) {

		boolean isPresent = false;
		WebDriverWait wait = new WebDriverWait(driver, 60);
		isPresent = wait.until(new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver d) {
				return d.findElement(by);
			}
		}).isDisplayed();
		return isPresent;
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}

}
