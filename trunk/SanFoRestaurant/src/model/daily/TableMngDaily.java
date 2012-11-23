package model.daily;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Database;
import database.JavaDBException;


public class TableMngDaily {
	
	private static TableMngDaily instance = null;
	public ArrayList<Table> tableList;
	
	
	// constructor
	private TableMngDaily() throws ClassNotFoundException, SQLException, JavaDBException {
		init();
	}

	private void init() throws ClassNotFoundException, SQLException, JavaDBException {
		Database.connect();
		String query = "SELECT * FROM APP.TableR";
		tableList = new ArrayList<Table>();

		ResultSet res = Database.interrogate(query);
		while (res.next()) {
			tableList.add(new Table(res.getInt("TableNumb"), 
									res.getInt("Seats"), 
									res.getInt("Content"), 
									res.getString("State"), 
									res.getInt("WaiterCode"), 
									res.getString("Joined")));
		}
		res.close();
		System.out.println("List Tables OK");
		Database.disconnect();
	}

	
	public static TableMngDaily getIstance() throws ClassNotFoundException,SQLException, JavaDBException {
		if (instance == null) {
			synchronized (TableMngDaily.class) {
				if (instance == null) {
					instance = new TableMngDaily();
				}
			}
		}
		return instance;
	}

	
	// Returns a list of tables
	public ArrayList<Table> getTableList() {
		return tableList;
	}


	public void setTableList(ArrayList<Table> tableList) {
		this.tableList = tableList;
	}
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	
	

	// Ritorna i tavoli occupati
	public ArrayList<Table> getOccupiedTables() throws ClassNotFoundException, SQLException{
		ArrayList<Table> occupiedTables = new ArrayList<Table>();
		for(int i=0; i<tableList.size(); i++){
			Table t = tableList.get(i);
			if(t.getState().equalsIgnoreCase("Occupato")){
				occupiedTables.add(t);
			}
		}
		return occupiedTables;
	}
		

	// Ritorna i tavoli liberi 
	public ArrayList<Table> getFreeTables() throws ClassNotFoundException, SQLException{
		ArrayList<Table> freeTables = new ArrayList<Table>();
		for(int i=0; i<tableList.size(); i++){		
			Table t = tableList.get(i);
			if(t.getState().equalsIgnoreCase("Libero")){
				freeTables.add(t);
			}
		}
		return freeTables;
	}
	
	
	// Ritorna i tavoli liberi con un certo numero di posti
	public ArrayList<Table> getFreeTables(int seats) throws ClassNotFoundException, SQLException{
		ArrayList<Table> freeTables = new ArrayList<Table>();
		for(int i=0; i<tableList.size(); i++){		
			Table t = tableList.get(i);
			if(t.getState().equalsIgnoreCase("Libero") && t.getSeats()==seats){
				freeTables.add(t);
			}
		}
		return freeTables;
	}
	

	// Ritorna tavoli passando uno stato
	public ArrayList<Table> getTables(String state) throws ClassNotFoundException, SQLException{
		ArrayList<Table> tables = new ArrayList<Table>();
		for(int i=0; i<tableList.size(); i++){
			Table t = tableList.get(i);
			if(t.getState().equalsIgnoreCase(state)){
				tables.add(t);
			}
		}
		return tables;
	}
	
	
	// Ritorna tavoli passando numero di posti e stato
	public ArrayList<Table> getTables(int seats, String state) throws ClassNotFoundException, SQLException{
		ArrayList<Table> tables = new ArrayList<Table>();
		for(int i=0; i<tableList.size(); i++){
			Table t = tableList.get(i);
			if(t.getSeats()==seats && t.getState().equalsIgnoreCase(state)){
				tables.add(t);
			}
		}
		return tables;
	}
	
	
	// Ritorna tavoli dato il numero di posti
	public ArrayList<Table> getTables(int seats) throws ClassNotFoundException, SQLException{
		ArrayList<Table> tables = new ArrayList<Table>();
		for(int i=0; i<tableList.size(); i++){
			Table t = tableList.get(i);
			if(t.getSeats()==seats){
				tables.add(t);
			}
		}
		return tables;
	}
	

	// Ritorna i tavoli contenuti in una sala
	public ArrayList<Table> getTablesRoom(int content) throws ClassNotFoundException, SQLException{
		ArrayList<Table> tablesRoom = new ArrayList<Table>();
		for(int i=0; i<tableList.size(); i++){
			Table t = tableList.get(i);
			if(t.getContent()==content){
				tablesRoom.add(t);
			}
		}
		return tablesRoom;
	}
	
	
	// Ritorna i tavoli con numero di posti dato, contenuti in una sala, e con un certo stato
	public ArrayList<Table> getTables(int seats, int content, String state) throws ClassNotFoundException, SQLException{
		ArrayList<Table> tables = new ArrayList<Table>();
		for(int i=0; i<tableList.size(); i++){
			Table t = tableList.get(i);
			if(t.getSeats()==seats && t.getContent()==content && t.getState().equalsIgnoreCase(state)){
				tables.add(t);
			}
		}
		return tables;
	}
	
	
	// Ritorna i tavoli serviti da un certo cameriere
	public ArrayList<Table> getTablesWaiter(int waiterCode) throws ClassNotFoundException, SQLException{
		ArrayList<Table> tablesWaiter = new ArrayList<Table>();
		for(int i=0; i<tableList.size(); i++){
			Table t = tableList.get(i);
			if(t.getWaiterCode()==waiterCode){
				tablesWaiter.add(t);
			}
		}
		return tablesWaiter;
	}
	
	
	// Ritorna i tavoli uniti
	public ArrayList<Table> getTablesJoined(String joined) throws ClassNotFoundException, SQLException{
		ArrayList<Table> tablesJoined = new ArrayList<Table>();
		for(int i=0; i<tableList.size(); i++){
			Table t = tableList.get(i);
			if(t.getJoined().equalsIgnoreCase(joined)){
				tablesJoined.add(t);
			}
		}
		return tablesJoined;
	}
	
	
	// Ritorna il tavolo passato il numero del tavolo
	public Table getTable(int tableNumb){
		Table table = null;
		for(int i=0; i<tableList.size(); i++){
			Table t = tableList.get(i);
			if(t.getTableNumb()==tableNumb){
				table = t;
			}
		}
		return table;
	}
	
	
	
	// Dato un tavolo, setta lo stato libero/occupato
	public void setTableState(int tableNumb, String state) throws ClassNotFoundException, SQLException{
		for(int i=0; i<tableList.size(); i++){
			Table t = tableList.get(i);
			if(t.getTableNumb()==tableNumb){
				t.setState(state);
				tableList.set(i, t);
			}
		}
	}
	
	
	// Dato un tavolo, setta la sala dove è contenuto
	public void setTableRoom(int tableNumb, int content) throws ClassNotFoundException, SQLException{
		for(int i=0; i<tableList.size(); i++){
			Table t = tableList.get(i);
			if(t.getTableNumb()==tableNumb){
				t.setContent(content);
				tableList.set(i, t);
			}
		}
	}
	
	
	// Dato un tavolo, setta il cameriere di riferimento
	public void setTableWaiter(int tableNumb, int waiterCode) throws ClassNotFoundException, SQLException{
		for(int i=0; i<tableList.size(); i++){
			Table t = tableList.get(i);
			if(t.getTableNumb()==tableNumb){
				t.setWaiterCode(waiterCode);
				tableList.set(i, t);
			}
		}
	}
	
	
	// Dato un tavolo, setta i tavoli ad esso uniti
	public void setTableJoined(int tableNumb, String joined) throws ClassNotFoundException, SQLException{
		for(int i=0; i<tableList.size(); i++){
			Table t = tableList.get(i);
			if(t.getTableNumb()==tableNumb){
				t.setJoined(joined);
				tableList.set(i, t);
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
