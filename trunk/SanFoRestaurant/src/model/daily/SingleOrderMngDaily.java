package model.daily;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Database;
import database.JavaDBException;


public class SingleOrderMngDaily {

	private static SingleOrderMngDaily instance = null;
	public ArrayList<SingleOrder> singleOrderList;
	
	// constructor
	private SingleOrderMngDaily() throws ClassNotFoundException, SQLException, JavaDBException {
		init();
	}
	
	
	private void init() throws ClassNotFoundException, SQLException, JavaDBException {
		Database.connect();
		String query = "SELECT * FROM APP.SingleOrder";
		singleOrderList = new ArrayList<SingleOrder>();
		
		ResultSet res = Database.interrogate(query);
		while (res.next()) {
			singleOrderList.add(new SingleOrder(res.getInt("RowNumb"), 
								  res.getInt("OrdNumb"), 
								  res.getInt("Quantity"), 
								  res.getString("Dish"),
								  res.getString("State"),
								  res.getString("Notes")));
		}
		res.close();
		System.out.println("List SingleOrder OK");
		Database.disconnect();
	}
	
	
	public static SingleOrderMngDaily getIstance() throws ClassNotFoundException,SQLException, JavaDBException {
		if (instance == null) {
			synchronized (SingleOrderMngDaily.class) {
				if (instance == null) {
					instance = new SingleOrderMngDaily();
				}
			}
		}
		return instance;
	}


	// Returns a list of orders
	public ArrayList<SingleOrder> getSingleOrderList() {
		return singleOrderList;
	}


	public void setSingleOrderList(ArrayList<SingleOrder> singleOrderList) {
		this.singleOrderList = singleOrderList;
	}


	//////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	
	
	// Ritorna le singole ordinazioni dato il numero di ordinazione 
	public ArrayList<SingleOrder> getSingleOrders(int ordNumb) throws ClassNotFoundException, SQLException{
		ArrayList<SingleOrder> single = new ArrayList<SingleOrder>();
		for(int i=0; i<singleOrderList.size(); i++){		
			SingleOrder s = singleOrderList.get(i);
			if(s.getOrdNumb()==ordNumb){
				single.add(s);
			}
		}
		return single;
	}
	
	
	// Ritorna le singole ordinazioni in un certo stato (DaCompletare/Servita) dato il numero di ordinazione 
	public ArrayList<SingleOrder> getSingleOrders(int ordNumb, String state) throws ClassNotFoundException, SQLException{
		ArrayList<SingleOrder> single = new ArrayList<SingleOrder>();
		for(int i=0; i<singleOrderList.size(); i++){		
			SingleOrder s = singleOrderList.get(i);
			if(s.getOrdNumb()==ordNumb && s.getState().equalsIgnoreCase(state)){
				single.add(s);
			}
		}
		return single;
	}
	
	
	// Ritorna tutte le singole ordinazioni incomplete 
	public ArrayList<SingleOrder> getIncompleteSingleOrders() throws ClassNotFoundException, SQLException{
		ArrayList<SingleOrder> single = new ArrayList<SingleOrder>();
		for(int i=0; i<singleOrderList.size(); i++){		
			SingleOrder s = singleOrderList.get(i);
			if(s.getState().equalsIgnoreCase("DaCompletare")){
				single.add(s);
			}
		}
		return single;
	}
	
	
	// Ritorna true se tutte le singole ordinazioni di un tavolo sono complete 
	public boolean isComplete(int numbOrd) throws ClassNotFoundException, SQLException{
		ArrayList<SingleOrder> single = new ArrayList<SingleOrder>();
		for(int i=0; i<singleOrderList.size(); i++){		
			SingleOrder s = singleOrderList.get(i);
			if(s.getState().equalsIgnoreCase("DaCompletare") && s.getOrdNumb()==numbOrd){		
				single.add(s);										// contiene le incomplete
			}
		}
		if (single.size()==0) return true;
		else return false;
	}
	
	// Ritorna tutte le singole ordinazioni complete 
	public ArrayList<SingleOrder> getCompleteSingleOrders() throws ClassNotFoundException, SQLException{
		ArrayList<SingleOrder> single = new ArrayList<SingleOrder>();
		for(int i=0; i<singleOrderList.size(); i++){		
			SingleOrder s = singleOrderList.get(i);
			if(s.getState().equalsIgnoreCase("Servita")){
				single.add(s);
			}
		}
		return single;
	}
	
	
	// Ritorna tutte le singole ordinazioni incomplete dato il numero di ordinazione 
	public ArrayList<SingleOrder> getIncompleteSingleOrders(int ordNumb) throws ClassNotFoundException, SQLException{
		ArrayList<SingleOrder> single = new ArrayList<SingleOrder>();
		for(int i=0; i<singleOrderList.size(); i++){		
			SingleOrder s = singleOrderList.get(i);
			if(s.getState().equalsIgnoreCase("DaCompletare") && s.getOrdNumb()==ordNumb){
				single.add(s);
			}
		}
		return single;
	}
	
	
	// Ritorna tutte le singole ordinazioni complete dato il numero di ordinazione 
	public ArrayList<SingleOrder> getCompleteSingleOrders(int ordNumb) throws ClassNotFoundException, SQLException{
		ArrayList<SingleOrder> single = new ArrayList<SingleOrder>();
		for(int i=0; i<singleOrderList.size(); i++){		
			SingleOrder s = singleOrderList.get(i);
			if(s.getState().equalsIgnoreCase("Servita") && s.getOrdNumb()==ordNumb){
				single.add(s);
			}
		}
		return single;
	}
	
	
	// Ritorna le singole ordinazioni dato il numero ordinazione e il piatto 
	public ArrayList<SingleOrder> getDishSingleOrders(int numbOrd, String dish) throws ClassNotFoundException, SQLException{
		ArrayList<SingleOrder> single = new ArrayList<SingleOrder>();
		for(int i=0; i<singleOrderList.size(); i++){		
			SingleOrder s = singleOrderList.get(i);
			if(s.getOrdNumb()==numbOrd 
					&& s.getDish().equalsIgnoreCase(dish)){
				single.add(s);
			}
		}
		return single;
	}
	
	
	// Ritorna le singole ordinazioni dato il numero ordinazione, il piatto e la quantità 
	public ArrayList<SingleOrder> getDishSingleOrders(int numbOrd, String dish, int quantity) throws ClassNotFoundException, SQLException{
		ArrayList<SingleOrder> single = new ArrayList<SingleOrder>();
		for(int i=0; i<singleOrderList.size(); i++){		
			SingleOrder s = singleOrderList.get(i);
			if(s.getOrdNumb()==numbOrd 
					&& s.getDish().equalsIgnoreCase(dish) 
					&& s.getQuantity()==quantity){
				single.add(s);
			}
		}
		return single;
	}
	
	
	// Ritorna le singole ordinazioni dato il numero ordinazione, il piatto, la quantità e lo stato
	public ArrayList<SingleOrder> getDishSingleOrders(int numbOrd, String dish, int quantity, String state) throws ClassNotFoundException, SQLException{
		ArrayList<SingleOrder> single = new ArrayList<SingleOrder>();
		for(int i=0; i<singleOrderList.size(); i++){		
			SingleOrder s = singleOrderList.get(i);
			if(s.getOrdNumb()==numbOrd 
					&& s.getDish().equalsIgnoreCase(dish) 
					&& s.getQuantity()==quantity
					&& s.getState().equalsIgnoreCase(state)){
				single.add(s);
			}
		}
		return single;
	}
	
	
	
	// Data una ordinazione e una riga, setta la singola ordinazione come servita
	public void setCompleteSingleOrder(int ordNumb, int rowNumb){
		for(int i=0; i<singleOrderList.size(); i++){
			SingleOrder s = singleOrderList.get(i);
			if(s.getOrdNumb()==ordNumb && s.getRowNumb()==rowNumb){
				s.setState("Servita");
				singleOrderList.set(i, s);
			}
		}
	}
	

	// Data una ordinazione e il piatto e la quantità, setta la singola ordinazione come servita
	public void setCompleteSingleOrder(int ordNumb, String dish, int quantity){
		for(int i=0; i<singleOrderList.size(); i++){
			SingleOrder s = singleOrderList.get(i);
			if(s.getOrdNumb()==ordNumb 
					&& s.getDish().equalsIgnoreCase(dish)
					&& s.getQuantity()==quantity){
				s.setState("Servita");
				singleOrderList.set(i, s);
			}
		}
	}
	
	
}















