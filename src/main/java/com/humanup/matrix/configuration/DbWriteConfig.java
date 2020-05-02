package com.humanup.matrix.configuration;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    entityManagerFactoryRef = "entityManagerFactoryWrite",
    basePackages = {"com.humanup.matrix.dao"},
    transactionManagerRef = "transactionManagerWrite")
public class DbWriteConfig {
  @Bean(name = "dataSourceWrite")
  @ConfigurationProperties(prefix = "write.datasource")
  public DataSource dataSource() {
    return DataSourceBuilder.create().build();
  }

  @Bean(name = "entityManagerFactoryWrite")
  public LocalContainerEntityManagerFactoryBean entityManagerFactory(
      EntityManagerFactoryBuilder builder, @Qualifier("dataSourceWrite") DataSource dataSource) {
    return builder
        .dataSource(dataSource)
        .packages("com.humanup.matrix.dao.entities")
        .persistenceUnit("write")
        .build();
  }

  @Bean(name = "transactionManagerWrite")
  public PlatformTransactionManager transactionManager(
      @Qualifier("entityManagerFactoryWrite") EntityManagerFactory entityManagerFactory) {
    return new JpaTransactionManager(entityManagerFactory);
  }
}
