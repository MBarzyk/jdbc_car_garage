package com.javagda25.jdbc_carshop.order_elements;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Long id;
    private Long carId;
    private LocalDateTime dateOfOrder;
    private Boolean ifExecuted;
    private LocalDateTime dateOfExecution;
    private String description;

    public Order(Long carId, LocalDateTime dateOfOrder, Boolean ifExecuted, LocalDateTime dateOfExecution, String description) {
        this.carId = carId;
        this.dateOfOrder = dateOfOrder;
        this.ifExecuted = ifExecuted;
        this.dateOfExecution = dateOfExecution;
        this.description = description;
    }

    public Order(Long carId, LocalDateTime dateOfOrder, String description) {
        this.carId = carId;
        this.dateOfOrder = dateOfOrder;
        this.description = description;
    }
}
