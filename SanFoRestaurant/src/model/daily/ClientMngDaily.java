package model.daily;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.ManagerDailyInterface;


import database.Database;
import database.JavaDBException;


// TODO: Auto-generated Javadoc
/**
 * Classe che crea la lista di Client e che contiene i metodi per gestirla.
 * 
 * @author Mauro
 */
public class ClientMngDaily implements ManagerDailyInterface {

	/** The instance. */
	private static ClientMngDaily instance = null;
	
	/** The client list. */
	public ArrayList<Client> clientList;
	
	

	/**
	 * Istanzia un nuovo Manager Cliente Daily.
	 *
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the sQL exception
	 * @throws JavaDBException the java db exception
	 */
	private ClientMngDaily() throws ClassNotFoundException, SQLException, JavaDBException {
		init();
	}
	
	
	/**
	 * Crea la lista Clienti.
	 *
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the sQL exception
	 * @throws JavaDBException the java db exception
	 */
	public void init() throws ClassNotFoundException, SQLException, JavaDBException {
		Database.connect();
		String query = "SELECT * FROM APP.Client";
		clientList = new ArrayList<Client>();

		ResultSet res = Database.interrogate(query);
		while (res.next()) {
			clientList.add(new Client(res.getInt("I"),
									  res.getInt("ID"), 
									  res.getString("Name"), 
									  res.getString("Surname"), 
									  res.getInt("Counter"), 
									  res.getString("Allergy")));
		}
		res.close();
		System.out.println("List Clients OK");
		Database.disconnect();
	}

	
	/**
	 * Ritorna l'istanza.
	 *
	 * @return the istance
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the sQL exception
	 * @throws JavaDBException the java db exception
	 */
	public static ClientMngDaily getIstance() throws ClassNotFoundException,SQLException, JavaDBException {
		if (instance == null) {
			synchronized (ClientMngDaily.class) {
				if (instance == null) {
					instance = new ClientMngDaily();
				}
			}
		}
		return instance;
	}


	/**
	 * Ritorna la lista dei clienti.
	 *
	 * @return lista clienti
	 */
	public ArrayList<Client> getClientList() {
		return clientList;
	}


	/**
	 * Setta la lista Clienti.
	 *
	 * @param clientList the new client list
	 */
	public void setClientList(ArrayList<Client> clientList) {
		this.clientList = clientList;
	}

}