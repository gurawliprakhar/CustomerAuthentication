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

@WebServlet("/updateCustomer")
public class CustomerUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bearerToken = "your_generated_bearer_token"; // Replace with the received Bearer token
        String customerId = request.getParameter("customerId"); // Get the customer ID from the request parameters
        // Form the JSON data for updating the customer
        String jsonInputString = "{\"customer_id\":\"" + customerId + "\",\"first_name\":\"UpdatedFirstName\",\"last_name\":\"UpdatedLastName\",\"street\":\"UpdatedStreet\",\"address\":\"UpdatedAddress\",\"city\":\"UpdatedCity\",\"state\":\"UpdatedState\",\"email\":\"updated_email@gmail.com\",\"phone\":\"9876543210\"}";

        // Make a PUT request to update the customer
        String url = "https://qa2.sunbasedata.com/sunbase/portal/api/update_customer"; // Replace with the actual update customer endpoint

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("PUT");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer " + bearerToken);
        con.setDoOutput(true);
        try (OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int responseCode = con.getResponseCode();
        if (responseCode == 200) {
            // Handle success case
            response.getWriter().write("Customer updated successfully.");
        } else if (responseCode == 400) {
            // Handle failure case
            response.getWriter().write("Failed to update customer. Please check the input data.");
        } else {
            // Handle other cases
            response.getWriter().write("An error occurred while updating the customer.");
        }
    }
}
