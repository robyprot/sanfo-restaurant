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
 * Classe che crea la lista di Order e che contiene i metodi per gestirla.
 * 
 * @author Mauro
 */
public class OrderMngDaily implements ManagerDailyInterface {

	/** The instance. */
	private static OrderMngDaily instance = null;
	
	/** The order list. */
	public ArrayList<Order> orderList;
	
	/** The incremental order number */
	public static int numOrd = 1;			// contatore delle ordinazioni

	
	/**
	 * Istanzia un nuovo Manager Order Daily.
	 *
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the sQL exception
	 * @throws JavaDBException the java db exception
	 */
	private OrderMngDaily() throws ClassNotFoundException, SQLException, JavaDBException {
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
		orderList = new ArrayList<Order>();
		ArrayList<Reservation> reservList = ReservationMngDaily.getIstance().getReservationList();
		for(int i=0; i<reservList.size(); i++){
			Reservation r = reservList.get(i);									// estraggo la prenotazione i
			int table = r.getReservedTable();									// estraggo il tavolo prenotato
			GregorianCalendar calend = r.getCalend();							// estraggo l'ora di prenotazione
			int seatsNumb = r.getPeopleNumb();									// estraggo il numero di posti
			Order o = new Order(0, numOrd, calend, table, seatsNumb, "InCorso");	// creo l'oggetto
			orderList.add(o);													// e lo inserisco nella lista
			numOrd++;
		}
	}

	
	/**
	 * Ritorna l'istanza.
	 *
	 * @return l'istanza
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the sQL exception
	 * @throws JavaDBException the java db exception
	 */
	public static OrderMngDaily getIstance() throws ClassNotFoundException,SQLException, JavaDBException {
		if (instance == null) {
			synchronized (OrderMngDaily.class) {
				if (instance == null) {
					instance = new OrderMngDaily();
				}
			}
		}
		return instance;
	}


	
	/**
	 * Ritorna la lista di ordinazioni.
	 *
	 * @return the order list
	 */
	public ArrayList<Order> getOrderList() {
		return orderList;
	}


	/**
	 * Setta la lista delle ordinazioni.
	 *
	 * @param orderList la nuova lista ordinazioni
	 */
	public void setOrderList(ArrayList<Order> orderList) {
		this.orderList = orderList;
	}
	
	
	
	/**
	 * Ritorna l'ordinazione inCorso di un tavolo.
	 *
	 * @param table il numero del tavolo
	 * @return l'ordinazione corrente
	 */
	public Order getCurrentOrderTable(int table){
		Order ord = null;
		for(int i=0; i<orderList.size(); i++){
			Order o = orderList.get(i);
			if(o.getRelativeTo()==table && o.getStateOrd().equalsIgnoreCase("InCorso")){
				ord = o;
				break;
			}
		}
		if(ord==null){
			System.out.println("Ordinazione per quel tavolo non esiste");
		}
		return ord;
	}
	
	
	/**
	 * Dato un tavolo, setta l'ordinazione InCorso a lui associata come Archiviata.
	 *
	 * @param table il numero del tavolo
	 */
	public void setTableStoredOrder(int table){
		for(int i=0; i<orderList.size(); i++){
			Order o = orderList.get(i);
			if(o.getRelativeTo()==table && o.getStateOrd().equalsIgnoreCase("InCorso")){
				o.setStateOrd("Archiviata");
				orderList.set(i, o);
			}
		}
	}
		
	
}
