package com.cosmostaker.emswebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cosmostaker.emswebapp.entity.User;

public interface IUserRepository extends JpaRepository<User, Long>{
        
    
}