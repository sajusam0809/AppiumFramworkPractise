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

public class eShopCase3 extends BaseTest {

    @Test
    public void VerifyPositiveMessage() throws InterruptedException {

        driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        String errorMessage= driver.findElement(AppiumBy.xpath("//android.widget.Toast[@text='Please enter your name']")).getText();
        System.out.println(errorMessage);
        Assert.assertEquals(errorMessage,"Please enter your name");

    }
}
