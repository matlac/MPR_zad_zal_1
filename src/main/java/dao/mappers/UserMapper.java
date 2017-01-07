package dao.mappers;

import domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements IMapResultSetToEntity<User>{

	public User map(ResultSet rs) throws SQLException {
		User u = new User();
			u.setId(rs.getInt("id"));
			u.setLogin(rs.getString("login"));
			u.setPassword(rs.getString("password"));
		return u;
	}

}
