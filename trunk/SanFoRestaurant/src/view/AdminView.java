package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.ImageIcon;
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
import javax.swing.table.TableColumn;

import model.admin.AdminMng;
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
import controller.AdminCtrl;
import controller.LoginCtrl;
import database.JavaDBException;

// TODO: Auto-generated Javadoc
/**
 * Classe Amministrazione view che contiene tutti i componenti grafici per interfacciarsi con l'amministratore
 * e tutti i metodi per gestirli.
 * 
 * @author Mauro
 */
public class AdminView extends JFrame {

	/** The controller. */
	private AdminCtrl controller;
	
	/** The log controller. */
	private LoginCtrl logController;
	
	/** The content pane. */
	private JPanel contentPane;
	
	/** The combo box1. */
	private static JComboBox comboBox1;
	
	/** The combo box2. */
	private static JComboBox comboBox2;
	
	/** The scroll pane_nascosto. */
	private JScrollPane scrollPane_nascosto;
	
	/** The table. */
	private static JTable table;
	
	/** The scroll pane. */
	private JScrollPane scrollPane;
	
	/** The button inserisci. */
	private static JButton buttonInserisci;
	
	/** The button elimina. */
	private static JButton buttonElimina;
	
	/** The button salva. */
	private static JButton buttonSalva;
	
	/** The button ok. */
	private static JButton buttonOK;
	
	/** The button annulla. */
	private JButton buttonAnnulla;
	
	/** The text data start. */
	private static JTextField textDataStart;
	
	/** The text data stop. */
	private static JTextField textDataStop;
	
	/** The lbl data start. */
	private static JLabel lblDataStart;
	
	/** The lbl data stop. */
	private static JLabel lblDataStop;
	
	/** The button nuovo admin. */
	private static JButton buttonNuovoAdmin;
	
	/** The button change. */
	private static JButton buttonChange;
	
	/** The button logout. */
	private static JButton buttonLogout;
	
	/** The text error date. */
	private static JTextField textErrorDate;
	private JLabel lblNewLabel;

	/**
	 * Create the frame.
	 *
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the sQL exception
	 * @throws JavaDBException the java db exception
	 */
	public AdminView() throws ClassNotFoundException, SQLException, JavaDBException {
		setTitle("Amministrazione");
		
		this.controller = new AdminCtrl();
		this.logController = new LoginCtrl();
		
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
		//scrollPane.setBounds(628, 63, 701, 402);
		scrollPane.setBounds(628, 63, screenSize.width-37-628, 402);
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
		lblOperazioni.setBounds(61, 41, 161, 20);
		contentPane.add(lblOperazioni);
		
		JLabel lblTabelle = new JLabel("Tabelle");
		lblTabelle.setBounds(267, 41, 161, 20);
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
		
		buttonChange = new JButton("Passa a serata");
		//buttonChange.setBounds(1174, 613, 155, 37);
		buttonChange.setBounds(screenSize.width-37-155, 613, 155, 37);
		contentPane.add(buttonChange);
		
		buttonLogout = new JButton("Logout");
		//buttonLogout.setBounds(1240, 20, 89, 23);
		buttonLogout.setBounds(screenSize.width-37-89, 20, 89, 23);
		contentPane.add(buttonLogout);

		textErrorDate = new JTextField();
		textErrorDate.setForeground(Color.RED);
		textErrorDate.setBounds(482, 351, 89, 20);
		contentPane.add(textErrorDate);
		textErrorDate.setColumns(10);
		
		JLabel lblFinestraVis = new JLabel("Finestra di Visualizzazione:");
		lblFinestraVis.setBounds(628, 32, 161, 20);
		contentPane.add(lblFinestraVis);
		
		lblNewLabel = new JLabel(new ImageIcon(getClass().getResource("logoRistorante.png")));
		lblNewLabel.setBounds(10, 607, 100, 83);
		contentPane.add(lblNewLabel);
		textErrorDate.setVisible(false);
		
		


		
		
		
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
		buttonChange.addActionListener(logController);
		buttonLogout.addActionListener(controller);
		this.setVisible(true);
	}
	
	


	
	/**
	 * Metodo per modificare la comboBox2 in base alla comboBox1.
	 *
	 * @param model lista da inserire nella comboBox2
	 */
	public static void modifyComboBox2(String[] model) {
		comboBox2.removeAllItems();
		for (int i=0; i<model.length; i++){
			comboBox2.insertItemAt(model[i], i);
		}		
	}
	

	/**
	 * Metodo per visualizzare la tabella Clienti.
	 *
	 * @param res contiene tutti i clienti
	 * @throws SQLException the sQL exception
	 */
	public static void viewClientTable(ResultSet res) throws SQLException{
		String col[] = {"I", "ID", "Name", "Surname", "Counter", "Allergy"};
		String data[][] = null;
		DefaultTableModel model = new DefaultTableModel(data, col);
		table.setModel(model);
		TableColumn colonna = table.getColumnModel().getColumn(0);
		colonna.setMinWidth(0);
		colonna.setMaxWidth(0);
		colonna.setPreferredWidth(0);
		int index = 0;
		while(res.next()){
			int a = res.getInt("I");
			int id = res.getInt("ID");
			String name = res.getString("Name");
			String surname = res.getString("Surname");
			int counter = res.getInt("Counter");
			String allergy = res.getString("Allergy");
			Object obj[] = {a, id, name, surname, counter, allergy};
			model.insertRow(index, obj);
			index++;
		}
	}
	
	
	/**
	 * Metodo per visualizzare la tabella Sale.
	 *
	 * @param res contiene tutte le sale
	 * @throws SQLException the sQL exception
	 */
	public static void viewRoomTable(ResultSet res) throws SQLException{
		String col[] = {"I", "RoomNumb", "Name", "Mq"};
		String data[][] = null;
		DefaultTableModel model = new DefaultTableModel(data, col);
		table.setModel(model);
		TableColumn colonna = table.getColumnModel().getColumn(0);
		colonna.setMinWidth(0);
		colonna.setMaxWidth(0);
		colonna.setPreferredWidth(0);
		int index = 0;
		while(res.next()){
			int a = res.getInt("I");
			int numb = res.getInt("RoomNumb");
			String name = res.getString("Name");
			int mq = res.getInt("Mq");
			Object obj[] = {a, numb, name, mq};
			model.insertRow(index, obj);
			index++;
		}
	}
	
	 
	/**
	 * Metodo per visualizzare la tabella Cibi.
	 *
	 * @param res contiene tutti i cibi
	 * @throws SQLException the sQL exception
	 */
	public static void viewFoodTable(ResultSet res) throws SQLException{
		String col[] = {"I", "Code", "Name", "Price", "Type", "Description"};
		String data[][] = null;
		DefaultTableModel model = new DefaultTableModel(data, col);
		table.setModel(model);
		TableColumn colonna = table.getColumnModel().getColumn(0);
		colonna.setMinWidth(0);
		colonna.setMaxWidth(0);
		colonna.setPreferredWidth(0);
		int index = 0;
		while(res.next()){
			int a = res.getInt("I");
			String numb = res.getString("Code");
			String name = res.getString("Name");
			float price = res.getFloat("Price");
			String type = res.getString("Type");
			String description = res.getString("Description");
			Object obj[] = {a, numb, name, price, type, description};
			model.insertRow(index, obj);
			index++;
		}
	}


	/**
	 * Metodo per visualizzare la tabella Camerieri.
	 *
	 * @param res contiene tutti i camerieri
	 * @throws SQLException the sQL exception
	 */
	public static void viewWaiterTable(ResultSet res) throws SQLException{
		String col[] = {"I", "Numb", "Surname", "Name"};
		String data[][] = null;
		DefaultTableModel model = new DefaultTableModel(data, col);
		table.setModel(model);
		TableColumn colonna = table.getColumnModel().getColumn(0);
		colonna.setMinWidth(0);
		colonna.setMaxWidth(0);
		colonna.setPreferredWidth(0);
		int index = 0;
		while(res.next()){
			int a = res.getInt("I");
			int numb = res.getInt("Numb");
			String surname = res.getString("Surname");
			String name = res.getString("Name");
			Object obj[] = {a, numb, surname, name};
			model.insertRow(index, obj);
			index++;
		}
	}
	
	 
	/**
	 * Metodo per visualizzare la tabella Tavoli.
	 *
	 * @param res contiene tutti i tavoli
	 * @throws SQLException the sQL exception
	 */
	public static void viewTablesTable(ResultSet res) throws SQLException{
		String col[] = {"I", "TableNumb", "Seats", "Content", "State", "WaiterCode", "Joined"};
		String data[][] = null;
		DefaultTableModel model = new DefaultTableModel(data, col);
		table.setModel(model);
		TableColumn colonna = table.getColumnModel().getColumn(0);
		colonna.setMinWidth(0);
		colonna.setMaxWidth(0);
		colonna.setPreferredWidth(0);
		int index = 0;
		while(res.next()){
			int a = res.getInt("I");
			int tableNumb = res.getInt("TableNumb");
			int seats = res.getInt("Seats");
			int content = res.getInt("Content");
			String state = res.getString("State");
			int waiterCode = res.getInt("WaiterCode");
			String joined = res.getString("Joined");
			Object obj[] = {a, tableNumb, seats, content, state, waiterCode, joined};
			model.insertRow(index, obj);
			index++;
		}
	}
	

	/**
	 * Metodo per visualizzare la tabella Prenotazioni.
	 *
	 * @param res contiene tutte le prenotazioni
	 * @throws SQLException the sQL exception
	 */
	public static void viewReservationTable(ResultSet res) throws SQLException{
		String col[] = {"I", "ReservationNumb", "Date", "ReservedTable", "IDClient", "PeopleNumb"};
		String data[][] = null;
		DefaultTableModel model = new DefaultTableModel(data, col);
		table.setModel(model);
		TableColumn colonna = table.getColumnModel().getColumn(0);
		colonna.setMinWidth(0);
		colonna.setMaxWidth(0);
		colonna.setPreferredWidth(0);
		int index = 0;
		while(res.next()){
			int a = res.getInt("I");
			int reservNumb = res.getInt("ReservationNumb");
			int reservTable = res.getInt("ReservedTable");
			int idClient = res.getInt("IDClient");
			int people = res.getInt("PeopleNumb");
			// prelevo Date e Time e li metto in un GregorianCalendar
			Date d = res.getDate("Date");
			Time t = res.getTime("Time");
			GregorianCalendar greg = new GregorianCalendar();
			greg.setTime(d);
			greg.set(Calendar.HOUR, t.getHours());
			greg.set(Calendar.MINUTE, t.getMinutes());
			greg.set(Calendar.SECOND, t.getSeconds());
			int year = greg.get(Calendar.YEAR);
			String month = checkFormat(greg.get(Calendar.MONTH)+1);
			String day = checkFormat(greg.get(Calendar.DAY_OF_MONTH));
			String hour = checkFormat(greg.get(Calendar.HOUR_OF_DAY));
			String min = checkFormat(greg.get(Calendar.MINUTE));
			String gregString = year+ "-" +month+ "-" +day+ " " +hour+ ":" +min;
			Object obj[] = {a, reservNumb, gregString, reservTable, idClient, people};
			model.insertRow(index, obj);
			index++;
		}
	}
	
 
	/**
	 * Metodo per visualizzare la tabella Tavoli Vicini.
	 *
	 * @param res contiene tutti i tavoli vicini
	 * @throws SQLException the sQL exception
	 */
	public static void viewNearTable(ResultSet res) throws SQLException{
		String col[] = {"I", "Table1", "Table2"};
		String data[][] = null;
		DefaultTableModel model = new DefaultTableModel(data, col);
		table.setModel(model);
		TableColumn colonna = table.getColumnModel().getColumn(0);
		colonna.setMinWidth(0);
		colonna.setMaxWidth(0);
		colonna.setPreferredWidth(0);
		int index = 0;
		while(res.next()){
			int a = res.getInt("I");
			int table1 = res.getInt("Table1");
			int table2 = res.getInt("Table2");
			Object obj[] = {a, table1, table2};
			model.insertRow(index, obj);
			index++;
		}
	}
	
	 
	/**
	 * Metodo per visualizzare la tabella Users.
	 *
	 * @param res contiene tutti gli utenti che possono utilizzare l'applicazione
	 * @throws SQLException the sQL exception
	 */
	public static void viewUsersLogin(ResultSet res) throws SQLException{
		String col[] = {"I", "ID", "Name", "Password", "Type", "dbUpdate"};
		String data[][] = null;
		DefaultTableModel model = new DefaultTableModel(data, col);
		table.setModel(model);
		TableColumn colonna = table.getColumnModel().getColumn(0);
		colonna.setMinWidth(0);
		colonna.setMaxWidth(0);
		colonna.setPreferredWidth(0);
		int index = 0;
		while(res.next()){
			int a = res.getInt("I");
			String id = res.getString("ID");
			String name = res.getString("Name");
			String password = res.getString("Password");
			String type = res.getString("Type");
			boolean dbUpdate = res.getBoolean("DBupdate");
			Object obj[] = {a, id, name, password, type, dbUpdate};
			model.insertRow(index, obj);
			index++;
		}
	}
	
	
	/**
	 * Metodo per visualizzare la tabella Conti.
	 *
	 * @param res contiene tutti i conti
	 * @throws SQLException the sQL exception
	 */
	public static void viewCheckTable(ResultSet res) throws SQLException{
		String col[] = {"I", "Date", "Amount", "PeopleNumb"}; 
		String data[][] = null;
		DefaultTableModel model = new DefaultTableModel(data, col);
		table.setModel(model);
		TableColumn colonna = table.getColumnModel().getColumn(0);
		colonna.setMinWidth(0);
		colonna.setMaxWidth(0);
		colonna.setPreferredWidth(0);
		int index = 0;
		while(res.next()){
			int a = res.getInt("I");
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
			int year = greg.get(Calendar.YEAR);
			String month = checkFormat(greg.get(Calendar.MONTH)+1);
			String day = checkFormat(greg.get(Calendar.DAY_OF_MONTH));
			String hour = checkFormat(greg.get(Calendar.HOUR_OF_DAY));
			String min = checkFormat(greg.get(Calendar.MINUTE));
			String gregString = year+ "-" +month+ "-" +day+ " " +hour+ ":" +min;
			Object obj[] = {a, gregString, amount, people};
			model.insertRow(index, obj);
			index++;
		}
	}
	
	
	/**
	 * Metodo per visualizzare la tabella Settings.
	 *
	 * @param res contiene tutti i settaggi
	 * @throws SQLException the sQL exception
	 */
	public static void viewSettings(ResultSet res) throws SQLException{
		String col[] = {"I", "Parametro", "Valore"};
		String data[][] = null;
		DefaultTableModel model = new DefaultTableModel(data, col);
		table.setModel(model);
		TableColumn colonna = table.getColumnModel().getColumn(0);
		colonna.setMinWidth(0);
		colonna.setMaxWidth(0);
		colonna.setPreferredWidth(0);
		int index = 0;
		while(res.next()){
			int a = res.getInt("I");
			String param = res.getString("NameParam");
			String value = res.getString("Value");
			Object obj[] = {a, param, value};
			model.insertRow(index, obj);
			index++;
		}
	}
	

	/**
	 * Metodo per aggiungere lo 0 ai componenti della data compresi tra 1 e 9.
	 *
	 * @param a componente della data 
	 * @return the string
	 */
	public static String checkFormat(int a){
		String res = null;
		if (a>=0 && a<=9) res = "0" + a;
		else res = String.valueOf(a);
		return res;
	}
	
	
	/**
	 * Metodo per abilitare il bottone Salva.
	 */
	public static void enableSalva(){
		buttonSalva.setEnabled(true);
	}
	
	
	/**
	 * Metodo per disabilitare il bottone Salva.
	 */
	public static void disableSalva(){
		buttonSalva.setEnabled(false);
	}
	
	
	/**
	 * Metodo per abilitare il bottone OK.
	 */
	public static void enableOK(){
		buttonOK.setEnabled(true);
	}
	
	
	/**
	 * Metodo per disabilitare il bottone OK.
	 */
	public static void disableOK(){
		buttonOK.setEnabled(false);
	}
	
	
	/**
	 * Metodo per abilitare il bottone Inserisci.
	 */
	public static void enableInserisci(){
		buttonInserisci.setEnabled(true);
	}
	
	
	/**
	 * Metodo per disabilitare il bottone Inserisci.
	 */
	public static void disableInserisci(){
		buttonInserisci.setEnabled(false);
	}
	
	
	/**
	 * Metodo per abilitare il bottone Elimina.
	 */
	public static void enableElimina(){
		buttonElimina.setEnabled(true);
	}
	
	
	/**
	 * Metodo per disabilitare il bottone Elimina.
	 */
	public static void disableElimina(){
		buttonElimina.setEnabled(false);
	}
	
	
	/**
	 * Metodo per abilitare la casella di testo DataStart.
	 */
	public static void enableDataStart(){
		textDataStart.setEditable(true);
	}
	
	
	/**
	 * Metodo per disabilitare la casella di testo DataStart.
	 */
	public static void disableDataStart(){
		textDataStart.setEditable(false);
	}
	
	
	/**
	 * Metodo per abilitare la casella di testo DataStop.
	 */
	public static void enableDataStop(){
		textDataStop.setEditable(true);
	}
	
	
	/**
	 * Metodo per disabilitare la casella di testo DataStop.
	 */
	public static void disableDataStop(){
		textDataStop.setEditable(false);
	}
	
	
	/**
	 * Metodo per abilitare la modifica e selezione della tabella.
	 */
	public static void enableTable(){
		table.setEnabled(true);
	}
	
	
	/**
	 * Metodo per disabilitare la modifica e selezione della tabella.
	 */
	public static void disableTable(){
		table.setEnabled(false);
	}
	
	
	/**
	 * Metodo per cancellare il testo nelle textDataStart e textDataStop.
	 */
	public static void cancText(){
		textDataStart.setText("");
		textDataStop.setText("");
	}
	
	
	/**
	 * Metodo per abilitare il bottone Inserisci Nuovo Amministratore.
	 */
	public static void enableNuovoAdmin(){
		buttonNuovoAdmin.setEnabled(true);
	}
	
	
	/**
	 * Metodo per disabilitare il bottone Inserisci Nuovo Amministratore.
	 */
	public static void disableNuovoAdmin(){
		buttonNuovoAdmin.setEnabled(false);
	}
	
	
	/**
	 * Metodo per abilitare il bottone Passa a serata.
	 */
	public static void enablePassaASerata(){
		buttonChange.setEnabled(true);
	}
	
	
	/**
	 * Metodo per disabilitare il bottone Passa a serata.
	 */
	public static void disablePassaASerata(){
		buttonChange.setEnabled(false);
	}
	
	
	
	
	/**
	 * Metodo per visualizzare il cliente più fedele.
	 *
	 * @param res contiene il cliente più fedele
	 * @throws SQLException the sQL exception
	 */
	public static void viewBestClient(ResultSet res) throws SQLException{
		String col[] = {"Name", "Surname", "Counter"};
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
	
	
	
	/**
	 * Metodo per visualizzare i clienti Vips (contatore>=5).
	 *
	 * @param res contiene i clienti vips
	 * @throws SQLException the sQL exception
	 */
	public static void viewVipsClient(ResultSet res) throws SQLException{
		String col[] = {"Name", "Surname", "Counter"};
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


	
	/**
	 * Metodo per visualizzare il piatto migliore.
	 *
	 * @param res contiene il piatto migliore
	 * @throws SQLException the sQL exception
	 */
	public static void viewBestDishes(ResultSet res) throws SQLException{
		String col[] = {"Code", "Name", "Price", "Type", "Description"};
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
	
	
	
	/**
	 * Metodo per visualizzare il tavolo più gettonato.
	 *
	 * @param res contiene il tavolo più gettonato
	 * @throws SQLException the sQL exception
	 */
	public static void viewBestTables(ResultSet res) throws SQLException{
		String col[] = {"TableNumb", "Seats", "Content"};
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
	
	
	
	/**
	 * Metodo che inserisce i Client della JTable in un ArrayList.
	 *
	 * @return clienti dalla jtable
	 */
	public static ArrayList<Client> getClientFromJTable(){
		int rows = table.getRowCount();
		ArrayList<Client> arr = new ArrayList<Client>();
		for(int i=0; i<rows; i++){
			// estraggo i campi di una riga della JTable 
			int a = Integer.parseInt(table.getValueAt(i, 0).toString());
			int id = Integer.parseInt(table.getValueAt(i, 1).toString());
			String name = table.getValueAt(i, 2).toString();
			String surname = table.getValueAt(i, 3).toString();
			int counter = Integer.parseInt(table.getValueAt(i, 4).toString());
			// controllo se Allergia è vuoto
			String allergy = "";
			if(table.getValueAt(i, 5)!=null){			// se non è vuoto
				if(!table.getValueAt(i, 5).toString().isEmpty())
					allergy = table.getValueAt(i, 5).toString();
			}
			Client c = new Client(a, id, name, surname, counter, allergy);
			arr.add(c);
		}
		return arr;
	}

	
	
	/**
	 * Metodo che inserisce le Sale della JTable in un ArrayList.
	 *
	 * @return le sale dalla jtable
	 */
	public static ArrayList<Room> getRoomFromJTable(){
		int rows = table.getRowCount();
		ArrayList<Room> arr = new ArrayList<Room>();
		for(int i=0; i<rows; i++){
			// estraggo i campi di una riga della JTable 
			int a = Integer.parseInt(table.getValueAt(i, 0).toString());
			int roomNumb = Integer.parseInt(table.getValueAt(i, 1).toString());
			String name = table.getValueAt(i, 2).toString();
			// controllo se Mq è vuoto (cioè 0)
			int mq = 0;
			if(Integer.parseInt(table.getValueAt(i, 3).toString())!=0){			// se c'è un valore diverso da zero
				mq = Integer.parseInt(table.getValueAt(i, 3).toString());
			}
			// creo l'oggetto Room e lo inserisco nell'ArrayList
			Room r = new Room(a, roomNumb, name, mq);
			arr.add(r);
		}
		return arr;
	}
	
	
	
	/**
	 * Metodo che inserisce i Cibi della JTable in un ArrayList.
	 *
	 * @return i cibi dalla jtable
	 */
	public static ArrayList<Food> getFoodFromJTable(){
		int rows = table.getRowCount();
		ArrayList<Food> arr = new ArrayList<Food>();
		for(int i=0; i<rows; i++){
			// estraggo i campi di una riga della JTable 
			int a = Integer.parseInt(table.getValueAt(i, 0).toString());
			String code = table.getValueAt(i, 1).toString();
			String name = table.getValueAt(i, 2).toString();
			float price = Float.parseFloat(table.getValueAt(i, 3).toString());
			String type = table.getValueAt(i, 4).toString();
			// controllo dell'enumerativo da scrivere su DB
			if (type.equalsIgnoreCase("Primo")){ type = "Primo"; }
			else if (type.equalsIgnoreCase("Secondo")){ type = "Secondo"; }
			else if (type.equalsIgnoreCase("Contorno")){ type = "Contorno"; }
			else if (type.equalsIgnoreCase("Dolce")){ type = "Dolce"; }
			else if (type.equalsIgnoreCase("Frutta")){ type = "Frutta"; }
			else if (type.equalsIgnoreCase("Bevanda")){ type = "Bevanda"; }
			else { type = "-----"; }
			// controllo se Descrizione è vuoto
			String description = "";
			if(table.getValueAt(i, 5)!=null){
				if(!table.getValueAt(i, 5).toString().isEmpty())			// se non è vuoto
					description = table.getValueAt(i, 5).toString();
			}
			// creo l'oggetto Food e lo inserisco nell'ArrayList
			Food f = new Food(a, code, name, price, type, description);
			arr.add(f);
		}
		return arr;
	}
	
	
	
	/**
	 * Metodo che inserisce i Camerieri della JTable in un ArrayList.
	 *
	 * @return i camerieri dalla jtable
	 */
	public static ArrayList<Waiter> getWaiterFromJTable(){
		int rows = table.getRowCount();
		ArrayList<Waiter> arr = new ArrayList<Waiter>();
		for(int i=0; i<rows; i++){
			// estraggo i campi di una riga della JTable 
			int a = Integer.parseInt(table.getValueAt(i, 0).toString());
			int numb = Integer.parseInt(table.getValueAt(i, 1).toString());
			String surname = table.getValueAt(i, 2).toString();
			String name = table.getValueAt(i, 3).toString();
			// creo l'oggetto Waiter e lo inserisco nell'ArrayList
			Waiter w = new Waiter(a, numb, surname, name);
			arr.add(w);
		}
		return arr;
	}
	
	
	
	/**
	 * Metodo che inserisce i Tavoli della JTable in un ArrayList.
	 *
	 * @return i tavoli dalla jtable
	 */
	public static ArrayList<Table> getTableFromJTable(){
		int rows = table.getRowCount();
		ArrayList<Table> arr = new ArrayList<Table>();
		for(int i=0; i<rows; i++){
			// estraggo i campi di una riga della JTable 
			int a = Integer.parseInt(table.getValueAt(i, 0).toString());
			int tableNumb = Integer.parseInt(table.getValueAt(i, 1).toString());
			int seats = Integer.parseInt(table.getValueAt(i, 2).toString());
			int content = Integer.parseInt(table.getValueAt(i, 3).toString());
			String state = table.getValueAt(i, 4).toString();
			// controllo dell'enumerativo da scrivere su DB
			if (state.equalsIgnoreCase("Libero")){ state = "Libero"; }
			else if (state.equalsIgnoreCase("Occupato")){ state = "Occupato"; }
			else { state = "-----"; }
			int waiterCode = Integer.parseInt(table.getValueAt(i, 5).toString());
			// controllo se Descrizione è vuoto
			String joined = "";
			if(table.getValueAt(i, 6)!=null){
				if(!table.getValueAt(i, 6).toString().isEmpty())			// se non è vuoto
					joined = table.getValueAt(i, 6).toString();
			}
			// creo l'oggetto Table e lo inserisco nell'ArrayList
			Table t = new Table(a, tableNumb, seats, content, state, waiterCode, joined);
			arr.add(t);
		}
		return arr;
	}
	
	
	
	/**
	 * Metodo che inserisce le Prenotazioni della JTable in un ArrayList.
	 *
	 * @return le prenotazioni dalla jtable
	 * @throws ParseException the parse exception
	 */
	public static ArrayList<Reservation> getReservationFromJTable() throws ParseException {
		int rows = table.getRowCount();
		ArrayList<Reservation> arr = new ArrayList<Reservation>();
		for(int i=0; i<rows; i++){
			// estraggo i campi di una riga della JTable 
			int a = Integer.parseInt(table.getValueAt(i, 0).toString());
			int reservNumb = Integer.parseInt(table.getValueAt(i, 1).toString());
			String d = table.getValueAt(i, 2).toString();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
			Date parsed = df.parse(d);
			GregorianCalendar calend = new GregorianCalendar();
			calend.setTime(parsed);
			int month = calend.get(Calendar.MONTH);
			calend.set(Calendar.MONTH, month+1);
			int reservTable = Integer.parseInt(table.getValueAt(i, 3).toString());
			int idClient = Integer.parseInt(table.getValueAt(i, 4).toString());
			int peopleNumb = Integer.parseInt(table.getValueAt(i, 5).toString());
			// creo l'oggetto Reservation e lo inserisco nell'ArrayList
			Reservation r = new Reservation(a, reservNumb, calend, reservTable, idClient, peopleNumb);
			arr.add(r);
		}
		return arr;
	}
	
	
	
	/**
	 * Metodo che inserisce i TavoliVicini della JTable in un ArrayList.
	 *
	 * @return i tavoli vicini dalla jtable
	 */
	public static ArrayList<NearTable> getNearTableFromJTable(){
		int rows = table.getRowCount();
		ArrayList<NearTable> arr = new ArrayList<NearTable>();
		for(int i=0; i<rows; i++){
			// estraggo i campi di una riga della JTable 
			int a = Integer.parseInt(table.getValueAt(i, 0).toString());
			int table1 = Integer.parseInt(table.getValueAt(i, 1).toString());
			int table2 = Integer.parseInt(table.getValueAt(i, 2).toString());
			// creo l'oggetto NearTable e lo inserisco nell'ArrayList
			NearTable t = new NearTable(a, table1, table2, 0);
			arr.add(t);
		}
		return arr;
	}
	
	
	
	/**
	 * Metodo che inserisce i dati UsersLogin della JTable in un ArrayList.
	 *
	 * @return gli users login dalla jtable
	 */
	public static ArrayList<UsersLogin> getUsersLoginFromJTable(){
		int rows = table.getRowCount();
		ArrayList<UsersLogin> arr = new ArrayList<UsersLogin>();
		for(int i=0; i<rows; i++){
			// estraggo i campi di una riga della JTable 
			int a = Integer.parseInt(table.getValueAt(i, 0).toString());
			String id = table.getValueAt(i, 1).toString();
			String name = table.getValueAt(i, 2).toString();
			String password = table.getValueAt(i, 3).toString();
			String type = table.getValueAt(i, 4).toString();
			// controllo dell'enumerativo da scrivere su DB
			if (type.equalsIgnoreCase("Admin")){ type = "Admin"; }
			else if (type.equalsIgnoreCase("Waiter")){ type = "Waiter"; }
			else { type = "Admin"; }
			boolean dbUpdate = Boolean.parseBoolean(table.getValueAt(i, 5).toString());
			// creo l'oggetto UsersLogin e lo inserisco nell'ArrayList
			UsersLogin u = new UsersLogin(a, id, name, password, type, dbUpdate);
			arr.add(u);
		}
		return arr;
	}
	

	
	/**
	 * Metodo che inserisce i dati Conti della JTable in un ArrayList.
	 *
	 * @return i conti dei tavoli dalla jtable
	 * @throws ParseException the parse exception
	 */
	public static ArrayList<TablesCheck> getTablesCheckFromJTable() throws ParseException{
		int rows = table.getRowCount();
		ArrayList<TablesCheck> arr = new ArrayList<TablesCheck>();
		for(int i=0; i<rows; i++){
			// estraggo i campi di una riga della JTable 
			int a = Integer.parseInt(table.getValueAt(i, 0).toString());
			String d = table.getValueAt(i, 1).toString();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
			Date parsed = df.parse(d);
			GregorianCalendar calend = new GregorianCalendar();
			calend.setTime(parsed);
			int month = calend.get(Calendar.MONTH);
			calend.set(Calendar.MONTH, month+1);
			float amount = Float.parseFloat(table.getValueAt(i, 2).toString());
			int peopleNumb = Integer.parseInt(table.getValueAt(i, 3).toString());
			// creo l'oggetto TablesCheck e lo inserisco nell'ArrayList
			TablesCheck c = new TablesCheck(a, calend, amount, peopleNumb);
			arr.add(c);
		}
		return arr;
	}

	
	
	/**
	 * Metodo che inserisce i dati Settings della JTable in un ArrayList.
	 *
	 * @return i settaggi dalla jtable
	 */
	public static ArrayList<Settings> getSettingsFromJTable(){
		int rows = table.getRowCount();
		ArrayList<Settings> arr = new ArrayList<Settings>();
		for(int i=0; i<rows; i++){
			// estraggo i campi di una riga della JTable 
			int a = Integer.parseInt(table.getValueAt(i, 0).toString());
			String nameParam = table.getValueAt(i, 1).toString();
			String value = table.getValueAt(i, 2).toString();
			// creo l'oggetto Settings e lo inserisco nell'ArrayList
			Settings s = new Settings(a, nameParam, value);
			arr.add(s);
		}
		return arr;
	}
	

	
	/**
	 * Passo da stringa nell'interfaccia grafica a nome della tabella del DB.
	 *
	 * @param str stringa letta dalla combobox
	 * @return the nome della tabella a cui si riferisce
	 */
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
		if (str.equalsIgnoreCase("Utenti Login")) translated = "Users";
		if (str.equalsIgnoreCase("Settaggi")) translated = "Settings";
		return translated;
	}
	
	
	
	/**
	 * Metodo per inserire una riga Nuovo Amministratore nella JTable.
	 * 
	 * @param numero dell'amministratore da inserire
	 */
	public static void insertAdmin(int num){
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		int cols = model.getColumnCount();
		model.addRow(new Object[cols]);
		int rows = model.getRowCount();
		model.setValueAt(0, rows-1, 0);
		model.setValueAt("Amm"+num, rows-1, 1);
		model.setValueAt("Admin", rows-1, 4);
		model.setValueAt(true, rows-1, 5);
		model.isCellEditable(0, 1);
		model.isCellEditable(0, 4);
		table.repaint();
	}
	
	
	
	/**
	 * Metodo per inserire una riga vuota nella JTable.
	 */
	public static void insertEmptyRow(){
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		int rows = model.getRowCount();
		int cols = model.getColumnCount();
		Object c[] = new Object[cols];
		model.addRow(c);
		model.setValueAt(0, rows, 0);				// inserisco 0 nella prima colonna
		table.repaint();
	}
	
	
	
	
	/**
	 * Metodo per eliminare la riga selezionata nella JTable.
	 */
	public static void deleteSelectedRow(){
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		int indexRow = table.getSelectedRow();								// posizione della riga da eliminare
		int a = (Integer) model.getValueAt(indexRow, 0);					// estraggo il valore I
		System.out.println(a);
		model.removeRow(indexRow);
		AdminMng.insertRowDeleted(a);										// inserisco I riga eliminata nell'array
		table.repaint();
	}
	
	
	/**
	 * Metodo per leggere la data nella casella DataStart.
	 *
	 * @return the data start
	 */
	public static String getDataStart() {
		String d = textDataStart.getText();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date data = null;
		try {
			data = dateFormat.parse(d);
		} catch (ParseException e) {
		   	textErrorDate.setVisible(true);
	    	textErrorDate.setText("Errore Data");
	    	textErrorDate.setEditable(false);
	    	AdminView.init();
			//e.printStackTrace();
		}
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
	
	
	/**
	 * Metodo per leggere la data nella casella DataStop.
	 *
	 * @return the data stop
	 */
	public static String getDataStop() {
		String d = textDataStop.getText();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date data = null;
		try {
			data = dateFormat.parse(d);
		} catch (ParseException e) {
		   	textErrorDate.setVisible(true);
	    	textErrorDate.setText("Errore Data");
	    	AdminView.init();
	    	textErrorDate.setEditable(false);
			//e.printStackTrace();
		}
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
	
	
	
	
	/**
	 * Metodo per visualizzare i profitti tra un range di date.
	 *
	 * @param res contiene i profitti
	 * @throws SQLException the sQL exception
	 */
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
	
	
	
	
	/**
	 * Metodo per visualizzare il numero di clienti tra un range di date.
	 *
	 * @param res contiene i clienti
	 * @throws SQLException the sQL exception
	 */
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
	
	
	
	
	/**
	 * Metodo per controllare che il giorno e il mese della data inseriti siano validi.
	 *
	 * @param day il giorno
	 * @param month il mese
	 * @param year l'anno
	 * @return true, se corretta
	 */
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
	
	
	
	/**
	 * Metodo per riportare il tutto alla situazione iniziale.
	 */
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
	
	
    
    /**
     * Metodo per settare la textErrorDate in caso di errore.
     */
    public static void textError(){
    	textErrorDate.setVisible(true);
    	textErrorDate.setText("Errore Data");
    	textErrorDate.setEditable(false);
    }
	
	
    
    /**
     * Metodo per rednere invisibile la textErrorDate.
     */
    public static void disableErrorDate(){
    	textErrorDate.setVisible(false);
    }
}
