package dao.uow;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;
import org.mockito.Mockito;

import domain.Entity;
import domain.Entity.EntityState;

public class UnitOfWorkTest extends Mockito{

	@Test
	public void save_changes_test() throws SQLException {
		
		Connection connection = mock(Connection.class);
		
		UnitOfWork sut = new UnitOfWork(connection);
		
		sut.saveChanges();
		
		verify(connection).commit();
	}
	
	@Test
	public void undo_test() throws SQLException {
		
		Connection connection = mock(Connection.class);		
		UnitOfWork sut = new UnitOfWork(connection);
		
		sut.undo();
		
		verify(connection).rollback();
	}
	
	@Test
	public void mark_as_new_test() throws SQLException {
		
		Connection connection = mock(Connection.class);	
		Entity entity = mock(Entity.class);
		IUnitOfWorkRepository repository = mock(IUnitOfWorkRepository.class);
		
		UnitOfWork sut = new UnitOfWork(connection);
		
		sut.markAsNew(entity, repository);
		
		verify(entity, times(1)).setState(EntityState.New);
	}
	
	@Test
	public void mark_as_deleted_test() throws SQLException {
		
		Connection connection = mock(Connection.class);	
		Entity entity = mock(Entity.class);
		IUnitOfWorkRepository repository = mock(IUnitOfWorkRepository.class);
		
		UnitOfWork sut = new UnitOfWork(connection);
		
		sut.markAsDeleted(entity, repository);
		
		verify(entity, times(1)).setState(EntityState.Deleted);
	}
	
	@Test
	public void mark_as_changed_test() throws SQLException {
		
		Connection connection = mock(Connection.class);	
		Entity entity = mock(Entity.class);
		IUnitOfWorkRepository repository = mock(IUnitOfWorkRepository.class);		
		
		UnitOfWork sut = new UnitOfWork(connection);
		
		sut.markAsChanged(entity, repository);
		
		verify(entity, times(1)).setState(EntityState.Modified);
	}

}
