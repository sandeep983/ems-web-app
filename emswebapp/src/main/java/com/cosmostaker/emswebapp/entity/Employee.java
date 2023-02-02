package com.cosmostaker.emswebapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@Table(name = "employee")
public class Employee {
    
    // Table fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
   

    @NotBlank(message = "{NotBlank.Msg}")
    @Size(min=3, message = "{Size.Min}")
    @Column(name = "first_name")
    private String firstName;


    @NotBlank(message = "{NotBlank.Msg}")
    @Size(min=3, message = "{Size.Min}")
    @Column(name = "last_name")
    private String lastName;


    @Pattern(regexp = "^[0-9]{10}$", message = "{Mobile.Msg}")
    @Column(name = "mobile")
    private String mobile;


    @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", message = "{Email.Msg}")
    @Column(name = "email")
    private String email;



    // Define constructors
    public Employee() {}
    public Employee(String firstName, String lastName, String email, String mobile) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobile = mobile;
    }
    public Employee(Long id, String firstName, String lastName, String email, String mobile) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobile = mobile;
    }
    


    // Define getter/setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
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
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }



    // Define toString
    @Override
    public String toString() {
        return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", mobile=" + mobile
                + ", email=" + email + "]";
    }
    
}
