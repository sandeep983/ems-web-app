package com.cosmostaker.emswebapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cosmostaker.emswebapp.service.IUserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private IUserService userService;


	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userService);
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
            .antMatchers("/register**").permitAll()
            .antMatchers("/resources/**").permitAll()
            .antMatchers("/updateEmployee*").hasRole("ADMIN")
            .antMatchers("/deleteEmployee*").hasRole("ADMIN")
            .antMatchers("/showAddEmployeeForm*").hasAnyRole("ADMIN", "EMPLOYEE")
            .antMatchers("/saveEmployee*").hasAnyRole("ADMIN", "EMPLOYEE")
            .anyRequest().authenticated()


            .and()
            .exceptionHandling()
                .accessDeniedPage("/accessDenied")
    
            
            .and()
            .formLogin()
                .loginPage("/login")
				.loginProcessingUrl("/authenticateUser")
				.permitAll()
                

            .and()
            .logout()
				.invalidateHttpSession(true)
                .clearAuthentication(true)
				.permitAll();
	}
}














// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;
// import org.springframework.security.web.SecurityFilterChain;


// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {

//     @Bean
//     public BCryptPasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }



//     // Storing admin details in memory
//     @Bean
//     public InMemoryUserDetailsManager userDetailsManager() {

//         UserDetails cosmostaker = User.builder()
//             .username("Cosmostaker")
//             .password(passwordEncoder().encode("cosmos@123"))
//             .roles("EMPLOYEE")
//             .build();

//         UserDetails admin = User.builder()
// 			.username("Admin")
// 			.password(passwordEncoder().encode("admin@123"))
// 			.roles("EMPLOYEE", "ADMIN")
// 			.build();
        
//         return new InMemoryUserDetailsManager(cosmostaker, admin);        
//     }

    

//     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//     	return http
// 		.authorizeRequests(configurer ->
// 			configurer
//                 //.antMatchers("/").permitAll()
//                 .antMatchers("/register**").permitAll()
//                 .antMatchers("/resources/**").permitAll()
//                 .antMatchers("/updateEmployee*").hasRole("ADMIN")
//                 .antMatchers("/deleteEmployee*").hasRole("ADMIN")
//                 .antMatchers("/showAddEmployeeForm*").hasAnyRole("ADMIN", "EMPLOYEE")
//                 .antMatchers("/saveEmployee*").hasAnyRole("ADMIN", "EMPLOYEE")
//                 .anyRequest().authenticated())


// 		.formLogin(configurer ->
// 			configurer
// 				.loginPage("/login")
// 				.loginProcessingUrl("/authenticateUser")
// 				.permitAll())
		

// 		.logout(configurer -> 
// 			configurer
//                 .invalidateHttpSession(true)
//                 .clearAuthentication(true)
// 				.permitAll())
		

// 		.exceptionHandling(configurer ->
// 			configurer
// 				.accessDeniedPage("/accessDenied"))
				
		
// 		.build();
//     }	
// }


