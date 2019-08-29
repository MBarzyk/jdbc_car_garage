package com.javagda25.jdbc_carshop.order_elements;

public interface OrderQueries {
    String CREATE_TABLE_ORDER = "create table if not exists `order` (\n" +
            "`id` int not null auto_increment primary key,\n" +
            "`car_id` int not null,\n" +
            "`date_of_order` datetime not null,\n" +
            "`if_executed` tinyint not null default 0,\n" +
            "`date_of_execution` datetime default null,\n" +
            "`description` varchar(100),\n" +
            "foreign key (car_id) references car (id)\n" +
            ");";

    String LIST_ALL_ORDERS_ID = "select * from `order` where car_id = ?;";

    String LIST_ALL_ORDERS = "select * from `order`;";

    String INSERT_ORDER = "insert into `order` (`car_id`, `date_of_order`, `description`)\n" +
            "values (?, now(), ?);";

    String SET_ORDER_EXECUTE = "update `order`\n" +
            "set `if_executed` = 1, `date_of_execution` = now() \n" +
            "where `id` = ?;";

    String LIST_NONEXECUTED = "select * from `order` where `if_executed` = 0;";

    String LIST_EXECUTED = "select * from `order` where `if_executed` = 1;";

    String LIST_ORDERS_DAYS_AGO = "SELECT * FROM `order`\n" +
            "WHERE date_of_order between date_sub(now(), INTERVAL ? DAY) and now();";
}
