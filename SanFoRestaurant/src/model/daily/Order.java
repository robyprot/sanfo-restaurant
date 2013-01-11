package model.daily;

import java.util.GregorianCalendar;

// TODO: Auto-generated Javadoc
/**
 * Classe che rappresenta un elemento della tabella OrderTable.
 * 
 * @author Mauro
 */
public class Order {

	/** The a. */
	int a;
	
	/** The order numb. */
	int orderNumb;
	
	/** The second. */
	int year, month, day, hour, minute, second;
	
	/** The calend. */
	GregorianCalendar calend = new GregorianCalendar(year, month, day, hour, minute, second);
	
	/** The relative to. */
	int relativeTo;
	
	/** The seats numb. */
	int seatsNumb;
	
	/** The state ord. */
	String stateOrd;
	
	

	/**
	 * Istanzia una nuova ordinazione.
	 *
	 * @param a l'identificatore di riga
	 * @param orderNumb il numero di ordinazione
	 * @param calend la data e l'ora
	 * @param relativeTo il numero del tavolo
	 * @param seatsNumb il numero di coperti
	 * @param stateOrd lo stato dell'ordinazione
	 */
	public Order(int a, int orderNumb, GregorianCalendar calend, int relativeTo, int seatsNumb, String stateOrd) {
		this.a = a;
		this.orderNumb = orderNumb;
		this.calend = calend;
		this.relativeTo = relativeTo;
		this.seatsNumb = seatsNumb;
		this.stateOrd = stateOrd;
	}



	/**
	 * Gets the order numb.
	 *
	 * @return the order numb
	 */
	public int getOrderNumb() {
		return orderNumb;
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
	 * Sets the order numb.
	 *
	 * @param orderNumb the new order numb
	 */
	public void setOrderNumb(int orderNumb) {
		this.orderNumb = orderNumb;
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
	 * Gets the relative to.
	 *
	 * @return the relative to
	 */
	public int getRelativeTo() {
		return relativeTo;
	}

	/**
	 * Sets the relative to.
	 *
	 * @param relativeTo the new relative to
	 */
	public void setRelativeTo(int relativeTo) {
		this.relativeTo = relativeTo;
	}

	/**
	 * Gets the seats numb.
	 *
	 * @return the seats numb
	 */
	public int getSeatsNumb() {
		return seatsNumb;
	}
	
	/**
	 * Sets the seats numb.
	 *
	 * @param seatsNumb the new seats numb
	 */
	public void setSeatsNumb(int seatsNumb) {
		this.seatsNumb = seatsNumb;
	}

	/**
	 * Gets the state ord.
	 *
	 * @return the state ord
	 */
	public String getStateOrd() {
		return stateOrd;
	}

	/**
	 * Sets the state ord.
	 *
	 * @param stateOrd the new state ord
	 */
	public void setStateOrd(String stateOrd) {
		this.stateOrd = stateOrd;
	}


	
}
