package com.jdc.mkt.test;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.jdc.mkt.config.ApplicationConfig;

@SpringJUnitConfig(classes = ApplicationConfig.class)
@TestMethodOrder(OrderAnnotation.class)
public class CategoryTest {

	@Autowired
	private JdbcOperations jdbc;

	@Test
	@Order(1)
	void test1(@Value("${c.insert}") String sql) {
		List<Object[]> list = new ArrayList<Object[]>();
		list.add(new Object[] { "Foods" });
		list.add(new Object[] { "Drinks" });
		list.add(new Object[] { "Snacks" });

		jdbc.batchUpdate(sql, list);
	}

	@Test
	@Order(2)
	@DisplayName("2. generate id")
	void test2(@Value("${c.insert}") String sql) {
		var factory = new PreparedStatementCreatorFactory(sql, Types.VARCHAR);
		var creator = factory.newPreparedStatementCreator(List.of("Other"));
		var key = new GeneratedKeyHolder();
		jdbc.update(creator, key);

		var id = key.getKey().intValue();
		System.out.println(id);

	}
}
