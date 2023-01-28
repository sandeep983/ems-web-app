package com.cosmostaker.emswebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cosmostaker.emswebapp.entity.Employee;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Integer>{
    
}
