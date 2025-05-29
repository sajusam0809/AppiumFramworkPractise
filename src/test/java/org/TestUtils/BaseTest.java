package org.TestUtils;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.appium.pageObjects.android.FormPage;
import org.appium.utilis.AppiumUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest extends AppiumUtils {

    public AndroidDriver driver;
    public AppiumDriverLocalService service;
    public  FormPage formPage;

    @BeforeClass(alwaysRun = true)
    public void ConfigureAppium() throws URISyntaxException, IOException {

        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//resources//data.properties");
        prop.load(fis);
        String ipAddress = prop.getProperty("ipAddress");
        String port = prop.getProperty("port");
        service= startAppiumServer(ipAddress, Integer.parseInt(port));

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(prop.getProperty("AndroidDeviceName"));
        options.setChromedriverExecutable("C://Users//sajus//Downloads//chromedriver-win32//chromedriver-win32//chromedriver.exe");
        options.setApp(System.getProperty("user.dir")+"//src//test//java//org//resources//General-Store.apk");

        driver = new AndroidDriver(service.getUrl(),options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        formPage = new FormPage(driver);

    }

    public void longPressAction(WebElement ele) throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("mobile:longClickGesture", ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId(), "duration", 2000));
        Thread.sleep(2000);

    }

    public void scrollToEndAction() {
        boolean canScrollMore;
        do {
            canScrollMore = (Boolean) ((JavascriptExecutor) driver).

                    executeScript("mobile:scrollGesture", ImmutableMap.of(
                            "left", 100, "top", 100, "width", 200, "height", 200,
                            "direction", "down",
                            "percentage", 3.0
                    ));
        } while (canScrollMore);
    }

    public Double getFormattedAmount(String amount){

        Double price= Double.parseDouble(amount.substring(1));
        return price;

    }


    @AfterClass (alwaysRun = true)
    public void tearDown() {
        driver.quit();
        service.stop();
        System.out.println("******************Server Stops*************************************");
    }

}