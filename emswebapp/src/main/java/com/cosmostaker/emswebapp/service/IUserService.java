package com.cosmostaker.emswebapp.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.cosmostaker.emswebapp.dto.RegistrationDTO;
import com.cosmostaker.emswebapp.entity.User;

public interface IUserService extends UserDetailsService {
 
    public User save(RegistrationDTO registrationDTO);
    List<User> getAll();

}
