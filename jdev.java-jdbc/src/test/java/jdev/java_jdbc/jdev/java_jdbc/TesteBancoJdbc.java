package jdev.java_jdbc.jdev.java_jdbc;

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
}
