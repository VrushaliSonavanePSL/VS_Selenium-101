package dragAndDropSliders;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class DragAndDropSlidersClass {

	private RemoteWebDriver driver;
	private String Status = "passed";

	@Parameters({"platform", "browser", "browserVersion"})
	@BeforeMethod
	public void setup(String platform, String browser, String browserVersion,
	                  Method m, ITestContext ctx) throws MalformedURLException {

	    ChromeOptions browserOptions = new ChromeOptions();
	    browserOptions.setBrowserVersion(browserVersion);

	    Map<String, Object> ltOptions = new HashMap<>();
	    ltOptions.put("username", "vrushali_sonavane");
	    ltOptions.put("accessKey", "LT_ncbJk92P10qykxZsUDjeprMOqcqOTXE1BWZ2ZToSVzl5L4N");

	    ltOptions.put("platformName", platform);
	    ltOptions.put("project", "Certification Project");
	    ltOptions.put("build", "Parallel Build");
	    ltOptions.put("name", m.getName());

	    // REQUIRED FOR CERTIFICATION
	    ltOptions.put("visual", true);
	    ltOptions.put("video", true);
	    ltOptions.put("console", true);
	    ltOptions.put("network", true);

	    ltOptions.put("w3c", true);
	    ltOptions.put("selenium_version", "4.21.0");

	    browserOptions.setCapability("LT:Options", ltOptions);

	    driver = new RemoteWebDriver(
	            new URL("https://hub.lambdatest.com/wd/hub"),
	            browserOptions
	    );
	}


	@Test
	public void TestScenario2() throws InterruptedException {

		driver.get("https://www.lambdatest.com/selenium-playground/");
		driver.manage().window().setSize(new Dimension(1920, 1080));

		Thread.sleep(2000);

		WebElement sliderLink = driver.findElement(By.xpath(
				"//a[@href='https://www.lambdatest.com/selenium-playground/drag-drop-range-sliders-demo']"));
		sliderLink.click();

		Thread.sleep(1000);

		WebElement slider3 = driver.findElement(By.xpath("//*[@id='slider3']/div/input"));
		Thread.sleep(1000);

		Actions move = new Actions(driver);
		move.dragAndDropBy(slider3, 99, 0).perform();

		WebElement expectedRange = driver.findElement(By.xpath("//*[@id='slider3']/div/output"));
		String actualRange = expectedRange.getText();
		String expected = "95";

		if (actualRange.contains(expected)) {
			System.out.println("Range is matched");
		} else {
			System.out.println("Range is not matched!");
		}
	}

	@AfterMethod
	public void tearDown() {
		try {
			driver.executeScript("lambda-status=" + Status);
		} catch (Exception e) {
			System.out.println("Unable to set LambdaTest status.");
		}
		driver.quit();
	}
}