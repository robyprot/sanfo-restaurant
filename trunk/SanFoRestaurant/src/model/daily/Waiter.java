package model.daily;

// TODO: Auto-generated Javadoc
/**
 * Classe che rappresenta un elemento della tabella Waiter.
 * 
 * @author Mauro
 */
public class Waiter {

	/** The a. */
	int a;
	
	/** The numb. */
	int numb;
	
	/** The surname. */
	String surname;
	
	/** The name. */
	String name;
	

	/**
	 * Istanzia un nuovo cameriere.
	 *
	 * @param a l'identificatore di riga
	 * @param numb il codice del cameriere
	 * @param surname il cognome
	 * @param name il nome
	 */
	public Waiter(int a, int numb, String surname, String name) {
		this.a = a;
		this.numb = numb;
		this.surname = surname;
		this.name = name;
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
	 * Gets the numb.
	 *
	 * @return the numb
	 */
	public int getNumb() {
		return numb;
	}

	/**
	 * Sets the numb.
	 *
	 * @param numb the new numb
	 */
	public void setNumb(int numb) {
		this.numb = numb;
	}

	/**
	 * Gets the surname.
	 *
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Sets the surname.
	 *
	 * @param surname the new surname
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
		
}	
