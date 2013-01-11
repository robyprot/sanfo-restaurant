package model.daily;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.ManagerDailyInterface;

import database.Database;
import database.JavaDBException;


// TODO: Auto-generated Javadoc
/**
 * Classe che crea la lista di Food e che contiene i metodi per gestirla.
 * 
 * @author Mauro
 */
public class FoodMngDaily implements ManagerDailyInterface {

	/** The instance. */
	private static FoodMngDaily instance = null;
	
	/** The food list. */
	public ArrayList<Food> foodList;

	// constructor
	/**
	 * Istanzia un nuovo Manager Food Daily.
	 *
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the sQL exception
	 * @throws JavaDBException the java db exception
	 */
	private FoodMngDaily() throws ClassNotFoundException, SQLException, JavaDBException {
		init();
	}
	

	/**
	 * Inizializza.
	 *
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the sQL exception
	 * @throws JavaDBException the java db exception
	 */
	public void init() throws ClassNotFoundException, SQLException, JavaDBException {
		Database.connect();
		String query = "SELECT * FROM APP.Food";
		foodList = new ArrayList<Food>();

		ResultSet res = Database.interrogate(query);
		while (res.next()) {
			foodList.add(new Food(res.getInt("I"),
								  res.getString("Code"), 
								  res.getString("Name"), 
								  res.getFloat("Price"),
								  res.getString("Type"),
								  res.getString("Description")));
		}
		res.close();
		System.out.println("List Food OK");
		Database.disconnect();
	}
	
	
	/**
	 * Ritorna l'istanza.
	 *
	 * @return l'istanza
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the sQL exception
	 * @throws JavaDBException the java db exception
	 */
	public static FoodMngDaily getIstance() throws ClassNotFoundException,SQLException, JavaDBException {
		if (instance == null) {
			synchronized (FoodMngDaily.class) {
				if (instance == null) {
					instance = new FoodMngDaily();
				}
			}
		}
		return instance;
	}
	
	
	
	/**
	 * Ritorna la lista dei Cibi.
	 *
	 * @return la lista cibi
	 */
	public ArrayList<Food> getFoodList() {
		return foodList;
	}


	/**
	 * Setta la lista cibi.
	 *
	 * @param foodList la nuova lista cibi
	 */
	public void setFoodList(ArrayList<Food> foodList) {
		this.foodList = foodList;
	}
	

   /**
	 * Ritorna il cibo passato il codice.
	 *
	 * @param code il codice
	 * @return the food from code
	 */
	public Food getFoodFromCode(String code){
		Food food = null;
		for(int i=0; i<foodList.size(); i++){
			Food f = foodList.get(i);
			if(f.getCode().equalsIgnoreCase(code)){
				food = f;
			}
		}
		return food;
	}
	
}
