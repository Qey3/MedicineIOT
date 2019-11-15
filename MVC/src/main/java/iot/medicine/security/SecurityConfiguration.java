package iot.medicine.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@ComponentScan("iot.medicine")
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/homePage").permitAll()
                .antMatchers("/sugar-page/*").hasAnyRole("USER", "ADMIN")
                .antMatchers("/device-catalog*").hasAnyRole("USER", "ADMIN")
                .antMatchers("/user-device-catalog*").hasAnyRole("USER", "ADMIN")
                .antMatchers("/addDeviceDetails").hasRole("ADMIN")
                .antMatchers("/pieChart").hasRole("USER")
                .and()
                .formLogin().permitAll()
                .and()
                .csrf().disable();
    }

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    @Qualifier("authService")
    UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

}
