package org.appium;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import org.TestUtils.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class eShopCase2 extends BaseTest {

@BeforeMethod
    public void preSetup() {
    System.out.println("helllooooooooooooooooo");
    Activity activity = new Activity("com.androidsample.generalstore", "com.androidsample.generalstore.MainActivity");
//driver.startActivity(activity);
    ((JavascriptExecutor) driver).executeScript("mobile:startActivity", ImmutableMap.of("intent", "com.androidsample.generalstore/com.androidsample.generalstore.SplashActivity"));
}
    @Test
    public void VerifyErrorMessage() throws InterruptedException {
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/nameField")).sendKeys("Steve Smith");
        driver.hideKeyboard();
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/radioFemale")).click();
        driver.findElement(AppiumBy.id("android:id/text1")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));"));
        driver.findElement(By.xpath("//android.widget.TextView[@text='Argentina']")).click();
        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        Thread.sleep(3000);

    }
    @Test
    public void VerifyPositiveMessage() throws InterruptedException {

        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        String errorMessage= driver.findElement(AppiumBy.xpath("//android.widget.Toast[@text='Please enter your name']")).getText();
        System.out.println(errorMessage);
        Assert.assertEquals(errorMessage,"Please enter your name");


    }
}
