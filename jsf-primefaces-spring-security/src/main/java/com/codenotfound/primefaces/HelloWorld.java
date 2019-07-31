package com.codenotfound.primefaces;

import javax.inject.Named;
import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * will provide data for the PrimeFaces (JSF) components.
 * We annotated the Bean with @Named so that it becomes a CDI managed bean with an EL name that is accessible by the JSF framework.
 */
@Named
@Data
public class HelloWorld {

  private String firstName = "John";
  
  private String lastName = "Doe";

  /**
   * how you can access the current Authentication object stored in the security context.
   * @return 
   */
  public String showGreeting() {
    Authentication authentication =
        SecurityContextHolder.getContext().getAuthentication();

    return "Hello " + authentication.getName() + "!";
  }
  
}
