package dao.mappers;

import domain.RolesPermissions;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RolesPermissionsMapper implements IMapResultSetToEntity<RolesPermissions>{

	public RolesPermissions map(ResultSet rs) throws SQLException {
		RolesPermissions rp = new RolesPermissions();
			rp.setId(rs.getInt("id"));
			rp.setPermName(rs.getString("permName"));
			rp.setPermType(rs.getInt("permType"));
		return rp;
	}

}
