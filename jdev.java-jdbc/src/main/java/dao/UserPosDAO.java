package dao;

import java.sql.Connection;

import conexaoJdbc.SingleConnection;

public class UserPosDAO {

	private Connection connection;
	
	public UserPosDAO() {
		connection = SingleConnection.getConnection();
	}
}
