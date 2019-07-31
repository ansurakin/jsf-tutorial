package com.codenotfound.primefaces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * WebSecurityConfigurerAdapter is a convenient base class that provides a
 * default security configuration. The default is that accessing the URL /logout
 * will log the user out.
 *
 * @EnableWebSecurity to enable Spring Security’s web security support.
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * to define when and how users need to be authenticated. Specifying
     * authorizeRequests().anyRequest().authenticated() ensures that any request
     * to our application requires the user to be authenticated.
     *
     * The static resources (CSS, JavaScript, …) need to be accessible to anyone
     * otherwise the look and feel of the login page won’t be the same as the
     * rest of the application. Adding
     * antMatchers("/javax.faces.resource/**").permitAll() allows anyone to
     * access a URL that begins with /javax.faces.resource/ (which is where the
     * static resources in a JSF application are served)
     *
     * when authentication is required, the browser is redirected to
     * /login.xhtml. We also need permitAll() on the login page so that anyone
     * has access otherwise we end up in a redirect loop.
     *
     * If the login fails we redirect to the same login page with a error=true
     * HTTP parameter in the URL using failureUrl("/login.xhtml?error=true").
     * This allows us to display and error message to the user.
     *
     * Spring Security applies measures to prevents CSRF attacks by requiring a
     * randomly generated token as an HTTP parameter. However as JSF 2.2 already
     * contains an explicit protection against CSRF attacks we disable the
     * Spring Security protection by specifying http.csrf().disable().
     *
     * @throws java.lang.Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // require all requests to be authenticated except for the resources
        http.authorizeRequests().antMatchers("/javax.faces.resource/**")
                .permitAll().anyRequest().authenticated();
        // login
        http.formLogin().loginPage("/login.xhtml").permitAll()
                .failureUrl("/login.xhtml?error=true");
        // logout
        http.logout().logoutSuccessUrl("/login.xhtml");
        // not needed as JSF 2.2 is implicitly protected against CSRF
        http.csrf().disable();
    }

    /**
     * override the default single user AuthenticationManager.
     *
     * prefix {noop} to the passwords in order for the DelegatingPasswordEncoder
     * to use the NoOpPasswordEncoder to validate them.
     *
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.inMemoryAuthentication()
                .withUser("john.doe").password("{noop}1234").roles("USER")
                .and().withUser("jane.doe").password("{noop}5678").roles("ADMIN");
    }
}
