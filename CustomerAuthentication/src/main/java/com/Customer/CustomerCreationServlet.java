package com.Customer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@WebServlet("/createCustomer")
public class CustomerCreationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bearerToken = "your_generated_bearer_token"; // Replace with the received Bearer token
        String url = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp";
        String jsonInputString = "{\"first_name\":\"Jane\",\"last_name\":\"Doe\",\"street\":\"Elvnu Street\",\"address\":\"H no 2\",\"city\":\"Delhi\",\"state\":\"Delhi\",\"email\":\"sam@gmail.com\",\"phone\":\"12345678\"}";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer " + bearerToken);
        con.setDoOutput(true);
        try (OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int responseCode = con.getResponseCode();
        if (responseCode == 201) {
            // Handle success case
            response.getWriter().write("Customer created successfully.");
        } else if (responseCode == 400) {
            // Handle failure case
            response.getWriter().write("Failed to create customer. Please check the input data.");
        } else {
            // Handle other cases
            response.getWriter().write("An error occurred while creating the customer.");
        }
    }
}
