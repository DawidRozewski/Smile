package pl.smile.SmileApp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import pl.smile.SmileApp.security.UrlAuthenticationSuccessHandler;
import pl.smile.SmileApp.security.CurrentUser;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/app/admin/**").hasRole("ADMIN")
                .antMatchers("/app/doctor/**").hasRole("DOCTOR")
                .antMatchers("/app/patient/**").hasRole("PATIENT")
                .antMatchers("/app/**").permitAll()
                .and().formLogin().successHandler(myAuthenticationSuccessHandler())
                .and().logout().logoutSuccessUrl("/app").deleteCookies("JSESSIONID")
                .and().exceptionHandling().accessDeniedPage("/403");
        http.csrf().disable();

    }

    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
        return new UrlAuthenticationSuccessHandler();
    }

    @Bean
    public CurrentUser currentUser() {
        return new CurrentUser();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
