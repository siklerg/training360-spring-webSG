package springmvc.backend;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.mariadb.jdbc.MariaDbDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackageClasses = BackendConfig.class)
@PropertySource("classpath:/application.properties")
@EnableJpaRepositories
@EnableTransactionManagement
public class BackendConfig {
	
	@Autowired
	Environment environment;
	
	@Bean
	public DataSource dataSource() {
		MariaDbDataSource dataSource = new MariaDbDataSource();

		try {
			dataSource.setUrl(environment.getProperty("jdbc.url"));
			dataSource.setUser(environment.getProperty("jdbc.username"));
			dataSource.setPassword(environment.getProperty("jdbc.password"));
			return dataSource;
		} catch (SQLException e) {
			throw new IllegalStateException("Can not create data source", e);
		}
	}

	@Bean
	public Flyway flyway() {
//		Flyway flyway = Flyway.configure().dataSource(dataSource()).load();
		Flyway flyway = new Flyway();
		flyway.setDataSource(dataSource());
		flyway.migrate();
		return flyway;
	}

	@Bean
	public JpaTransactionManager transactionManager() {
		return new JpaTransactionManager();
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setShowSql(true);
		return hibernateJpaVendorAdapter;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter());
		entityManagerFactoryBean.setDataSource(dataSource);
		entityManagerFactoryBean.setPackagesToScan("springmvc", "springmvc.model");

		return entityManagerFactoryBean;
	}

}
