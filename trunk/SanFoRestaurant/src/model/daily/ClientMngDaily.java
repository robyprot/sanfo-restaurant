package model.daily;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import database.Database;
import database.JavaDBException;


public class ClientMngDaily {

	private static ClientMngDaily instance = null;
	public ArrayList<Client> clientList;
	
	
	// constructor
	private ClientMngDaily() throws ClassNotFoundException, SQLException, JavaDBException {
		init();
	}
	
	
	private void init() throws ClassNotFoundException, SQLException, JavaDBException {
		Database.connect();
		String query = "SELECT * FROM APP.Client";
		clientList = new ArrayList<Client>();

		ResultSet res = Database.interrogate(query);
		while (res.next()) {
			clientList.add(new Client(res.getInt("ID"), 
									  res.getString("Name"), 
									  res.getString("Surname"), 
									  res.getInt("Counter"), 
									  res.getString("Allergy")));
		}
		res.close();
		System.out.println("List Clients OK");
		Database.disconnect();
	}

	
	public static ClientMngDaily getIstance() throws ClassNotFoundException,SQLException, JavaDBException {
		if (instance == null) {
			synchronized (ClientMngDaily.class) {
				if (instance == null) {
					instance = new ClientMngDaily();
				}
			}
		}
		return instance;
	}


	// Returns a list of clients
	public ArrayList<Client> getClientList() {
		return clientList;
	}


	public void setClientList(ArrayList<Client> clientList) {
		this.clientList = clientList;
	}
	

//////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////

	// Ritorna il cliente passato il nome e il cognome
	public Client getClient(String name, String surname){
		Client client = null;
		for(int i=0; i<clientList.size(); i++){
			Client c = clientList.get(i);
			if(c.getName().equalsIgnoreCase(name) && c.getSurname().equalsIgnoreCase(surname)){
				client = c;
			}
		}
		return client;
	}
	
	
	// Ritorna il cliente passato l'id
	public Client getClient(int id){
		Client client = null;
		for(int i=0; i<clientList.size(); i++){
			Client c = clientList.get(i);
			if(c.getId()==id){
				client = c;
			}
		}
		return client;
	}
	
	
	// Ritorna i clienti con allergia
	public ArrayList<Client> getAllergicClient() throws ClassNotFoundException, SQLException{
		ArrayList<Client> allergic = new ArrayList<Client>();
		for(int i=0; i<clientList.size(); i++){
			Client c = clientList.get(i);
			if(c.allergy!=null){
				allergic.add(c);
			}
		}
		return allergic;
	}
	
	
	// Ritorna i clienti vip cioè quelli con contatore>=0
	public ArrayList<Client> getVipClient() throws ClassNotFoundException, SQLException{
		ArrayList<Client> vip = new ArrayList<Client>();
		for(int i=0; i<clientList.size(); i++){
			Client c = clientList.get(i);
			if(c.getCounter()>=0){
				vip.add(c);
			}
		}
		return vip;
	}
	
	
	// Dato un id cliente, incrementa di 1 il suo contatore
	public void increaseCounter(int id) throws ClassNotFoundException, SQLException{
		for(int i=0; i<clientList.size(); i++){
			Client c = clientList.get(i);
			if(c.getId()==id){
				int count = c.getCounter();
				c.setCounter(count+1);
				clientList.set(i, c);
			}
		}
	}
	
	
	// Dato un nome cognome cliente, incrementa di 1 il suo contatore
	public void increaseCounter(String name, String surname) throws ClassNotFoundException, SQLException{
		for(int i=0; i<clientList.size(); i++){
			Client c = clientList.get(i);
			if(c.getName().equalsIgnoreCase(name) && c.getSurname().equalsIgnoreCase(surname)){
				int count = c.getCounter();
				c.setCounter(count+1);
				clientList.set(i, c);
			}
		}
	}
	
	
	// Dato un id cliente, aggiungi allergia
	public void addAllergy(int id, String allergy) throws ClassNotFoundException, SQLException{
		for(int i=0; i<clientList.size(); i++){
			Client c = clientList.get(i);
			if(c.getId()==id){
				c.setAllergy(allergy);
				clientList.set(i, c);
			}
		}
	}


	// Dato un nome cognome cliente, aggiungi allergia
	public void addAllergy(String name, String surname, String allergy) throws ClassNotFoundException, SQLException{
		for(int i=0; i<clientList.size(); i++){
			Client c = clientList.get(i);
			if(c.getName().equalsIgnoreCase(name) && c.getSurname().equalsIgnoreCase(surname)){
				c.setAllergy(allergy);
				clientList.set(i, c);
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}