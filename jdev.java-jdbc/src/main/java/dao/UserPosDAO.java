package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import conexaoJdbc.SingleConnection;
import model.UserPosJava;

public class UserPosDAO {

	private Connection connection;
	
	public UserPosDAO() {
		connection = SingleConnection.getConnection();
	}
	
	public void salvar(UserPosJava userposjava) {
		//Instanciar um PreparedStatement - Realizar um insert, toda a estrutura para insert
		try {
			String sql = "insert into userposjava (id, nome, email) values(?,?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			//Passar os parametros
			insert.setLong(1, userposjava.getId());
			insert.setString(2, userposjava.getNome());
			insert.setString(3, userposjava.getEmail());
			insert.execute();
			connection.commit();//Salavar no banco
			
		} catch (Exception e) {
			try {
				connection.rollback(); //Reverter a operação do banco
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
			e.printStackTrace();
		}
	}
}
