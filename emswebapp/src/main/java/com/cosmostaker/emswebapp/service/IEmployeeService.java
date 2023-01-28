package com.cosmostaker.emswebapp.service;

import java.util.List;

import com.cosmostaker.emswebapp.entity.Employee;

public interface IEmployeeService {
    List<Employee> getAllEmployees();

    public void saveEmployee(Employee employee);

    public Employee getEmployeeById(int id);
}
