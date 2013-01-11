package model.daily;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.ManagerDailyInterface;

import database.Database;
import database.JavaDBException;


// TODO: Auto-generated Javadoc
/**
 * Classe che crea la lista di Single Order e che contiene i metodi per gestirla.
 * 
 * @author Mauro
 */
public class SingleOrderMngDaily implements ManagerDailyInterface {

	/** The instance. */
	private static SingleOrderMngDaily instance = null;
	
	/** The single order list. */
	public ArrayList<SingleOrder> singleOrderList;
	
	
	/**
	 * Istanzia un nuovo Manager SingleOrder Daily.
	 *
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the sQL exception
	 * @throws JavaDBException the java db exception
	 */
	private SingleOrderMngDaily() throws ClassNotFoundException, SQLException, JavaDBException {
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
		String query = "SELECT * FROM APP.SingleOrder";
		singleOrderList = new ArrayList<SingleOrder>();
		
		ResultSet res = Database.interrogate(query);
		while (res.next()) {
			singleOrderList.add(new SingleOrder(res.getInt("I"), 
												res.getInt("OrdNumb"), 
												res.getInt("Quantity"), 
												res.getString("Dish"),
												res.getString("State"),
												res.getString("Notes")));
		}
		res.close();
		System.out.println("List SingleOrder OK");
		Database.disconnect();
		
		singleOrderList.clear();    // ArrayList singole ordinazioni lo svuoto
	}
	
	
	/**
	 * Ritorna l'istanza.
	 *
	 * @return l'istanza
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the sQL exception
	 * @throws JavaDBException the java db exception
	 */
	public static SingleOrderMngDaily getIstance() throws ClassNotFoundException,SQLException, JavaDBException {
		if (instance == null) {
			synchronized (SingleOrderMngDaily.class) {
				if (instance == null) {
					instance = new SingleOrderMngDaily();
				}
			}
		}
		return instance;
	}


	
	/**
	 * Ritorna la lista delle singole ordinazioni.
	 *
	 * @return la lista delle singole ordinazioni
	 */
	public ArrayList<SingleOrder> getSingleOrderList() {
		return singleOrderList;
	}


	/**
	 * Setta la lista delle singole ordinazioni.
	 *
	 * @param singleOrderList la nuova lista delle singole ordinazioni
	 */
	public void setSingleOrderList(ArrayList<SingleOrder> singleOrderList) {
		this.singleOrderList = singleOrderList;
	}


	
	/**
	 * Ritorna le singole ordinazioni dato il numero di ordinazione.
	 *
	 * @param ordNumb il numero dell'ordinazione
	 * @return le singole ordinazioni
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the sQL exception
	 */
	public ArrayList<SingleOrder> getSingleOrders(int ordNumb) throws ClassNotFoundException, SQLException{
		ArrayList<SingleOrder> single = new ArrayList<SingleOrder>();
		for(int i=0; i<singleOrderList.size(); i++){		
			SingleOrder s = singleOrderList.get(i);
			if(s.getOrdNumb()==ordNumb){
				single.add(s);
			}
		}
		return single;
	}
	
	
	
	/**
	 * Ritorna true se tutte le singole ordinazioni di un tavolo sono complete.
	 *
	 * @param numbOrd il numero dell'ordinazione
	 * @return true, se tutte le singole ordinazioni sono complete
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the sQL exception
	 */
	public boolean isComplete(int numbOrd) throws ClassNotFoundException, SQLException{
		ArrayList<SingleOrder> single = new ArrayList<SingleOrder>();
		for(int i=0; i<singleOrderList.size(); i++){		
			SingleOrder s = singleOrderList.get(i);
			if(s.getState().equalsIgnoreCase("DaCompletare") && s.getOrdNumb()==numbOrd){		
				single.add(s);										// contiene le incomplete
			}
		}
		if (single.size()==0) return true;
		else return false;
	}
	
	
	
	/**
	 * Ritorna tutte le singole ordinazioni incomplete dato il numero di ordinazione.
	 *
	 * @param ordNumb il numero di ordinazione
	 * @return le singole ordinazioni incomplete
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the sQL exception
	 */
	public ArrayList<SingleOrder> getIncompleteSingleOrders(int ordNumb) throws ClassNotFoundException, SQLException{
		ArrayList<SingleOrder> single = new ArrayList<SingleOrder>();
		for(int i=0; i<singleOrderList.size(); i++){		
			SingleOrder s = singleOrderList.get(i);
			if(s.getState().equalsIgnoreCase("DaCompletare") && s.getOrdNumb()==ordNumb){
				single.add(s);
			}
		}
		return single;
	}

	
	
	/**
	 * Data un identificativo I, setta la singola ordinazione come Servita.
	 *
	 * @param a l'identificativo
	 */
	public void setCompleteSingleOrder(int a){
		for(int i=0; i<singleOrderList.size(); i++){
			SingleOrder s = singleOrderList.get(i);
			if(s.getA()==a){
				s.setState("Servita");
				singleOrderList.set(i, s);
			}
		}
	}
	
	
	
	/**
	 * Data un identificativo I, rimuove la singola ordinazione.
	 *
	 * @param a l'identificativo
	 */
	public void removeSingleOrder(int a){
		for(int i=0; i<singleOrderList.size(); i++){
			SingleOrder s = singleOrderList.get(i);
			if(s.getA()==a){
				singleOrderList.remove(i);
			}
		}
	}
	

}
