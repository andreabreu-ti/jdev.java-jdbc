package jdev.java_jdbc.jdev.java_jdbc;

import org.junit.Test;

import conexaoJdbc.SingleConnection;

public class TesteBancoJdbc {

	@Test
	public void initBanco() {
		SingleConnection.getConnection();
	}
}
