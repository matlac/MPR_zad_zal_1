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
import domain.GroupsRoles;

public class GroupsRolesRepositoryTest extends Mockito {

	@Test
	public void get_by_id_test() throws SQLException {
		
		GroupsRoles u = new GroupsRoles();
		u.setRoleId(2);
		u.setGroupId(3);
		u.setId(1);
		
		Connection connection = mock(Connection.class);
		IMapResultSetToEntity<GroupsRoles> mapper = mock(IMapResultSetToEntity.class);
		IUnitOfWork uow = mock(IUnitOfWork.class);
		DatabaseMetaData metadata = mock(DatabaseMetaData.class);
		PreparedStatement preparedStatement = mock(PreparedStatement.class);
		
		when(preparedStatement.executeQuery()).thenReturn(mock(ResultSet.class));
		when(metadata.getTables(null, null, null, null)).thenReturn(mock(ResultSet.class));
		when(connection.createStatement()).thenReturn(mock(Statement.class));
		when(connection.getMetaData()).thenReturn(metadata);
		when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
		when(mapper.map(any(ResultSet.class))).thenReturn(u);
		
		GroupsRolesRepository sut = new GroupsRolesRepository(connection, mapper, uow);
		
		GroupsRoles result = sut.get(1);

		assertEquals(2, result.getRoleId());
		assertEquals(3, result.getGroupId());
		assertEquals(1, result.getId());		
	}
	
	@Test
	public void get_all_test() throws SQLException {
		
		GroupsRoles u = new GroupsRoles();
		u.setRoleId(2);
		u.setGroupId(3);
		u.setId(1);
		
		GroupsRoles u2 = new GroupsRoles();
		u2.setRoleId(7);
		u2.setGroupId(8);
		u2.setId(2);
		
		Connection connection = mock(Connection.class);
		IMapResultSetToEntity<GroupsRoles> mapper = mock(IMapResultSetToEntity.class);
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
		
		GroupsRolesRepository sut = new GroupsRolesRepository(connection, mapper, uow);
		
		List<GroupsRoles> result = sut.getAll();	
		
		assertEquals(2, result.size());
		assertEquals(2, result.get(0).getRoleId());
		assertEquals(3, result.get(0).getGroupId());
		assertEquals(1, result.get(0).getId());
		assertEquals(7, result.get(1).getRoleId());
		assertEquals(8, result.get(1).getGroupId());
		assertEquals(2, result.get(1).getId());
	}	
}
