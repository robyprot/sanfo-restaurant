package model.daily;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.ManagerDailyInterface;

import database.Database;
import database.JavaDBException;


// TODO: Auto-generated Javadoc
/**
 * Classe che crea la lista di Waiter e che contiene i metodi per gestirla.
 * 
 * @author Mauro
 */
public class WaiterMngDaily implements ManagerDailyInterface {

	/** The instance. */
	private static WaiterMngDaily instance = null;
	
	/** The waiter list. */
	public ArrayList<Waiter> waiterList;


	/**
	 * Istanzia un nuovo Manager Cameriere Daily.
	 *
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the sQL exception
	 * @throws JavaDBException the java db exception
	 */
	private WaiterMngDaily() throws ClassNotFoundException, SQLException, JavaDBException {
		init();
	}

	
	/**
	 * Inizializza.
	 *
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the sQL exception
	 * @throws JavaDBException the java db exception
	 */
	public void init() throws ClassNotFoundException, SQLException, JavaDBException {
		Database.connect();
		String query = "SELECT * FROM APP.Waiter";
		waiterList = new ArrayList<Waiter>();
		
		ResultSet res = Database.interrogate(query);
		while (res.next()) {
			waiterList.add(new Waiter(res.getInt("I"),
									  res.getInt("Numb"), 
									  res.getString("Surname"), 
								   	  res.getString("Name")));
		}
		res.close();
		System.out.println("List Waiters OK");
		Database.disconnect();
	}

	
	/**
	 * Ritorna l'istanza.
	 *
	 * @return l'istanza
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the sQL exception
	 * @throws JavaDBException the java db exception
	 */
	public static WaiterMngDaily getIstance() throws ClassNotFoundException,SQLException, JavaDBException {
		if (instance == null) {
			synchronized (WaiterMngDaily.class) {
				if (instance == null) {
					instance = new WaiterMngDaily();
				}
			}
		}
		return instance;
	}
	
	
	/**
	 * Ritorna la lista dei camerieri.
	 *
	 * @return la lista dei camerieri
	 */
	public ArrayList<Waiter> getWaiterList() {
		return waiterList;
	}
	
	
	/**
	 * Setta la lista dei camerieri.
	 *
	 * @param waiterList la nuova lista dei camerieri
	 */
	public void setWaiterList(ArrayList<Waiter> waiterList) {
		this.waiterList = waiterList;
	}
		
}
