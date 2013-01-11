package model.daily;

// TODO: Auto-generated Javadoc
/**
 * Classe che rappresenta un elemento della tabella Food.
 * 
 * @author Mauro
 */
public class Food {

	/** The a. */
	int a;
	
	/** The code. */
	String code;
	
	/** The name. */
	String name;
	
	/** The price. */
	float price;
	
	/** The type. */
	String type;
	
	/** The description. */
	String description;



	/**
	 * Istanzia un nuovo cibo.
	 *
	 * @param a l'identificatore di riga
	 * @param code il codice
	 * @param name il nome
	 * @param price il prezzo
	 * @param type il tipo
	 */
	public Food(int a, String code, String name, float price, String type) {
		this.a = a;
		this.code = code;
		this.name = name;
		this.price = price;
		this.type = type;
	}

	/**
	 * Istanzia un nuovo cibo.
	 *
	 * @param a l'identificatore di riga
	 * @param code il codice
	 * @param name il nome
	 * @param price il prezzo
	 * @param type il tipo
	 * @param description la descrizione aggiuntiva
	 */
	public Food(int a, String code, String name, float price, String type, String description) {
		this(a, code, name, price, type);
		this.description = description;
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
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets the code.
	 *
	 * @param code the new code
	 */
	public void setCode(String code) {
		this.code = code;
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
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
}
