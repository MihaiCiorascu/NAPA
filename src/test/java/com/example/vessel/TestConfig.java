package com.example.vessel;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;

@TestConfiguration
public class TestConfig {
    
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSource.setUrl("jdbc:sqlserver://KYLIAN-MBAPPÉ-P:1433;databaseName=Vessel_Management_Test;encrypt=true;trustServerCertificate=true");
        dataSource.setUsername("user1");
        dataSource.setPassword("parola");
        return dataSource;
    }
} 