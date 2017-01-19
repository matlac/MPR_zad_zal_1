package dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import domain.Groups;
import domain.GroupsRoles;
import domain.User;

public class GroupsRolesCRUDTest {

	@Test
	public void testAdd() throws SQLException {
		Connection connection = DriverManager.getConnection(""
				+ "jdbc:hsqldb:hsql://localhost/workdb");
		
		IRepositoryCatalog catalogOf = new RepositoryCatalog(connection);
		GroupsRoles g = new GroupsRoles();
		g.setRoleId(1);
		g.setGroupId(1);
		catalogOf.groupsRoles().add(g);
		
		catalogOf.save();
		
		List<GroupsRoles> groupsRoles = catalogOf.groupsRoles().getAll();
		
		groupsRoles.get(0).setRoleId(2);
		groupsRoles.get(0).setGroupId(3);
		catalogOf.groupsRoles().update(groupsRoles.get(0));
		
		GroupsRoles groupsRoles_from_db = catalogOf.groupsRoles().get(groupsRoles.get(0).getId());
		
		catalogOf.groupsRoles().delete(groupsRoles_from_db);
		
		catalogOf.saveAndClose();				
	}

}
