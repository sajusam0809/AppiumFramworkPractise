package org.appium;
import org.TestUtils.BaseTest;
import org.appium.pageObjects.android.CartPage;
import org.appium.pageObjects.android.ProductCatalog;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class eShopCase1 extends BaseTest {

    @Test(dataProvider = "getData",groups ={"Smoke"})
    public void FillForm( HashMap<String,String> input) throws InterruptedException {

        formPage.setNameField(input.get("name"));
        formPage.setGender(input.get("gender"));
        formPage.setCountrySelection(input.get("country"));
        ProductCatalog productCatalog = formPage.submitForm();

        productCatalog.addItemToCart();
        CartPage cartPage = productCatalog.goToCartPage();

        double totalSum = cartPage.getProductSum();
        double displayFormattedSum = cartPage.getTotalAmountDisplayed();
        Assert.assertEquals(totalSum, displayFormattedSum);
        cartPage.acceptTermsCondition();
       // cartPage.submitOrder();


    }

    @BeforeMethod
    public void preSetup(){
        formPage.SetActivity();
    }
    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String,String>> data= getJsonData(System.getProperty("user.dir")+"//src//test//java//org//testdata//eCommerce.json");
        //return new Object[][]{{data.get(0)},{data.get(1)} };
        return new Object[][]{{data.get(0)} };
    }
}