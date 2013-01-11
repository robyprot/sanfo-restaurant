package model.daily;

// TODO: Auto-generated Javadoc
/**
 * Classe che rappresenta un elemento della tabella Settings.
 * 
 * @author Mauro
 */
public class Settings {

	/** The a. */
	int a;
	
	/** The name param. */
	String nameParam;
	
	/** The value. */
	String value;
	

	/**
	 * Istanzia un nuovo Settings.
	 *
	 * @param a l'identificatore di riga
	 * @param nameParam il nome del parametro
	 * @param value il valore del parametro
	 */
	public Settings(int a, String nameParam, String value){
		this.a = a;
		this.nameParam = nameParam;
		this.value = value;
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
	 * Gets the name param.
	 *
	 * @return the name param
	 */
	public String getNameParam() {
		return nameParam;
	}

	/**
	 * Sets the name param.
	 *
	 * @param nameParam the new name param
	 */
	public void setNameParam(String nameParam) {
		this.nameParam = nameParam;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(String value) {
		this.value = value;
	}	
	
}
