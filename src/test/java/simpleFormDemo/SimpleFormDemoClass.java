package simpleFormDemo;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SimpleFormDemoClass {
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
    public void TestScenario1() throws InterruptedException {
        driver.get("https://www.lambdatest.com/selenium-playground/");
        driver.manage().window().setSize(new Dimension(1920, 1080));

        Thread.sleep(2000);

        WebElement SimpleFormDemoLink = driver.findElement(By.xpath(
                "//a[@href='https://www.lambdatest.com/selenium-playground/simple-form-demo']"));
        SimpleFormDemoLink.click();

        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.contains("simple-form-demo")) {
            System.out.println("URL matched");
        } else {
            System.out.println("URL does not match!");
        }

        String message = "Welcome to LambdaTest.";
        WebElement mess_send = driver.findElement(By.xpath(
                "//div/input[@class='border border-gray-550 w-full h-35 rounded px-10']"));
        Thread.sleep(1000);
        mess_send.sendKeys(message);

        Thread.sleep(1000);
        WebElement button = driver.findElement(By.xpath(
                "//button[text()='Get Checked Value']"));
        button.click();

        WebElement your_mess = driver.findElement(By.xpath(
                "//div[@class='w-4/12 smtablet:w-full rigth-input']/div/p[1]"));
        String print_mess = your_mess.getText();

        if (print_mess.contains(message)) {
            System.out.println("Message is matched");
        } else {
            System.out.println("Message is not matched!");
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
