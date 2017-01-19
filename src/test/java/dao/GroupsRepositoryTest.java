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
import domain.Groups;

public class GroupsRepositoryTest extends Mockito {

	@Test
	public void get_by_id_test() throws SQLException {
		
		Groups u = new Groups();
		u.setName("admini");
		u.setId(1);
		
		Connection connection = mock(Connection.class);
		IMapResultSetToEntity<Groups> mapper = mock(IMapResultSetToEntity.class);
		IUnitOfWork uow = mock(IUnitOfWork.class);
		DatabaseMetaData metadata = mock(DatabaseMetaData.class);
		PreparedStatement preparedStatement = mock(PreparedStatement.class);
		
		when(preparedStatement.executeQuery()).thenReturn(mock(ResultSet.class));
		when(metadata.getTables(null, null, null, null)).thenReturn(mock(ResultSet.class));
		when(connection.createStatement()).thenReturn(mock(Statement.class));
		when(connection.getMetaData()).thenReturn(metadata);
		when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
		when(mapper.map(any(ResultSet.class))).thenReturn(u);
		
		GroupsRepository sut = new GroupsRepository(connection, mapper, uow);
		
		Groups result = sut.get(1);

		assertEquals("admini", result.getName());
		assertEquals(1, result.getId());		
	}
	
	@Test
	public void get_all_test() throws SQLException {
		
		Groups u = new Groups();
		u.setName("admini");
		u.setId(1);
		
		Groups u2 = new Groups();
		u2.setName("userzy");
		u2.setId(2);
		
		Connection connection = mock(Connection.class);
		IMapResultSetToEntity<Groups> mapper = mock(IMapResultSetToEntity.class);
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
		
		GroupsRepository sut = new GroupsRepository(connection, mapper, uow);
		
		List<Groups> result = sut.getAll();	
		
		assertEquals(2, result.size());
		assertEquals("admini", result.get(0).getName());
		assertEquals(1, result.get(0).getId());
		assertEquals("userzy", result.get(1).getName());
		assertEquals(2, result.get(1).getId());
	}	
}
