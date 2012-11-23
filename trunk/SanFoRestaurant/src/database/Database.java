package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	
	public static String driver = null;
	public static Connection con = null;
	public static ResultSet res = null;
	public static Statement cmd = null;
	public static String query = null;
	static boolean a;

	
	// Connessione al DB
	public static void connect() throws JavaDBException{
		// Load the driver derby
		driver = "org.apache.derby.jdbc.EmbeddedDriver";
		try {
			Class.forName(driver);
		}
		catch(Exception e){
			throw new JavaDBException(1);
		}
		// Create the connection string
		String url = "jdbc:derby:MyDB;create=true";
		// Get a connection
		try{
			con = DriverManager.getConnection(url);
			System.out.println("Connection done");
		}
		catch(SQLException e){
			throw new JavaDBException(2);
		}
	}
	
	
	
	public static ResultSet interrogate(String qry) throws JavaDBException{
		a = true;
		// Create a Statement object to query the db
		try {
			cmd = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		} catch (SQLException e) {
			throw new JavaDBException(3);
		}
		// Query and save the results in a ResultSet object
		try {
			res = cmd.executeQuery(qry);
		} catch (SQLException e) {
			throw new JavaDBException(4);	
		}
		return res;
	}
	
	
	
	// Insert a tuple into a table
	public static void insert(String qry) throws JavaDBException {
		a = false;
		try {
			cmd = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		} catch (SQLException e) {
			throw new JavaDBException(3);
		}
		try {
			cmd.executeUpdate(qry);
		} catch (SQLException e) {
			throw new JavaDBException(5);
		}
	}
	
	
	// Update table
	public static void update(String qry) throws JavaDBException {
		a = false;
		try {
			cmd = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		} catch (SQLException e) {
			throw new JavaDBException(3);
		}
		try {
			cmd.executeUpdate(qry);
		} catch (SQLException e) {
			throw new JavaDBException(5);
		}
	}
	
	
	
	
	public static void disconnect() throws JavaDBException {
		if(a==true){		// if it is a query
			try {
				res.close();
			} 
			catch (SQLException e) {
				throw new JavaDBException(6);
			}
		} 
		try {
			cmd.close();
		} catch (SQLException e) {
			throw new JavaDBException(7);
		}
		try {
			con.close();
			System.out.println("Disconnection done");
		} catch (SQLException e) {
			throw new JavaDBException(8);
		}
	}
	
	
	
}
