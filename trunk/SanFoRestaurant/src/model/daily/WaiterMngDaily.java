package model.daily;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Database;
import database.JavaDBException;


public class WaiterMngDaily {

	private static WaiterMngDaily instance = null;
	public ArrayList<Waiter> waiterList;

	// constructor
	private WaiterMngDaily() throws ClassNotFoundException, SQLException, JavaDBException {
		init();
	}

	
	private void init() throws ClassNotFoundException, SQLException, JavaDBException {
		Database.connect();
		String query = "SELECT * FROM APP.Waiter";
		waiterList = new ArrayList<Waiter>();
		
		ResultSet res = Database.interrogate(query);
		while (res.next()) {
			waiterList.add(new Waiter(res.getInt("Numb"), 
									  res.getString("Surname"), 
								   	  res.getString("Name")));
		}
		res.close();
		System.out.println("List Waiters OK");
		Database.disconnect();
	}

	
	public static WaiterMngDaily getIstance() throws ClassNotFoundException,SQLException, JavaDBException {
		if (instance == null) {
			synchronized (WaiterMngDaily.class) {
				if (instance == null) {
					instance = new WaiterMngDaily();
				}
			}
		}
		return instance;
	}
	
	
	// Returns a list of waiters
	public ArrayList<Waiter> getWaiterList() {
		return waiterList;
	}
	
	
	public void setWaiterList(ArrayList<Waiter> waiterList) {
		this.waiterList = waiterList;
	}
	
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	
	
	// Ritorna il cameriere passato il cognome e il nome
	public Waiter getWaiter(String surname, String name){
		Waiter waiter = null;
		for(int i=0; i<waiterList.size(); i++){
			Waiter w = waiterList.get(i);
			if(w.getSurname().equalsIgnoreCase(surname) && w.getName().equalsIgnoreCase(name)){
				waiter = w;
			}
		}
		return waiter;
	}
	
	
	// Ritorna il cameriere passato il numero di matricola
	public Waiter getWaiter(int numb){
		Waiter waiter = null;
		for(int i=0; i<waiterList.size(); i++){
			Waiter w = waiterList.get(i);
			if(w.getNumb()==numb){
				waiter = w;
			}
		}
		return waiter;
	}
	
	
	// Dato un numero di matricola, setta nome e cognome del cameriere
	public void setWaiterFullName(int numb, String surname, String name) throws ClassNotFoundException, SQLException{
		for(int i=0; i<waiterList.size(); i++){
			Waiter w = waiterList.get(i);
			if(w.getSurname().equalsIgnoreCase(surname) && w.getName().equalsIgnoreCase(name)){
				w.setNumb(numb);
				waiterList.set(i, w);
			}
		}
	}
	
	

	
	
	
	
	
	
	
}