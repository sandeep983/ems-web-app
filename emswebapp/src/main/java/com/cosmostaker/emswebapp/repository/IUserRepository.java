package com.cosmostaker.emswebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cosmostaker.emswebapp.entity.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Long>{
        
    // Login
    User findByEmail(String email);
}