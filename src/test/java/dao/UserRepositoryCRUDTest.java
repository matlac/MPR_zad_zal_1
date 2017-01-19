package dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;

import domain.User;

public class UserRepositoryCRUDTest {

	@Test
	public void test() throws SQLException {
		Connection connection = DriverManager.getConnection(""
				+ "jdbc:hsqldb:hsql://localhost/workdb");
		
		IRepositoryCatalog catalogOf = new RepositoryCatalog(connection);
		User u = new User();
		u.setLogin("admin");
		u.setPassword("tajnehaslo");		
		catalogOf.users().add(u);
		
		User admin = catalogOf.users().withLogin("admin");
		
		admin.setLogin("admin2");
		catalogOf.users().update(admin);
		
		User admin2 = catalogOf.users().withLogin("admin2");
		catalogOf.users().delete(admin2);
		
		catalogOf.saveAndClose();		
	}

}
