package com.haneefs.springbootpoc.configuration;

import com.haneefs.springbootpoc.constants.AppUserRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.haneefs.springbootpoc.constants.AppUserPermission.PRODUCT_ADD;
import static com.haneefs.springbootpoc.constants.AppUserPermission.PRODUCT_MODIFY;
import static com.haneefs.springbootpoc.constants.AppUserRoles.*;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests() //Authorize Requests
                .antMatchers("/","index","/css/*","/js/*").permitAll()//Telling that to permit all URIs.
                .antMatchers("/api/v1/customers/**").hasRole(CUSTOMER.name())
                .antMatchers(HttpMethod.DELETE,"/api/v1/products/**").hasAuthority(PRODUCT_ADD.getPermission())
                .antMatchers(HttpMethod.POST,"/api/v1/products/").hasAuthority(PRODUCT_ADD.getPermission())
                .antMatchers(HttpMethod.PUT,"/api/v1/products/**").hasAuthority(PRODUCT_MODIFY.getPermission())
                .antMatchers(HttpMethod.GET,"/api/v1/products/**").hasAnyRole(ADMIN.name(),EMPLOYEE.name())
                .anyRequest() //All requests
                .authenticated()//must be Authenticated
                .and()//joining
                .httpBasic();//Telling to use Basic Authentication
    }
    @Override
    @Bean
    public UserDetailsService userDetailsServiceBean() throws Exception {
        UserDetails user1 = User.builder()
                .username("user1")
                .password(passwordEncoder.encode("user1"))
                //.roles(CUSTOMER.name())
                .authorities(CUSTOMER.getGrantedAuthorities())
                .build();

        UserDetails employee1 = User.builder()
                .username("employee1")
                .password(passwordEncoder.encode("employee1"))
                //.roles(AppUserRoles.EMPLOYEE.name())
                .authorities(EMPLOYEE.getGrantedAuthorities())
                .build();

        UserDetails admin1 = User.builder()
                .username("admin1")
                .password(passwordEncoder.encode("admin1"))
                //.roles(AppUserRoles.ADMIN.name())
                .authorities(ADMIN.getGrantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(user1,employee1, admin1);
    }
}
