package dao.mappers;

import domain.GroupsRoles;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupsRolesMapper implements IMapResultSetToEntity<GroupsRoles>{

	public GroupsRoles map(ResultSet rs) throws SQLException {
		GroupsRoles gr = new GroupsRoles();
			gr.setId(rs.getInt("id"));
			gr.setRoleId(rs.getInt("roleId"));
			gr.setGroupId(rs.getInt("groupId"));
		return gr;
	}

}
