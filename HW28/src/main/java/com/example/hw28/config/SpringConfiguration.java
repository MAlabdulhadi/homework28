package com.example.hw28.config;


import com.example.hw28.Service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringConfiguration {

    private final MyUserDetailsService myUserDetailsService;

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(myUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(authenticationProvider())
                .authorizeHttpRequests()
                // auth controller
                .requestMatchers("/api/v1/auth/register").permitAll()
                //customer controller
                .requestMatchers("/api/v1/customer/get-order").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/customer/update").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/customer/delete").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/customer/delete-order/{orderId}").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/customer/get-order-by-id/{orderId}").hasAuthority("CUSTOMER")
                .requestMatchers("/api/v1/customer/get-customer-by-id/{customerId}").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/customer/get-all-customer").hasAuthority("ADMIN")
                //product controller
                .requestMatchers("/api/v1/product/add").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/product/update/{productId}").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/product/delete/{productId}").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/product/get-all-product").permitAll()
                //delete
                //order controller
                .requestMatchers("/api/v1/order/change-status/{orderId}/{status}").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/order/add-order").hasAuthority("CUSTOMER")
                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("/api/v1/user/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();
        return http.build();

    }


}
