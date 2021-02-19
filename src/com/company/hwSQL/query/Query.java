package com.company.hwSQL.query;

import com.company.hwSQL.model.Customer;

import java.sql.Connection;
import java.sql.ResultSet;

import static com.company.hwSQL.query.ExecutionQuery.*;


public class Query {

    public void insert(Connection connection, Customer customer) {
        executeInsert(connection, customer);
    }

    public ResultSet select(Connection connection, int fromId, int toId) {
        return executeSelect(connection, fromId, toId);
    }

    public void update(Connection connection, long id, String email) {
        executeUpdate(connection, id, email);
    }

    public void delete(Connection connection, long id) {
        executeDelete(connection, id);
    }

}
