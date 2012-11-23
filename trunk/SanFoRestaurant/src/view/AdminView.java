package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.AdminCtrl;
import database.Database;
import database.JavaDBException;

public class AdminView extends JFrame {

	private AdminCtrl controller;
	private JPanel contentPane;
	private static JComboBox comboBox1;
	private static JComboBox comboBox2;
	private JScrollPane scrollPane_nascosto;
	private static JTable table;
	private JScrollPane scrollPane;
	private static JButton buttonInserisci;
	private static JButton buttonElimina;
	private static JButton buttonSalva;
	private static JButton buttonOK;
	private JButton buttonAnnulla;
	private static JTextField textDataStart;
	private static JTextField textDataStop;
	private static JLabel lblDataStart;
	private static JLabel lblDataStop;
	private static JButton buttonNuovoAdmin;
	

	private enum BOX1 { Null, Visualizza, Modifica, Statistiche };
	
	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws JavaDBException 
	 */
	public AdminView() throws ClassNotFoundException, SQLException, JavaDBException {
		
		this.controller = new AdminCtrl();
	
		//dimensioni schermo intero
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, screenSize.width, screenSize.height);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
			
		comboBox1 = new JComboBox();
		comboBox1.setToolTipText("\r\n");
		comboBox1.setBounds(61, 72, 161, 20);
		comboBox1.setName("ComboBox1");
		comboBox1.insertItemAt("Visualizza", 0);		// elementi nel 1° menu a tendina
		comboBox1.insertItemAt("Modifica", 1);
		comboBox1.insertItemAt("Statistiche", 2);
		contentPane.add(comboBox1);
		
		comboBox2 = new JComboBox();
		comboBox2.setBounds(267, 72, 161, 20);
		comboBox2.setName("ComboBox2");
		contentPane.add(comboBox2);
		
		buttonOK = new JButton("OK");
		buttonOK.setBounds(482, 71, 89, 23);
		buttonOK.setEnabled(false);
		contentPane.add(buttonOK);
		
		buttonAnnulla = new JButton("Annulla");
		buttonAnnulla.setBounds(482, 114, 89, 23);
		contentPane.add(buttonAnnulla);
		
		buttonSalva = new JButton("Salva");
		buttonSalva.setBounds(482, 158, 89, 23);
		buttonSalva.setEnabled(false);
		contentPane.add(buttonSalva);
		
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(628, 63, 701, 402);
		contentPane.add(scrollPane);
		
		scrollPane_nascosto = new JScrollPane();
		scrollPane.setViewportView(scrollPane_nascosto);
		scrollPane_nascosto.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane_nascosto.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		table = new JTable();
		scrollPane_nascosto.setViewportView(table);
		
		buttonInserisci = new JButton("Inserisci");
		buttonInserisci.setBounds(692, 487, 89, 23);
		buttonInserisci.setEnabled(false);
		contentPane.add(buttonInserisci);
		
		buttonElimina = new JButton("Elimina");
		buttonElimina.setBounds(813, 487, 89, 23);
		buttonElimina.setEnabled(false);
		contentPane.add(buttonElimina);
		
		JLabel lblOperazioni = new JLabel("Operazioni");
		lblOperazioni.setBounds(61, 35, 161, 20);
		contentPane.add(lblOperazioni);
		
		JLabel lblTabelle = new JLabel("Tabelle");
		lblTabelle.setBounds(267, 38, 161, 20);
		contentPane.add(lblTabelle);
		
		textDataStart = new JTextField();
		textDataStart.setBounds(482, 275, 89, 20);
		textDataStart.setColumns(10);
		textDataStart.setEditable(false);
		contentPane.add(textDataStart);
		
		lblDataStart = new JLabel("Da giorno (aaaa-mm-gg):\r\n");
		lblDataStart.setBounds(335, 278, 147, 14);
		contentPane.add(lblDataStart);
		
		textDataStop = new JTextField();
		textDataStop.setColumns(10);
		textDataStop.setBounds(482, 320, 89, 20);
		textDataStop.setEditable(false);
		contentPane.add(textDataStop);
		
		lblDataStop = new JLabel("A giorno (aaaa-mm-gg):");
		lblDataStop.setBounds(335, 323, 147, 14);
		contentPane.add(lblDataStop);
		
		buttonNuovoAdmin = new JButton("Inserisci Nuovo Amministratore");
		buttonNuovoAdmin.setBounds(1006, 487, 221, 23);
		buttonNuovoAdmin.setEnabled(false);
		contentPane.add(buttonNuovoAdmin);
		
		


		
		
		
		buttonOK.addActionListener(controller);
		comboBox1.addActionListener(controller);
		comboBox2.addActionListener(controller);
		buttonSalva.addActionListener(controller);
		buttonInserisci.addActionListener(controller);
		buttonElimina.addActionListener(controller);
		textDataStart.addActionListener(controller);
		textDataStop.addActionListener(controller);
		buttonAnnulla.addActionListener(controller);
		buttonNuovoAdmin.addActionListener(controller);
		this.setVisible(true);
	}
	
	



	
	
	// metodo per modificare la comboBox2 in base alla comboBox1
	public static void modifyComboBox2(String[] model) {
		comboBox2.removeAllItems();
		for (int i=0; i<model.length; i++){
			comboBox2.insertItemAt(model[i], i);
		}		
	}
	
	
	// metodo per visualizzare la tabella Clienti
	public static void viewClientTable(ResultSet res) throws SQLException{
		String col[] = {"ID", "Name", "Surname", "Counter", "Allergy"};
		//String data[][] = {{"","","","",""}}; 
		String data[][] = null;
		DefaultTableModel model = new DefaultTableModel(data, col);
		table.setModel(model);
		int i = 0;
		while(res.next()){
			int id = res.getInt("ID");
			String name = res.getString("Name");
			String surname = res.getString("Surname");
			int counter = res.getInt("Counter");
			String allergy = res.getString("Allergy");
			Object obj[] = {id, name, surname, counter, allergy};
			model.insertRow(i, obj);
			i++;
		}
	}
	
	// metodo per visualizzare la tabella Sale
	public static void viewRoomTable(ResultSet res) throws SQLException{
		String col[] = {"RoomNumb", "Name", "Type", "Mq"};
		//String data[][] = {{"","","",""}}; 
		String data[][] = null;
		DefaultTableModel model = new DefaultTableModel(data, col);
		table.setModel(model);
		int i = 0;
		while(res.next()){
			int numb = res.getInt("RoomNumb");
			String name = res.getString("Name");
			String type = res.getString("Type");
			int mq = res.getInt("Mq");
			Object obj[] = {numb, name, type, mq};
			model.insertRow(i, obj);
			i++;
		}
	}
	
	// metodo per visualizzare la tabella Cibi
	public static void viewFoodTable(ResultSet res) throws SQLException{
		String col[] = {"Code", "Name", "Price", "Type", "Description"};
		//String data[][] = {{"","","","",""}}; 
		String data[][] = null;
		DefaultTableModel model = new DefaultTableModel(data, col);
		table.setModel(model);
		int i = 0;
		while(res.next()){
			String numb = res.getString("Code");
			String name = res.getString("Name");
			float price = res.getFloat("Price");
			String type = res.getString("Type");
			String description = res.getString("Description");
			Object obj[] = {numb, name, price, type, description};
			model.insertRow(i, obj);
			i++;
		}
	}

	// metodo per visualizzare la tabella Camerieri
	public static void viewWaiterTable(ResultSet res) throws SQLException{
		String col[] = {"Numb", "Surname", "Name"};
		//String data[][] = {{"","",""}}; 
		String data[][] = null;
		DefaultTableModel model = new DefaultTableModel(data, col);
		table.setModel(model);
		int i = 0;
		while(res.next()){
			int numb = res.getInt("Numb");
			String surname = res.getString("Surname");
			String name = res.getString("Name");
			Object obj[] = {numb, surname, name};
			model.insertRow(i, obj);
			i++;
		}
	}
	
	// metodo per visualizzare la tabella Tavoli
	public static void viewTablesTable(ResultSet res) throws SQLException{
		String col[] = {"TableNumb", "Seats", "Content", "State", "WaiterCode", "Joined"};
		//String data[][] = {{"","","","","",""}}; 
		String data[][] = null;
		DefaultTableModel model = new DefaultTableModel(data, col);
		table.setModel(model);
		int i = 0;
		while(res.next()){
			int tableNumb = res.getInt("TableNumb");
			int seats = res.getInt("Seats");
			int content = res.getInt("Content");
			String state = res.getString("State");
			int waiterCode = res.getInt("WaiterCode");
			String joined = res.getString("Joined");
			Object obj[] = {tableNumb, seats, content, state, waiterCode, joined};
			model.insertRow(i, obj);
			i++;
		}
	}
	
	// metodo per visualizzare la tabella Prenotazioni
	public static void viewReservationTable(ResultSet res) throws SQLException{
		String col[] = {"ReservationNumb", "Date", "ReservedTable", "IDClient", "PeopleNumb", "SmokingRoom"};
		//String data[][] = {{"","","","","",""}}; 
		String data[][] = null;
		DefaultTableModel model = new DefaultTableModel(data, col);
		table.setModel(model);
		int i = 0;
		while(res.next()){
			int reservNumb = res.getInt("ReservationNumb");
			int reservTable = res.getInt("ReservedTable");
			int idClient = res.getInt("IDClient");
			int people = res.getInt("PeopleNumb");
			String smoking = res.getString("SmokingRoom");
			// prelevo Date e Time e li metto in un GregorianCalendar
			Date d = res.getDate("Date");
			Time t = res.getTime("Time");
			GregorianCalendar greg = new GregorianCalendar();
			greg.setTime(d);
			greg.set(Calendar.HOUR, t.getHours());
			greg.set(Calendar.MINUTE, t.getMinutes());
			greg.set(Calendar.SECOND, t.getSeconds());
			String gregString = greg.get(Calendar.YEAR)+ "-" +greg.get(Calendar.MONTH)+ "-" +greg.get(Calendar.DAY_OF_MONTH)
							+ "   " +greg.get(Calendar.HOUR_OF_DAY)+ ":" +greg.get(Calendar.MINUTE);
			Object obj[] = {reservNumb, gregString, reservTable, idClient, people, smoking};
			model.insertRow(i, obj);
			i++;
		}
	}

	// metodo per visualizzare la tabella Tavoli Vicini
	public static void viewNearTable(ResultSet res) throws SQLException{
		String col[] = {"Table1", "Table2"};
		//String data[][] = {{"",""}}; 
		String data[][] = null;
		DefaultTableModel model = new DefaultTableModel(data, col);
		table.setModel(model);
		int i = 0;
		while(res.next()){
			int table1 = res.getInt("Table1");
			int table2 = res.getInt("Table2");
			Object obj[] = {table1, table2};
			model.insertRow(i, obj);
			i++;
		}
	}
	
	// metodo per visualizzare la tabella Users
	public static void viewUsersLogin(ResultSet res) throws SQLException{
		String col[] = {"ID", "Name", "Password", "Type"};
		String data[][] = null;
		DefaultTableModel model = new DefaultTableModel(data, col);
		table.setModel(model);
		int i = 0;
		while(res.next()){
			String id = res.getString("ID");
			String name = res.getString("Name");
			String password = res.getString("Password");
			String type = res.getString("Type");
			Object obj[] = {id, name, password, type};
			model.insertRow(i, obj);
			i++;
		}
	}
	
	// metodo per visualizzare la tabella Conti
	public static void viewCheckTable(ResultSet res) throws SQLException{
		String col[] = {"ID", "Date", "Amount", "PeopleNumb"};
		//String data[][] = {{"","","",""}}; 
		String data[][] = null;
		DefaultTableModel model = new DefaultTableModel(data, col);
		table.setModel(model);
		int i = 0;
		while(res.next()){
			int id = res.getInt("ID");
			float amount = res.getFloat("Amount");
			int people = res.getInt("PeopleNumb");
			// prelevo Date e Time e li metto in un GregorianCalendar
			Date d = res.getDate("Date");
			Time t = res.getTime("Time");
			GregorianCalendar greg = new GregorianCalendar();
			greg.setTime(d);
			greg.set(Calendar.HOUR, t.getHours());
			greg.set(Calendar.MINUTE, t.getMinutes());
			greg.set(Calendar.SECOND, t.getSeconds());
			String gregString = greg.get(Calendar.YEAR)+ "-" +greg.get(Calendar.MONTH)+ "-" +greg.get(Calendar.DAY_OF_MONTH)
			+ "   " +greg.get(Calendar.HOUR_OF_DAY)+ ":" +greg.get(Calendar.MINUTE);
			Object obj[] = {id, gregString, amount, people};
			model.insertRow(i, obj);
			i++;
		}
	}

	
	
	// metodo per abilitare il bottone Salva
	public static void enableSalva(){
		buttonSalva.setEnabled(true);
	}
	
	// metodo per disabilitare il bottone Salva
	public static void disableSalva(){
		buttonSalva.setEnabled(false);
	}
	
	// metodo per abilitare il bottone OK
	public static void enableOK(){
		buttonOK.setEnabled(true);
	}
	
	// metodo per disabilitare il bottone OK
	public static void disableOK(){
		buttonOK.setEnabled(false);
	}
	
	// metodo per abilitare il bottone Inserisci
	public static void enableInserisci(){
		buttonInserisci.setEnabled(true);
	}
	
	// metodo per disabilitare il bottone Inserisci
	public static void disableInserisci(){
		buttonInserisci.setEnabled(false);
	}
	
	// metodo per abilitare il bottone Elimina
	public static void enableElimina(){
		buttonElimina.setEnabled(true);
	}
	
	// metodo per disabilitare il bottone Elimina
	public static void disableElimina(){
		buttonElimina.setEnabled(false);
	}
	
	// metodo per abilitare la casella di testo DataStart
	public static void enableDataStart(){
		textDataStart.setEditable(true);
	}
	
	// metodo per disabilitare la casella di testo DataStart
	public static void disableDataStart(){
		textDataStart.setEditable(false);
	}
	
	// metodo per abilitare la casella di testo DataStop
	public static void enableDataStop(){
		textDataStop.setEditable(true);
	}
	
	// metodo per disabilitare la casella di testo DataStop
	public static void disableDataStop(){
		textDataStop.setEditable(false);
	}
	
	// metodo per abilitare la modifica e selezione della tabella
	public static void enableTable(){
		table.setEnabled(true);
	}
	
	// metodo per disabilitare la modifica e selezione della tabella
	public static void disableTable(){
		table.setEnabled(false);
	}
	
	// metodo per cancellare il testo nelle textDataStart e textDataStop
	public static void cancText(){
		textDataStart.setText("");
		textDataStop.setText("");
	}
	
	// metodo per abilitare il bottone Inserisci Nuovo Amministratore
	public static void enableNuovoAdmin(){
		buttonNuovoAdmin.setEnabled(true);
	}
	
	// metodo per disabilitare il bottone Inserisci Nuovo Amministratore
	public static void disableNuovoAdmin(){
		buttonNuovoAdmin.setEnabled(false);
	}
	
	
	
	// metodo per visualizzare il cliente più fedele
	public static void viewBestClient(ResultSet res) throws SQLException{
		String col[] = {"Name", "Surname", "Counter"};
		//String data[][] = {{"","",""}}; 
		String data[][] = null;
		DefaultTableModel model = new DefaultTableModel(data, col);
		table.setModel(model);
		int i = 0;
		while(res.next()){
			String name = res.getString("Name");
			String surname = res.getString("Surname");
			int counter = res.getInt("Counter");
			Object obj[] = {name, surname, counter};
			model.insertRow(i, obj);
			i++;
		}
	}
	
	
	// metodo per visualizzare i clienti Vips (contatore>=5)
	public static void viewVipsClient(ResultSet res) throws SQLException{
		String col[] = {"Name", "Surname", "Counter"};
		//String data[][] = {{"","",""}}; 
		String data[][] = null;
		DefaultTableModel model = new DefaultTableModel(data, col);
		table.setModel(model);
		int i = 0;
		while(res.next()){
			String name = res.getString("Name");
			String surname = res.getString("Surname");
			int counter = res.getInt("Counter");
			Object obj[] = {name, surname, counter};
			model.insertRow(i, obj);
			i++;
		}
	}


	// metodo per visualizzare i piatti migliori
	public static void viewBestDishes(ResultSet res) throws SQLException{
		String col[] = {"Code", "Name", "Price", "Type", "Description"};
		//String data[][] = {{"","","","",""}}; 
		String data[][] = null;
		DefaultTableModel model = new DefaultTableModel(data, col);
		table.setModel(model);
		int i = 0;
		while(res.next()){
			String code = res.getString("Code");
			String name = res.getString("Name");
			float price = res.getFloat("Price");
			String type = res.getString("Type");
			String description = res.getString("Description");
			Object obj[] = {code, name, price, type, description};
			model.insertRow(i, obj);
			i++;
		}
	}
	
	
	// metodo per visualizzare il tavolo più gettonato
	public static void viewBestTables(ResultSet res) throws SQLException{
		String col[] = {"TableNumb", "Seats", "Content"};
		//String data[][] = {{"","",""}}; 
		String data[][] = null;
		DefaultTableModel model = new DefaultTableModel(data, col);
		table.setModel(model);
		int i = 0;
		while(res.next()){
			int tableNumb = res.getInt("TableNumb");
			int seats = res.getInt("Seats");
			int content = res.getInt("Content");
			Object obj[] = {tableNumb, seats, content};
			model.insertRow(i, obj);
			i++;
		}
	}
	

	
	// metodo per prelevare i dati Clienti visualizzati nella JTable e aggiornarli nel DB
	public static void transferClientJTableToDB() throws SQLException, JavaDBException{
		int rows = table.getRowCount();				
		Database.connect();
		for(int i=0; i<rows; i++){
			// estraggo i campi di una riga della JTable 
			int id = Integer.parseInt(table.getValueAt(i, 0).toString());
			String name = table.getValueAt(i, 1).toString();
			String surname = table.getValueAt(i, 2).toString();
			int counter = Integer.parseInt(table.getValueAt(i, 3).toString());
			// controllo se Allergia è vuoto
			String allergy = " ";
			if(table.getValueAt(i, 4).toString().equalsIgnoreCase("")==false){		// se non è vuoto
				allergy = table.getValueAt(i, 4).toString();
			}
			// creo la SET modificando la riga con i campi appena estratti
			String qry = "update APP.Client set Name = '" +name+ "', Surname = '" +surname+ 
						 "', Counter = " +counter+ ", Allergy = '" +allergy+ "' where ID = " +id; 
			Database.update(qry);		
		}
		Database.disconnect();
		System.out.println("Salvataggio effettuato");
	}
	
	
	// metodo per prelevare i dati Sale visualizzati nella JTable e aggiornarli nel DB
	public static void transferRoomJTableToDB() throws SQLException, JavaDBException{
		int rows = table.getRowCount();				
		Database.connect();
		for(int i=0; i<rows; i++){
			// estraggo i campi di una riga della JTable 
			int roomNumb = Integer.parseInt(table.getValueAt(i, 0).toString());
			String name = table.getValueAt(i, 1).toString();
			String type = table.getValueAt(i, 2).toString();
			// controllo dell'enumerativo da scrivere su DB
			if (type.equalsIgnoreCase("Fumatori")){	type = "Fumatori"; }
			else if (type.equalsIgnoreCase("NonFumatori")){ type = "NonFumatori"; }
			else { type = "-----"; }
			// controllo se Mq è vuoto (cioè 0)
			int mq = 0;
			if(Integer.parseInt(table.getValueAt(i, 3).toString())!=0){			// se c'è un valore diverso da zero
				mq = Integer.parseInt(table.getValueAt(i, 3).toString());
			}
			// creo la SET modificando la riga con i campi appena estratti
			String qry = "update APP.Room set Name = '" +name+ "', Type = '" +type+ 
						 "', Mq = " +mq+ " where RoomNumb = " +roomNumb; 
			Database.update(qry);		
		}
		Database.disconnect();
		System.out.println("Salvataggio effettuato");
	}
	
	
	// metodo per prelevare i dati Cibi visualizzati nella JTable e aggiornarli nel DB
	public static void transferFoodJTableToDB() throws SQLException, JavaDBException{
		int rows = table.getRowCount();				
		Database.connect();
		for(int i=0; i<rows; i++){
			// estraggo i campi di una riga della JTable 
			String code = table.getValueAt(i, 0).toString();
			String name = table.getValueAt(i, 1).toString();
			float price = Float.parseFloat(table.getValueAt(i, 2).toString());
			String type = table.getValueAt(i, 3).toString();
			// controllo dell'enumerativo da scrivere su DB
			if (type.equalsIgnoreCase("Primo")){ type = "Primo"; }
			else if (type.equalsIgnoreCase("Secondo")){ type = "Secondo"; }
			else if (type.equalsIgnoreCase("Contorno")){ type = "Contorno"; }
			else if (type.equalsIgnoreCase("Dolce")){ type = "Dolce"; }
			else if (type.equalsIgnoreCase("Frutta")){ type = "Frutta"; }
			else if (type.equalsIgnoreCase("Bevanda")){ type = "Bevanda"; }
			else { type = "-----"; }
			// controllo se Descrizione è vuoto
			String description = " ";
			if(table.getValueAt(i, 4).toString().equalsIgnoreCase("")==false){			// se non è vuoto
				description = table.getValueAt(i, 4).toString();
			}
			// creo la SET modificando la riga con i campi appena estratti
			String qry = "update APP.Food set Name = '" +name+ "', Price = " +price+ 
						 ", Type = '" +type+ "', Description = '" +description+ "' where Code = '" +code+ "'"; 
			Database.update(qry);		
		}
		Database.disconnect();
		System.out.println("Salvataggio effettuato");
	}
	
	
	// metodo per prelevare i dati Camerieri visualizzati nella JTable e aggiornarli nel DB
	public static void transferWaiterJTableToDB() throws SQLException, JavaDBException{
		int rows = table.getRowCount();				
		Database.connect();
		for(int i=0; i<rows; i++){
			// estraggo i campi di una riga della JTable 
			int numb = Integer.parseInt(table.getValueAt(i, 0).toString());
			String surname = table.getValueAt(i, 1).toString();
			String name = table.getValueAt(i, 2).toString();
			// creo la SET modificando la riga con i campi appena estratti
			String qry = "update APP.Waiter set Surname = '" +surname+ "', Name = '" +name+ 
						 "' where Numb = " +numb; 
			Database.update(qry);		
		}
		Database.disconnect();
		System.out.println("Salvataggio effettuato");
	}
	
	
	// metodo per prelevare i dati Tavoli visualizzati nella JTable e aggiornarli nel DB
	public static void transferTableJTableToDB() throws SQLException, JavaDBException{
		int rows = table.getRowCount();				
		Database.connect();
		for(int i=0; i<rows; i++){
			// estraggo i campi di una riga della JTable 
			int tableNumb = Integer.parseInt(table.getValueAt(i, 0).toString());
			int seats = Integer.parseInt(table.getValueAt(i, 1).toString());
			int content = Integer.parseInt(table.getValueAt(i, 2).toString());
			String state = table.getValueAt(i, 3).toString();
			// controllo dell'enumerativo da scrivere su DB
			if (state.equalsIgnoreCase("Libero")){ state = "Libero"; }
			else if (state.equalsIgnoreCase("Occupato")){ state = "Occupato"; }
			else { state = "-----"; }
			int waiterCode = Integer.parseInt(table.getValueAt(i, 4).toString());
			// controllo se Descrizione è vuoto
			String joined = " ";
			if(table.getValueAt(i, 5).toString().equalsIgnoreCase("")==false){			// se non è vuoto
				joined = table.getValueAt(i, 5).toString();
			}
			// creo la SET modificando la riga con i campi appena estratti
			String qry = "update APP.TableR set Seats = " +seats+ ", Content = " +content+ 
						 ", State = '" +state+ "', WaiterCode = " +waiterCode+ ", Joined = '" +joined+
						 "' where TableNumb = " +tableNumb; 
			Database.update(qry);		
		}
		Database.disconnect();
		System.out.println("Salvataggio effettuato");
	}
	
	
	// metodo per prelevare i dati Prenotazioni visualizzati nella JTable e aggiornarli nel DB
	public static void transferReservationJTableToDB() throws SQLException, JavaDBException{
		int rows = table.getRowCount();				
		Database.connect();
		for(int i=0; i<rows; i++){
			// estraggo i campi di una riga della JTable 
			int reservNumb = Integer.parseInt(table.getValueAt(i, 0).toString());
			String date = table.getValueAt(i, 1).toString();
			String time = table.getValueAt(i, 2).toString();
			int reservTable = Integer.parseInt(table.getValueAt(i, 3).toString());
			int idClient = Integer.parseInt(table.getValueAt(i, 4).toString());
			int peopleNumb = Integer.parseInt(table.getValueAt(i, 5).toString());
			String smokingRoom = table.getValueAt(i, 6).toString();
			// controllo dell'enumerativo da scrivere su DB
			if (smokingRoom.equalsIgnoreCase("Si")){ smokingRoom = "Si"; }
			else if (smokingRoom.equalsIgnoreCase("No")){ smokingRoom = "No"; }
			else { smokingRoom = "--"; }
			// creo la SET modificando la riga con i campi appena estratti
			String qry = "update APP.Reservation set Date = '" +date+ "', Time = '" +time+ 
						 "', ReservedTable = " +reservTable+ ", IDClient = " +idClient+ ", PeopleNumb = " +peopleNumb+
						 ", SmokingRoom = '" +smokingRoom+ "' where ReservationNumb = " +reservNumb; 
			Database.update(qry);		
		}
		Database.disconnect();
		System.out.println("Salvataggio effettuato");
	}
	
	
	// metodo per prelevare i dati Tavoli Vicini visualizzati nella JTable e aggiornarli nel DB
	public static void transferNearTableJTableToDB() throws SQLException, JavaDBException{
		int rows = table.getRowCount();				
		Database.connect();
		for(int i=0; i<rows; i++){
			// estraggo i campi di una riga della JTable 
			int table1 = Integer.parseInt(table.getValueAt(i, 0).toString());
			int table2 = Integer.parseInt(table.getValueAt(i, 1).toString());
			// creo la SET modificando la riga con i campi appena estratti
			String qry = "update APP.NearTable set Table1 = " +table1+ ", Table2 = " +table2; 
			Database.update(qry);		
		}
		Database.disconnect();
		System.out.println("Salvataggio effettuato");
	}
	
	
	// metodo per prelevare i dati Conti visualizzati nella JTable e aggiornarli nel DB
	public static void transferTablesCheckJTableToDB() throws SQLException, JavaDBException{
		int rows = table.getRowCount();				
		Database.connect();
		for(int i=0; i<rows; i++){
			// estraggo i campi di una riga della JTable 
			int id = Integer.parseInt(table.getValueAt(i, 0).toString());
			String date = table.getValueAt(i, 1).toString();
			String time = table.getValueAt(i, 2).toString();
			float amount = Float.parseFloat(table.getValueAt(i, 3).toString());
			int peopleNumb = Integer.parseInt(table.getValueAt(i, 4).toString());
			// creo la SET modificando la riga con i campi appena estratti
			String qry = "update APP.TablesCheck set Date = '" +date+ "', Time = '" +time+ 
						 "', Amount = " +amount+ ", PeopleNumb = " +peopleNumb+ 
						 " where ID = " +id; 
			Database.update(qry);		
		}
		Database.disconnect();
		System.out.println("Salvataggio effettuato");
	}
	
	
	// Metodo per inserire (solo al 1° Login) i camerieri nella tabella Users
	public static void insertUsersLogin(ResultSet res) throws JavaDBException, SQLException{
		Database.connect();
		while(res.next()){
			String id = res.getString("Numb");
			String name = res.getString("Name");
			String qry = "insert into APP.Users (ID, Name, Password, Type) values " +
						 "('" +id+ "', '" +name+ "', '" +name+ "', 'Waiter');";
			//String qry = "insert into APP.Users values ('" +id+ "','" +name+ "','" +name+ "','Waiter');";
			Database.insert(qry);
		}
		Database.disconnect();
	}


	// passo da stringa nell'interfaccia grafica ad nome della tabella del DB
	public static String translator(String str){
		String translated = new String();
		if (str.equalsIgnoreCase("Clienti")) translated = "Client";
		if (str.equalsIgnoreCase("Cibi")) translated = "Food";
		if (str.equalsIgnoreCase("Sale")) translated = "Room";
		if (str.equalsIgnoreCase("Tavoli")) translated = "TableR";
		if (str.equalsIgnoreCase("Camerieri")) translated = "Waiter";
		if (str.equalsIgnoreCase("Prenotazioni")) translated = "Reservation";
		if (str.equalsIgnoreCase("Conti Tavoli")) translated = "TablesCheck";
		if (str.equalsIgnoreCase("Tavoli Vicini")) translated = "NearTable";
		if (str.equalsIgnoreCase("Ordinazioni")) translated = "OrderTable";
		if (str.equalsIgnoreCase("Singole Ordinazioni")) translated = "SingleOrder";
		return translated;
	}
	
	
	// metodo per inserire una riga Nuovo Amministratore nella JTable
	public static void insertAdmin(){
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		int cols = model.getColumnCount();
		model.addRow(new Object[cols]);
		int rows = model.getRowCount();
		model.setValueAt("Amm", rows-1, 0);
		model.setValueAt("Admin", rows-1, 3);
		model.isCellEditable(0, 0);
		model.isCellEditable(0, 3);
		table.repaint();
	}
	
	
	// metodo per inserire una riga vuota nella JTable
	public static void insertEmptyRow(){
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		int cols = model.getColumnCount();
		model.addRow(new Object[cols]);
		table.repaint();
	}
	
	
	
	// metodo per eliminare la riga selezionata nella JTable
	public static void deleteSelectedRow(){
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		model.removeRow(table.getSelectedRow());
		table.repaint();
	}
	
	
	
	// metodo per leggere la data nella casella DataStart
	public static String getDataStart() throws ParseException{
		String d = textDataStart.getText();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date data = dateFormat.parse(d);
        int day = data.getDate();
		int month = data.getMonth()+1;
		int year = data.getYear()+1900;
        boolean isTrue = checkData(day, month, year);
        if (!isTrue){
        	System.out.println("Data non corretta");
        	return "";
        }
        String dat = year+ "-" +month+ "-" +day;
        return dat;
	}
	
	
	
	// metodo per leggere la data nella casella DataStop
	public static String getDataStop() throws ParseException{
		String d = textDataStop.getText();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date data = dateFormat.parse(d);
        int day = data.getDate();
		int month = data.getMonth()+1;
		int year = data.getYear()+1900;
        boolean isTrue = checkData(day, month, year);
        if (!isTrue){
        	System.out.println("Data non corretta");
        	return "";
        }
        String dat = year+ "-" +month+ "-" +day;
        return dat;
	}
	
	
	
	// metodo per visualizzare i profitti tra un range di date
	public static void viewProfit(ResultSet res) throws SQLException{
		String col[] = {"Date", "People Number", "Amount"};
		String data[][] = {{"","",""}}; 
		DefaultTableModel model = new DefaultTableModel(data, col);
		table.setModel(model);
		int i = 0;
		float sum = 0;
		while(res.next()){
			Date d = res.getDate("Date");
			int people = res.getInt("PeopleNumb");
			int amount = res.getInt("Amount");
			sum = sum + amount;							// conto totale
			Object obj[] = {d, people, amount};
			model.insertRow(i, obj);
			i++;
		}
		model.setValueAt(sum, i, 2);
	}
	
	
	
	// metodo per visualizzare il numero di clienti tra un range di date
	public static void viewClients(ResultSet res) throws SQLException{
		String col[] = {"Date", "Amount", "People Number"};
		String data[][] = {{"","",""}}; 
		DefaultTableModel model = new DefaultTableModel(data, col);
		table.setModel(model);
		int i = 0;
		int sum = 0;
		while(res.next()){
			Date d = res.getDate("Date");
			int amount = res.getInt("Amount");
			int people = res.getInt("PeopleNumb");
			sum = sum + people;							// conto totale
			Object obj[] = {d, amount, people};
			model.insertRow(i, obj);
			i++;
		}
		model.setValueAt(sum, i, 2);
	}
	
	
	
	// metodo per controllare che il giorno e il mese della data inseriti siano validi
	public static boolean checkData(int day, int month, int year){
		boolean res = false;
		switch(month){
		case 1: 
			if(day>0 && day<=31){res = true;}
			break;	 
		case 2: 
			if(day>0 && day<=29){res = true;}
			break;
		case 3: 
			if(day>0 && day<=31){res = true;}
			break;
		case 4: 
			if(day>0 && day<=30){res = true;}
			break;
		case 5: 
			if(day>0 && day<=31){res = true;}
			break;
		case 6: 
			if(day>0 && day<=30){res = true;}
			break;
		case 7: 
			if(day>0 && day<=31){res = true;}
			break;
		case 8: 
			if(day>0 && day<=31){res = true;}
			break;
		case 9: 
			if(day>0 && day<=30){res = true;}
			break;
		case 10: 
			if(day>0 && day<=31){res = true;}
			break;
		case 11: 
			if(day>0 && day<=30){res = true;}
			break;
		case 12: 
			if(day>0 && day<=31){res = true;}
			break;
		default:
			res = false;
			break;
		}
		return res;	
	}
	
	
	// metodo per riportare il tutto alla situazione iniziale
	public static void init(){
		buttonSalva.setEnabled(false);
		buttonInserisci.setEnabled(false);
		buttonElimina.setEnabled(false);
		textDataStart.setEditable(false);
		textDataStop.setEditable(false);
		buttonOK.setEnabled(false);
		// per eliminare la tabella
		DefaultTableModel mod = new DefaultTableModel();
		mod = (DefaultTableModel) table.getModel();
		mod.setRowCount(0);
		// per rimouovere la selezione della Combobox2
		comboBox2.removeAllItems();
		// cancellare testo nella textDataStart e textDataStop
		textDataStart.setText("");
		textDataStop.setText("");
	}
}
