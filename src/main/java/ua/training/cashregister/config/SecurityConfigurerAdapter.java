package ua.training.cashregister.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Primary
@Configuration
public class SecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
    public SecurityConfigurerAdapter() {
        super();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                .disable();

        httpSecurity
                .authorizeRequests()
                .antMatchers("/", "/login", "/logout")
                .permitAll();

        httpSecurity
                .authorizeRequests()
                .antMatchers("/cashier/**")
                .access("hasAuthority(T(ua.training.cashregister.entity.Role).CASHIER.getAuthority())");

        httpSecurity
                .authorizeRequests()
                .antMatchers("/admin/**")
                .access("hasAuthority(T(ua.training.cashregister.entity.Role).ADMIN.getAuthority())");

        httpSecurity
                .authorizeRequests()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/access-denied");

        httpSecurity
                .authorizeRequests()
                .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error")
                .defaultSuccessUrl("/default-success", true)
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout");
    }
}
