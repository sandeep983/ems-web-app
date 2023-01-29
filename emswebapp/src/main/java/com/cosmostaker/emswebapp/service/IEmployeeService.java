package com.cosmostaker.emswebapp.service;

import java.util.List;

import com.cosmostaker.emswebapp.entity.Employee;

public interface IEmployeeService {
    
    public List<Employee> getAllEmployees();

    public void saveEmployee(Employee employee);

    public Employee getEmployeeById(int id);

    public void deleteEmployeeById(int id);

    public List<Employee> searchBy(String theName);

}
