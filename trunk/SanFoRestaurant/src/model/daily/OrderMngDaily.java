package model.daily;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import database.Database;
import database.JavaDBException;


public class OrderMngDaily {

	private static OrderMngDaily instance = null;
	public ArrayList<Order> orderList;

	// constructor
	private OrderMngDaily() throws ClassNotFoundException, SQLException, JavaDBException {
		init();
	}

	
	private void init() throws ClassNotFoundException, SQLException, JavaDBException {
		Database.connect();
		String query = "SELECT * FROM APP.OrderTable";
		orderList = new ArrayList<Order>();
		
		ResultSet res = Database.interrogate(query);
		while (res.next()) {
			Date d = res.getDate("DateOrd");
			Time t = res.getTime("TimeOrd");
			GregorianCalendar greg = new GregorianCalendar();
			greg.setTime(d);
			greg.set(Calendar.HOUR, t.getHours());
			greg.set(Calendar.MINUTE, t.getMinutes());
			greg.set(Calendar.SECOND, t.getSeconds());
			orderList.add(new Order(res.getInt("OrderNumb"),
									greg,
									res.getInt("RelativeTo"), 
									res.getInt("SeatsNumb"),
									res.getString("StateOrd")));
		}
		res.close();
		System.out.println("List Order OK");
		Database.disconnect();
	}

	
	public static OrderMngDaily getIstance() throws ClassNotFoundException,SQLException, JavaDBException {
		if (instance == null) {
			synchronized (OrderMngDaily.class) {
				if (instance == null) {
					instance = new OrderMngDaily();
				}
			}
		}
		return instance;
	}


	// Returns a list of orders
	public ArrayList<Order> getOrderList() {
		return orderList;
	}


	public void setOrderList(ArrayList<Order> orderList) {
		this.orderList = orderList;
	}
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	
	
	// Ritorna l'ordinazione passato il numero
	public Order getOrderNumber(int orderNumb){
		Order ord = null;
		for(int i=0; i<orderList.size(); i++){
			Order o = orderList.get(i);
			if(o.getOrderNumb()==orderNumb){
				ord = o;
			}
		}
		return ord;
	}
	
	
	// Ritorna le ordinazioni inCorso 
	public ArrayList<Order> getCurrentOrders() throws ClassNotFoundException, SQLException{
		ArrayList<Order> order = new ArrayList<Order>();
		for(int i=0; i<orderList.size(); i++){		
			Order o = orderList.get(i);
			if(o.getStateOrd().equalsIgnoreCase("InCorso")){
				order.add(o);
			}
		}
		return order;
	}
	
	
	// Ritorna le ordinazioni di un certo tavolo in uno stato 
	public ArrayList<Order> getOrdersTable(int table, String stateOrd) throws ClassNotFoundException, SQLException{
		ArrayList<Order> order = new ArrayList<Order>();
		for(int i=0; i<orderList.size(); i++){		
			Order o = orderList.get(i);
			if(o.getRelativeTo()==table && o.getStateOrd().equalsIgnoreCase(stateOrd)){
				order.add(o);
			}
		}
		return order;
	}
	
	
	// Ritorna le ordinazioni di un certo tavolo in uno stato in un certo giorno (ora non importa)
	public ArrayList<Order> getOrdersTableOnDate(int table, String stateOrd, GregorianCalendar gr) throws ClassNotFoundException, SQLException{
		ArrayList<Order> order = new ArrayList<Order>();
		for(int i=0; i<orderList.size(); i++){		
			Order o = orderList.get(i);
			if(o.getRelativeTo()==table && o.getStateOrd().equalsIgnoreCase(stateOrd)
					&& o.getCalend().get(Calendar.YEAR)==gr.get(Calendar.YEAR)
					&& o.getCalend().get(Calendar.MONTH)==gr.get(Calendar.MONTH)	
					&& o.getCalend().get(Calendar.DAY_OF_MONTH)==gr.get(Calendar.DAY_OF_MONTH)){
				order.add(o);
			}
		}
		return order;
	}
	
	
	// Ritorna l'ordinazione inCorso di un tavolo
	public Order getCurrentOrderTable(int table){
		Order ord = null;
		for(int i=0; i<orderList.size(); i++){
			Order o = orderList.get(i);
			if(o.getRelativeTo()==table && o.getStateOrd().equalsIgnoreCase("InCorso")){
				ord = o;
			}
		}
		return ord;
	}
	
	
	// Ritorna le ordinazioni in un certo giorno (ora non importa)
	public ArrayList<Order> getOrdersOnDate(GregorianCalendar gr) throws ClassNotFoundException, SQLException{
		ArrayList<Order> order = new ArrayList<Order>();
		for(int i=0; i<orderList.size(); i++){		
			Order o = orderList.get(i);
			if(o.getCalend().get(Calendar.YEAR)==gr.get(Calendar.YEAR)
					&& o.getCalend().get(Calendar.MONTH)==gr.get(Calendar.MONTH)	
					&& o.getCalend().get(Calendar.DAY_OF_MONTH)==gr.get(Calendar.DAY_OF_MONTH)){
				order.add(o);
			}
		}
		return order;
	}
	
	
	// Ritorna le ordinazioni archiviate in un certo giorno
	public ArrayList<Order> getStoredOrdersOnDate(GregorianCalendar gr) throws ClassNotFoundException, SQLException{
		ArrayList<Order> order = new ArrayList<Order>();
		for(int i=0; i<orderList.size(); i++){		
			Order o = orderList.get(i);
			if(o.getStateOrd().equalsIgnoreCase("Archiviata")
					&& o.getCalend().get(Calendar.YEAR)==gr.get(Calendar.YEAR)
					&& o.getCalend().get(Calendar.MONTH)==gr.get(Calendar.MONTH)	
					&& o.getCalend().get(Calendar.DAY_OF_MONTH)==gr.get(Calendar.DAY_OF_MONTH)){
				order.add(o);
			}
		}
		return order;
	}
	
	
	// Ritorna le ordinazioni InCorso in un certo giorno
	public ArrayList<Order> getCurrentsOrdersOnDate(GregorianCalendar gr) throws ClassNotFoundException, SQLException{
		ArrayList<Order> order = new ArrayList<Order>();
		for(int i=0; i<orderList.size(); i++){		
			Order o = orderList.get(i);
			if(o.getStateOrd().equalsIgnoreCase("InCorso")
					&& o.getCalend().get(Calendar.YEAR)==gr.get(Calendar.YEAR)
					&& o.getCalend().get(Calendar.MONTH)==gr.get(Calendar.MONTH)	
					&& o.getCalend().get(Calendar.DAY_OF_MONTH)==gr.get(Calendar.DAY_OF_MONTH)){
				order.add(o);
			}
		}
		return order;
	}
	
	
	// Data una ordinazione, la setta come archiviata
	public void setStoredOrder(int numbOrd){
		for(int i=0; i<orderList.size(); i++){
			Order o = orderList.get(i);
			if(o.getOrderNumb()==numbOrd){
				o.setStateOrd("Archviata");
				orderList.set(i, o);
			}
		}
	}
	
	
	// Dato un tavolo, setta l'ordinazione InCorso a lui associata come Archiviata
	public void setTableStoredOrder(int table){
		for(int i=0; i<orderList.size(); i++){
			Order o = orderList.get(i);
			if(o.getRelativeTo()==table && o.getStateOrd().equalsIgnoreCase("InCorso")){
				o.setStateOrd("Archviata");
				orderList.set(i, o);
			}
		}
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}














