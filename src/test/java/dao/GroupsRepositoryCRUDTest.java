package dao;

import static org.junit.Assert.*;

import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;

import domain.Groups;
import domain.User;

public class GroupsRepositoryCRUDTest {

	@Test
	public void test() throws SQLException {
		Connection connection = DriverManager.getConnection(""
				+ "jdbc:hsqldb:hsql://localhost/workdb");
		
		IRepositoryCatalog catalogOf = new RepositoryCatalog(connection);
		Groups g = new Groups();
		g.setName("admini");
		catalogOf.groups().add(g);
		
		catalogOf.save();
		
		List<Groups> groups = catalogOf.groups().getAll();
		
		groups.get(0).setName("admini_uwierzytelnieni");
		catalogOf.groups().update(groups.get(0));
		
		Groups group_from_db = catalogOf.groups().get(groups.get(0).getId());
		
		catalogOf.groups().delete(group_from_db);
		
		catalogOf.saveAndClose();		
	}

}
