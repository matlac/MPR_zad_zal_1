package dao;

import java.sql.Connection;
import java.sql.SQLException;

import dao.mappers.*;
import dao.uow.IUnitOfWork;
import dao.uow.UnitOfWork;

public class RepositoryCatalog implements IRepositoryCatalog {

	IUserRepository userRepo;
	IGroupsRepository groupsRepo;
	IGroupRolesRepository groupsRolesRepo;
	IRolesPermissionsRepository rolesPermissionsRepo;

	IUnitOfWork uow;
	Connection connection;
	
	public RepositoryCatalog(Connection connection) throws SQLException {
		this.connection = connection;
		uow = new UnitOfWork(connection);

		userRepo = new UserRepository(connection, new UserMapper(), uow);
		groupsRepo = new GroupsRepository(connection, new GroupsMapper(), uow);
		groupsRolesRepo = new GroupsRolesRepository(connection, new GroupsRolesMapper(), uow);
		rolesPermissionsRepo = new RolesPermissionsRepository(connection, new RolesPermissionsMapper(), uow);
	}

	public IUserRepository users() {
		return userRepo;
	}
	public IGroupsRepository groups() {
		return groupsRepo;
	}
	public IGroupRolesRepository groupsRoles() {
		return groupsRolesRepo;
	}
	public IRolesPermissionsRepository rolesPermissions() { return rolesPermissionsRepo; }

	public void saveAndClose() throws SQLException {
		uow.saveChanges();
		connection.close();
		connection=null;
	}
	
	public void save() throws SQLException {
		uow.saveChanges();
	}

}
