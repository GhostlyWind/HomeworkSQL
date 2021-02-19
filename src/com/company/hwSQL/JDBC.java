package com.company.hwSQL;

import com.company.hwSQL.model.Customer;
import com.company.hwSQL.query.Query;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Properties;

public class JDBC {

    public static void main(String[] args) {

        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = JDBC.getConnection();
        } catch (ClassNotFoundException | IOException | SQLException e) {
            e.printStackTrace();
        }

        Query query = new Query();

        query.insert(connection, new Customer.Builder()
                .firstName("Kate")
                .lastName("Catt")
                .email("11111@mail.ru")
                .storeId(2)
                .addressId(30)
                .build());


        query.delete(connection, 15);

        query.update(connection, 20, "5555@mail.ru");

        try {
            ResultSet result = query.select(connection, 22, 29);
            while (result.next()) {
                int id = result.getInt("customer_id");
                String firstName = result.getString("first_name");
                String lastName = result.getString("last_name");
                String email = result.getString("email");
                System.out.println(id + "\s" + lastName + "\s" + lastName + "\s" + email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    private static Connection getConnection() throws IOException, SQLException {
        Properties properties = new Properties();
        try (InputStream inputStream = Files.newInputStream(Paths.get("database.properties"))) {
            properties.load(inputStream);
        }

        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");

        return DriverManager.getConnection(url, user, password);
    }

}
