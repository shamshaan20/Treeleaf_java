package com.diwash.apivalidation.Model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "employees")
public class Employee {

    private long id;

    @NotNull
    @Size(min = 4, message = "First Name should have atleast 4 characters")
    private String firstName;

    @NotNull
    @Size(min = 4, message = "Last Name should have atleast 4 characters")
    private String lastName;

    @Email
    @NotBlank
    private String emailId;

    @NotNull
    @Size(min = 2, message = "Password should have atleast 4 characters")
    private String password;

    public Employee() {

    }

    public Employee(String firstName, String lastName, String emailId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "first_name", nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "email_address", nullable = false)
    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void getPassword(String password) {
        this.password = password;
    }

}