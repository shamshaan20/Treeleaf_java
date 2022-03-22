package com.diwash.springboot.controller;

import com.diwash.springboot.exception.ResourceNotFoundException;
import com.diwash.springboot.model.Employee;
import com.diwash.springboot.model.EmployeeAllDetail;
import com.diwash.springboot.repository.EmployeeAllDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/employeealldetail")
public class EmployeeAllDetailController {
    @Autowired
    private EmployeeAllDetailRepository employeeAllDetailRepository;

    @GetMapping
    public List<EmployeeAllDetail> getAllEmployees(){
        return employeeAllDetailRepository.findAll();
    }

    // create employee REST API
    @PostMapping
    public EmployeeAllDetail createEmployeeAllDetail(@RequestBody EmployeeAllDetail employeeAllDetail) {
        return employeeAllDetailRepository.save(employeeAllDetail);
    }

    // get employee by id REST API
    @GetMapping("{id}")
    public ResponseEntity<EmployeeAllDetail> getEmployeeAllDetailById(@PathVariable  long id){
        EmployeeAllDetail employeeAllDetail = employeeAllDetailRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee  details not exist with id:" + id));
        return ResponseEntity.ok(employeeAllDetail);
    }

    // update employee REST API
    @PutMapping("{id}")
    public ResponseEntity<EmployeeAllDetail> updateEmployeeAllDetail(@PathVariable long id,@RequestBody EmployeeAllDetail employeeAllDetail) {
        EmployeeAllDetail updateEmployeeAllDetail = employeeAllDetailRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee detail not exist with id: " + id));

        updateEmployeeAllDetail.setWorkspace(employeeAllDetail.getWorkspace());
        updateEmployeeAllDetail.setNoOfProblemsSolved(employeeAllDetail.getNoOfProblemsSolved());

        employeeAllDetailRepository.save(updateEmployeeAllDetail);

        return ResponseEntity.ok(updateEmployeeAllDetail);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable long id){

        EmployeeAllDetail employeeAllDetail = employeeAllDetailRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee detail not exist with id: " + id));

        employeeAllDetailRepository.delete(employeeAllDetail);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}

