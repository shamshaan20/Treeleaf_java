package com.diwash.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name") private String firstName;

    @Column(name = "last_name") private String lastName;

    @Column(name = "email") private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_all_detail_id")
    private EmployeeAllDetail employeeAllDetail;

    public Employee() {}
    public Employee(String firstName, String lastName,
                   String email)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getEmail() { return email; }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public EmployeeAllDetail getEmployeeAllDetail()
    {
        return employeeAllDetail;
    }

    public void
    setEmployeeAllDetail(EmployeeAllDetail employeeAllDetail)
    {
        this.employeeAllDetail = employeeAllDetail;
    }

    @Override public String toString()
    {
        return "Employee{"
                + "id=" + id + ", firstName='" + firstName
                + '\'' + ", lastName='" + lastName + '\''
                + ", email='" + email + '\''
                + ", employeeAllDetails=" + employeeAllDetail
                + '}';
    }

}