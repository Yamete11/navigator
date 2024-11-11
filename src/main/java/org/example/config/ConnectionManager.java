package org.example.config;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.apache.ibatis.session.Configuration;
import org.example.dao.*;
import org.example.model.EndLocation;
import org.example.model.Route;
import org.example.model.RouteCity;
import org.example.model.StartLocation;

import java.util.logging.Logger;

public class ConnectionManager {
    private static ConnectionManager instance;
    private static SqlSessionFactory sqlSessionFactory;
    private static final Logger logger = Logger.getLogger(ConnectionManager.class.getName());

    private static final String URL = "jdbc:mysql://localhost:3306/navigator";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "8Nlcjqlh";

    private ConnectionManager() {
        try {
            PooledDataSource dataSource = new PooledDataSource();
            dataSource.setDriver("com.mysql.cj.jdbc.Driver");
            dataSource.setUrl(URL);
            dataSource.setUsername(USERNAME);
            dataSource.setPassword(PASSWORD);

            JdbcTransactionFactory transactionFactory = new JdbcTransactionFactory();
            Environment environment = new Environment("development", transactionFactory, dataSource);
            Configuration configuration = new Configuration(environment);

            configuration.addMapper(CityMapper.class);
            configuration.addMapper(CityConnectionMapper.class);
            configuration.addMapper(StartLocationMapper.class);
            configuration.addMapper(EndLocationMapper.class);
            configuration.addMapper(RouteMapper.class);
            configuration.addMapper(RouteCityMapper.class);

            sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        } catch (Exception e) {
            logger.severe("Error initializing SqlSessionFactory: " + e.getMessage());
            throw new RuntimeException("Error initializing SqlSessionFactory", e);
        }
    }

    public static synchronized ConnectionManager getInstance() {
        if (instance == null) {
            instance = new ConnectionManager();
        }
        return instance;
    }

    public SqlSessionFactory getSessionFactory() {
        return sqlSessionFactory;
    }
}
