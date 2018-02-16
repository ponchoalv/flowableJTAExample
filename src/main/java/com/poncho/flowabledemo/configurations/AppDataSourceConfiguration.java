package com.poncho.flowabledemo.configurations;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;




import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@DependsOn("transactionManager")
@EnableConfigurationProperties
@EnableJpaRepositories(
        basePackages = "com.poncho.flowabledemo.accessControlDao.repositories",
        entityManagerFactoryRef = "appEntityManagerFactory", transactionManagerRef = "transactionManager")
public class AppDataSourceConfiguration {

    private final JpaVendorAdapter jpaVendorAdapter;

    @Autowired
    public AppDataSourceConfiguration(JpaVendorAdapter jpaVendorAdapter) {
        this.jpaVendorAdapter = jpaVendorAdapter;
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.jta.atomikos.datasource.app")
    public DataSource appDataSource() {
        return new AtomikosDataSourceBean();
    }

    private Properties jpaProperties() {
        return EngineDataSourceConfiguration.getProperties();
    }

    @Bean
    @DependsOn("transactionManager")
    public LocalContainerEntityManagerFactoryBean appEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setJtaDataSource(appDataSource());
        entityManager.setJpaVendorAdapter(jpaVendorAdapter);
        entityManager.setPackagesToScan("com.poncho.flowabledemo.accessControlDao.model");
        entityManager.setPersistenceUnitName("app-pu");
        entityManager.setJpaProperties(jpaProperties());

        return entityManager;
    }
}