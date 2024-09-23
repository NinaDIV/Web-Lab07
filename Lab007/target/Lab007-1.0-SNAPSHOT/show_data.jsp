 <%@ page import="Modelo.DatabaseConnection" %>
<%@ page import="java.sql.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product List</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }
        .container {
            width: 80%;
            max-width: 900px;
            margin: 20px;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
        }
        h2 {
            text-align: center;
            font-size: 1.8rem;
            color: #333;
            margin-bottom: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 0 auto;
        }
        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
            color: #333;
            font-weight: bold;
        }
        td {
            color: #555;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
        .error-message {
            color: red;
            text-align: center;
            margin-top: 20px;
        }
    </style>
</head>
<body>

    <div class="container">
        <h2>Product List</h2>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Price</th>
                </tr>
            </thead>
            <tbody>
                <% 
                try {
                    Connection connection = DatabaseConnection.getConnection();
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM products");
                    
                    boolean hasProducts = false;
                    
                    while (resultSet.next()) {
                        hasProducts = true;
                %>
                <tr>
                    <td><%= resultSet.getInt("id") %></td>
                    <td><%= resultSet.getString("name") %></td>
                    <td>$<%= resultSet.getDouble("price") %></td>
                </tr>
                <% 
                    }
                    
                    if (!hasProducts) {
                %>
                <tr>
                    <td colspan="3" style="text-align:center;">No products available.</td>
                </tr>
                <% 
                    }
                    
                    resultSet.close();
                    statement.close();
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace(); 
                %>
                <tr>
                    <td colspan="3" class="error-message">Error: <%= e.getMessage() %></td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </div>

</body>
</html>
