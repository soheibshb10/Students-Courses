package com.student.Students.Courses.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {

@Bean
    public InMemoryUserDetailsManager userDetailsManager(){

        UserDetails pers1= User.builder()
                .username("user")
                .password("{noop}password")
                .roles("admin")
                .build();

    return new InMemoryUserDetailsManager(pers1);

}


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
        http.authorizeHttpRequests(configurer ->   configurer
                //authenticate categories api
                .requestMatchers(HttpMethod.GET, "/categories/**").hasRole("admin")
                .requestMatchers(HttpMethod.POST, "/categories/**").hasRole("admin")
                .requestMatchers(HttpMethod.PUT, "/categories/**").hasRole("admin")
                .requestMatchers(HttpMethod.DELETE, "/categories/**").hasRole("admin")

                //authenticate cycles api
                .requestMatchers(HttpMethod.GET, "/cycles/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/cycles/cyclesByCategoryLevel").hasRole("admin")
                .requestMatchers(HttpMethod.POST, "/cycles/**").hasRole("admin")
                .requestMatchers(HttpMethod.PUT, "/cycles/**").hasRole("admin")
                .requestMatchers(HttpMethod.DELETE, "/cycles/**").hasRole("admin")

                //authenticate students api
                .requestMatchers(HttpMethod.GET, "/students/view/{studentId}").permitAll()
                .requestMatchers(HttpMethod.GET, "/students/list").hasRole("admin")
                .requestMatchers(HttpMethod.POST, "/students/save").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/students/**").hasRole("admin")
                 .requestMatchers(HttpMethod.PUT, "/students/**").hasRole("admin")

        );




                http.httpBasic();

        http.formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                );
        http.csrf().disable();

        return http.build();

    }

}
