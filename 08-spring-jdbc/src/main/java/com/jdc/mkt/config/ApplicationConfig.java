package com.jdc.mkt.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
@ComponentScan(basePackages = "com.jdc.mkt.dao")
public class ApplicationConfig {

	public DataSource dataSource() {
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		builder.setType(EmbeddedDatabaseType.HSQL);
		builder.addScript("classpath:/ddlTestdb.sql");
		return builder.build();
	}

	public JdbcOperations template(DataSource dataSource) {
		return new JdbcTemplate(dataSource, true);
	}
}
