package com.cosmostaker.emswebapp.service;

import com.cosmostaker.emswebapp.dao.UserRegistrationDAO;
import com.cosmostaker.emswebapp.entity.User;

public interface IUserService {
 
    public User save(UserRegistrationDAO registrationDAO);

}
