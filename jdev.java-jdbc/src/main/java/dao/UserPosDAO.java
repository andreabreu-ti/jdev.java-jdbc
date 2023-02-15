package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexaoJdbc.SingleConnection;
import model.BeanUserFone;
import model.Telefone;
import model.UserPosJava;

public class UserPosDAO {

	private Connection connection;

	public UserPosDAO() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(UserPosJava userposjava) {
		// Instanciar um PreparedStatement - Realizar um insert, toda a estrutura para
		// insert
		try {
			String sql = "insert into userposjava (nome, email) values(?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			// Passar os parametros
			insert.setString(1, userposjava.getNome());
			insert.setString(2, userposjava.getEmail());
			insert.execute();
			connection.commit();// Salavar no banco

		} catch (Exception e) {
			try {
				connection.rollback(); // Reverter a operação do banco
			} catch (Exception e2) {
				e2.printStackTrace();
			}

			e.printStackTrace();
		}
	}

	public void salvarTelefone(Telefone telefone) {
		try {

			String sql = "INSERT INTO public.telefoneuser(numero, tipo, usuariopessoa) VALUES (?, ?, ?);";
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, telefone.getNumero());
			statement.setString(2, telefone.getTipo());
			statement.setLong(3, telefone.getUsuario());
			statement.execute();
			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public List<UserPosJava> listar() throws Exception {
		List<UserPosJava> list = new ArrayList<UserPosJava>();
		String sql = "select * from userposjava";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();

		while (resultado.next()) {
			UserPosJava userPosJava = new UserPosJava();
			userPosJava.setId(resultado.getLong("id"));
			userPosJava.setNome(resultado.getString("nome"));
			userPosJava.setEmail(resultado.getString("email"));

			list.add(userPosJava);
		}

		return list;
	}

	public List<BeanUserFone> listaUserFone(Long idUser) {
		List<BeanUserFone> beanUserFones = new ArrayList<BeanUserFone>();

		String sql = "select u.nome, t.numero, u.email from telefoneuser t ";
		sql += "inner join userposjava u on ";
		sql += "t.usuariopessoa = u.id ";
		sql += "where u.id = " + idUser;

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				BeanUserFone userFone = new BeanUserFone();
				userFone.setEmail(resultSet.getString("email"));
				userFone.setNome(resultSet.getString("nome"));
				userFone.setNumero(resultSet.getString("numero"));
				beanUserFones.add(userFone);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return beanUserFones;
	}

	public UserPosJava buscar(Long id) throws Exception {
		UserPosJava retorno = new UserPosJava();
		String sql = "select * from userposjava where id =" + id;
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();

		while (resultado.next()) { // Retorna apenas um ou nenhum
			retorno.setId(resultado.getLong("id"));
			retorno.setNome(resultado.getString("nome"));
			retorno.setEmail(resultado.getString("email"));

		}
		return retorno;
	}

	public void atualizar(UserPosJava userposjava) {
		try {
			String sql = "update userposjava set nome = ? where id = " + userposjava.getId();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, userposjava.getNome());

			statement.execute();
			connection.commit();

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public void deletar(Long id) {
		try {

			String sql = "delete from userposjava where id=" + id;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.execute();
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public void deleteFonesPorUser(Long idUser) {
		
		try {
			String sqlFone = "delete from telefoneuser where usuariopessoa = "+idUser;
			String sqlUser = "delete from userposjava where id = "+idUser;
			
			PreparedStatement preparedStatement = connection.prepareStatement(sqlFone);
			preparedStatement.executeUpdate();
			connection.commit();
			
			preparedStatement = connection.prepareStatement(sqlUser);
			preparedStatement.executeUpdate();
			connection.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		
	}
}
