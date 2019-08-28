package com.javagda25.jdbc_carshop.car_elements;

import com.javagda25.jdbc_carshop.MysqlConnection;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.javagda25.jdbc_carshop.car_elements.CarQueries.*;

public class CarDao {
    private MysqlConnection mysqlConnection;

    public CarDao() throws SQLException, IOException {
        mysqlConnection = new MysqlConnection();
        createTableIfNotExists();
    }

    private void createTableIfNotExists() throws SQLException {
        try (Connection connection = mysqlConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(CREATE_TABLE_CAR)) {
                statement.execute();
            }
        }
    }

    public void insertCar(Car car) throws SQLException {
        try (Connection connection = mysqlConnection.getConnection()) {

            try (PreparedStatement statement = connection.prepareStatement(INSERT_CAR, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, car.getPlates());
                statement.setLong(2, car.getMileage());
                statement.setString(3, car.getModel());
                statement.setInt(4, car.getYear());
                statement.setString(5, car.getCarType().toString());
                statement.setString(6, car.getOwnerName());

                statement.execute();
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    long generatedId = resultSet.getLong(1);
                    System.out.println("Record created with id: " + generatedId);
                }
            }
        }
    }

    public void deleteCarId(Long id) throws SQLException {
        try (Connection connection = mysqlConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(DELETE_ID_CAR)) {
                statement.setLong(1, id);

                int affectedRecords = statement.executeUpdate();
                if (affectedRecords > 0) {
                    System.out.println("Successfully deleted record with id: " + id);
                } else {
                    System.out.println("No car found with id: " + id);
                }
            }
        }
    }

    public void deleteCarPlates(String plates) throws SQLException {
        try (Connection connection = mysqlConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(DELETE_PLATES_CAR)) {
                statement.setString(1, plates);

                int affectedRecords = statement.executeUpdate();
                if (affectedRecords > 0) {
                    System.out.println("Successfully deleted record with plates: " + plates);
                } else {
                    System.out.println("No car found with plates: " + plates);
                }
            }
        }
    }

    public List<Car> listAllCars() throws SQLException {
        try (Connection connection = mysqlConnection.getConnection()) {
            List<Car> carList = new ArrayList<>();
            try (PreparedStatement statement = connection.prepareStatement(LIST_CAR)) {
                ResultSet resultSet = statement.executeQuery();

                loadMultipleCarsFromResultSet(carList, resultSet);
            }
            return carList;
        }
    }

    public List<Car> getCar(String line, String query) throws SQLException {
        try (Connection connection = mysqlConnection.getConnection()) {
            List<Car> carList = new ArrayList<>();
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, "%" + line + "%");
                ResultSet resultSet = statement.executeQuery();

                loadMultipleCarsFromResultSet(carList, resultSet);

            }
            return carList;
        }
    }


    private Car loadCarFromResultSet(ResultSet resultSet) throws SQLException {
        Car car = new Car();
        car.setId(resultSet.getLong(1));
        car.setPlates(resultSet.getString(2));
        car.setMileage(resultSet.getLong(3));
        car.setModel(resultSet.getString(4));
        car.setYear(resultSet.getInt(5));
        car.setCarType(CarType.valueOf(resultSet.getString(6)));
        car.setOwnerName(resultSet.getString(7));

        return car;
    }

    private void loadMultipleCarsFromResultSet(List<Car> carList, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            Car car = loadCarFromResultSet(resultSet);
            carList.add(car);
        }
    }
}
