package vn.com;//******************************************************************************
//
// Copyright (c) 2016 Microsoft Corporation. All rights reserved.
//
// This code is licensed under the MIT License (MIT).
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
// THE SOFTWARE.
//
//******************************************************************************

import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.net.URL;

public class CalculatorTest {

    private static WindowsDriver driver = null;
    private static AppiumDriverLocalService service;

    @BeforeSuite
    public void globalSetup() throws IOException {
        service = AppiumDriverLocalService.buildDefaultService();
        service.start();
    }

    @AfterSuite
    public void globalTearDown() {
        if (service != null) {
            service.stop();
        }
    }

    public URL getServiceUrl() {
        return service.getUrl();
//        try {
//            return new URL("http://localhost:4723");
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        return null;
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

    @BeforeMethod
    public void Clear() {
        //driver.findElementByName("Clear").click();
        // Assert.assertEquals("0", _GetCalculatorResultText());
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
