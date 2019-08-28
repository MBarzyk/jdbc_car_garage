package com.javagda25.jdbc_carshop;

import com.javagda25.jdbc_carshop.car_elements.Car;
import com.javagda25.jdbc_carshop.car_elements.CarDao;
import com.javagda25.jdbc_carshop.car_elements.CarType;
import com.javagda25.jdbc_carshop.order_elements.Order;
import com.javagda25.jdbc_carshop.order_elements.OrderDao;

import java.sql.SQLException;
import java.util.Scanner;

import static com.javagda25.jdbc_carshop.car_elements.CarQueries.*;
import static com.javagda25.jdbc_carshop.order_elements.OrderQueries.LIST_EXECUTED;
import static com.javagda25.jdbc_carshop.order_elements.OrderQueries.LIST_NONEXECUTED;

public class ScannerContentLoader {
    private final Scanner scanner = new Scanner(System.in);


    public void modifyOrder(OrderDao orderDao) {
        String line;
        System.out.println("Welcome to Order Manager!");
        do {
            System.out.println("What to do? [add][setExecuted][listAll][listExecuted][ListNonExecuted][ListDaysAgo][return][exit]");
            line = scanner.nextLine();
            try {
                if (line.equalsIgnoreCase("add")) {
                    Order order = new Order();
                    System.out.println("Write car ID: ");
                    order.setCarId(Long.parseLong(scanner.nextLine()));
                    System.out.println("Write description: ");
                    order.setDescription(scanner.nextLine());

                    orderDao.insertOrder(order);

                } else if (line.equalsIgnoreCase("setExecuted")) {
                    System.out.println("Give ID");
                    Long id = Long.parseLong(scanner.nextLine());

                    orderDao.setOrderAsExecuted(id);

                } else if (line.equalsIgnoreCase("listAll")) {
                    System.out.println("Give car ID");
                    Long carId = Long.parseLong(scanner.nextLine());

                    orderDao.listAllOrders(carId).forEach(System.out::println);

                } else if (line.equalsIgnoreCase("listNonExecuted")) {

                    orderDao.listOrdersByQuery(LIST_NONEXECUTED).forEach(System.out::println);

                } else if (line.equalsIgnoreCase("listExecuted")) {

                    orderDao.listOrdersByQuery(LIST_EXECUTED).forEach(System.out::println);

                } else if (line.equalsIgnoreCase("listDaysAgo")) {
                    System.out.println("Show orders from how many days ago?");
                    int days = Integer.parseInt(scanner.nextLine());

                    orderDao.listOrdersByDaysAgo(days).forEach(System.out::println);
                } else if (line.equalsIgnoreCase("return")) {
                    break;
                } else if (line.equalsIgnoreCase("exit")) {
                    System.exit(1);
                }
            } catch (SQLException e) {
                System.err.println("Error executing command: " + e.getMessage());
            }
        } while (!line.equalsIgnoreCase("exit"));
    }

    public void modifyCar(CarDao carDao) {
        String line;
        System.out.println("Welcome to Garage Manager!");
        do {
            System.out.println("What to do? [add][deleteId][deletePlates][listAll][selectPlates][selectModel][selectName][return][exit]");
            line = scanner.nextLine();
            try {
                if (line.equalsIgnoreCase("add")) {
                    Car car = new Car();
                    System.out.println("Write plates: ");
                    car.setPlates(scanner.nextLine());
                    System.out.println("Write mileage: ");
                    car.setMileage(Long.parseLong(scanner.nextLine()));
                    System.out.println("Write what model is it: ");
                    car.setModel(scanner.nextLine());
                    System.out.println("Write year of production: ");
                    car.setYear(Integer.parseInt(scanner.nextLine()));
                    System.out.println("Write what type of car is it: ");
                    for (CarType value : CarType.values()) {
                        System.out.println(value);
                    }
                    do {
                        try {
                            car.setCarType(CarType.valueOf(scanner.nextLine().toUpperCase()));
                        } catch (IllegalArgumentException iae) {
                            System.err.println("Wrong type! Try again.");
                        }
                    } while (car.getCarType() == null);
                    System.out.println("Write the name of the owner: ");
                    car.setOwnerName(scanner.nextLine());

                    carDao.insertCar(car);

                } else if (line.equalsIgnoreCase("deleteId")) {
                    System.out.println("Give ID");
                    Long id = Long.parseLong(scanner.nextLine());

                    carDao.deleteCarId(id);

                } else if (line.equalsIgnoreCase("deletePlates")) {
                    System.out.println("Give plates");
                    String plates = scanner.nextLine();

                    carDao.deleteCarPlates(plates);

                } else if (line.equalsIgnoreCase("listAll")) {

                    carDao.listAllCars().forEach(System.out::println);

                } else if (line.equalsIgnoreCase("selectName")) {
                    System.out.println("Give name");
                    String name = scanner.nextLine();

                    System.out.println(carDao.getCar(name, SELECT_NAME));
                } else if (line.equalsIgnoreCase("selectPlates")) {
                    System.out.println("Give plates");
                    String name = scanner.nextLine();

                    System.out.println(carDao.getCar(name, SELECT_PLATES));

                } else if (line.equalsIgnoreCase("selectModel")) {
                    System.out.println("Give model");
                    String name = scanner.nextLine();

                    System.out.println(carDao.getCar(name, SELECT_MODEL));
                } else if (line.equalsIgnoreCase("return")) {
                    break;

                } else if (line.equalsIgnoreCase("exit")) {
                    System.exit(1);
                }
            } catch (SQLException e) {
                System.err.println("Error executing command: " + e.getMessage());
            }
        } while (!line.equalsIgnoreCase("exit"));
    }
}
