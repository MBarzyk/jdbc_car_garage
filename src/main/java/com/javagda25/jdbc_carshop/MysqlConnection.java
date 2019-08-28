package com.javagda25.jdbc_carshop;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class MysqlConnection {
    private MysqlConnectionParameters mysqlConnectionParameters;
    private MysqlDataSource dataSource;

    public MysqlConnection() throws IOException {
        mysqlConnectionParameters = new MysqlConnectionParameters();
        initialize();
    }

    private void initialize() {
        dataSource = new MysqlDataSource();

        dataSource.setPort(Integer.parseInt(mysqlConnectionParameters.getDbPort()));
        dataSource.setUser(mysqlConnectionParameters.getDbUser());
        dataSource.setServerName(mysqlConnectionParameters.getDbHost());
        dataSource.setPassword(mysqlConnectionParameters.getDbPassword());
        dataSource.setDatabaseName(mysqlConnectionParameters.getDbName());

        try {
            dataSource.setServerTimezone("Europe/Warsaw");
            dataSource.setUseSSL(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
