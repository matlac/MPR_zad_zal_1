package dao;

import dao.mappers.IMapResultSetToEntity;
import dao.uow.IUnitOfWork;
import domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserRepository extends RepositoryBase<User> implements IUserRepository{

	protected PreparedStatement withLogin;
	protected PreparedStatement withLoginAndPassword;

	public UserRepository(Connection connection,
						  IMapResultSetToEntity<User> mapper,
						  IUnitOfWork uow) {

		super(connection,mapper, uow);
		try {
			withLogin = connection.prepareStatement("SELECT * FROM user WHERE login = ?");
			withLoginAndPassword = connection.prepareStatement("SELECT * FROM user WHERE login = ? and password = ?");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setUpdateQuery(User u) throws SQLException {
		update.setString(1, u.getLogin());
		update.setString(2, u.getPassword());
		update.setInt(3, u.getId());
	}

	public void setInsertQuery(User u) throws SQLException {
		insert.setString(1, u.getLogin());
		insert.setString(2, u.getPassword());
	}

	@Override
	protected String tableName() {
		return "user";
	}

	protected String createTableSql() {
		return "CREATE TABLE user("
				+ "id bigint GENERATED BY DEFAULT AS IDENTITY,"
				+ "login VARCHAR(20)," + "password VARCHAR(50)"
				+ ")";
	}

	protected String insertSql() {
		return "INSERT INTO user(login, password) VALUES (?,?)";
	}

	protected String updateSql() {
		return "UPDATE user SET login = ?, password = ? WHERE id = ?";
	}

	public User withLogin(String login) {
		try {
			withLogin.setString(1, login);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return getOne(withLogin);
	}

	public User withLoginAndPassword(String login, String password) {
		try {
			withLoginAndPassword.setString(1, login);
			withLoginAndPassword.setString(2, password);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return getOne(withLoginAndPassword);
	}

	public void setupPermissions(User user) {

	}
}
