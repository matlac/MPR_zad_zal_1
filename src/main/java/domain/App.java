package domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import dao.IRepositoryCatalog;
import dao.RepositoryCatalog;
import dao.uow.IUnitOfWork;
import dao.uow.UnitOfWork;
import domain.User;

public class App 
{
    public static void main( String[] args )
    {
    	try {
			Connection connection = DriverManager.getConnection(""
					+ "jdbc:hsqldb:hsql://localhost/workdb");
			
			IRepositoryCatalog catalogOf = new RepositoryCatalog(connection);

			User admin = new User();
			admin.setLogin("admin");
			admin.setPassword("password");
			catalogOf.users().add(admin);
			catalogOf.saveAndClose();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
        System.out.println( "koniec!" );
    }
}
