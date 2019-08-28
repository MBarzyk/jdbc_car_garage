package com.javagda25.jdbc_carshop;

import com.javagda25.jdbc_carshop.car_elements.CarDao;
import com.javagda25.jdbc_carshop.order_elements.OrderDao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ScannerContentLoader loader = new ScannerContentLoader();
        Scanner scanner = new Scanner(System.in);
        try {
            CarDao carDao = new CarDao();
            OrderDao orderDao = new OrderDao();
            String line;
            do {
                System.out.println("What records do you want to modify? [car][order][exit]");
                line = scanner.nextLine();
                if (line.equalsIgnoreCase("car")) {
                    loader.modifyCar(carDao);
                } else if (line.equalsIgnoreCase("order")) {
                    loader.modifyOrder(orderDao);
                } else {
                    System.out.println("Wrong choice, try again!");
                }
            } while (!line.equalsIgnoreCase("exit"));

            loader.modifyCar(carDao);
        } catch (SQLException e) {
            System.err.println("Car dao cannot be created. Mysql error.");
            System.err.println("Error: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Configuration file error.");
            System.err.println("Error: " + e.getMessage());
        }
    }
}
