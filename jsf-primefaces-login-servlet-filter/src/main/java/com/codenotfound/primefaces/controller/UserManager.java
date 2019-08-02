package com.codenotfound.primefaces.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codenotfound.primefaces.model.User;
import lombok.Getter;
import lombok.Setter;

/**
 * will handle all login related activities.
 */
@ManagedBean
@SessionScoped
public class UserManager implements Serializable {

    private static final long serialVersionUID = -9107952969237527819L;

    private static final Logger LOGGER
            = LoggerFactory.getLogger(UserManager.class);

    public static final String HOME_PAGE_REDIRECT
            = "/secured/home.xhtml?faces-redirect=true";
    public static final String LOGOUT_PAGE_REDIRECT
            = "/logout.xhtml?faces-redirect=true";

    @Getter
    @Setter
    private String userId;

    @Getter
    @Setter
    private String userPassword;

    /**
     * Do not provide a setter for the currentUser variable, as this potentially
     * allows a way to circumvent the login() method!
     */
    @Getter
    private User currentUser;

    /**
     * will try to look-up a user based on a userName and userPassword
     * combination entered on the login page.
     *
     * When the user is found it is assigned to the currentUser variable and a
     * redirect to the home page is returned. If the user is not found, a
     * redirect to the login page is returned.
     */
    public String login() {
        // lookup the user based on user name and user password
        currentUser = find(userId, userPassword);

        if (currentUser != null) {
            LOGGER.info("login successful for '{}'", userId);

            return HOME_PAGE_REDIRECT;
        } else {
            LOGGER.info("login failed for '{}'", userId);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Login failed",
                            "Invalid or unknown credentials."));

            return null;
        }
    }

    /**
     * invalidate the session and the redirect to the logout page will make sure
     * that the previous data is longer available.
     */
    public String logout() {
        String identifier = userId;

        // invalidate the session
        LOGGER.debug("invalidating session for '{}'", identifier);
        FacesContext.getCurrentInstance().getExternalContext()
                .invalidateSession();

        LOGGER.info("logout successful for '{}'", identifier);
        return LOGOUT_PAGE_REDIRECT;
    }

    /**
     * will be used by the LoginFilter to check if a user is logged in.
     *
     * currentUser is only set after a successful login.
     */
    public boolean isLoggedIn() {
        return currentUser != null;
    }

    /**
     * will return a redirect to the home page in case a user is already logged
     * in.
     */
    public String isLoggedInForwardHome() {
        if (isLoggedIn()) {
            return HOME_PAGE_REDIRECT;
        }

        return null;
    }

    private User find(String userId, String password) {
        User result = null;

        // TODO to be replaced with actual retrieval of user
        if ("john.doe".equalsIgnoreCase(userId)
                && "1234".equals(password)) {
            result = new User(userId, "John", "Doe");
        }

        return result;
    }

}
