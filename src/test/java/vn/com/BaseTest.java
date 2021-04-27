package vn.com;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.net.URL;

/**
 * Base Test
 */
public abstract class BaseTest {

    static AppiumDriverLocalService service;
    AppiumServiceBuilder builder;

    @BeforeSuite
    public void globalSetup() throws IOException {
        builder = new AppiumServiceBuilder();
        appiumBuilder(builder);
        service = AppiumDriverLocalService.buildService(builder);
        service.start();
    }

    public abstract void appiumBuilder(AppiumServiceBuilder builder);

    @AfterSuite
    public void globalTearDown() {
        if (service != null) {
            service.stop();
        }
    }

    public URL getServiceUrl() {
        return service.getUrl();
    }

}
