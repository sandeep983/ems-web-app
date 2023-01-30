package com.cosmostaker.emswebapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.cosmostaker.emswebapp.service.IUserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    // @Autowired
    // private IUserService userService;

    
    // @Bean
    // public BCryptPasswordEncoder passwordEncoder() {
    //     return new BCryptPasswordEncoder();
    // }


    // @Bean
    // public DaoAuthenticationProvider authenticationProvider() {
    //     DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
    //     auth.setUserDetailsService((UserDetailsService) userService);
    //     auth.setPasswordEncoder(passwordEncoder());
    //     return auth;
    // }



    // Storing admin details in memory
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {

        UserDetails cosmostaker = User.builder()
            .username("Cosmostaker")
            .password("{noop}cosmos@123")
            .roles("EMPLOYEE")
            .build();

        UserDetails admin = User.builder()
			.username("Admin")
			.password("{noop}admin@123")
			.roles("EMPLOYEE", "ADMIN")
			.build();
        
        return new InMemoryUserDetailsManager(cosmostaker, admin);        
    }



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    	return http
		.authorizeRequests(configurer ->
			configurer
                // .antMatchers("/").permitAll()
                .antMatchers("/register**").permitAll()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/updateEmployee*").hasRole("ADMIN")
                .antMatchers("/deleteEmployee*").hasRole("ADMIN")
                .antMatchers("/showAddEmployeeForm*").hasAnyRole("ADMIN", "EMPLOYEE")
                .antMatchers("/saveEmployee*").hasAnyRole("ADMIN", "EMPLOYEE")
                .anyRequest().authenticated())
                

        
                

        // authentication
		// .authenticationProvider(authenticationProvider())


		.formLogin(configurer ->
			configurer
				.loginPage("/login")
				.loginProcessingUrl("/authenticateUser")
				.permitAll())
		

		.logout(configurer -> 
			configurer
                .invalidateHttpSession(true)
                .clearAuthentication(true)
				.permitAll())
		

		.exceptionHandling(configurer ->
			configurer
				.accessDeniedPage("/accessDenied"))
				
		
		.build();        
    }	
}