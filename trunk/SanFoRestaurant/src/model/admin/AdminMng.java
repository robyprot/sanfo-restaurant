package model.admin;

import java.sql.ResultSet;

import database.Database;
import database.JavaDBException;

public class AdminMng {

	private static AdminMng instance = null;

	// constructor
	private AdminMng(){}

	
	public static AdminMng getIstance() {
		if (instance == null) {
			synchronized (AdminMng.class) {
				if (instance == null) {
					instance = new AdminMng();
				}
			}
		}
		return instance;
	}
	
	

	// Ritorno tutti i clienti in un oggetto ResultSet
	public ResultSet getAllClients() throws JavaDBException{
		String qry = "SELECT * FROM APP.Client";
		ResultSet result = Database.interrogate(qry);
		return result;
	}

	
	// Ritorno tutti i cibi in un oggetto ResultSet
	public ResultSet getAllFood() throws JavaDBException{
		String qry = "SELECT * FROM APP.Food";
		ResultSet result = Database.interrogate(qry);
		return result;
	}
	
	
	// Ritorno tutte le sale in un oggetto ResultSet
	public ResultSet getAllRooms() throws JavaDBException{
		String qry = "SELECT * FROM APP.Room";
		ResultSet result = Database.interrogate(qry);
		return result;
	}
	
	
	// Ritorno tutti i camerieri in un oggetto ResultSet
	public ResultSet getAllWaiters() throws JavaDBException{
		String qry = "SELECT * FROM APP.Waiter";
		ResultSet result = Database.interrogate(qry);
		return result;
	}
	
	
	// Ritorno tutti i tavoli in un oggetto ResultSet
	public ResultSet getAllTables() throws JavaDBException{
		String qry = "SELECT * FROM APP.TableR";
		ResultSet result = Database.interrogate(qry);
		return result;
	}
	
	
	// Ritorno tutte le prenotazioni in un oggetto ResultSet
	public ResultSet getAllReservations() throws JavaDBException{
		String qry = "SELECT * FROM APP.Reservation";
		ResultSet result = Database.interrogate(qry);
		return result;
	}
	
	
	// Ritorno tutte le ordinazioni in un oggetto ResultSet
	public ResultSet getAllOrder() throws JavaDBException{
		String qry = "SELECT * FROM APP.OrderTable";
		ResultSet result = Database.interrogate(qry);
		return result;
	}
	
	
	// Ritorno tutte le singole ordinazioni in un oggetto ResultSet
	public ResultSet getAllSingleOrder() throws JavaDBException{
		String qry = "SELECT * FROM APP.SingleOrder";
		ResultSet result = Database.interrogate(qry);
		return result;
	}
	
	
	// Ritorno tutti i tavoli vicini in un oggetto ResultSet
	public ResultSet getAllNearTable() throws JavaDBException{
		String qry = "SELECT * FROM APP.NearTable";
		ResultSet result = Database.interrogate(qry);
		return result;
	}
	
	
	// Ritorno tutti i conti in un oggetto ResultSet
	public ResultSet getAllTablesCheck() throws JavaDBException{
		String qry = "SELECT * FROM APP.TablesCheck";
		ResultSet result = Database.interrogate(qry);
		return result;
	}
	
	
	// Ritorno tutti gli utenti login in un oggetto ResultSet
	public ResultSet getAllUsersLogin() throws JavaDBException{
		String qry = "SELECT * FROM APP.Users";
		ResultSet result = Database.interrogate(qry);
		return result;
	}
	
	
	// Ritorno tutti i camerieri (per inserirli nella tabella Users) in un oggetto ResultSet
	public ResultSet getWaitersForLogin() throws JavaDBException{
		String qry = "SELECT Numb, Name FROM APP.WAITER";
		ResultSet result = Database.interrogate(qry);
		return result;
	}
	
	
	

		

}
