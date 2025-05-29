package org.appium.utilis;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.sound.sampled.Port;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public abstract class AppiumUtils {
    public AppiumDriverLocalService service;

    public Double getFormattedAmount(String amount) {

        Double price = Double.parseDouble(amount.substring(1));
        return price;
    }

    public List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException {
        //convert json file content to json string ---System.getProperty("user.dir")+"//src//test//java//org//testdata//eCommerce.json
        String jsonContent = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent,
                new TypeReference<List<HashMap<String, String>>>() {
                });
        return data;
    }

    public void waitForElementToAppear(WebElement ele, AndroidDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.attributeContains((ele), "text", "Cart"));
    }

    public AppiumDriverLocalService startAppiumServer(String ipAddress, int port){
        service = new AppiumServiceBuilder().withAppiumJS(new File("C://Users//sajus//AppData//Roaming//npm//node_modules//appium//build//lib//main.js")).withIPAddress(ipAddress).usingPort(port).build();
        System.out.println("******************Server Starts*************************************");
        service.start();
        return service;
    }

    public String getScreenshotPath(String testCaseName, AppiumDriver driver) throws IOException {
        File source =driver.getScreenshotAs(OutputType.FILE);
        String destinationFile =System.getProperty("user.dir")+"//reports"+testCaseName+".png";
        FileUtils.copyFile(source,new File(destinationFile));
        return destinationFile;
        //1. Capture and Place in folder
        //2.Extent report Pick file and attach to report
    }
}
