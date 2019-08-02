package com.codenotfound.primefaces.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codenotfound.primefaces.controller.UserManager;

/**
 * The LoginFilter class is a Servlet Filter that will be used to restrict
 * access to the home page. When called, it will try to retrieve the UserManager
 * from the ServletRequest. If the isLoggedIn() returns true then the call is
 * ALLOWED THROUGH. In all other cases, a redirect is done to the login page.
 *
 * Note that the session attribute name used to retrieve the UserManager is the
 * name of the managed bean.
 * 
 * The configuration of the LoginFilter is done in the FilterConfig class.
 */
public class LoginFilter implements Filter {

    private static final Logger LOGGER
            = LoggerFactory.getLogger(LoginFilter.class);

    public static final String LOGIN_PAGE = "/login.xhtml";

    @Override
    public void doFilter(ServletRequest servletRequest,
            ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest httpServletRequest
                = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse
                = (HttpServletResponse) servletResponse;

        // managed bean name is exactly the session attribute name
        UserManager userManager = (UserManager) httpServletRequest
                .getSession().getAttribute("userManager");

        if (userManager != null) {
            if (userManager.isLoggedIn()) {
                LOGGER.debug("user is logged in");
                // user is logged in, continue request
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                LOGGER.debug("user is not logged in");
                // user is not logged in, redirect to login page
                httpServletResponse.sendRedirect(
                        httpServletRequest.getContextPath() + LOGIN_PAGE);
            }
        } else {
            LOGGER.debug("userManager not found");
            // user is not logged in, redirect to login page
            httpServletResponse.sendRedirect(
                    httpServletRequest.getContextPath() + LOGIN_PAGE);
        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        LOGGER.debug("loginFilter initialized");
    }

    @Override
    public void destroy() {
        // close resources
        LOGGER.debug("loginFilter destroyed");
    }
}
