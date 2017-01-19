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
import domain.RolesPermissions;

public class RolesPermissionRepositoryTest extends Mockito {

	@Test
	public void get_by_id_test() throws SQLException {
		
		RolesPermissions u = new RolesPermissions();
		u.setPermName("insert");
		u.setPermType(1);
		u.setId(1);
		
		Connection connection = mock(Connection.class);
		IMapResultSetToEntity<RolesPermissions> mapper = mock(IMapResultSetToEntity.class);
		IUnitOfWork uow = mock(IUnitOfWork.class);
		DatabaseMetaData metadata = mock(DatabaseMetaData.class);
		PreparedStatement preparedStatement = mock(PreparedStatement.class);
		
		when(preparedStatement.executeQuery()).thenReturn(mock(ResultSet.class));
		when(metadata.getTables(null, null, null, null)).thenReturn(mock(ResultSet.class));
		when(connection.createStatement()).thenReturn(mock(Statement.class));
		when(connection.getMetaData()).thenReturn(metadata);
		when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
		when(mapper.map(any(ResultSet.class))).thenReturn(u);
		
		RolesPermissionsRepository sut = new RolesPermissionsRepository(connection, mapper, uow);
		
		RolesPermissions result = sut.get(1);

		assertEquals("insert", result.getPermName());
		assertEquals(1, result.getPermType());
		assertEquals(1, result.getId());		
	}
	
	@Test
	public void get_all_test() throws SQLException {
		
		RolesPermissions u = new RolesPermissions();
		u.setPermName("insert");
		u.setPermType(1);
		u.setId(1);
		
		RolesPermissions u2 = new RolesPermissions();
		u2.setPermName("update");
		u2.setPermType(0);
		u2.setId(2);
		
		Connection connection = mock(Connection.class);
		IMapResultSetToEntity<RolesPermissions> mapper = mock(IMapResultSetToEntity.class);
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
		
		RolesPermissionsRepository sut = new RolesPermissionsRepository(connection, mapper, uow);
		
		List<RolesPermissions> result = sut.getAll();	
		
		assertEquals(2, result.size());
		assertEquals("insert", result.get(0).getPermName());
		assertEquals(1, result.get(0).getPermType());
		assertEquals(1, result.get(0).getId());
		assertEquals("update", result.get(1).getPermName());
		assertEquals(0, result.get(1).getPermType());
		assertEquals(2, result.get(1).getId());
	}	
}
