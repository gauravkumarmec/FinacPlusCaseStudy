package com.finacplus.qa.pages;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.finacplus.qa.base.TestBase;

public class ProductsPage extends TestBase {

    @FindBy(xpath = "(//div[@class='inventory_item_name '])[1]")
    WebElement firstProductName;

    @FindBy(xpath = "(//div[@class='inventory_item_price'])[1]")
    WebElement firstProductPrice;

    @FindBy(xpath = "(//button[text()='Add to cart'])[1]")
    WebElement addToCartBtn;

    @FindBy(xpath = "//div[@id='shopping_cart_container']/a")
    WebElement cartLink;

    public ProductsPage() {
        PageFactory.initElements(driver, this);
    }

    public void storeFirstProductDetails() throws InterruptedException {
        Thread.sleep(2000); // Wait for 2 seconds to ensure the page is loaded
        if (isAlertPresent(driver)) {
            Alert alert = driver.switchTo().alert();
            System.out.println("Alert Text: " + alert.getText());

            alert.accept(); // Clicks "OK"
            System.out.println("Alert accepted successfully");
        } else {
            System.out.println("No alert present");
        }
        String productName = firstProductName.getText();
        String productPrice = firstProductPrice.getText();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("productDetails.txt"))) {
            writer.write("Product Name: " + productName);
            writer.newLine();
            writer.write("Product Price: " + productPrice);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addFirstProductToCart() {
        addToCartBtn.click();

    }

    public CartPage navigateToCart() {
        if (isAlertPresent(driver)) {
            Alert alert = driver.switchTo().alert();
            System.out.println("Alert Text: " + alert.getText());

            alert.accept(); // Clicks "OK"
            System.out.println("Alert accepted successfully");
        } else {
            System.out.println("No alert present");
        }
        cartLink.click();
        return new CartPage();
    }

    public static boolean isAlertPresent(WebDriver driver) {
        try {
            driver.switchTo().alert();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public String getFirstProductName() {
        return firstProductName.getText();
    }
    public String getFirstProductPrice() {
        return firstProductPrice.getText();
    }
}