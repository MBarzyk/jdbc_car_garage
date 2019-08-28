package com.javagda25.jdbc_carshop.order_elements;

import com.javagda25.jdbc_carshop.MysqlConnection;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.javagda25.jdbc_carshop.order_elements.OrderQueries.*;

public class OrderDao {
    private MysqlConnection mysqlConnection;

    public OrderDao() throws IOException, SQLException {
        mysqlConnection = new MysqlConnection();
        createTableIfNotExists();
    }

    private void createTableIfNotExists() throws SQLException {
        try (Connection connection = mysqlConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(CREATE_TABLE_ORDER)) {
                statement.execute();
            }
        }
    }

    public void insertOrder(Order order) throws SQLException {
        try (Connection connection = mysqlConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(INSERT_ORDER, Statement.RETURN_GENERATED_KEYS)) {
                statement.setLong(1, order.getCarId());
                statement.setString(2, order.getDescription());

                statement.execute();
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    long generatedId = resultSet.getLong(1);
                    System.out.println("Order generated with id: " + generatedId);
                }
            }
        }
    }

    public List<Order> listAllOrders(Long id) throws SQLException {
        try (Connection connection = mysqlConnection.getConnection()) {
            List<Order> orderList = new ArrayList<>();
            try (PreparedStatement statement = connection.prepareStatement(LIST_ALL_ORDERS_ID)) {
                statement.setLong(1, id);
                ResultSet resultSet = statement.executeQuery();

                loadMultipleOrdersFromResultSet(orderList, resultSet);
            }
            return orderList;
        }
    }

    public void setOrderAsExecuted(Long id) throws SQLException {
        try (Connection connection = mysqlConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(SET_ORDER_EXECUTE)) {
                statement.setLong(1, id);

                int affectedRecords = statement.executeUpdate();
                if (affectedRecords > 0) {
                    System.out.println("Successfully set order: " + id + " as executed.");
                } else {
                    System.out.println("Did not found the order of id: " + id);
                }
            }
        }
    }

    public List<Order> listOrdersByQuery (String query) throws SQLException {
        try (Connection connection = mysqlConnection.getConnection()) {
            List<Order> orderList = new ArrayList<>();
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                ResultSet resultSet = statement.executeQuery();

                loadMultipleOrdersFromResultSet(orderList, resultSet);
            }
            return orderList;
        }
    }

    public List<Order> listOrdersByDaysAgo (int days) throws SQLException {
        try (Connection connection = mysqlConnection.getConnection()) {
            List<Order> orderList = new ArrayList<>();
            try (PreparedStatement statement = connection.prepareStatement(LIST_ORDERS_DAYS_AGO)) {
                statement.setInt(1, days);
                ResultSet resultSet = statement.executeQuery();

                loadMultipleOrdersFromResultSet(orderList, resultSet);
            }
            return orderList;
        }
    }



    private Order loadOrderFromResultSet(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getLong(1));
        order.setCarId(resultSet.getLong(2));
        order.setDateOfOrder(resultSet.getObject(3, LocalDateTime.class));
        order.setIfExecuted(resultSet.getBoolean(4));
        order.setDateOfExecution(resultSet.getObject(5, LocalDateTime.class));
        order.setDescription(resultSet.getString(6));

        return order;
    }

    private void loadMultipleOrdersFromResultSet(List<Order> orderList, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            Order order = loadOrderFromResultSet(resultSet);
            orderList.add(order);
        }
    }
}
