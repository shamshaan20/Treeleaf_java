package com.diwash.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "employee_all_detail")
public class EmployeeAllDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "workspace") private String workspace;

    @Column(name = "no_of_problems_solved")
    private int noOfProblemsSolved;

    public EmployeeAllDetail() {}

    public EmployeeAllDetail(String workspace,
                            int noOfProblemsSolved)
    {
        this.workspace = workspace;
        this.noOfProblemsSolved = noOfProblemsSolved;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getWorkspace() { return workspace; }

    public void setWorkspace(String workspace)
    {
        this.workspace = workspace;
    }

    public int getNoOfProblemsSolved()
    {
        return noOfProblemsSolved;
    }

    public void
    setNoOfProblemsSolved(int noOfProblemsSolved)
    {
        this.noOfProblemsSolved = noOfProblemsSolved;
    }

    @Override public String toString()
    {
        return "EmployeeAllDetail{"
                + "id=" + id + ", workspace='" + workspace + '\''
                + ", noOfProblemsSolved=" + noOfProblemsSolved
                + '}';
    }
}

