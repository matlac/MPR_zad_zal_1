package dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

import dao.mappers.IMapResultSetToEntity;
import dao.uow.IUnitOfWork;
import domain.User;

public class UserRepositoryTest extends Mockito {

	@Test
	public void get_by_id_test() throws SQLException {
		
		User u = new User();
		u.setLogin("admin");
		u.setPassword("tajnehaslo");
		u.setId(1);
		
		Connection connection = mock(Connection.class);
		IMapResultSetToEntity<User> mapper = mock(IMapResultSetToEntity.class);
		IUnitOfWork uow = mock(IUnitOfWork.class);
		DatabaseMetaData metadata = mock(DatabaseMetaData.class);
		PreparedStatement preparedStatement = mock(PreparedStatement.class);
		
		when(preparedStatement.executeQuery()).thenReturn(mock(ResultSet.class));
		when(metadata.getTables(null, null, null, null)).thenReturn(mock(ResultSet.class));
		when(connection.createStatement()).thenReturn(mock(Statement.class));
		when(connection.getMetaData()).thenReturn(metadata);
		when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
		when(mapper.map(any(ResultSet.class))).thenReturn(u);
		
		UserRepository sut = new UserRepository(connection, mapper, uow);
		
		User result = sut.get(1);

		assertEquals("admin", result.getLogin());
		assertEquals("tajnehaslo", result.getPassword());
		assertEquals(1, result.getId());		
	}
	
	@Test
	public void get_all_test() throws SQLException {
		
		User u = new User();
		u.setLogin("admin");
		u.setPassword("tajnehaslo");
		u.setId(1);
		
		User u2 = new User();
		u2.setLogin("admin2");
		u2.setPassword("tajnehaslo");
		u2.setId(2);
		
		Connection connection = mock(Connection.class);
		IMapResultSetToEntity<User> mapper = mock(IMapResultSetToEntity.class);
		IUnitOfWork uow = mock(IUnitOfWork.class);
		DatabaseMetaData metadata = mock(DatabaseMetaData.class);
		PreparedStatement preparedStatement = mock(PreparedStatement.class);
		ResultSet rs = mock(ResultSet.class);
		
		when(preparedStatement.executeQuery()).thenReturn(rs);
		when(metadata.getTables(null, null, null, null)).thenReturn(mock(ResultSet.class));
		when(connection.createStatement()).thenReturn(mock(Statement.class));
		when(connection.getMetaData()).thenReturn(metadata);
		when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
		when(rs.next()).thenReturn(true).thenReturn(true).thenReturn(false);
		when(mapper.map(rs)).thenReturn(u).thenReturn(u2);		
		
		UserRepository sut = new UserRepository(connection, mapper, uow);
		
		List<User> result = sut.getAll();	
		
		assertEquals(2, result.size());
		assertEquals("admin", result.get(0).getLogin());
		assertEquals("tajnehaslo", result.get(0).getPassword());
		assertEquals(1, result.get(0).getId());
		assertEquals("admin2", result.get(1).getLogin());
		assertEquals("tajnehaslo", result.get(1).getPassword());
		assertEquals(2, result.get(1).getId());
	}	
}
