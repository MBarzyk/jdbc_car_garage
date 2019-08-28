package com.javagda25.jdbc_carshop;

public interface CarQueries {
    String CREATE_TABLE_QUERY = "create table if not exists car (\n" +
            "id int not null auto_increment primary key,\n" +
            "plates varchar(10) not null unique,\n" +
            "mileage long not null,\n" +
            "model varchar(50) not null,\n" +
            "year int not null,\n" +
            "car_type varchar(10) not null,\n" +
            "owner_name varchar(100) not null);";

    String INSERT_QUERY = "insert into `car` (`plates`, `mileage`, `model`, `year`, `car_type`, `owner_name`)\n" +
            "values (?, ? , ?, ?, ?, ?);";

    String DELETE_ID_QUERY = "delete from `car` where `id` = ?;";

    String DELETE_PLATES_QUERY = "delete from `car` where `plates` = ?;";

    String LIST_QUERY = "select * from `car`;";

    String SELECT_PLATES = "select * from `car` where `plates` like ?;";

    String SELECT_NAME = "select * from `car` where `owner_name` like ?;";

    String SELECT_MODEL = "select * from `car` where `model` like ?;";
}
