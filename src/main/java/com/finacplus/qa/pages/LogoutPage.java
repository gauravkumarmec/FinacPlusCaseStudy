package com.finacplus.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.finacplus.qa.base.TestBase;

public class LogoutPage extends TestBase {

    @FindBy(xpath = "//button[text()='Open Menu']")
    WebElement menuBtn;

    @FindBy(xpath = "//a[text()='Logout']")
    WebElement logoutLink;

    public LogoutPage() {
        PageFactory.initElements(driver, this);
    }

    public void logout() {
        menuBtn.click();
        logoutLink.click();
    }
}
