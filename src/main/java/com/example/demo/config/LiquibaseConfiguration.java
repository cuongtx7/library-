package com.example.demo.config;

import io.github.jhipster.config.JHipsterConstants;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;

import javax.sql.DataSource;
import java.util.concurrent.Executor;

@Configuration
@EnableConfigurationProperties(LiquibaseProperties.class)
public class LiquibaseConfiguration {
    @Autowired
    private Environment env;

    @Bean
    public SpringLiquibase liquibase(@Qualifier("taskExecutor") Executor executor, DataSource dataSource,
                                     LiquibaseProperties liquibaseProperties) {

        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog("classpath:config/liquibase/master.xml");

        if (liquibaseProperties.getContexts() != null && !liquibaseProperties.getContexts().isEmpty()) {
            liquibase.setContexts(String.join(",", liquibaseProperties.getContexts()));
        }

        liquibase.setDefaultSchema(liquibaseProperties.getDefaultSchema());
        liquibase.setDropFirst(liquibaseProperties.isDropFirst());
        liquibase.setChangeLogParameters(liquibaseProperties.getParameters());

        if (env.acceptsProfiles(Profiles.of(JHipsterConstants.SPRING_PROFILE_NO_LIQUIBASE))) {
            liquibase.setShouldRun(false);
        } else {
            liquibase.setShouldRun(liquibaseProperties.isEnabled());
        }

        return liquibase;
    }
}
