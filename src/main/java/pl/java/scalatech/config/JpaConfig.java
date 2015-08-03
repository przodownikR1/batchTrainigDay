package pl.java.scalatech.config;

import javax.sql.DataSource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "pl.java.scalatech.repository")
@EntityScan(basePackages = "pl.java.scalatech.entity")
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
@EnableTransactionManagement
@ComponentScan(basePackages = { "pl.java.scalatech.service", "pl.java.scalatech.repository" })
@Slf4j
@PropertySource(value = "classpath:application.properties")
public class JpaConfig {
    @Value("${spring.datasource.driver-class-name}")
    private String driverDB;
    @Value("${spring.datasource.url}")
    private String urlDB;
    @Value("${spring.datasource.username}")
    private String userDB;
    @Value("${spring.datasource.password}")
    private String passwdDB;

    @Bean
    public DataSource dataSource() {
        log.debug("+++ primary DataSource -> Batch config <- {} : {}", driverDB, urlDB);
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(driverDB);
        ds.setUrl(urlDB);
        ds.setUsername(userDB);
        ds.setPassword(passwdDB);
        return ds;
    }

    /*
     * <beans profile="dev-prepare-db" >
     * <jdbc:initialize-database data-source="dataSource">
     * <jdbc:script location="${batch.schema.script.drop}" />
     * <jdbc:script location="${batch.schema.script}" />
     * </jdbc:initialize-database>
     * </beans>
     * Ponizej javaConfig ==
     */
    /*
     * @Bean
     * public ResourceDatabasePopulator resourceDatabasePopulator() {
     * ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
     * resourceDatabasePopulator.addScript(new ClassPathResource(dropScript));
     * resourceDatabasePopulator.addScript(new ClassPathResource(createScript));
     * log.debug("+++ resourceDbPopulator drop.... {}", dropScript);
     * log.debug("+++ resourceDbPopulator init.... {}", createScript);
     * return resourceDatabasePopulator;
     * }
     * @Bean
     * public DataSourceInitializer dataSourceInitializer(DataSource dataSource) {
     * DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
     * dataSourceInitializer.setDataSource(dataSource);
     * return dataSourceInitializer;
     * }
     */

}