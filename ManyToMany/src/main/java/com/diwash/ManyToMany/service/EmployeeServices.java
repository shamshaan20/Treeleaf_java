package com.diwash.ManyToMany.service;

import com.diwash.ManyToMany.Models.Employee;
import com.diwash.ManyToMany.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class EmployeeServices {
    @Autowired
    private EmployeeRepository repo;

    public List<Employee> listAll(){
        return repo.findAll(Sort.by("employeeId").ascending());
    }
}
