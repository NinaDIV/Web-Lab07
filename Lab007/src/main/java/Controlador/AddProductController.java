package Controlador;

import Modelo.DatabaseConnection;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 
public class AddProductController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        DatabaseConnection.insertProduct(name, price);
        // Redirect to the show_data.jsp page
        response.sendRedirect("show_data.jsp");
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Display a form for adding products
        request.getRequestDispatcher("add_product.jsp").forward(request, response);
    }
}
