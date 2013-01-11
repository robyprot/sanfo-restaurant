package model.daily;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import model.ManagerDailyInterface;

import database.Database;
import database.JavaDBException;


// TODO: Auto-generated Javadoc
/**
 * Classe che crea la lista di Tables Check e che contiene i metodi per gestirla.
 * 
 * @author Mauro
 */
public class TablesCheckMngDaily implements ManagerDailyInterface {

	/** The instance. */
	private static TablesCheckMngDaily instance = null;
	
	/** The tables check list. */
	public ArrayList<TablesCheck> tablesCheckList;
	
	
	/**
	 * Istanzia un nuovo Manager TablesCheck Daily.
	 *
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the sQL exception
	 * @throws JavaDBException the java db exception
	 */
	private TablesCheckMngDaily() throws ClassNotFoundException, SQLException, JavaDBException {
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
		String query = "SELECT * FROM APP.TablesCheck";
		tablesCheckList = new ArrayList<TablesCheck>();
		
		ResultSet res = Database.interrogate(query);
		while (res.next()) {
			Date d = res.getDate("Date");
			Time t = res.getTime("Time");
			GregorianCalendar greg = new GregorianCalendar();
			greg.setTime(d);
			greg.set(Calendar.HOUR, t.getHours());
			greg.set(Calendar.MINUTE, t.getMinutes());
			greg.set(Calendar.SECOND, t.getSeconds());
			tablesCheckList.add(new TablesCheck(res.getInt("I"),
												greg,
												res.getFloat("Amount"), 
												res.getInt("PeopleNumb")));
		}
		res.close();
		System.out.println("List TablesCheck OK");
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
	public static TablesCheckMngDaily getIstance() throws ClassNotFoundException,SQLException, JavaDBException {
		if (instance == null) {
			synchronized (TablesCheckMngDaily.class) {
				if (instance == null) {
					instance = new TablesCheckMngDaily();
				}
			}
		}
		return instance;
	}
	
	
	
	/**
	 * Ritorna la lista dei conti tavoli.
	 *
	 * @return la lista dei conti tavoli
	 */
	public ArrayList<TablesCheck> getTablesCheckList() {
		return tablesCheckList;
	}


	/**
	 * Setta la lista dei conti tavoli.
	 *
	 * @param tablesCheckList la lista dei conti tavoli
	 */
	public void setTablesCheckList(ArrayList<TablesCheck> tablesCheckList) {
		this.tablesCheckList = tablesCheckList;
	}


	//////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	
	
	/**
	 * Ritorna i conti di un certo giorno (ora non importa).
	 *
	 * @param gr il giorni
	 * @return i conti di quel giorno
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the sQL exception
	 */
	
	public ArrayList<TablesCheck> getTablesCheckOnDate(GregorianCalendar gr) throws ClassNotFoundException, SQLException{
		ArrayList<TablesCheck> check = new ArrayList<TablesCheck>();
		for(int i=0; i<tablesCheckList.size(); i++){		
			TablesCheck r = tablesCheckList.get(i);
			if(r.getCalend().get(Calendar.YEAR)==gr.get(Calendar.YEAR)
					&& r.getCalend().get(Calendar.MONTH)==gr.get(Calendar.MONTH)	
					&& r.getCalend().get(Calendar.DAY_OF_MONTH)==gr.get(Calendar.DAY_OF_MONTH)){
				check.add(r);
			}
		}
		return check;
	}
	
	
}






