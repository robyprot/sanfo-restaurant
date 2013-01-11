package start;

// TODO: Auto-generated Javadoc
/**
 * Classe che rappresenta l'oggetto Conto che servirà nel momento in cui si stampa il conto di un tavolo.
 * 
 * @author Mauro
 */
public class Conto {

	/** The dish. */
	String dish;
	
	/** The name. */
	String name;
	
	/** The price. */
	float price;
	
	/** The type. */
	String type;
	
	/** The quantity. */
	int quantity;
	
	/** The state. */
	String state;
	

	/**
	 * Istanzia un nuovo conto.
	 *
	 * @param dish il codice del piatto
	 * @param name il nome del piatto
	 * @param price il prezzo del piatto
	 * @param type il tipo di piatto
	 * @param quantity la quantità presa
	 * @param state lo stato
	 */
	public Conto(String dish, String name, float price, String type, int quantity, String state) {
		this.dish = dish;
		this.name = name;
		this.price = price;
		this.type = type;
		this.quantity = quantity;
		this.state = state;
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

	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}

	/**
	 * Sets the price.
	 *
	 * @param price the new price
	 */
	public void setPrice(float price) {
		this.price = price;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(String type) {
		this.type = type;
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
	
	
}
