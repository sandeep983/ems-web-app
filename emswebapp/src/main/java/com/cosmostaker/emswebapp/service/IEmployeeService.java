package com.cosmostaker.emswebapp.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.cosmostaker.emswebapp.entity.Employee;

public interface IEmployeeService {
    
    public List<Employee> getAllEmployees();

    public void saveEmployee(Employee employee);

    public Employee getEmployeeById(Long id);

    public void deleteEmployeeById(Long id);

    public List<Employee> searchBy(String theName);

    public Page<Employee> findPaginated(int pageNo, int pageSize);

}
