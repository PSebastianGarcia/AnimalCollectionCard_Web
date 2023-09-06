package com.mycompany.animalcollectioncard_web.ConnectionDataBase;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp2.BasicDataSource;
import javax.sql.DataSource;

public class ConnectionDB {

    private static final String DDBB_URL = "jdbc:mysql://localhost:3306/animalcollectionweb?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static BasicDataSource dataSource;

    private ConnectionDB() {
    }

    private static DataSource getDataSource() {

        if (dataSource == null) {
            try {
                dataSource = new BasicDataSource();
                dataSource.setUrl(DDBB_URL);
                dataSource.setUsername(USERNAME);
                dataSource.setPassword(PASSWORD);
                dataSource.setInitialSize(50);
                dataSource.setDriverClassName("com.mysql.jdbc.Driver");
            } catch (Exception ex) {
                throw new RuntimeException("Error E/S, lectura configuraión Base de datos", ex);
            }
        }
        return dataSource;
    }
    
    public static Connection getConnection() throws SQLException{
        return getDataSource().getConnection();
    }
}
