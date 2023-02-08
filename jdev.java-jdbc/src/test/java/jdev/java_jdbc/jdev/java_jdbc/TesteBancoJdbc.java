package jdev.java_jdbc.jdev.java_jdbc;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import dao.UserPosDAO;
import model.UserPosJava;

public class TesteBancoJdbc {

	@Test
	public void initBanco() {
		//SingleConnection.getConnection();
		UserPosDAO userPosDAO = new UserPosDAO();
		UserPosJava userposjava = new UserPosJava();
		
		userposjava.setId(6L);
		userposjava.setNome("miakalifa");
		userposjava.setEmail("mia@xvideos.com");
		
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
	
	
	
	
	
	
	
}
