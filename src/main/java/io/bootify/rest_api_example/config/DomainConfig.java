package io.bootify.rest_api_example.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan(basePackages = {"io.bootify.rest_api_example.domain"})
@EnableJpaRepositories(basePackages = {"io.bootify.rest_api_example.repos"})
@EnableTransactionManagement
public class DomainConfig {
}
