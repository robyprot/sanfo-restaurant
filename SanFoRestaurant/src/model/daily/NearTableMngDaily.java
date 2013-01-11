package model.daily;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.ManagerDailyInterface;

import database.Database;
import database.JavaDBException;


// TODO: Auto-generated Javadoc
/**
 * Classe che crea la lista di NearTable e che contiene i metodi per gestirla.
 * 
 * @author Mauro
 */
public class NearTableMngDaily implements ManagerDailyInterface {

	/** The instance. */
	private static NearTableMngDaily instance = null;
	
	/** The near table list. */
	public ArrayList<NearTable> nearTableList;

	// constructor
	/**
	 * Istanzia un nuovo Manager NearTable Daily.
	 *
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the sQL exception
	 * @throws JavaDBException the java db exception
	 */
	private NearTableMngDaily() throws ClassNotFoundException, SQLException, JavaDBException {
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
		String query = "SELECT * FROM APP.NearTable";
		nearTableList = new ArrayList<NearTable>();
		
		ResultSet res = Database.interrogate(query);
		while (res.next()) {
			int sumSeats = 0;
			nearTableList.add(new NearTable(res.getInt("I"),
											res.getInt("Table1"), 
											res.getInt("Table2"), 
											sumSeats));
		}
		res.close();
		System.out.println("List NearTable OK");
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
	public static NearTableMngDaily getIstance() throws ClassNotFoundException,SQLException, JavaDBException {
		if (instance == null) {
			synchronized (NearTableMngDaily.class) {
				if (instance == null) {
					instance = new NearTableMngDaily();
				}
			}
		}
		return instance;
	}
	
	
	/**
	 * Ritorna la lista dei tavoli vicini.
	 *
	 * @return la lista dei tavoli vicini
	 */
	public ArrayList<NearTable> getNearTableList() {
		return nearTableList;
	}


	/**
	 * Setta la lista dei tavoli vicini.
	 *
	 * @param nearTableList la nuova lista tavoli vicini
	 */
	public void setNearTableList(ArrayList<NearTable> nearTableList) {
		this.nearTableList = nearTableList;
	}
	
	
}
