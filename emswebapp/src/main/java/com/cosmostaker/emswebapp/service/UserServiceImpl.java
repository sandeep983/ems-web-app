package com.cosmostaker.emswebapp.service;

import java.util.Arrays;
import java.util.List;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.cosmostaker.emswebapp.dto.RegistrationDTO;
import com.cosmostaker.emswebapp.entity.Role;
import com.cosmostaker.emswebapp.entity.User;
import com.cosmostaker.emswebapp.repository.IUserRepository;

@Service
public class UserServiceImpl implements IUserService {

    private IUserRepository userRepository;


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    public UserServiceImpl(IUserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }


    @Override
    public User save(RegistrationDTO registrationDTO) {
        User user = new User(registrationDTO.getFirstName(),
                     registrationDTO.getLastName(), registrationDTO.getEmail(), 
                     passwordEncoder.encode(registrationDTO.getPassword()), Arrays.asList(new Role("ROLE_EMPLOYEE")));
        return userRepository.save(user);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }


    
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }



    @Override
    public List<User> getAll() {
       return userRepository.findAll();
    }   
}
