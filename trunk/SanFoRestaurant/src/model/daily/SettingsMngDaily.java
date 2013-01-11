package model.daily;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.ManagerDailyInterface;

import database.Database;
import database.JavaDBException;

// TODO: Auto-generated Javadoc
/**
 * Classe che crea la lista di Settings e che contiene i metodi per gestirla.
 * 
 * @author Mauro
 */
public class SettingsMngDaily implements ManagerDailyInterface {

	/** The instance. */
	private static SettingsMngDaily instance = null;
	
	/** The settings list. */
	public ArrayList<Settings> settingsList;

	
	/**
	 * Istanzia un nuovo Manager Settings Daily.
	 *
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the sQL exception
	 * @throws JavaDBException the java db exception
	 */
	private SettingsMngDaily() throws ClassNotFoundException, SQLException, JavaDBException {
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
		String query = "SELECT * FROM APP.Settings";
		settingsList = new ArrayList<Settings>();
		
		ResultSet res = Database.interrogate(query);
		while (res.next()) {
			settingsList.add(new Settings(res.getInt("I"),
									  	  res.getString("NameParam"), 
								   	      res.getString("Value")));
		}
		res.close();
		System.out.println("List Settings OK");
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
	public static SettingsMngDaily getIstance() throws ClassNotFoundException,SQLException, JavaDBException {
		if (instance == null) {
			synchronized (SettingsMngDaily.class) {
				if (instance == null) {
					instance = new SettingsMngDaily();
				}
			}
		}
		return instance;
	}
	
	

	/**
	 * Ritorna la lista dei settaggi.
	 *
	 * @return la lista dei settaggi
	 */
	public ArrayList<Settings> getSettingsList() {
		return settingsList;
	}
	
	
	/**
	 * Setta la lista dei settings.
	 *
	 * @param settingsList the new settings list
	 */
	public void setSettingsList(ArrayList<Settings> settingsList) {
		this.settingsList = settingsList;
	}

}
