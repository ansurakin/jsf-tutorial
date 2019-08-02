package com.codenotfound.primefaces.model;

import java.io.Serializable;
import lombok.Data;

@Data
public class User implements Serializable {

  private static final long serialVersionUID = -1389546558353914770L;

  private String userId;
  
  private String firstName;
  
  private String lastName;

  public User(String userId, String firstName, String lastName) {
    this.userId = userId;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public String getName() {
    return firstName + " " + lastName;
  }
}
