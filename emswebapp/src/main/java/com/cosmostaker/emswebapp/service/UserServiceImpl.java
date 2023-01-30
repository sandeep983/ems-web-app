package com.cosmostaker.emswebapp.service;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.cosmostaker.emswebapp.dao.UserRegistrationDAO;
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
    public User save(UserRegistrationDAO registrationDAO) {
        User user = new User(registrationDAO.getFirstName(),
                     registrationDAO.getLastName(), registrationDAO.getEmail(), 
                     registrationDAO.getPassword(), Arrays.asList(new Role("ROLE_USER")));
        return userRepository.save(user);
    }
    
}
