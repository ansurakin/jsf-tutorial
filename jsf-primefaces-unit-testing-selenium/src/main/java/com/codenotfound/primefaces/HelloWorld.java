package com.codenotfound.primefaces;

import javax.inject.Named;
import lombok.Data;

/**
 * will provide data for the PrimeFaces (JSF) components. We annotated the Bean
 * with @Named so that it becomes a CDI managed bean with an EL name that is
 * accessible by the JSF framework.
 */
@Named
@Data
public class HelloWorld {

    private String firstName;

    private String lastName;

    public String showGreeting() {
        return "Hello " + firstName + " " + lastName + "!";
    }

}
