package model.daily;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Database;
import database.JavaDBException;


public class NearTableMngDaily {

	private static NearTableMngDaily instance = null;
	public ArrayList<NearTable> nearTableList;

	// constructor
	private NearTableMngDaily() throws ClassNotFoundException, SQLException, JavaDBException {
		init();
	}
	
	
	private void init() throws ClassNotFoundException, SQLException, JavaDBException {
		Database.connect();
		String query = "SELECT * FROM APP.NearTable";
		nearTableList = new ArrayList<NearTable>();
		
		ResultSet res = Database.interrogate(query);
		while (res.next()) {
			nearTableList.add(new NearTable(res.getInt("Table1"), 
								  res.getInt("Table2")));
		}
		res.close();
		System.out.println("List NearTable OK");
		Database.disconnect();
	}
	
	
	public static NearTableMngDaily getIstance() throws ClassNotFoundException,SQLException, JavaDBException {
		if (instance == null) {
			synchronized (NearTableMngDaily.class) {
				if (instance == null) {
					instance = new NearTableMngDaily();
				}
			}
		}
		return instance;
	}
	
	
	// Returns a list of nears tables
	public ArrayList<NearTable> getSingleOrderList() {
		return nearTableList;
	}


	public void setNearTableList(ArrayList<NearTable> nearTableList) {
		this.nearTableList = nearTableList;
	}

	
	//////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	
	
	
	
	
	
	
	
	
}

















