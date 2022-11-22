package ru.kolobkevic.market.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtRequestFilter jwtRequestFilter;

//    @Bean
//    public SecurityFilterChain mySecurityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests()
////                .antMatchers("/#!/create_product").hasRole("ADMIN")
//                .antMatchers("/api/v1/products/create_product").hasRole("ADMIN")
//                .antMatchers("/api/v1/products/delete/**").hasRole("ADMIN")
//                .antMatchers("/api/v1/products/edit_product/**").hasAnyRole("ADMIN", "MANAGER")
//                .antMatchers("/api/v1/products").authenticated()
//                .antMatchers("/api/v1/cart/**").authenticated()
//                .antMatchers("/").permitAll()
//                .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .headers().frameOptions().disable()
//                .and()
//                .exceptionHandling()
//                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
//        return http.build();
//    }
//
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return WebSecurity::ignoring;
//    }
//
//    @Bean
//    public GrantedAuthorityDefaults grantedAuthorityDefaults() {
//        return new GrantedAuthorityDefaults("");
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
//                .antMatchers("/api/v1/products/create_product/**").hasRole("ADMIN")
//                .antMatchers("/api/v1/products/delete/**").hasRole("ADMIN")
//                .antMatchers("/api/v1/products/edit_product/**").hasAnyRole("ADMIN", "MANAGER")
//                .antMatchers("/api/v1/products").authenticated()
//                .antMatchers("/api/v1/cart/**").authenticated()
                .antMatchers("/").permitAll()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .headers().frameOptions().disable()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}