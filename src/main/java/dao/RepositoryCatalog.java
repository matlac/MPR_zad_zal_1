package dao;

import java.sql.Connection;
import java.sql.SQLException;

import dao.mappers.UserMapper;
import dao.mappers.EnumerationValueMapper;
import dao.uow.IUnitOfWork;
import dao.uow.UnitOfWork;

public class RepositoryCatalog implements IRepositoryCatalog {

	IEnumerationValueRepository enumerationValueRepo;
	IUserRepository userRepo;

	IUnitOfWork uow;
	Connection connection;
	
	public RepositoryCatalog(Connection connection) throws SQLException {
		this.connection = connection;
		uow = new UnitOfWork(connection);

		userRepo = new UserRepository(connection, new UserMapper(), uow);
		enumerationValueRepo = new EnumerationValueRepository(connection, new EnumerationValueMapper(), uow);
	}

	public IUserRepository users() {
		return userRepo;
	}

	public IEnumerationValueRepository enumerationValues() {
		return enumerationValueRepo;
	}

	public void saveAndClose() throws SQLException {
		uow.saveChanges();
		connection.close();
		connection=null;
	}

}
