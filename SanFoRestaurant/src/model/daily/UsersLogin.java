package model.daily;

// TODO: Auto-generated Javadoc
/**
 * Classe che rappresenta un elemento della tabella Users.
 * 
 * @author Mauro
 */
public class UsersLogin {

	/** The a. */
	int a;
	
	/** The id. */
	String id;
	
	/** The name. */
	String name;
	
	/** The password. */
	String password;
	
	/** The type. */
	String type;
	
	/** The dbUpdate. */
	boolean dbUpdate;
	
	
	/**
	 * Istanzia un nuovo utente per il login.
	 *
	 * @param a l'identificatore di riga
	 * @param id l'id
	 * @param name il nome
	 * @param password la password
	 * @param type il tipo di utente
	 * @param dbUpdate il controllo update per client
	 */
	public UsersLogin(int a, String id, String name, String password, String type, boolean dbUpdate) {
		this.a = a;
		this.id = id;
		this.name = name;
		this.password = password;
		this.type = type;
		this.dbUpdate = dbUpdate;
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
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
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
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
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
	 * Gets the isDbUpdate.
	 *
	 * @return the isDbUpdate
	 */
	public boolean isDbUpdate() {
		return dbUpdate;
	}
	
	/**
	 * Sets the dbUpdate.
	 *
	 * @param type the new dbUpdate
	 */
	public void setDbUpdate(boolean dbUpdate) {
		this.dbUpdate = dbUpdate;
	}
	
}
