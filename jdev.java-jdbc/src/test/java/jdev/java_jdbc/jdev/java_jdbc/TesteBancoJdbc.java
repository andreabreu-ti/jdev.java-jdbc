package jdev.java_jdbc.jdev.java_jdbc;

import java.util.List;

import org.junit.Test;

import dao.UserPosDAO;
import model.Telefone;
import model.UserPosJava;

public class TesteBancoJdbc {

	@Test
	public void initBanco() {
		//SingleConnection.getConnection();
		UserPosDAO userPosDAO = new UserPosDAO();
		UserPosJava userposjava = new UserPosJava();
		
		userposjava.setNome("Fulano de Tal");
		userposjava.setEmail("teste@teste.com");
		
		userPosDAO.salvar(userposjava);
	}
	
	@Test
	public void initListar() {
		UserPosDAO dao = new UserPosDAO();
		try {
			List<UserPosJava> list = dao.listar();
			for (UserPosJava userposjava : list) {
				System.out.println(userposjava);
				System.out.println("=======================================================");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void initBuscar() {
		UserPosDAO dao = new UserPosDAO();
		try {
			UserPosJava userposjava = dao.buscar(5L);
			System.out.println(userposjava);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void initAtualizar() {
		
		try {
			UserPosDAO dao = new UserPosDAO();
			UserPosJava objetoBanco = dao.buscar(2L);
			objetoBanco.setNome("Nome mudado");
			dao.atualizar(objetoBanco);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void initDeletar() {
		try {
			UserPosDAO dao = new UserPosDAO();
			
			dao.deletar(7L);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testeInsertTelefone() {
		Telefone telefone = new Telefone();
		telefone.setNumero("(92)99277-5876");
		telefone.setTipo("Casa");
		telefone.setUsuario(20L);
		
		UserPosDAO dao = new UserPosDAO();
		dao.salvarTelefone(telefone);
	}
	
	
	
}
