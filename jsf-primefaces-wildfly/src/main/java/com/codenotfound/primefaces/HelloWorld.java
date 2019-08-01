package com.codenotfound.primefaces;

import javax.faces.bean.ManagedBean;
import lombok.Data;

/**
 * will provide data for the PrimeFaces (JSF) components.
 * We annotated the Bean with @Named so that it becomes a CDI managed bean with an EL name that is accessible by the JSF framework.
 */

/**
 * class with the @ManagedBean annotation it becomes a Managed Bean which is accessible and controlled by the JSF framework.
 */
@ManagedBean
@Data
public class HelloWorld {

  private String firstName = "John";
  
  private String lastName = "Doe";

  public String showGreeting() {
    return "Hello " + firstName + " " + lastName + "!";
  }
  
}
