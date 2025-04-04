package com.finacplus.qa.testcases;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.finacplus.qa.base.TestBase;
import com.finacplus.qa.pages.CartPage;
import com.finacplus.qa.pages.LoginPage;
import com.finacplus.qa.pages.LogoutPage;
import com.finacplus.qa.pages.ProductsPage;

public class FinacPlusEndToEndTest extends TestBase {
    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;
    LogoutPage logoutPage;

    public FinacPlusEndToEndTest() {
        super();
    }

    @BeforeMethod
    public void setUp() {
        initialization();
        loginPage = new LoginPage();
        productsPage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
    }

    @Test(priority = 1)
    public void endToEndTest() throws InterruptedException, IOException {
        // Verify login
        Assert.assertNotNull(productsPage);

        // Verify product details
        String productName = productsPage.getFirstProductName();
        String productPrice = productsPage.getFirstProductPrice();

        // Store product details in a text file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("productDetails.txt"))) {
            writer.write("Product Name: " + productName);
            writer.newLine();
            writer.write("Product Price: " + productPrice);
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail("Failed to write product details to file");
        }

        // Add product to cart and verify
        productsPage.addFirstProductToCart();
        cartPage = productsPage.navigateToCart();
        Assert.assertTrue(cartPage.verifyProductInCart(productName));

        // Logout and verify
        logoutPage = new LogoutPage();
        logoutPage.logout();
        Assert.assertTrue(driver.getCurrentUrl().contains("saucedemo.com"));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}