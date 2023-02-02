package com.cosmostaker.emswebapp.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class RegistrationDTO {
    

    @NotBlank(message = "{NotBlank.Msg}")
    @Size(min=3, message = "{Size.Min}")
    private String firstName;


    @NotBlank(message = "{NotBlank.Msg}")
    @Size(min=3, message = "{Size.Min}")
    private String lastName;


    @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", message = "{Email.Msg}")
    private String email;

    
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "{Password.Msg}")
    private String password;


    // Constructor
    public RegistrationDTO() {}
 

    // Getters and Setters
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
