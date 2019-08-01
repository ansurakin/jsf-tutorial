package com.codenotfound.primefaces;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.codenotfound.PageObject;

/**
 * The JSF framework prefixes HTML elements inside a <form> with the ID of the
 * form. If no ID is present then one will be auto-generated.
 */
public class HelloWorldPage extends PageObject {

    @FindBy(id = "hello-world-form:first-name")
    private WebElement firstNameInput;

    @FindBy(id = "hello-world-form:last-name")
    private WebElement lastNameInput;

    @FindBy(id = "hello-world-form:submit")
    private WebElement submitButton;

    @FindBy(id = "hello-world-form:greeting")
    private WebElement greetingOutput;

    public HelloWorldPage(WebDriver driver) {
        super(driver);
    }

    /**
     * we represent our submit button as a submit() method.
     *
     * The method finishes by reloading the page elements so that the greeting
     * output field is set with the new value.
     */
    public void submit(String firstName, String lastName) {
        // set the input fields
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        // submit the form
        submitButton.submit();
        // refresh the output field
        PageFactory.initElements(driver, this);
    }

    public String getGreeting() {
        return greetingOutput.getAttribute("textContent");
    }
}
