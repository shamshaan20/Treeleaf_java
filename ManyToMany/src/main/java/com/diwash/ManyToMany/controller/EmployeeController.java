package com.diwash.ManyToMany.controller;

import com.diwash.ManyToMany.Models.Employee;
import com.diwash.ManyToMany.exception.ResourceNotFoundException;
import com.diwash.ManyToMany.exporter.EmployeeExcelExporter;
import com.diwash.ManyToMany.exporter.EmployeePDFExporter;
import com.diwash.ManyToMany.repository.EmployeeRepository;
import com.diwash.ManyToMany.service.EmployeeServices;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/proemployee")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeServices service;

    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    // create employee REST API
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }
    // get employee by id REST API
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable  long id){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:" + id));
        return ResponseEntity.ok(employee);
    }
    @GetMapping("/employees/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Employees_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Employee> listEmployees = service.listAll();

        EmployeePDFExporter exporter = new EmployeePDFExporter(listEmployees);
        exporter.export(response);
    }
    @GetMapping("/employees/export/csv")
    public void exportToPCSV(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Employees_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);

        List<Employee> listEmployees = service.listAll();

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"Employee Id", "First Name", "Last Name"};
        String[] nameMapping = {"employeeId", "firstName", "LastName"};

        csvWriter.writeHeader(csvHeader);
        for(Employee employee : listEmployees){
            csvWriter.write(employee, nameMapping);
        }
        csvWriter.close();
    }
    @GetMapping("/employees/export/excel")
    public void exportToExcel(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/excel");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Employees_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Employee> listEmployees = service.listAll();

        EmployeeExcelExporter excelExporter = new EmployeeExcelExporter(listEmployees);
        excelExporter.export(response);

    }

    // update employee REST API
    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable long id,@RequestBody Employee employeeDetails) {
        Employee updateEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));

        updateEmployee.setFirstName(employeeDetails.getFirstName());
        updateEmployee.setLastName(employeeDetails.getLastName());

        employeeRepository.save(updateEmployee);

        return ResponseEntity.ok(updateEmployee);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable long id){

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));

        employeeRepository.delete(employee);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}

