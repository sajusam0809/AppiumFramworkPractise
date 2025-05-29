package org.appium.utilis;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

public class AndroidActions extends AppiumUtils{
     AndroidDriver driver;

public AndroidActions(AndroidDriver driver){
    this.driver=driver;

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

    public void scrollToText(String text){
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"));"));

    }
}
