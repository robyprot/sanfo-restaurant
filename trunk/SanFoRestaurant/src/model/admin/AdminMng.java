package model.admin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import view.AdminView;

import model.daily.Client;
import model.daily.Food;
import model.daily.NearTable;
import model.daily.Reservation;
import model.daily.Room;
import model.daily.Settings;
import model.daily.Table;
import model.daily.TablesCheck;
import model.daily.UsersLogin;
import model.daily.Waiter;
import database.Database;
import database.JavaDBException;

// TODO: Auto-generated Javadoc
/**
 * Classe che gestisce le interazioni con il database che riguardano la parte Amministrazione.
 * 
 * @author Mauro
 */
public class AdminMng {

	/** The instance. */
	private static AdminMng instance = null;
	
	/** The row deleted admin. */
	public static ArrayList<Integer> rowDeletedAdmin = new ArrayList<Integer>();	// array che contiene le righe eliminate
	
	
	/**
	 * Istanzia un nuovo Manager Admin.
	 */
	private AdminMng(){}

	
	/**
	 * Ritorna l'istanza.
	 *
	 * @return l'istanza
	 */
	public static AdminMng getIstance() {
		if (instance == null) {
			synchronized (AdminMng.class) {
				if (instance == null) {
					instance = new AdminMng();
				}
			}
		}
		return instance;
	}
	
	

	/**
	 * Ritorno tutti i clienti in un oggetto ResultSet.
	 *
	 * @return tutti i clienti
	 * @throws JavaDBException the java db exception
	 */
	public ResultSet getAllClients() throws JavaDBException{
		String qry = "SELECT * FROM APP.Client";
		ResultSet result = Database.interrogate(qry);
		return result;
	}

	
	/**
	 * Ritorno tutti i cibi in un oggetto ResultSet.
	 *
	 * @return tutti i cibi
	 * @throws JavaDBException the java db exception
	 */
	public ResultSet getAllFood() throws JavaDBException{
		String qry = "SELECT * FROM APP.Food";
		ResultSet result = Database.interrogate(qry);
		return result;
	}
	
	
	/**
	 * Ritorno tutte le sale in un oggetto ResultSet.
	 *
	 * @return tutte le sale
	 * @throws JavaDBException the java db exception
	 */
	public ResultSet getAllRooms() throws JavaDBException{
		String qry = "SELECT * FROM APP.Room";
		ResultSet result = Database.interrogate(qry);
		return result;
	}
	
	
	/**
	 * Ritorno tutti i camerieri in un oggetto ResultSet.
	 *
	 * @return tutti i camerieri
	 * @throws JavaDBException the java db exception
	 */
	public ResultSet getAllWaiters() throws JavaDBException{
		String qry = "SELECT * FROM APP.Waiter";
		ResultSet result = Database.interrogate(qry);
		return result;
	}
	
	
	/**
	 * Ritorno tutti i tavoli in un oggetto ResultSet.
	 *
	 * @return tutti i tavoli
	 * @throws JavaDBException the java db exception
	 */
	public ResultSet getAllTables() throws JavaDBException{
		String qry = "SELECT * FROM APP.TableR";
		ResultSet result = Database.interrogate(qry);
		return result;
	}
	
	
	/**
	 * Ritorno tutte le prenotazioni in un oggetto ResultSet.
	 *
	 * @return tutte le prenotazioni
	 * @throws JavaDBException the java db exception
	 */
	public ResultSet getAllReservations() throws JavaDBException{
		String qry = "SELECT * FROM APP.Reservation";
		ResultSet result = Database.interrogate(qry);
		return result;
	}
	
	
	/**
	 * Ritorno tutte le ordinazioni in un oggetto ResultSet.
	 *
	 * @return tutte le ordinazioni
	 * @throws JavaDBException the java db exception
	 */
	public ResultSet getAllOrder() throws JavaDBException{
		String qry = "SELECT * FROM APP.OrderTable";
		ResultSet result = Database.interrogate(qry);
		return result;
	}
	
	
	/**
	 * Ritorno tutte le singole ordinazioni in un oggetto ResultSet.
	 *
	 * @return tutte le singole ordinazioni
	 * @throws JavaDBException the java db exception
	 */
	public ResultSet getAllSingleOrder() throws JavaDBException{
		String qry = "SELECT * FROM APP.SingleOrder";
		ResultSet result = Database.interrogate(qry);
		return result;
	}
	
	
	/**
	 * Ritorno tutti i tavoli vicini in un oggetto ResultSet.
	 *
	 * @return tutti i tavoli vicini
	 * @throws JavaDBException the java db exception
	 */
	public ResultSet getAllNearTable() throws JavaDBException{
		String qry = "SELECT * FROM APP.NearTable";
		ResultSet result = Database.interrogate(qry);
		return result;
	}
	
	
	/**
	 * Ritorno tutti i conti in un oggetto ResultSet.
	 *
	 * @return tutti i conti
	 * @throws JavaDBException the java db exception
	 */
	public ResultSet getAllTablesCheck() throws JavaDBException{
		String qry = "SELECT * FROM APP.TablesCheck";
		ResultSet result = Database.interrogate(qry);
		return result;
	}
	
	
	/**
	 * Ritorno tutti gli utenti login in un oggetto ResultSet.
	 *
	 * @return tutti gli utenti login
	 * @throws JavaDBException the java db exception
	 */
	public ResultSet getAllUsersLogin() throws JavaDBException{
		String qry = "SELECT * FROM APP.Users";
		ResultSet result = Database.interrogate(qry);
		return result;
	}
	
	
	/**
	 * Ritorno tutti i settings in un oggetto ResultSet.
	 *
	 * @return tutti i settaggi
	 * @throws JavaDBException the java db exception
	 */
	public ResultSet getAllSettings() throws JavaDBException{
		String qry = "SELECT * FROM APP.Settings";
		ResultSet result = Database.interrogate(qry);
		return result;
	}
	
	
	/**
	 * Ritorno tutti i camerieri (per inserirli nella tabella Users) in un oggetto ResultSet.
	 *
	 * @return i camerieri per il login
	 * @throws JavaDBException the java db exception
	 */
	public ResultSet getWaitersForLogin() throws JavaDBException{
		String qry = "SELECT Numb, Surname, Name FROM APP.WAITER";
		ResultSet result = Database.interrogate(qry);
		return result;
	}
	

	
	
	/**
	 * Metodo per inserire (solo al 1° Login) i camerieri nella tabella Users del DB.
	 *
	 * @param res i camerieri per il login
	 * @throws SQLException the sQL exception
	 * @throws JavaDBException the java db exception
	 */
	public void insertUsersLogin(ResultSet res) throws SQLException, JavaDBException {
		while(res.next()){
			String numb = String.valueOf(res.getInt("Numb"));
			String identity = res.getString("Surname") + res.getString("Name");
			String qry = "insert into APP.Users (ID, Name, Password, Type, DBupdate) values ('" +numb+ "', '" +identity+ "', '" +identity+ "', 'Waiter', true)";
			System.out.println(qry);
			Database.update(qry);
		}
	}
	
	
	
	/**
	 * Ritorno il numero di elementi di una tabella.
	 *
	 * @param nameTable il nome della tabella
	 * @return il numero di elementi della tabella
	 * @throws JavaDBException the java db exception
	 * @throws SQLException the sQL exception
	 */
	public int getNumbElementTableDB(String nameTable) throws JavaDBException, SQLException{
		int num = 0;
		String qry = "SELECT COUNT(I) FROM APP." + nameTable;
		ResultSet result = Database.interrogate(qry);
		while(result.next()){
			num = result.getInt(1);
		}
		return num;
	}
	

	
	/**
	 * Ritorna il valore della variabile FirstLogin.
	 *
	 * @return la variabile firstLogin
	 * @throws JavaDBException the java db exception
	 * @throws SQLException the sQL exception
	 */
	public boolean getFirstLogin() throws JavaDBException, SQLException{
		boolean firstLogin = false;
		String qry = "SELECT Value FROM APP.SETTINGS WHERE NameParam = 'FirstLogin'";
		ResultSet result = Database.interrogate(qry);
		while(result.next()){
			String s = result.getString(1);
			if(s.equalsIgnoreCase("true")){
				firstLogin = true;
			}
			else {
				firstLogin = false;
			}
		}
		return firstLogin;
	}
	
	
	
	/**
	 * Setta la variabile FirstLogin al valore passato.
	 *
	 * @param b il valore a cui settare FirstLogin 
	 * @throws JavaDBException the java db exception
	 */
	public void setFirstLogin(boolean b) throws JavaDBException{
		String qry = "update APP.Settings set Value = '" +b+ "' where I = 3";
		Database.update(qry);
	}
	
	
	//////////////////////////////////////////////////////////////////////////////////////////
	
	
	/**
	 * Metodo che inserisce l'id della riga da cancellare nell'array.
	 *
	 * @param numb la riga
	 */
	public static void insertRowDeleted(int numb){
		rowDeletedAdmin.add(numb);
	}
	
	
	
	/**
	 * Metodo che inserisce un ArrayList di Client (provenienti dalla JTable) nel DB.
	 *
	 * @param arr l'array di clienti
	 * @throws JavaDBException the java db exception
	 * @throws SQLException the sQL exception
	 */
	public void insertClientIntoDB(ArrayList<Client> arr) throws JavaDBException, SQLException{
		Database.connect();
		for(int i=0; i<arr.size(); i++){
			Client c = arr.get(i);
			int a = c.getA();
			int id = c.getId();
			String name = c.getName();
			String surname = c.getSurname();
			int counter = c.getCounter();
			String allergy = c.getAllergy();
			String qry;
			// Inserimento
			if (a==0){
				if(allergy.equalsIgnoreCase("")){
					qry = "insert into APP.Client (ID, Name, Surname, Counter, Allergy) values (" +id+ ", '" +name+ "', '" +surname+ "', " +counter+ ", null)";	
				}
				else{
					qry = "insert into APP.Client (ID, Name, Surname, Counter, Allergy) values (" +id+ ", '" +name+ "', '" +surname+ "', " +counter+ ", '" +allergy+ "')";
				}
				Database.update(qry);
			}
			// Aggiornamento
			else {
				qry = "update APP.Client set ID = " +id+ ", Name = '" +name+ "', Surname = '" +surname+ 
			 	 			 "', Counter = " +counter+ ", Allergy = '" +allergy+ "' where I = " +a;
				Database.update(qry);
			}
		}
		// Cancellazione
		for(int i=0; i<rowDeletedAdmin.size(); i++){
			int numb = rowDeletedAdmin.get(i);
			String qry = "delete from APP.Client where I = " +numb;
			Database.update(qry);
		}
		rowDeletedAdmin.clear();	
		System.out.println("Salvataggio effettuato");
		Database.disconnect();
		// Aggiornamento view
		Database.connect();
		ResultSet res = this.getAllClients();
		AdminView.viewClientTable(res);
		Database.disconnect();
	}
		
	
	
	/**
	 * Metodo che inserisce un ArrayList di Room (provenienti dalla JTable) nel DB.
	 *
	 * @param arr l'array di sale
	 * @throws JavaDBException the java db exception
	 * @throws SQLException the sQL exception
	 */
	public void insertRoomIntoDB(ArrayList<Room> arr) throws JavaDBException, SQLException{
		Database.connect();
		for(int i=0; i<arr.size(); i++){
			Room r = arr.get(i);
			int a = r.getA();
			int roomNumb = r.getRoomNumb();
			String name = r.getName();
			int mq = r.getMq();
			String qry;
			// Inserimento
			if (a==0){
				qry = "insert into APP.Room (RoomNumb, Name, Mq) values (" +roomNumb+ ", '" +name+ "', " +mq+ ")";
				Database.update(qry);
			}
			// Aggiornamento
			else {
				qry = "update APP.Room set RoomNumb = " +roomNumb+ ", Name = '" +name+ "', Mq = " +mq+ " where I = " +a; 
				Database.update(qry);
			}
		}
		// Cancellazione
		for(int i=0; i<rowDeletedAdmin.size(); i++){
			int numb = rowDeletedAdmin.get(i);
			String qry = "delete from APP.Room where I = " +numb;
			Database.update(qry);
		}
		rowDeletedAdmin.clear();	
		System.out.println("Salvataggio effettuato");
		Database.disconnect();
		// Aggiornamento view
		Database.connect();
		ResultSet res = this.getAllRooms();
		AdminView.viewRoomTable(res);
		Database.disconnect();
	}
		

	
	/**
	 * Metodo che inserisce un ArrayList di Food (provenienti dalla JTable) nel DB.
	 *
	 * @param arr l'array di cibi
	 * @throws JavaDBException the java db exception
	 * @throws SQLException the sQL exception
	 */
	public void insertFoodIntoDB(ArrayList<Food> arr) throws JavaDBException, SQLException{
		Database.connect();
		for(int i=0; i<arr.size(); i++){
			Food f = arr.get(i);
			int a = f.getA();
			String code = f.getCode();
			String name = f.getName();
			float price = f.getPrice();
			String type = f.getType();
			String description = f.getDescription();
			String qry;
			// Inserimento
			if (a==0){
				if(description.equalsIgnoreCase("")){
					qry = "insert into APP.Food (Code, Name, Price, Type, Description) values ('" +code+ "', '" +name+ "', " +price+ ", '" +type+ "', null)";	
				}
				else{
					qry = "insert into APP.Food (Code, Name, Price, Type, Description) values ('" +code+ "', '" +name+ "', " +price+ ", '" +type+ "', '" +description+ "')";
				}
				Database.update(qry);
			}
			// Aggiornamento
			else {
				qry = "update APP.Food set Code = '" +code+ "', Name = '" +name+ "', Price = " +price+ 
					  ", Type = '" +type+ "', Description = '" +description+ "' where I = " +a; 
				Database.update(qry);
			}
		}
		// Cancellazione
		for(int i=0; i<rowDeletedAdmin.size(); i++){
			int numb = rowDeletedAdmin.get(i);
			String qry = "delete from APP.Food where I = " +numb;
			Database.update(qry);
		}
		rowDeletedAdmin.clear();	
		System.out.println("Salvataggio effettuato");
		Database.disconnect();
		// Aggiornamento view
		Database.connect();
		ResultSet res = this.getAllFood();
		AdminView.viewFoodTable(res);
		Database.disconnect();
	}
		
	
	
	/**
	 * Metodo che inserisce un ArrayList di Waiter (provenienti dalla JTable) nel DB.
	 *
	 * @param arr l'array di camerieri
	 * @throws JavaDBException the java db exception
	 * @throws SQLException the sQL exception
	 */
	public void insertWaiterIntoDB(ArrayList<Waiter> arr) throws JavaDBException, SQLException{
		Database.connect();
		for(int i=0; i<arr.size(); i++){
			Waiter w = arr.get(i);
			int a = w.getA();
			int numb = w.getNumb();
			String surname = w.getSurname();
			String name = w.getName();
			String qry;
			// Inserimento
			if (a==0){
				qry = "insert into APP.Waiter (Numb, Surname, Name) values (" +numb+ ", '" +surname+ "', '" +name+ "')";
				Database.update(qry);
			}
			// Aggiornamento
			else {
				qry = "update APP.Waiter set Numb = " +numb+ ", Surname = '" +surname+ "', Name = '" +name+ 
				      "' where I = " +a; 
				Database.update(qry);
			}
		}
		// Cancellazione
		for(int i=0; i<rowDeletedAdmin.size(); i++){
			int nb = rowDeletedAdmin.get(i);
			String qry = "delete from APP.Waiter where I = " +nb;
			Database.update(qry);
		}
		rowDeletedAdmin.clear();	
		System.out.println("Salvataggio effettuato");
		Database.disconnect();
		// Aggiornamento view
		Database.connect();
		ResultSet res = this.getAllWaiters();
		AdminView.viewWaiterTable(res);
		Database.disconnect();
	}
		

	
	/**
	 * Metodo che inserisce un ArrayList di Table (provenienti dalla JTable) nel DB.
	 *
	 * @param arr l'array di tavoli
	 * @throws JavaDBException the java db exception
	 * @throws SQLException the sQL exception
	 */
	public void insertTableIntoDB(ArrayList<Table> arr) throws JavaDBException, SQLException{
		Database.connect();
		for(int i=0; i<arr.size(); i++){
			Table t = arr.get(i);
			int a = t.getA();
			int tableNumb = t.getTableNumb();
			int seats = t.getSeats();
			int content = t.getContent();
			String state = t.getState();
			int waiterCode = t.getWaiterCode();
			String joined = t.getJoined();
			String qry;
			// Inserimento
			if (a==0){
				if(joined.equalsIgnoreCase("")){
					qry = "insert into APP.TableR (TableNumb, Seats, Content, State, WaiterCode, Joined) values (" +tableNumb+ ", " +seats+ ", " +content+ ", '" +state+ "', " +waiterCode+ ", null)";	
				}
				else{
					qry = "insert into APP.TableR (TableNumb, Seats, Content, State, WaiterCode, Joined) values (" +tableNumb+ ", " +seats+ ", " +content+ ", '" +state+ "', " +waiterCode+ ", '" +joined+ "')";
				}
				Database.update(qry);
			}
			// Aggiornamento
			else {
				qry = "update APP.TableR set TableNumb = " +tableNumb+ " , Seats = " +seats+ ", Content = " +content+ 
					  ", State = '" +state+ "', WaiterCode = " +waiterCode+ ", Joined = '" +joined+
				      "' where I = " +a; 
				Database.update(qry);
			}
		}
		// Cancellazione
		for(int i=0; i<rowDeletedAdmin.size(); i++){
			int nb = rowDeletedAdmin.get(i);
			String qry = "delete from APP.TableR where I = " +nb;
			Database.update(qry);
		}
		rowDeletedAdmin.clear();	
		System.out.println("Salvataggio effettuato");
		Database.disconnect();
		// Aggiornamento view
		Database.connect();
		ResultSet res = this.getAllTables();
		AdminView.viewTablesTable(res);
		Database.disconnect();
	}
		

	
	/**
	 * Metodo che inserisce un ArrayList di Reservation (provenienti dalla JTable) nel DB.
	 *
	 * @param arr l'array di prenotazioni
	 * @throws JavaDBException the java db exception
	 * @throws SQLException the sQL exception
	 */
	public void insertReservationIntoDB(ArrayList<Reservation> arr) throws JavaDBException, SQLException{
		Database.connect();
		for(int i=0; i<arr.size(); i++){
			Reservation r = arr.get(i);
			int a = r.getA();
			int reservNumb = r.getReservationNumb();
			GregorianCalendar calend = r.getCalend();
			int year = calend.get(Calendar.YEAR);
			String month = AdminMng.checkFormat(calend.get(Calendar.MONTH));
			String day = AdminMng.checkFormat(calend.get(Calendar.DAY_OF_MONTH));
			String date = year+ "-" +month+ "-" +day; 
			String hour = AdminMng.checkFormat(calend.get(Calendar.HOUR_OF_DAY));
			String min = AdminMng.checkFormat(calend.get(Calendar.MINUTE));
			String sec = "00";
			String time = hour+ ":" +min+ ":" +sec;
			int reservTable = r.getReservedTable();
			int idClient = r.getIdClient();
			if(idClient==0){							// se è un nuovo ciente
				idClient = this.insertNewClient();		// inserisco il nuovo cliente nella tabella Client del DB e ritorno il nuovo ID
			}
			else{										// altrimenti
				this.incrementCounter(idClient);		// incremento il contatore del cliente
			}
			int peopleNumb = r.getPeopleNumb();
			String qry;
			// Inserimento
			if (a==0){
				qry = "insert into APP.Reservation (ReservationNumb, Date, Time, ReservedTable, IDClient, PeopleNumb) values " +
					  "(" +reservNumb+ ", '" +date+ "', '" +time+ "', " +reservTable+ ", " +idClient+ ", " +peopleNumb+ ")";
				System.out.println(qry);
				Database.update(qry);
			}
			// Aggiornamento
			else {
				qry = "update APP.Reservation set ReservationNumb = " +reservNumb+ ", Date = '" +date+ "', Time = '" +time+ 
	 			 	  "', ReservedTable = " +reservTable+ ", IDClient = " +idClient+ ", PeopleNumb = " +peopleNumb+
	 			 	  " where I = " +a; 
				System.out.println(qry);
				Database.update(qry);
			}
		}
		// Cancellazione
		for(int i=0; i<rowDeletedAdmin.size(); i++){
			int nb = rowDeletedAdmin.get(i);
			String qry = "delete from APP.Reservation where I = " +nb;
			Database.update(qry);
		}
		rowDeletedAdmin.clear();	
		System.out.println("Salvataggio effettuato");
		Database.disconnect();
		// Aggiornamento view
		Database.connect();
		ResultSet res = this.getAllReservations();
		AdminView.viewReservationTable(res);
		Database.disconnect();
	}
		
	
	
	/**
	 * // Metodo che inserisce un ArrayList di NearTable (provenienti dalla JTable) nel DB.
	 *
	 * @param arr l'array di tavoli vicini
	 * @throws JavaDBException the java db exception
	 * @throws SQLException the sQL exception
	 */
	public void insertNearTableIntoDB(ArrayList<NearTable> arr) throws JavaDBException, SQLException{
		Database.connect();
		for(int i=0; i<arr.size(); i++){
			NearTable n = arr.get(i);
			int a = n.getA();
			int table1 = n.getTable1();
			int table2 = n.getTable2();
			String qry;
			// Inserimento
			if (a==0){
				qry = "insert into APP.NearTable (Table1, Table2) values (" +table1+ ", " +table2+ ")";
				Database.update(qry);
			}
			// Aggiornamento
			else {
				qry = "update APP.NearTable set Table1 = " +table1+ ", Table2 = " +table2+ " where I = " +a;
				Database.update(qry);
			}
		}
		// Cancellazione
		for(int i=0; i<rowDeletedAdmin.size(); i++){
			int nb = rowDeletedAdmin.get(i);
			String qry = "delete from APP.NearTable where I = " +nb;
			Database.update(qry);
		}
		rowDeletedAdmin.clear();	
		System.out.println("Salvataggio effettuato");
		Database.disconnect();
		// Aggiornamento view
		Database.connect();
		ResultSet res = this.getAllNearTable();
		AdminView.viewNearTable(res);
		Database.disconnect();
	}
	
	
	
	/**
	 * Metodo che inserisce un ArrayList di UsersLogin (provenienti dalla JTable) nel DB.
	 *
	 * @param arr l'array di utenti login
	 * @throws JavaDBException the java db exception
	 * @throws SQLException the sQL exception
	 */
	public void insertUsersLoginIntoDB(ArrayList<UsersLogin> arr) throws JavaDBException, SQLException{
		Database.connect();
		for(int i=0; i<arr.size(); i++){
			UsersLogin u = arr.get(i);
			int a = u.getA();
			String id = u.getId();
			String name = u.getName();
			String password = u.getPassword();
			String type = u.getType();
			boolean dbUpdate = u.isDbUpdate();
			String qry;
			// Inserimento
			if (a==0){
				qry = "insert into APP.Users (ID, Name, Password, Type, DBupdate) values ('" +id+ "', '" +name+ "', '" +password+ "', '" +type+ "', " +dbUpdate+ ")";
				Database.update(qry);
			}
			// Aggiornamento
			else {
				qry = "update APP.Users set ID = '" +id+ "', Name = '" +name+ "', Password = '" +password+ "', " +
					  "Type = '" +type+ "', DBupdate = " +dbUpdate+ " where I = " +a; 	
				Database.update(qry);
			}
		}
		// Cancellazione
		for(int i=0; i<rowDeletedAdmin.size(); i++){
			int nb = rowDeletedAdmin.get(i);
			String qry = "delete from APP.Users where I = " +nb;
			Database.update(qry);
		}
		rowDeletedAdmin.clear();	
		System.out.println("Salvataggio effettuato");
		Database.disconnect();
		// Aggiornamento view
		Database.connect();
		ResultSet res = this.getAllUsersLogin();
		AdminView.viewUsersLogin(res);
		Database.disconnect();
	}
		
		
	
	/**
	 * Metodo che inserisce un ArrayList di Settings (provenienti dalla JTable) nel DB.
	 *
	 * @param arr l'array dei settaggi
	 * @throws JavaDBException the java db exception
	 * @throws SQLException the sQL exception
	 */
	public void insertSettingsIntoDB(ArrayList<Settings> arr) throws JavaDBException, SQLException{
		Database.connect();
		for(int i=0; i<arr.size(); i++){
			Settings s = arr.get(i);
			int a = s.getA();
			String nameParam = s.getNameParam();
			String value = s.getValue();
			String qry;
			// Inserimento
			if (a==0){
				qry = "insert into APP.Settings (NameParam, Value) values ('" +nameParam+ "', '" +value+ "')";
				Database.update(qry);
			}
			// Aggiornamento
			else {
				qry = "update APP.Settings set NameParam = '" +nameParam+ "', Value = '" +value+ "' where I = " +a; 	
				Database.update(qry);
			}
		}
		// Cancellazione
		for(int i=0; i<rowDeletedAdmin.size(); i++){
			int nb = rowDeletedAdmin.get(i);
			String qry = "delete from APP.Settings where I = " +nb;
			Database.update(qry);
		}
		rowDeletedAdmin.clear();	
		System.out.println("Salvataggio effettuato");
		Database.disconnect();
		// Aggiornamento view
		Database.connect();
		ResultSet res = this.getAllSettings();
		AdminView.viewSettings(res);
		Database.disconnect();
	}
		

	
	/**
	 * Metodo che inserisce un ArrayList di TablesCheck (provenienti dalla JTable) nel DB.
	 *
	 * @param arr l'array di conti
	 * @throws JavaDBException the java db exception
	 * @throws SQLException the sQL exception
	 */
	public void insertTablesCheckIntoDB(ArrayList<TablesCheck> arr) throws JavaDBException, SQLException{
		Database.connect();
		for(int i=0; i<arr.size(); i++){
			TablesCheck c = arr.get(i);
			int a = c.getA();
			GregorianCalendar calend = c.getCalend();
			int year = calend.get(Calendar.YEAR);
			String month = AdminMng.checkFormat(calend.get(Calendar.MONTH));
			String day = AdminMng.checkFormat(calend.get(Calendar.DAY_OF_MONTH));
			String date = year+ "-" +month+ "-" +day; 
			String hour = AdminMng.checkFormat(calend.get(Calendar.HOUR_OF_DAY));
			String min = AdminMng.checkFormat(calend.get(Calendar.MINUTE));
			String sec = AdminMng.checkFormat(calend.get(Calendar.SECOND));
			String time = hour+ ":" +min+ ":" +sec;
			float amount = c.getAmount();
			int peopleNumb = c.getPeopleNumb();
			String qry;
			// Inserimento
			if (a==0){
				qry = "insert into APP.TablesCheck (Date, Time, Amount, PeopleNumb) values " +
			   		  "('" +date+ "', '" +time+ "', " +amount+ ", " +peopleNumb+ ")";	
				Database.update(qry);
			}
			// Aggiornamento
			else {
				qry = "update APP.TablesCheck set Date = '" +date+ "', Time = '" +time+ 
					  "', Amount = " +amount+ ", PeopleNumb = " +peopleNumb+ 
					  " where I = " +a; 
				Database.update(qry);
			}
		}
		// Cancellazione
		for(int i=0; i<rowDeletedAdmin.size(); i++){
			int nb = rowDeletedAdmin.get(i);
			String qry = "delete from APP.TablesCheck where I = " +nb;
			Database.update(qry);
		}
		rowDeletedAdmin.clear();	
		System.out.println("Salvataggio effettuato");
		Database.disconnect();
		// Aggiornamento view
		Database.connect();
		ResultSet res = this.getAllTablesCheck();
		AdminView.viewTablesTable(res);
		Database.disconnect();
	}
	

	/**
	 * Metodo per aggiungere lo 0 ai componenti della data.
	 *
	 * @param a l'intero giorno o mese della data
	 * @return stringa formattata
	 */
	public static String checkFormat(int a){
		String res = null;
		if (a>=0 && a<=9) res = "0" + a;
		else res = String.valueOf(a);
		return res;
	}
	
	
	
	/**
	 * Metodo che inserisce un nuovo cliente nel DB (caso di prenotazione da parte di un nuovo cliente) e ritorna il nuovo ID.
	 *
	 * @return il nuovo id
	 * @throws JavaDBException the java db exception
	 * @throws SQLException the sQL exception
	 */
	public int insertNewClient() throws JavaDBException, SQLException{
		// prima mi faccio restituire il max ID presente nella tabella Client e ci sommo 1
		String qry1 = "select max(ID) from APP.CLIENT";
		ResultSet res = Database.interrogate(qry1);
		int max = 0;
		while(res.next()){
			max = res.getInt(1);
		}
		max++;
		// poi aggiungo una nuova riga nella tabella Client
		String qry2 = "insert into APP.Client (ID, Name, Surname, Counter, Allergy) values (" +max+ ", '-----', '-----', 1, null)";
		Database.update(qry2);
		return max;
	}
	
	

	/**
	 * Metodo che incrementa il contatore di un cliente.
	 *
	 * @param idClient l'id del cliente
	 * @throws JavaDBException the java db exception
	 * @throws SQLException the sQL exception
	 */
	public void incrementCounter(int idClient) throws JavaDBException, SQLException{
		// estraggo il valore del contatore del cliente e lo incremento di 1
		String qry1 = "select Counter from APP.CLIENT where ID = " +idClient;
		ResultSet res = Database.interrogate(qry1);
		int count = 0;
		while(res.next()){
			count = res.getInt(1);
		}
		count++;
		// aggiorno nel DB
		String qry2 = "UPDATE APP.CLIENT SET Counter = " +count+ " WHERE ID = " +idClient;
		Database.update(qry2);
	}
	

	
	/**
	 * Metodo che cancella tutti i camerieri dalla tabella Users.
	 *
	 * @throws JavaDBException the java db exception
	 */
	public void deleteWaitersIntoUsers() throws JavaDBException{
		String qry = "delete from APP.USERS where Type = 'Waiter'";
		Database.update(qry);
	}
	
	
	
	/**
	 * Metodo che inserisce tutti i camerieri nella tabella Users.
	 *
	 * @param waiters i camerieri
	 * @throws SQLException the sQL exception
	 * @throws JavaDBException the java db exception
	 */
	public void insertWaitersIntoUsers(ResultSet waiters) throws SQLException, JavaDBException{
		while(waiters.next()){
			String id = String.valueOf(waiters.getInt("Numb"));
			String name = waiters.getString("Surname") + waiters.getString("Name");
			String password = name;
			String qry = "insert into APP.Users (ID, Name, Password, Type, DBupdate) values ('" +id+ "', '" +name+ "', '" +password+ "', 'Waiter', true)";
			Database.update(qry);
		}
	}
	
	
	/**
	 * Metodo che setta a true il campo di Users quando i sono modifiche nelle tabelle Tavoli e Cibi.
	 *
	 * @throws JavaDBException the java db exception
	 */
	public void setTrueUsers() throws JavaDBException{
		Database.connect();
		String qry = "UPDATE APP.USERS SET DBupdate = true";
		Database.update(qry);
		Database.disconnect();
	}
	
	
	/**
	 * Metodo che ritorna il numero di Amministratori presenti nella tabella Users
	 * 
	 * @return numero di amministratori
	 * @throws JavaDBException 
	 * @throws SQLException 
	 */
	public int getNumbAdmin() throws JavaDBException, SQLException{
		int num = 0;
		Database.connect();
		String qry = "select count(*) from APP.USERS where Type = 'Admin'";
		ResultSet res = Database.interrogate(qry);
		while(res.next()){
			num = res.getInt(1);
		}
		return num;
	}
	
	
}
