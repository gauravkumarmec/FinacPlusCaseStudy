package com.finacplus.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.finacplus.qa.base.TestBase;

public class CartPage extends TestBase {

    @FindBy(xpath = "//div[@class='inventory_item_name']")
    WebElement cartProductName;

    public CartPage() {
        PageFactory.initElements(driver, this);
    }

    public boolean verifyProductInCart(String expectedProductName) {
        return cartProductName.getText().equals(expectedProductName);
    }
}