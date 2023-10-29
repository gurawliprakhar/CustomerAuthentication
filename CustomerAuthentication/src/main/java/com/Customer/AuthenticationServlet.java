package com.Customer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login_id = request.getParameter("login_id");
        String password = request.getParameter("password");

        // Perform authentication using the provided credentials

        // If authentication is successful, generate the Bearer token
        String token = "generated_bearer_token";

        // Set the token as an attribute
        request.setAttribute("token", token);

        // Forward the request to a JSP to display the token
        request.getRequestDispatcher("displayToken.jsp").forward(request, response);
    }
}
