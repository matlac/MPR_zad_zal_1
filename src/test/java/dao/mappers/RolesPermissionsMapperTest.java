package dao.mappers;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;
import org.mockito.Mockito;

import domain.RolesPermissions;

public class RolesPermissionsMapperTest extends Mockito {

	@Test
	public void mapTest() throws SQLException {

		RolesPermissionsMapper sut = new RolesPermissionsMapper();
		ResultSet rs = mock(ResultSet.class);
		
		when(rs.getString("permName")).thenReturn("insert");
		when(rs.getInt("permType")).thenReturn(1);
		when(rs.getInt("id")).thenReturn(1);
		
		RolesPermissions rp = sut.map(rs);

		assertEquals("insert", rp.getPermName());
		assertEquals(1, rp.getPermType());
		assertEquals(1, rp.getId());
		
	}

}
