package com.cosmostaker.emswebapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cosmostaker.emswebapp.entity.Employee;
import com.cosmostaker.emswebapp.repository.IEmployeeRepository;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private IEmployeeRepository employeeRepository;


    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }


    @Override
    public void saveEmployee(Employee employee) {
        this.employeeRepository.save(employee);
    }


    @Override
    public Employee getEmployeeById(int id) {
        Optional<Employee> result = employeeRepository.findById(id);

        Employee employee = null;

        if (result.isPresent()) {
            employee = result.get();
        } else {
            throw new RuntimeException("Employee not found for id :: " + id);
        }

        return employee;
    }

    
    
}
