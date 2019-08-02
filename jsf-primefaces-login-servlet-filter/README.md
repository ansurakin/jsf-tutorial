JSF - Login Servlet Filter Example

In order to run the above example open a command prompt and execute following Maven command:

mvn spring-boot:run

Open a web browser and enter the following URL: http://localhost:9090/codenotfound/login.xhtml

The home.xhtml page is located in a /secured folder, to which access will be protected by the LoginFilter.

The unsecured.xhtml page is an example of a page that can be accessed without needing to log in.

Enter following credentials: User name=”john.doe” and Password=”1234” and a welcome page will be displayed.
Click the Logout button and a redirect to the logout page.