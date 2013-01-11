package model.daily;

import java.util.GregorianCalendar;

// TODO: Auto-generated Javadoc
/**
 * Classe che rappresenta un elemento della tabella Reservation.
 * 
 * @author Mauro
 */
public class Reservation {

	/** The a. */
	int a;
	
	/** The reservation numb. */
	int reservationNumb;
	
	/** The second. */
	int year, month, day, hour, minute, second;
	
	/** The calend. */
	GregorianCalendar calend = new GregorianCalendar(year, month, day, hour, minute, second);
	
	/** The reserved table. */
	int reservedTable;
	
	/** The id client. */
	int idClient;
	
	/** The people numb. */
	int peopleNumb;
	
	
	
	/**
	 * Istanzia una nuova prenotazione.
	 *
	 * @param a l'identificatore di riga
	 * @param reservationNumb il numero di prenotazione
	 * @param calend il giorno e l'ora
	 * @param reservedTable il numero del tavolo
	 * @param idClient l'id del cliente
	 * @param peopleNumb il numero di persone
	 */
	public Reservation(int a, int reservationNumb, GregorianCalendar calend, int reservedTable, int idClient, int peopleNumb) {
		this.a = a;
		this.reservationNumb = reservationNumb;
		this.calend = calend;
		this.reservedTable = reservedTable;
		this.idClient = idClient;
		this.peopleNumb = peopleNumb;
	}



	/**
	 * Gets the a.
	 *
	 * @return the a
	 */
	public int getA() {
		return a;
	}

	/**
	 * Sets the a.
	 *
	 * @param a the new a
	 */
	public void setA(int a) {
		this.a = a;
	}
	
	/**
	 * Gets the reservation numb.
	 *
	 * @return the reservation numb
	 */
	public int getReservationNumb() {
		return reservationNumb;
	}

	/**
	 * Sets the reservation numb.
	 *
	 * @param reservationNumb the new reservation numb
	 */
	public void setReservationNumb(int reservationNumb) {
		this.reservationNumb = reservationNumb;
	}

	/**
	 * Gets the calend.
	 *
	 * @return the calend
	 */
	public GregorianCalendar getCalend() {
		return calend;
	}

	/**
	 * Sets the calend.
	 *
	 * @param calend the new calend
	 */
	public void setCalend(GregorianCalendar calend) {
		this.calend = calend;
	}

	/**
	 * Gets the reserved table.
	 *
	 * @return the reserved table
	 */
	public int getReservedTable() {
		return reservedTable;
	}

	/**
	 * Sets the reserved table.
	 *
	 * @param reservedTable the new reserved table
	 */
	public void setReservedTable(int reservedTable) {
		this.reservedTable = reservedTable;
	}

	/**
	 * Gets the id client.
	 *
	 * @return the id client
	 */
	public int getIdClient() {
		return idClient;
	}

	/**
	 * Sets the id client.
	 *
	 * @param idClient the new id client
	 */
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	/**
	 * Gets the people numb.
	 *
	 * @return the people numb
	 */
	public int getPeopleNumb() {
		return peopleNumb;
	}

	/**
	 * Sets the people numb.
	 *
	 * @param peopleNumb the new people numb
	 */
	public void setPeopleNumb(int peopleNumb) {
		this.peopleNumb = peopleNumb;
	}
	
}
