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


public class TablesCheckMngDaily {

	private static TablesCheckMngDaily instance = null;
	public ArrayList<TablesCheck> tablesCheckList;
	
	// constructor
	private TablesCheckMngDaily() throws ClassNotFoundException, SQLException, JavaDBException {
		init();
	}
	
	
	private void init() throws ClassNotFoundException, SQLException, JavaDBException {
		Database.connect();
		String query = "SELECT * FROM APP.TablesCheck";
		tablesCheckList = new ArrayList<TablesCheck>();
		
		ResultSet res = Database.interrogate(query);
		while (res.next()) {
			Date d = res.getDate("Date");
			Time t = res.getTime("Time");
			GregorianCalendar greg = new GregorianCalendar();
			greg.setTime(d);
			greg.set(Calendar.HOUR, t.getHours());
			greg.set(Calendar.MINUTE, t.getMinutes());
			greg.set(Calendar.SECOND, t.getSeconds());
			tablesCheckList.add(new TablesCheck(res.getInt("ID"),
												greg,
												res.getFloat("Amount"), 
												res.getInt("PeopleNumb")));
		}
		res.close();
		System.out.println("List TablesCheck OK");
		Database.disconnect();
	}
	
	
	public static TablesCheckMngDaily getIstance() throws ClassNotFoundException,SQLException, JavaDBException {
		if (instance == null) {
			synchronized (TablesCheckMngDaily.class) {
				if (instance == null) {
					instance = new TablesCheckMngDaily();
				}
			}
		}
		return instance;
	}
	
	
	// Returns a list of TablesCheck
	public ArrayList<TablesCheck> getTablesCheckList() {
		return tablesCheckList;
	}


	public void setTablesCheckList(ArrayList<TablesCheck> tablesCheckList) {
		this.tablesCheckList = tablesCheckList;
	}


	//////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	
	
	// Ritorna il conto passato l'id
	public TablesCheck getTablesCheck(int id){
		TablesCheck check = null;
		for(int i=0; i<tablesCheckList.size(); i++){
			TablesCheck t = tablesCheckList.get(i);
			if(t.getId()==id){
				check = t;
			}
		}
		return check;
	}
	
	
	// Ritorna i conti di un certo giorno (ora non importa)
	public ArrayList<TablesCheck> getTablesCheckOnDate(GregorianCalendar gr) throws ClassNotFoundException, SQLException{
		ArrayList<TablesCheck> check = new ArrayList<TablesCheck>();
		for(int i=0; i<tablesCheckList.size(); i++){		
			TablesCheck r = tablesCheckList.get(i);
			if(r.getCalend().get(Calendar.YEAR)==gr.get(Calendar.YEAR)
					&& r.getCalend().get(Calendar.MONTH)==gr.get(Calendar.MONTH)	
					&& r.getCalend().get(Calendar.DAY_OF_MONTH)==gr.get(Calendar.DAY_OF_MONTH)){
				check.add(r);
			}
		}
		return check;
	}
	
	
	
	
	
	
	
	
}

















