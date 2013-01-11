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
 * Classe che crea la lista di Reservation e che contiene i metodi per gestirla.
 * 
 * @author Mauro
 */
public class ReservationMngDaily implements ManagerDailyInterface {

	/** The instance. */
	private static ReservationMngDaily instance = null;
	
	/** The reservation list. */
	public ArrayList<Reservation> reservationList;

	
	
	/**
	 * Istanzia un nuovo Manager Reservation Daily.
	 *
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the sQL exception
	 * @throws JavaDBException the java db exception
	 */
	private ReservationMngDaily() throws ClassNotFoundException, SQLException, JavaDBException {
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
		//GregorianCalendar gc = new GregorianCalendar();			// ottengo la data corrente
		//int day = gc.get(Calendar.DAY_OF_MONTH);
		//int month = gc.get(Calendar.MONTH)+1;
		//int year = gc.get(Calendar.YEAR);
		//String d = year+ "-" +month+ "-" +day;
		//String query = "SELECT * FROM APP.Reservation WHERE Date = '" +d+ "'";
		String query = "SELECT * FROM APP.Reservation WHERE Date = '2011-09-30'";		// DA CAMBIARE CON SOPRA!!!!
		reservationList = new ArrayList<Reservation>();
		
		ResultSet res = Database.interrogate(query);
		while (res.next()) {
			Date d = res.getDate("Date");
			Time t = res.getTime("Time");
			GregorianCalendar greg = new GregorianCalendar();
			greg.setTime(d);
			greg.set(Calendar.HOUR, t.getHours());
			greg.set(Calendar.MINUTE, t.getMinutes());
			greg.set(Calendar.SECOND, t.getSeconds());
			reservationList.add(new Reservation(res.getInt("I"),
												res.getInt("ReservationNumb"),
												greg,
												res.getInt("ReservedTable"), 
												res.getInt("IDClient"),
												res.getInt("PeopleNumb")));
		}
		res.close();
		System.out.println("List Reservation OK");
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
	public static ReservationMngDaily getIstance() throws ClassNotFoundException,SQLException, JavaDBException {
		if (instance == null) {
			synchronized (ReservationMngDaily.class) {
				if (instance == null) {
					instance = new ReservationMngDaily();
				}
			}
		}
		return instance;
	}
	
	
	
	/**
	 * Ritorna la lista delle prenotazioni.
	 *
	 * @return la lista delle prenotazioni
	 */
	public ArrayList<Reservation> getReservationList() {
		return reservationList;
	}
	
	
	/**
	 * Setta la lista delle prenotazioni.
	 *
	 * @param reservationList la nuova lista prenotazioni
	 */
	public void setReservationList(ArrayList<Reservation> reservationList) {
		this.reservationList = reservationList;
	}
	
	
	/**
	 * Ritorna le prenotazioni di un certo tavolo in un certo giorno (ora non importa).
	 *
	 * @param reservedTable il numero del tavolo
	 * @param gr il giorno
	 * @return le prenotazioni
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the sQL exception
	 */
	public ArrayList<Reservation> getReservationsTableOnDate(int reservedTable, GregorianCalendar gr) throws ClassNotFoundException, SQLException{
		ArrayList<Reservation> reserv = new ArrayList<Reservation>();
		for(int i=0; i<reservationList.size(); i++){		
			Reservation r = reservationList.get(i);
			if(r.getReservedTable()==reservedTable && r.getCalend().get(Calendar.YEAR)==gr.get(Calendar.YEAR)
					&& r.getCalend().get(Calendar.MONTH)==gr.get(Calendar.MONTH)	
					&& r.getCalend().get(Calendar.DAY_OF_MONTH)==gr.get(Calendar.DAY_OF_MONTH)){
				reserv.add(r);
			}
		}
		return reserv;
	}
	
}
