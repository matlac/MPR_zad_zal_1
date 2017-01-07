package dao.mappers;

import domain.EnumerationValue;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EnumerationValueMapper implements IMapResultSetToEntity<EnumerationValue>{

	public EnumerationValue map(ResultSet rs) throws SQLException {
		EnumerationValue e = new EnumerationValue();
			e.setId(rs.getInt("id"));
			e.setIntKey(rs.getInt("intKey"));
			e.setStringKey(rs.getString("stringKey"));
			e.setValue(rs.getString("value"));
			e.setName(rs.getString("name"));
		return e;
	}

}
