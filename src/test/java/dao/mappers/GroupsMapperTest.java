package dao.mappers;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;
import org.mockito.Mockito;

import domain.Groups;

public class GroupsMapperTest extends Mockito {

	@Test
	public void mapTest() throws SQLException {

		GroupsMapper sut = new GroupsMapper();
		ResultSet rs = mock(ResultSet.class);
		
		when(rs.getString("name")).thenReturn("administratorzy");
		when(rs.getInt("id")).thenReturn(1);
		
		Groups g = sut.map(rs);

		assertEquals("administratorzy", g.getName());
		assertEquals(1, g.getId());
		
	}

}
