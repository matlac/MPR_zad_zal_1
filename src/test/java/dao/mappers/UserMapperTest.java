package dao.mappers;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;
import org.mockito.Mockito;

import domain.User;

public class UserMapperTest extends Mockito {

	@Test
	public void mapTest() throws SQLException {

		UserMapper sut = new UserMapper();
		ResultSet rs = mock(ResultSet.class);
		
		when(rs.getString("login")).thenReturn("admin");
		when(rs.getString("password")).thenReturn("tajne");
		when(rs.getInt("id")).thenReturn(1);
		
		User u = sut.map(rs);

		assertEquals("admin", u.getLogin());
		assertEquals("tajne", u.getPassword());
		assertEquals(1, u.getId());
		
	}

}
