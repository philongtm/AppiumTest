package vn.com;

import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.URL;

/**
 * Windows Calculator Test.
 */
public class WindowsCalculatorTest extends BaseTest {

    private static WindowsDriver driver;

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
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10");
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Windows");
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, "WindowsPC");
            caps.setCapability(MobileCapabilityType.APP, "Microsoft.WindowsCalculator_8wekyb3d8bbwe!App");
            caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 2000);
            driver = new WindowsDriver(getServiceUrl(), caps);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }

    boolean isFirst = true;

    @BeforeMethod
    public void Clear() {
        driver.findElementByName(isFirst ? "Clear" : "Clear entry").click();
        isFirst = false;
        Assert.assertEquals("0", _GetCalculatorResultText());
    }

    @AfterClass
    public void TearDown() {
        driver.quit();
    }

    @Test
    public void Addition() {
        driver.findElementByName("One").click();
        driver.findElementByName("Plus").click();
        driver.findElementByName("Seven").click();
        driver.findElementByName("Equals").click();
        Assert.assertEquals("8", _GetCalculatorResultText());
    }

    @Test
    public void Combination() {
        driver.findElementByName("Seven").click();
        driver.findElementByName("Multiply by").click();
        driver.findElementByName("Nine").click();
        driver.findElementByName("Plus").click();
        driver.findElementByName("One").click();
        driver.findElementByName("Equals").click();
        driver.findElementByName("Divide by").click();
        driver.findElementByName("Eight").click();
        driver.findElementByName("Equals").click();
        Assert.assertEquals("8", _GetCalculatorResultText());
    }

    @Test
    public void Division() {
        driver.findElementByName("Eight").click();
        driver.findElementByName("Eight").click();
        driver.findElementByName("Divide by").click();
        driver.findElementByName("One").click();
        driver.findElementByName("One").click();
        driver.findElementByName("Equals").click();
        Assert.assertEquals("8", _GetCalculatorResultText());
    }

    @Test
    public void Multiplication() {
        driver.findElementByName("Nine").click();
        driver.findElementByName("Multiply by").click();
        driver.findElementByName("Nine").click();
        driver.findElementByName("Equals").click();
        Assert.assertEquals("81", _GetCalculatorResultText());
    }

    @Test
    public void Subtraction() {
        driver.findElementByName("Nine").click();
        driver.findElementByName("Minus").click();
        driver.findElementByName("One").click();
        driver.findElementByName("Equals").click();
        Assert.assertEquals("8", _GetCalculatorResultText());
    }

    protected String _GetCalculatorResultText() {
        // trim extra text and whitespace off of the display value
        return driver.findElementByAccessibilityId("CalculatorResults").getText().replace("Display is", "").trim();
    }
}
