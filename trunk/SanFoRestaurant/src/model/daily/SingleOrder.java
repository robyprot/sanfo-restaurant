package model.daily;


// TODO: Auto-generated Javadoc
/**
 * Classe che rappresenta un elemento della tabella SingleOrder.
 * 
 * @author Mauro
 */
public class SingleOrder {

	/** The a. */
	int a; 
	
	/** The ord numb. */
	int ordNumb;
	
	/** The quantity. */
	int quantity;
	
	/** The dish. */
	String dish;
	
	/** The state. */
	String state;
	
	/** The notes. */
	String notes;
	
	
	/**
	 * Istanzia una nuova singola ordinazione.
	 *
	 * @param a l'identificatore di riga
	 * @param ordNumb il numero di ordinazione
	 * @param quantity il numero di porzioni
	 * @param dish il codice del piatto
	 * @param state lo stato della singola ordinazione
	 * @param notes le note
	 */
	public SingleOrder(int a, int ordNumb, int quantity, String dish, String state, String notes) {
		this.a = a;
		this.ordNumb = ordNumb;
		this.quantity = quantity;
		this.dish = dish;
		this.state = state;
		this.notes = notes;
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
	 * Gets the ord numb.
	 *
	 * @return the ord numb
	 */
	public int getOrdNumb() {
		return ordNumb;
	}

	/**
	 * Sets the ord numb.
	 *
	 * @param ordNumb the new ord numb
	 */
	public void setOrdNumb(int ordNumb) {
		this.ordNumb = ordNumb;
	}

	/**
	 * Gets the quantity.
	 *
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Sets the quantity.
	 *
	 * @param quantity the new quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * Gets the dish.
	 *
	 * @return the dish
	 */
	public String getDish() {
		return dish;
	}

	/**
	 * Sets the dish.
	 *
	 * @param dish the new dish
	 */
	public void setDish(String dish) {
		this.dish = dish;
	}

	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * Sets the state.
	 *
	 * @param state the new state
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * Gets the notes.
	 *
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * Sets the notes.
	 *
	 * @param notes the new notes
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
}
