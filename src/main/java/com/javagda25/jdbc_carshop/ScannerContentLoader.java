package com.javagda25.jdbc_carshop;

import com.javagda25.jdbc_carshop.car_elements.Car;
import com.javagda25.jdbc_carshop.car_elements.CarDao;
import com.javagda25.jdbc_carshop.car_elements.CarType;
import com.javagda25.jdbc_carshop.order_elements.Order;
import com.javagda25.jdbc_carshop.order_elements.OrderDao;

import java.sql.SQLException;
import java.util.List;
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

                    List<Order> orderList = orderDao.listAllOrdersOfId(carId);
                    if (orderList.isEmpty()) {
                        System.out.println("There is no orders for id car: " + carId);
                    } else {
                        orderList.forEach(System.out::println);
                    }

                } else if (line.equalsIgnoreCase("listNonExecuted")) {

                    List<Order> orderList = orderDao.listOrdersByQuery(LIST_NONEXECUTED);
                    if (orderList.isEmpty()) {
                        System.out.println("There are none non-executed orders");
                    } else {
                        orderList.forEach(System.out::println);
                    }

                } else if (line.equalsIgnoreCase("listExecuted")) {

                    List<Order> orderList = orderDao.listOrdersByQuery(LIST_EXECUTED);
                    if (orderList.isEmpty()) {
                        System.out.println("There are none executed orders");
                    } else {
                        orderList.forEach(System.out::println);
                    }

                } else if (line.equalsIgnoreCase("listDaysAgo")) {
                    System.out.println("Show orders from how many days ago?");
                    int days = Integer.parseInt(scanner.nextLine());

                    List<Order> orderList = orderDao.listOrdersByDaysAgo(days);
                    if (orderList.isEmpty()) {
                        System.out.println("There were no orders found within given time period");
                    } else {
                        orderList.forEach(System.out::println);
                    }
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

                    List<Car> cars = carDao.listAllCars();
                    if (cars.isEmpty()) {
                        System.out.println("There were no cars found");
                    } else {
                        cars.forEach(System.out::println);
                    }

                } else if (line.equalsIgnoreCase("selectName")) {
                    System.out.println("Give name");
                    String name = scanner.nextLine();

                    List<Car> carList = carDao.getCar(name, SELECT_NAME);
                    if (carList.isEmpty()) {
                        System.out.println("There were no cars found with given owner's name");
                    } else {
                        carList.forEach(System.out::println);
                    }


                } else if (line.equalsIgnoreCase("selectPlates")) {
                    System.out.println("Give plates");
                    String name = scanner.nextLine();

                    List<Car> carList = carDao.getCar(name, SELECT_PLATES);
                    if (carList.isEmpty()) {
                        System.out.println("There were no cars found with given plates");
                    } else {
                        carList.forEach(System.out::println);
                    }

                } else if (line.equalsIgnoreCase("selectModel")) {
                    System.out.println("Give model");
                    String name = scanner.nextLine();

                    List<Car> carList = carDao.getCar(name, SELECT_MODEL);
                    if (carList.isEmpty()) {
                        System.out.println("There were no cars found with given model");
                    } else {
                        carList.forEach(System.out::println);
                    }

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
