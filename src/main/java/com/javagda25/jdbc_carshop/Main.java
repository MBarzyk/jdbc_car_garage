package com.javagda25.jdbc_carshop;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        ScannerContentLoader loader = new ScannerContentLoader();

        try {
            CarDao carDao = new CarDao();
            loader.modify(carDao);
        } catch (SQLException e) {
            System.err.println("Car dao cannot be created. Mysql error.");
            System.err.println("Error: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Configuration file error.");
            System.err.println("Error: " + e.getMessage());
        }
    }
}
