package com.codenotfound;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * This is a small helper class that uses the PageFactory factory class provided
 * by the WebDriver’s support library to help realize the PageObject pattern.
 */
public class PageObject {

    protected WebDriver driver;

    /**
     * WebElements that we have annotated in the HelloWorldPage class.
     */    
    public PageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
