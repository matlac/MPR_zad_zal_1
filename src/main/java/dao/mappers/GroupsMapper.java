package dao.mappers;

import domain.Groups;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupsMapper implements IMapResultSetToEntity<Groups>{

	public Groups map(ResultSet rs) throws SQLException {
		Groups g = new Groups();
			g.setId(rs.getInt("id"));
			g.setName(rs.getString("name"));
		return g;
	}

}
