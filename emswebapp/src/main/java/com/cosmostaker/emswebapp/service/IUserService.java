package com.cosmostaker.emswebapp.service;

import com.cosmostaker.emswebapp.dao.RegistrationDAO;
import com.cosmostaker.emswebapp.entity.User;

public interface IUserService {
 
    public User save(RegistrationDAO registrationDAO);

}
