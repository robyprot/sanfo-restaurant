package model.daily;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Database;
import database.JavaDBException;


public class FoodMngDaily {

	private static FoodMngDaily instance = null;
	public ArrayList<Food> foodList;

	// constructor
	private FoodMngDaily() throws ClassNotFoundException, SQLException, JavaDBException {
		init();
	}
	

	private void init() throws ClassNotFoundException, SQLException, JavaDBException {
		Database.connect();
		String query = "SELECT * FROM APP.Food";
		foodList = new ArrayList<Food>();

		ResultSet res = Database.interrogate(query);
		while (res.next()) {
			foodList.add(new Food(res.getString("Code"), 
								  res.getString("Name"), 
								  res.getFloat("Price"),
								  res.getString("Type"),
								  res.getString("Description")));
		}
		res.close();
		System.out.println("List Food OK");
		Database.disconnect();
	}
	
	
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
	
	
	// Returns a list of food
	public ArrayList<Food> getFoodList() {
		return foodList;
	}


	public void setFoodList(ArrayList<Food> foodList) {
		this.foodList = foodList;
	}
	

//////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////

	
	// Ritorna il cibo passato il codice
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
	
	
	// Ritorna il cibo passato il nome
	public Food getFoodFromName(String name){
		Food food = null;
		for(int i=0; i<foodList.size(); i++){
			Food f = foodList.get(i);
			if(f.getName().equalsIgnoreCase(name)){
				food = f;
			}
		}
		return food;
	}
	
	
	// Dato il codice, inserisce la descrizione del piatto
	public void insertDescriptionFromCode(String code, String description){
		for(int i=0; i<foodList.size(); i++){
			Food f = foodList.get(i);
			if(f.getCode().equalsIgnoreCase(code)){
				f.setDescription(description);
				foodList.set(i, f);
			}
		}
	}
	
	
	// Dato il nome, inserisce la descrizione del piatto
	public void insertDescriptionFromName(String name, String description){
		for(int i=0; i<foodList.size(); i++){
			Food f = foodList.get(i);
			if(f.getName().equalsIgnoreCase(description)){
				f.setDescription(description);
				foodList.set(i, f);
			}
		}
	}
	
	
	// Dato il codice, modifica il prezzo del piatto
	public void changePriceFromCode(String code, float price){
		for(int i=0; i<foodList.size(); i++){
			Food f = foodList.get(i);
			if(f.getCode().equalsIgnoreCase(code)){
				f.setPrice(price);
				foodList.set(i, f);
			}
		}
	}
	
	
	// Dato il nome, modifica il prezzo del piatto
	public void changePriceFromName(String name, float price){
		for(int i=0; i<foodList.size(); i++){
			Food f = foodList.get(i);
			if(f.getName().equalsIgnoreCase(name)){
				f.setPrice(price);
				foodList.set(i, f);
			}
		}
	}
	
	
	
	
	
	
	


}


















