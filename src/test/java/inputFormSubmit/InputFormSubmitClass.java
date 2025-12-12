package inputFormSubmit;

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
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class InputFormSubmitClass {
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
    public void TestScenario3() throws InterruptedException {
        driver.get("https://www.lambdatest.com/selenium-playground/");
        driver.manage().window().setSize(new Dimension(1920, 1080));

        Thread.sleep(2000);

        WebElement InputFormLink = driver.findElement(By.xpath(
                "//a[@href='https://www.lambdatest.com/selenium-playground/input-form-demo']"));
        InputFormLink.click();

        WebElement submit = driver.findElement(By.xpath("//div[@class='text-right mt-20']/button"));
        submit.click();

        Thread.sleep(1000);

        WebElement name = driver.findElement(By.xpath(
                "//div[@class='form-group w-4/12 smtablet:w-full text-section pr-20 smtablet:pr-0']/input[@type='text']"));
        String Expected_validation = name.getAttribute("validationMessage");
        String Actual_validation = "Please fill out this field.";
        Assert.assertEquals(Actual_validation, Expected_validation);

        if (Expected_validation.equals(Actual_validation)) {
            System.out.println("Validation is properly appear.");
        } else {
            System.out.println("Validation is not properly appear.");
        }

        name.sendKeys("TestName");

        WebElement email = driver.findElement(By.xpath(
                "//div[@class='form-group w-4/12 smtablet:w-full text-section pr-20 smtablet:pr-0']/input[@type='email']"));
        email.sendKeys("Test123@gmail.com");

        WebElement password = driver.findElement(
                By.xpath("//div[@class='form-group w-4/12 smtablet:w-full']/input[@type='password']"));
        password.sendKeys("Test@1234");

        WebElement company = driver.findElement(By.xpath("//*[@id=\"company\"]"));
        company.sendKeys("TestCompany");

        WebElement website = driver.findElement(
                By.xpath("//div[@class='form-group w-6/12 smtablet:w-full']/input[@id=\"websitename\"]"));
        website.sendKeys("Testdomain.com");

        WebElement country = driver.findElement(By.xpath(
                "//div[@class='form-group w-6/12 smtablet:w-full pr-20 smtablet:pr-0']/select[@name='country']"));
        Select select = new Select(country);
        select.selectByVisibleText("United States");

        WebElement city = driver.findElement(By.xpath("//div[@class='form-group w-6/12 smtablet:w-full']/input[@id='inputCity']"));
        city.sendKeys("TestCity");

        WebElement address1 = driver.findElement(By.xpath(
                "//div[@class='form-group w-6/12 smtablet:w-full pr-20 smtablet:pr-0']/input[@id='inputAddress1']"));
        address1.sendKeys("TestAddress1");

        WebElement address2 = driver.findElement(By.xpath("//div[@class='form-group w-6/12 smtablet:w-full']/input[@id='inputAddress2']"));
        address2.sendKeys("TestAddress2");

        WebElement state = driver.findElement(By.xpath(
                "//div[@class='form-group w-6/12 smtablet:w-full pr-20 smtablet:pr-0']/input[@id='inputState']"));
        state.sendKeys("TestState");

        WebElement zipcode = driver.findElement(By.xpath("//div[@class='form-group w-6/12 smtablet:w-full']/input[@id='inputZip']"));
        zipcode.sendKeys("360002");

        submit.click();

        Thread.sleep(2000);

        WebElement successmessage = driver.findElement(By.xpath(
                "//p[contains(text(),'Thanks for contacting us')]"));
        String Actualmessage = successmessage.getText();
        String Expectedmessage = "Thanks for contacting us, we will get back to you shortly.";

        if (Actualmessage.equals(Expectedmessage)) {
            System.out.println("Success message is properly appear.");
        } else {
            System.out.println("Success message is not properly appear.");
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
