package com.cosmostaker.emswebapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public Employee getEmployeeById(Long id) {
        Optional<Employee> result = employeeRepository.findById(id);

        Employee employee = null;

        if (result.isPresent()) {
            employee = result.get();
        } else {
            throw new RuntimeException("Employee not found for id :: " + id);
        }

        return employee;
    }


    @Override
    public void deleteEmployeeById(Long id) {
        this.employeeRepository.deleteById(id);
    }
    

    @Override
	public List<Employee> searchBy(String theName) {
		List<Employee> results = null;
		
		if (theName != null && (theName.trim().length() > 0)) {
			results = employeeRepository.findByFirstNameContainsOrLastNameContainsAllIgnoreCase(theName, theName);
        }
		else {
			results = getAllEmployees();
		}
        
		return results;
	}


    @Override
    public Page<Employee> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1, pageSize);
        return this.employeeRepository.findAll(pageable);
    }
}
