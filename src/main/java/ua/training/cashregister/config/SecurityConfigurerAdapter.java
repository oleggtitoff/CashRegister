package ua.training.cashregister.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(1) //TODO: try to replace by @Primary
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
                .antMatchers("/login", "/logout")
                .permitAll();

        httpSecurity
                .authorizeRequests()
                .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error")
                .defaultSuccessUrl("/cashier/index", true)
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout");
    }
}
