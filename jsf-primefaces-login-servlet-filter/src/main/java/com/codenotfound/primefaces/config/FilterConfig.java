package com.codenotfound.primefaces.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.codenotfound.primefaces.filter.LoginFilter;

/**
 * @Configuration which indicates that the class can be used by the Spring IoC container as a source of bean definitions.
 */
@Configuration
public class FilterConfig {

    /**
     * A FilterRegistrationBean registers filters in a Servlet 3.0+ container.
     * 
     * LoginFilter will be applied to all pages inside the /secured directory.
     */
    @Bean
    public FilterRegistrationBean loginFilter() {
        FilterRegistrationBean registration
                = new FilterRegistrationBean();
        registration.setFilter(new LoginFilter());
        registration.addUrlPatterns("/secured/*");
        return registration;
    }
}
