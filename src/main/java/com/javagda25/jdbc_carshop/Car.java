package com.javagda25.jdbc_carshop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    private Long Id;
    private String plates;
    private Long mileage;
    private String model;
    private Integer year;
    private CarType carType;
    private String ownerName;

    public Car(String plates, Long mileage, String model, Integer year, CarType carType, String ownerName) {
        this.plates = plates;
        this.mileage = mileage;
        this.model = model;
        this.year = year;
        this.carType = carType;
        this.ownerName = ownerName;
    }
}
