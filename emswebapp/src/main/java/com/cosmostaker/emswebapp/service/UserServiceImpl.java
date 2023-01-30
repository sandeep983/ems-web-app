package com.cosmostaker.emswebapp.service;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.cosmostaker.emswebapp.dao.RegistrationDAO;
import com.cosmostaker.emswebapp.entity.Role;
import com.cosmostaker.emswebapp.entity.User;
import com.cosmostaker.emswebapp.repository.IUserRepository;

@Service
public class UserServiceImpl implements IUserService {

    private IUserRepository userRepository;


    public UserServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User save(RegistrationDAO registrationDAO) {
        User user = new User(registrationDAO.getFirstName(),
                     registrationDAO.getLastName(), registrationDAO.getEmail(), 
                     registrationDAO.getPassword(), Arrays.asList(new Role("ROLE_EMPLOYEE")));
        return userRepository.save(user);
    }
    
}
