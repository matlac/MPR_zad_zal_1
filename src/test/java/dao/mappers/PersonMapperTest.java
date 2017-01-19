package dao.mappers;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;
import org.mockito.Mockito;

import domain.Person;

public class PersonMapperTest extends Mockito {

	@Test
	public void mapTest() throws SQLException {

		PersonMapper sut = new PersonMapper();
		ResultSet rs = mock(ResultSet.class);
		
		when(rs.getString("name")).thenReturn("jan");
		when(rs.getString("surname")).thenReturn("kowalski");
		when(rs.getInt("id")).thenReturn(1);
		when(rs.getInt("age")).thenReturn(30);
		
		Person person = sut.map(rs);

		assertEquals("jan", person.getName());
		assertEquals("kowalski", person.getSurname());
		assertEquals(1, person.getId());
		assertEquals(30,person.getAge());
		
	}

}
