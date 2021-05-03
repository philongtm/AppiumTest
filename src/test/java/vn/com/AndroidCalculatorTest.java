package vn.com;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URL;

/**
 * Android Calculator Test.
 */
public class AndroidCalculatorTest extends BaseTest {
    public static final String URL = "http://127.0.0.1:4723/wd/hub";
    private static AndroidDriver<AndroidElement> driver;
    private static WebDriverWait wait;

    @Override
    public void appiumBuilder(AppiumServiceBuilder builder) {
        // default settings
    }

    public URL getServiceUrl() {
        return service.getUrl();
    }

    @BeforeClass
    public void setup() {
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.0");
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "LG");
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
            capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.google.android.calculator");
            capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.android.calculator2.Calculator");
            capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 2000);
            driver = new AndroidDriver<AndroidElement>(new URL(URL), capabilities);
            wait = new WebDriverWait(driver, 1000);
            (new AndroidTouchAction(driver)).tap(PointOption.point(316, 1519)).perform();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }

    @AfterClass
    public void TearDown() {
        driver.quit();
    }

    @Test
    public void Addition() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.google.android.calculator:id/digit_1"))).click();
        driver.findElementById("com.google.android.calculator:id/op_add").click();
        driver.findElementById("com.google.android.calculator:id/digit_7").click();
        driver.findElementById("com.google.android.calculator:id/eq").click();
        Assert.assertEquals("8", _GetCalculatorResultText());
    }

    @Test
    public void Combination() {
        driver.findElementById("com.google.android.calculator:id/digit_7").click();
        driver.findElementById("com.google.android.calculator:id/op_mul").click();
        driver.findElementById("com.google.android.calculator:id/digit_9").click();
        driver.findElementById("com.google.android.calculator:id/op_add").click();
        driver.findElementById("com.google.android.calculator:id/digit_1").click();
        driver.findElementById("com.google.android.calculator:id/eq").click();
        driver.findElementById("com.google.android.calculator:id/op_div").click();
        driver.findElementById("com.google.android.calculator:id/digit_8").click();
        driver.findElementById("com.google.android.calculator:id/eq").click();
        Assert.assertEquals("8", _GetCalculatorResultText());
    }

    @Test
    public void Division() {
        driver.findElementById("com.google.android.calculator:id/digit_8").click();
        driver.findElementById("com.google.android.calculator:id/digit_8").click();
        driver.findElementById("com.google.android.calculator:id/op_div").click();
        driver.findElementById("com.google.android.calculator:id/digit_1").click();
        driver.findElementById("com.google.android.calculator:id/digit_1").click();
        driver.findElementById("com.google.android.calculator:id/eq").click();
        Assert.assertEquals("8", _GetCalculatorResultText());
    }

    @Test
    public void Multiplication() {
        driver.findElementById("com.google.android.calculator:id/digit_9").click();
        driver.findElementById("com.google.android.calculator:id/op_mul").click();
        driver.findElementById("com.google.android.calculator:id/digit_9").click();
        driver.findElementById("com.google.android.calculator:id/eq").click();
        Assert.assertEquals("81", _GetCalculatorResultText());
    }

    @Test
    public void Subtraction() {
        driver.findElementById("com.google.android.calculator:id/digit_9").click();
        driver.findElementById("com.google.android.calculator:id/op_sub").click();
        driver.findElementById("com.google.android.calculator:id/digit_1").click();
        driver.findElementById("com.google.android.calculator:id/eq").click();
        Assert.assertEquals("8", _GetCalculatorResultText());
    }

    protected String _GetCalculatorResultText() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.google.android.calculator:id/result_final")));
        return driver.findElementById("com.google.android.calculator:id/result_final").getText();
    }
}
