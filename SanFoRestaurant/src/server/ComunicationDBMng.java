package server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.Database;
import database.JavaDBException;

// TODO: Auto-generated Javadoc
/**
 * La classe ComunicationDBMng viene utilizzata per far comunicare il thread che gestisce
 * la connesione client-server, con il database
 * 
 * @author Marco
 */
public class ComunicationDBMng {

	/** The list update. */
	private List<String> listUpdate;
	
	/** The list new order. */
	public List<String> listNewOrder = new ArrayList<String>();
	
	/**
	 * Instantiates a new comunication db mng.
	 */
	public ComunicationDBMng(){
	}
	
	/**
	 * Check for update, controlla se ci sono aggiornamenti per un determinato client sulla lista tavoli
	 * e sul menà
	 *
	 * @param clientId the client id
	 * @return the list of update
	 * @throws JavaDBException the java db exception
	 * @throws SQLException the sQL exception
	 */
	public List<String> checkForUpdate(String clientId) throws JavaDBException, SQLException{
		listUpdate = new ArrayList<String>();
		boolean update = false;
		Database.connect();
		String qry = "select DBupdate from APP.USERS where Type = 'Waiter' and ID = '" +clientId+ "'";
		ResultSet res = Database.interrogate(qry);
		while(res.next()){
			update = res.getBoolean("DBupdate");
		}
		
		if(update){
			qry = "select * from APP.TABLER";
			res = Database.interrogate(qry);
			while(res.next()){
				listUpdate.add("table-" + res.getInt("TableNumb") + "-" + "sala" + res.getInt("Content") + "-" + res.getString("State"));
			}
			qry = "select * from APP.FOOD";
			res = Database.interrogate(qry);
			while(res.next()){
				listUpdate.add("menu-" + res.getString("Code") + "-" + res.getString("Name") + "-" + res.getString("Type") +
									"-" + res.getFloat("Price") + "-" + res.getString("Description"));
			}
			qry = "UPDATE APP.USERS set DBupdate = false where ID = '" + clientId + "'";
			Database.update(qry);
			Database.disconnect();
			return listUpdate;
		}else{
			Database.disconnect();
			return null;
		}
		
		
		
	}
	
}
