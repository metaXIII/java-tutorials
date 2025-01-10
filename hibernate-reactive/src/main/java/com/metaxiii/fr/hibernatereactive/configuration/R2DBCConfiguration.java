package com.metaxiii.fr.hibernatereactive.configuration;

import io.r2dbc.h2.H2ConnectionConfiguration;
import io.r2dbc.h2.H2ConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.lang.NonNull;
import org.springframework.r2dbc.connection.init.CompositeDatabasePopulator;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

@Configuration
@EnableR2dbcRepositories(basePackages = "com.metaxiii.fr.hibernatereactive.repository")
public class R2DBCConfiguration extends AbstractR2dbcConfiguration {

  @Bean
  @NonNull
  @Override
  public H2ConnectionFactory connectionFactory() {
    return new H2ConnectionFactory(
      H2ConnectionConfiguration.builder().url("mem:testdb;DB_CLOSE_DELAY=-1;TRACE_LEVEL_FILE=4;").username("sa").build()
    );
  }

  @Bean
  public ConnectionFactoryInitializer initializer(final ConnectionFactory connectionFactory) {
    final var initializer = new ConnectionFactoryInitializer();
    initializer.setConnectionFactory(connectionFactory);
    final var populator = new CompositeDatabasePopulator();
    populator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("init.sql")));
    initializer.setDatabasePopulator(populator);
    return initializer;
  }
}
