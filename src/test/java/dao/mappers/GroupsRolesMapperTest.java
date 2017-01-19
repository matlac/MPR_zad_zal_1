package dao.mappers;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;
import org.mockito.Mockito;

import domain.GroupsRoles;

public class GroupsRolesMapperTest extends Mockito {

	@Test
	public void mapTest() throws SQLException {

		GroupsRolesMapper sut = new GroupsRolesMapper();
		ResultSet rs = mock(ResultSet.class);
		
		when(rs.getInt("roleId")).thenReturn(3);
		when(rs.getInt("groupId")).thenReturn(2);
		when(rs.getInt("id")).thenReturn(5);
		
		GroupsRoles gr = sut.map(rs);

		assertEquals(3, gr.getRoleId());
		assertEquals(2, gr.getGroupId());
		assertEquals(5, gr.getId());
		
	}

}
