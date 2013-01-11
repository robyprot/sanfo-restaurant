package model.daily;

import java.util.GregorianCalendar;

// TODO: Auto-generated Javadoc
/**
 * Classe che rappresenta un elemento della tabella TablesCheck.
 * 
 * @author Mauro
 */
public class TablesCheck {

	/** The a. */
	int a;
	
	/** The second. */
	int year, month, day, hour, minute, second;
	
	/** The calend. */
	GregorianCalendar calend = new GregorianCalendar(year, month, day, hour, minute, second);
	
	/** The amount. */
	float amount;
	
	/** The people numb. */
	int peopleNumb;

	
	/**
	 * Istanzia un nuovo conto tavolo.
	 *
	 * @param a l'identificatore di riga
	 * @param calend la data
	 * @param amount l'ammontare
	 * @param peopleNumb il numero di persone
	 */
	public TablesCheck(int a, GregorianCalendar calend, float amount, int peopleNumb) {
		this.a = a;
		this.calend = calend;
		this.amount = amount;
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
	 * Gets the amount.
	 *
	 * @return the amount
	 */
	public float getAmount() {
		return amount;
	}

	/**
	 * Sets the amount.
	 *
	 * @param amount the new amount
	 */
	public void setAmount(float amount) {
		this.amount = amount;
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
