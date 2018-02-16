package com.poncho.flowabledemo.configurations;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@DependsOn("transactionManager")
@EnableConfigurationProperties
@EnableJpaRepositories(
        basePackages = "com.poncho.flowabledemo.secondDatabaseExample.repositories",
        entityManagerFactoryRef = "secEntityManagerFactory", transactionManagerRef = "transactionManager")
public class SecondDataSourceConfiguration {

    private final JpaVendorAdapter jpaVendorAdapter;

    @Autowired
    public SecondDataSourceConfiguration(JpaVendorAdapter jpaVendorAdapter) {
        this.jpaVendorAdapter = jpaVendorAdapter;
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.jta.atomikos.datasource.sec")
    public DataSource secondDataSource() {
        return new AtomikosDataSourceBean();
    }

    private Properties jpaProperties() {
        return EngineDataSourceConfiguration.getProperties();
    }

    @Bean
    @DependsOn("transactionManager")
    public LocalContainerEntityManagerFactoryBean secEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setJtaDataSource(secondDataSource());
        entityManager.setJpaVendorAdapter(jpaVendorAdapter);
        entityManager.setPackagesToScan("com.poncho.flowabledemo.secondDatabaseExample.model");
        entityManager.setPersistenceUnitName("sec-pu");
        entityManager.setJpaProperties(jpaProperties());

        return entityManager;
    }
}