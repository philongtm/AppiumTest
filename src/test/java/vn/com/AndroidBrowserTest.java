package vn.com;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.AndroidServerFlag;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Android Browser Test.
 */
public class AndroidBrowserTest extends BaseTest {
    public static final String URL = "http://127.0.0.1:4723/wd/hub";
    public static AndroidDriver<?> mobiledriver;

    @BeforeTest
    public void beforeTest() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.0");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "LG V30 plus");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "chrome");
        capabilities.setCapability("newCommandTimeout", 2000);
        mobiledriver = new AndroidDriver(new URL(URL), capabilities);
    }

    @AfterTest
    public void afterTest() {
        mobiledriver.quit();
    }

    @Test
    public static void launchBrowser() {
        mobiledriver.get("https://appium.io/");
        Assert.assertEquals(mobiledriver.getCurrentUrl(), "https://appium.io/", "URL Mismatch");
        try {
            Files.copy(mobiledriver.getScreenshotAs(OutputType.FILE).toPath(), Paths.get("01.png"), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(mobiledriver.getTitle(), "Appium: Mobile App Automation Made Awesome.", "Title Mismatch");
    }

    @Override
    public void appiumBuilder(AppiumServiceBuilder builder) {
        try {
            builder.withArgument(AndroidServerFlag.CHROME_DRIVER_EXECUTABLE, Paths.get(AndroidBrowserTest.class.getClassLoader().getResource("chromedriver.exe").toURI()).toFile().getAbsolutePath());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
