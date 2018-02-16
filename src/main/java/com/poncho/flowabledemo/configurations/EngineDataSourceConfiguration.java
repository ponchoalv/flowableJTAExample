package com.poncho.flowabledemo.configurations;

import org.flowable.engine.IdentityService;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.flowable.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.hibernate.jpa.HibernatePersistenceProvider;

import javax.sql.DataSource;
import javax.transaction.TransactionManager;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
@EnableConfigurationProperties
@EnableJpaRepositories(
        basePackages = "com.poncho.flowabledemo.engineJpaDatabase.repositories",
        entityManagerFactoryRef = "engineEntityManagerFactory", transactionManagerRef = "transactionManager")
public class EngineDataSourceConfiguration {

    private final JpaVendorAdapter jpaVendorAdapter;


    @Autowired
    public EngineDataSourceConfiguration(JpaVendorAdapter jpaVendorAdapter) {
        this.jpaVendorAdapter = jpaVendorAdapter;
    }

    @Bean
    @DependsOn("transactionManager")
    @Primary
    @ConfigurationProperties(prefix = "spring.jta.atomikos.datasource.engineDataSource")
    public DataSource pooledDataSource() {
        return new AtomikosDataSourceBean();
    }

    private Properties jpaProperties() {
        return getProperties();
    }

    static Properties getProperties() {
        Properties props = new Properties();
        props.setProperty("hibernate.physical_naming_strategy", SpringPhysicalNamingStrategy.class.getName());
        props.setProperty("hibernate.implicit_naming_strategy", SpringImplicitNamingStrategy.class.getName());
        props.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        props.setProperty("hibernate.id.new_generator_mappings", "false");
        props.setProperty("hibernate.transaction.jta.platform", AtomikosJtaPlatform.class.getName());
        props.setProperty("javax.persistence.transactionType", "JTA");
        props.setProperty("show_sql", "true");
        return props;
    }

    @Bean(name = "engineEntityManagerFactory")
    @DependsOn("transactionManager")
    @Primary
    public LocalContainerEntityManagerFactoryBean engineEntityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setJtaDataSource(pooledDataSource());
        entityManager.setJpaVendorAdapter(jpaVendorAdapter);
        entityManager.setPackagesToScan("com.poncho.flowabledemo.engineJpaDatabase.model");
        entityManager.setPersistenceUnitName("engine-pu");
        entityManager.setJpaProperties(jpaProperties());
        return entityManager;
    }

}
