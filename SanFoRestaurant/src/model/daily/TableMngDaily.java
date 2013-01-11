package model.daily;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import model.ManagerDailyInterface;

import database.Database;
import database.JavaDBException;


// TODO: Auto-generated Javadoc
/**
 * Classe che crea la lista di Table e che contiene i metodi per gestirla.
 * 
 * @author Mauro
 */
public class TableMngDaily implements ManagerDailyInterface {
	
	/** The instance. */
	private static TableMngDaily instance = null;
	
	/** The table list. */
	public ArrayList<Table> tableList;
	
	/** The near table list. */
	public ArrayList<NearTable> nearTableList;
	
	
	/**
	 * Istanzia un nuovo Manager Tavolo Daily.
	 *
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the sQL exception
	 * @throws JavaDBException the java db exception
	 */
	private TableMngDaily() throws ClassNotFoundException, SQLException, JavaDBException {
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
		String query = "SELECT * FROM APP.TableR ORDER BY TableNumb";
		tableList = new ArrayList<Table>();

		ResultSet res = Database.interrogate(query);
		while (res.next()) {
			tableList.add(new Table(res.getInt("I"),
									res.getInt("TableNumb"), 
									res.getInt("Seats"), 
									res.getInt("Content"), 
									res.getString("State"), 
									res.getInt("WaiterCode"), 
									res.getString("Joined")));
		}
		res.close();
		
		// Aggiornamento tavoli occupati in base alla lista Reservation
		ArrayList<Reservation> reservList = ReservationMngDaily.getIstance().getReservationList();
		for(int i=0; i<reservList.size(); i++){
			int table = reservList.get(i).getReservedTable();		// estraggo tavolo prenotato
			for(int j=0; j<tableList.size(); j++){					// cerco tra i tavoli quello con il numero estratto
				Table t = tableList.get(j);
				if (t.getTableNumb()==table){						// quando lo trovo
					t.setState("Occupato");							// setto il tavolo "Occupato"
					tableList.set(j, t);							// e lo aggiorno nella lista
				}
			}
		}
		
		Collections.sort(tableList);
		
		nearTableList = NearTableMngDaily.getIstance().getNearTableList();
		for(int i=0; i<nearTableList.size(); i++){
			NearTable n = nearTableList.get(i);
			int a = n.getA();
			int table1 = n.getTable1();
			int table2 = n.getTable2();
			NearTable nt = new NearTable(a, table1, table2, getTable(table1).getSeats()+getTable(table2).getSeats());
			nearTableList.set(i, nt);
		}
		System.out.println("List Tables OK");
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

	
	
	/**
	 * Ritorna la lista dei tavoli.
	 *
	 * @return la lista dei tavoli
	 */
	public ArrayList<Table> getTableList() {
		return tableList;
	}


	/**
	 * Setta la lista dei tavoli.
	 *
	 * @param tableList la nuova lista dei tavoli
	 */
	public void setTableList(ArrayList<Table> tableList) {
		this.tableList = tableList;
	}

	
	
	/**
	 * Ritorna i tavoli occupati e con il campo Joined = null.
	 *
	 * @return i tavoli occupati
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the sQL exception
	 */
	public ArrayList<Table> getOccupiedTables() throws ClassNotFoundException, SQLException{
		ArrayList<Table> occupiedTables = new ArrayList<Table>();
		for(int i=0; i<tableList.size(); i++){
			Table t = tableList.get(i);
			if(t.getState().equalsIgnoreCase("Occupato") && (t.getJoined() == null || t.getJoined().equalsIgnoreCase("") || t.getJoined().equalsIgnoreCase("null"))){
				System.out.println(t.getJoined());
				occupiedTables.add(t);
			}
		} 
		return occupiedTables;
	}
	
		

	/**
	 * Ritorna i tavoli liberi.
	 *
	 * @return i tavoli liberi
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the sQL exception
	 */
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
	
	
	
	/**
	 * Ritorna il tavolo passato il numero del tavolo.
	 *
	 * @param tableNumb il numero del tavolo
	 * @return il tavolo
	 */
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
	
	
	
	/**
	 * Dato un tavolo, setta lo stato libero/occupato.
	 *
	 * @param tableNumb il numero del tavolo
	 * @param state lo stato del tavolo
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the sQL exception
	 */
	public void setTableState(int tableNumb, String state) throws ClassNotFoundException, SQLException{
		for(int i=0; i<tableList.size(); i++){
			Table t = tableList.get(i);
			if(t.getTableNumb()==tableNumb){
				t.setState(state);
				tableList.set(i, t);
			}
		}
	}

	
	
	/**
	 * Dato un tavolo, setta i tavoli ad esso uniti.
	 *
	 * @param tableNumb il numero del tavolo
	 * @param joined i tavoli uniti
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the sQL exception
	 */
	public void setTableJoined(int tableNumb, String joined) throws ClassNotFoundException, SQLException{
		for(int i=0; i<tableList.size(); i++){
			Table t = tableList.get(i);
			if(t.getTableNumb()==tableNumb){
				t.setJoined(joined);
				tableList.set(i, t);
			}
		}
	}
	
	
	
	/**
	 * Dato il numero di persone, restituisce una stringa con il singolo tavolo oppure i tavoli da unire.
	 *
	 * @param number il numero di persone
	 * @return the string
	 */
	public String searchFreeTable(int number){
		
		int state = 0;
		int count = 0;
		int tableIndex = 0;
		while(true){
			switch (state) {
			case 0:
				for(Table t : tableList){
					if(t.getState().equalsIgnoreCase("Libero")){
						if(t.getSeats()<number)
							continue;
						if(t.getSeats()==number){
							t.setState("Occupato");
							return String.valueOf(t.getTableNumb());
						}
						if(t.getSeats()==(number+1)){
							t.setState("Occupato");
							return String.valueOf(t.getTableNumb());
						}
						if(t.getSeats()==(number+2)){
							t.setState("Occupato");
							return String.valueOf(t.getTableNumb());
						}
						if(t.getSeats()>(number+2))
							break;
					}
				}
				state = 1;  
				break;
			case 1:
				for(Table t : tableList){
					count++;
					if(t.getSeats()>number || count==tableList.size()){
						tableIndex = tableList.indexOf(t);
					}
				}
				for(int i=tableIndex; i>=0; i--){
					if(tableList.get(i).getState().equalsIgnoreCase("Libero")){
						for(NearTable nt : nearTableList){
							if((nt.getTable1()==tableList.get(i).getTableNumb()) && 
									((nt.getSumSeats())==number) && 
									getTable(nt.getTable2()).getState().equalsIgnoreCase("Libero")){
								getTable(nt.table1).setState("Occupato");
								getTable(nt.table2).setState("Occupato");
								return String.valueOf(nt.getTable1())+"-"+String.valueOf(nt.getTable2());
							}
						}
					}
				}
				for(int i=tableIndex; i>=0; i--){
					if(tableList.get(i).getState().equalsIgnoreCase("Libero")){
						for(NearTable nt : nearTableList){
							if((nt.getTable1()==tableList.get(i).getTableNumb()) && 
									((nt.getSumSeats())==(number+1)) && 
									getTable(nt.getTable2()).getState().equalsIgnoreCase("Libero")){
								getTable(nt.table1).setState("Occupato");
								getTable(nt.table2).setState("Occupato");
								return String.valueOf(nt.getTable1())+"-"+String.valueOf(nt.getTable2());
							}
						}
					}
				}
				for(int i=tableIndex; i>=0; i--){
					if(tableList.get(i).getState().equalsIgnoreCase("Libero")){
						for(NearTable nt : nearTableList){
							if((nt.getTable1()==tableList.get(i).getTableNumb()) && 
									((nt.getSumSeats())==(number+2)) && 
									getTable(nt.getTable2()).getState().equalsIgnoreCase("Libero")){
								getTable(nt.table1).setState("Occupato");
								getTable(nt.table2).setState("Occupato");
								return String.valueOf(nt.getTable1())+"-"+String.valueOf(nt.getTable2());
							}
						}
					}
				}
				state = 2;
				break;
			case 2:
				for(int i=tableIndex; i>=0; i--){
					if(tableList.get(i).getState().equalsIgnoreCase("Libero")){
						for(NearTable nt : nearTableList){
							if((nt.getTable1()==tableList.get(i).getTableNumb()) && 
									getTable(nt.getTable2()).getState().equalsIgnoreCase("Libero")){
								for(NearTable nt2 : nearTableList){
									if(nt2.getTable1()==nt.getTable2() && 
											getTable(nt2.getTable2()).getState().equalsIgnoreCase("Libero") &&
											(nt.getSumSeats()+getTable(nt2.getTable2()).getSeats())==number){
										getTable(nt.table1).setState("Occupato");
										getTable(nt.table2).setState("Occupato");
										getTable(nt2.table2).setState("Occupato");
										return String.valueOf(nt.getTable1())+"-"+
												String.valueOf(nt.getTable2())+"-"+
												String.valueOf(nt2.getTable2());
									}
								}
							}
						}
					}
				}
				for(int i=tableIndex; i>=0; i--){
					if(tableList.get(i).getState().equalsIgnoreCase("Libero")){
						for(NearTable nt : nearTableList){
							if((nt.getTable1()==tableList.get(i).getTableNumb()) && 
									getTable(nt.getTable2()).getState().equalsIgnoreCase("Libero")){
								for(NearTable nt2 : nearTableList){
									if(nt2.getTable1()==nt.getTable2() && 
											getTable(nt2.getTable2()).getState().equalsIgnoreCase("Libero") &&
											(nt.getSumSeats()+getTable(nt2.getTable2()).getSeats())==(number+1)){
										getTable(nt.table1).setState("Occupato");
										getTable(nt.table2).setState("Occupato");
										getTable(nt2.table2).setState("Occupato");
										return String.valueOf(nt.getTable1())+"-"+
												String.valueOf(nt.getTable2())+"-"+
												String.valueOf(nt2.getTable2());
									}
								}
							}
						}
					}
				}
				for(int i=tableIndex; i>=0; i--){
					if(tableList.get(i).getState().equalsIgnoreCase("Libero")){
						for(NearTable nt : nearTableList){
							if((nt.getTable1()==tableList.get(i).getTableNumb()) && 
									getTable(nt.getTable2()).getState().equalsIgnoreCase("Libero")){
								for(NearTable nt2 : nearTableList){
									if(nt2.getTable1()==nt.getTable2() && 
											getTable(nt2.getTable2()).getState().equalsIgnoreCase("Libero") &&
											(nt.getSumSeats()+getTable(nt2.getTable2()).getSeats())==(number+2)){
										getTable(nt.table1).setState("Occupato");
										getTable(nt.table2).setState("Occupato");
										getTable(nt2.table2).setState("Occupato");
										return String.valueOf(nt.getTable1())+"-"+
												String.valueOf(nt.getTable2())+"-"+
												String.valueOf(nt2.getTable2());
									}
								}
							}
						}
					}
				}
			state = 3;
			case 3:
				return "non c'è";
			}
			
		}
	}
	
}
