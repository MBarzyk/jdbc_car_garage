package com.javagda25.jdbc_carshop;

import java.sql.SQLException;
import java.util.Scanner;

import static com.javagda25.jdbc_carshop.CarQueries.*;

public class ScannerContentLoader {
    private final Scanner scanner = new Scanner(System.in);


    public void modify(CarDao carDao) {
        Scanner scanner = new Scanner(System.in);
        String line;
        System.out.println("Welcome to Student Manager!");
        do {
            System.out.println("What to do? [add][deleteId][deletePlates][listAll][selectPlates][selectModel][selectName][exit]");
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
                    System.out.println("Give name");
                    String name = scanner.nextLine();

                    System.out.println(carDao.getCar(name, SELECT_PLATES));

                } else if (line.equalsIgnoreCase("selectModel")) {
                    System.out.println("Give name");
                    String name = scanner.nextLine();

                    System.out.println(carDao.getCar(name, SELECT_MODEL));
                }
            } catch (SQLException e) {
                System.err.println("Error executing command: " + e.getMessage());
            }
        } while (!line.equalsIgnoreCase("exit"));
    }
}
