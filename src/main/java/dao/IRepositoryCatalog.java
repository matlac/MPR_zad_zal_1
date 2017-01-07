package dao;

import java.sql.SQLException;

public interface IRepositoryCatalog {

	public IUserRepository users();
	public IEnumerationValueRepository enumerationValues();
	
	public void saveAndClose() throws SQLException;
	
}
