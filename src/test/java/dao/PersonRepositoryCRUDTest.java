package dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;

import domain.Person;

public class PersonRepositoryCRUDTest {

	@Test
	public void testAdd() throws SQLException {
		Connection connection = DriverManager.getConnection(""
				+ "jdbc:hsqldb:hsql://localhost/workdb");
		
		IRepositoryCatalog catalogOf = new RepositoryCatalog(connection);
		Person janek = new Person();
		janek.setName("janek");
		janek.setSurname("kowalski");
		janek.setAge(30);
		catalogOf.people().add(janek);
		catalogOf.saveAndClose();
		
	}

}
