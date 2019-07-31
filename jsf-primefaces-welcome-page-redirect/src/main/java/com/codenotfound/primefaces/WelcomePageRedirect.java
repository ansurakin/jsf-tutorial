package com.codenotfound.primefaces;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Configuration. This indicates that the class can be used by the Spring IoC container as a source of bean definitions.
 */
@Configuration
public class WelcomePageRedirect implements WebMvcConfigurer {

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/")
        .setViewName("forward:/helloworld.xhtml");
    registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
  }
}
