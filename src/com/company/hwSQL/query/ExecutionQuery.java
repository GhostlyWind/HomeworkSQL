package com.company.hwSQL.query;

import com.company.hwSQL.model.Customer;
import org.apache.commons.validator.routines.EmailValidator;

import java.sql.*;

public class ExecutionQuery {

    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;

    static void executeInsert(Connection connection, Customer customer) {
        String sqlForeignKey = "SET FOREIGN_KEY_CHECKS=0";
        String sql = "INSERT INTO customer (first_name, last_name, email, store_id, address_id) VALUE (?, ?, ?, ?, ?)";
        try {
            connection.prepareStatement(sqlForeignKey).executeUpdate();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setString(3, customer.getEmail());
            preparedStatement.setLong(4, customer.getStoreId());
            preparedStatement.setLong(5, customer.getAddressId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static ResultSet executeSelect(Connection connection, int fromId, int toId) {
        if (fromId > 0 && toId > 0 && fromId <= toId) {
            String sql = "SELECT * FROM customer WHERE customer_id >= ? AND customer_id <= ?";
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setLong(1, fromId);
                preparedStatement.setLong(2, toId);
                resultSet = preparedStatement.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return resultSet;
        } else {
            throw new IllegalArgumentException("Check the arguments:  0 < fromId <= toId ");
        }
    }

    static void executeUpdate(Connection connection, long id, String email) {
        if (EmailValidator.getInstance().isValid(email) && id > 0) {
            String sql = "UPDATE customer SET email = ? WHERE customer_id = ?";
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, email);
                preparedStatement.setLong(2, id);
                preparedStatement.executeUpdate();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            throw new IllegalArgumentException("Check the arguments: id > 0; email must be in a valid format");
        }
    }

    static void executeDelete(Connection connection, long id) {
        if (id > 0) {
            String sqlChildPayment = "DELETE FROM payment WHERE customer_id = ?";
            String sqlChildRental = "DELETE FROM rental WHERE customer_id = ?";
            String sqlParentCustomer = "DELETE FROM customer WHERE customer_id = ?";
            try {
                preparedStatement = connection.prepareStatement(sqlChildPayment);
                preparedStatement.setLong(1, id);
                preparedStatement.executeUpdate();

                preparedStatement = connection.prepareStatement(sqlChildRental);
                preparedStatement.setLong(1, id);
                preparedStatement.executeUpdate();

                preparedStatement = connection.prepareStatement(sqlParentCustomer);
                preparedStatement.setLong(1, id);
                preparedStatement.executeUpdate();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            throw new IllegalArgumentException("Check the argument: id > 0");
        }
    }
}
