package model.daily;

// TODO: Auto-generated Javadoc
/**
 * Classe che rappresenta un elemento della tabella Client.
 * 
 * @author Mauro
 */
public class Client {

	/** The a. */
	int a;
	
	/** The id. */
	int id;
	
	/** The name. */
	String name;
	
	/** The surname. */
	String surname;
	
	/** The counter. */
	int counter;
	
	/** The allergy. */
	String allergy;
	
	
	// constructors
	/**
	 * Instantiates a new client.
	 *
	 * @param a l'identificatore di riga
	 * @param id l'id cliente
	 * @param name il nome
	 * @param surname il cognome
	 * @param counter il contatore presenze
	 */
	public Client(int a, int id, String name, String surname, int counter) {
		this.a = a;
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.counter = counter;
	}
	
	/**
	 * Istanzia un nuovo cliente.
	 *
	 * @param a the a
	 * @param id the id
	 * @param name the name
	 * @param surname the surname
	 * @param counter the counter
	 * @param allergy the allergy
	 */
	public Client(int a, int id, String name, String surname, int counter, String allergy) {
		this(a, id, name, surname, counter);
		this.allergy = allergy;
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
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
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
	 * Gets the counter.
	 *
	 * @return the counter
	 */
	public int getCounter() {
		return counter;
	}

	/**
	 * Sets the counter.
	 *
	 * @param counter the new counter
	 */
	public void setCounter(int counter) {
		this.counter = counter;
	}

	/**
	 * Gets the allergy.
	 *
	 * @return the allergy
	 */
	public String getAllergy() {
		return allergy;
	}

	/**
	 * Sets the allergy.
	 *
	 * @param allergy the new allergy
	 */
	public void setAllergy(String allergy) {
		this.allergy = allergy;
	}
		
}

