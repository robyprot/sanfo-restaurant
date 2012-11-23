package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import start.Conto;

import model.daily.Client;
import model.daily.Table;

import controller.DailyCtrl;
import database.Database;
import database.JavaDBException;


public class DailyView extends JFrame  {

	private DailyCtrl controller;
	private JPanel contentPane;
	private JScrollPane scrollPane_nascosto;
	private JScrollPane scrollPane_nascosto_Conto;
	private static JTable table;
	private static JTable table_Conto;
	private JScrollPane scrollPane;
	private JScrollPane scrollPaneConto;
	private static JComboBox comboBox;
	private static JTextField textFieldTavolo;
	private static JButton btnInizioSerata;
	private static JButton btnNuovoTavolo;
	private static JButton btnStampaConto;
	private static JButton btnOk;
	private static JTextField textFieldLabel;
	private static JTextField textFieldResult;
	private static JButton btnAnnulla;

		
	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws JavaDBException 
	 */
	public DailyView() throws ClassNotFoundException, SQLException, JavaDBException {
		
		this.controller = new DailyCtrl();
	
		//dimensioni schermo intero
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, screenSize.width, screenSize.height);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(733, 63, 596, 603);
		contentPane.add(scrollPane);
		
		scrollPane_nascosto = new JScrollPane();
		scrollPane.setViewportView(scrollPane_nascosto);
		scrollPane_nascosto.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane_nascosto.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		table = new JTable();
		scrollPane_nascosto.setViewportView(table);
		
		btnInizioSerata = new JButton("Inizio Serata");
		btnInizioSerata.setBounds(91, 61, 136, 42);
		contentPane.add(btnInizioSerata);
		
		btnNuovoTavolo = new JButton("Nuovo Tavolo");
		btnNuovoTavolo.setBounds(91, 143, 136, 42);
		btnNuovoTavolo.setEnabled(false);
		contentPane.add(btnNuovoTavolo);
		
		comboBox = new JComboBox();
		comboBox.setToolTipText("\r\n");
		comboBox.setBounds(263, 230, 182, 42);
		comboBox.setName("ComboBoxConto");
		comboBox.setEnabled(false);
		
		
		btnStampaConto = new JButton("Stampa Conto");
		btnStampaConto.setBounds(91, 230, 136, 42);
		btnStampaConto.setEnabled(false);
		contentPane.add(btnStampaConto);
		contentPane.add(comboBox);
		
		textFieldTavolo = new JTextField();
		textFieldTavolo.setBounds(263, 143, 182, 42);
		textFieldTavolo.setEditable(false);
		textFieldTavolo.setColumns(10);
		textFieldTavolo.setName("TextFieldTavolo");
		contentPane.add(textFieldTavolo);
		
		
		btnOk = new JButton("OK");
		btnOk.setBounds(510, 186, 136, 42);
		btnOk.setEnabled(false);
		contentPane.add(btnOk);
		
		textFieldLabel = new JTextField();
		textFieldLabel.setBounds(91, 343, 177, 20);
		contentPane.add(textFieldLabel);
		textFieldLabel.setColumns(10);
		
		textFieldResult = new JTextField();
		textFieldResult.setColumns(10);
		textFieldResult.setBounds(309, 343, 136, 20);
		contentPane.add(textFieldResult);
		
		scrollPaneConto = new JScrollPane();
		scrollPaneConto.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneConto.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPaneConto.setBounds(91, 374, 555, 292);
		contentPane.add(scrollPaneConto);
		
		scrollPane_nascosto_Conto = new JScrollPane();
		scrollPaneConto.setViewportView(scrollPane_nascosto_Conto);
		scrollPane_nascosto_Conto.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane_nascosto_Conto.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		table_Conto = new JTable();
		scrollPane_nascosto_Conto.setViewportView(table_Conto);
		
		btnAnnulla = new JButton("Annulla");
		btnAnnulla.setBounds(510, 266, 136, 42);
		btnAnnulla.setEnabled(false);
		contentPane.add(btnAnnulla);
		
		
		
		btnInizioSerata.addActionListener(controller);
		btnNuovoTavolo.addActionListener(controller);
		btnStampaConto.addActionListener(controller);
		comboBox.addActionListener(controller);
		btnOk.addActionListener(controller);
		btnAnnulla.addActionListener(controller);
		textFieldTavolo.addActionListener(controller);
		this.setVisible(true);
	}
	
	
	// abilitazione iniziale
	public static void start(){
		btnNuovoTavolo.setEnabled(true);
		btnStampaConto.setEnabled(true);
		DailyView.disableCombobox();
		DailyView.disableTextTavolo();
		DailyView.disableOK();
		// per eliminare la tabella Conto
		DefaultTableModel mod = new DefaultTableModel();
		mod = (DefaultTableModel) table_Conto.getModel();
		mod.setRowCount(0);
		// per rimouovere la selezione della Combobox
		comboBox.removeAllItems();
		// per rimuovere il testo nelle TextField
		textFieldLabel.setText("");
		textFieldResult.setText("");
		textFieldTavolo.setText("");
	}
	
	
	// metodo per abilitare la casella di testo Tavolo
	public static void enableTextTavolo(){
		textFieldTavolo.setEditable(true);
	}
	
	// metodo per diabilitare la casella di testo Tavolo
	public static void disableTextTavolo(){
		textFieldTavolo.setEditable(false);
	}
	
	// metodo per sapere se la casella di testo Tavolo è abilitata
	public static boolean isEnableTextTavolo(){
		return textFieldTavolo.isEditable();
	}
	
	// metodo per abilitare la Combobox di Stampa Conto
	public static void enableCombobox(){
		comboBox.setEnabled(true);
	}

	// metodo per disabilitare la Combobox di Stampa Conto
	public static void disableCombobox(){
		comboBox.setEnabled(false);
	}
	
	// metodo per sapere se la Combobox di Stampa Conto è abilitata
	public static boolean isEnableCombobox(){
		return comboBox.isEnabled();
	}
	
	// metodo per abilitare il bottone Inizio Serata
	public static void enableInizioSerata(){
		btnInizioSerata.setEnabled(true);
	}
	
	// metodo per disabilitare il bottone Inizio Serata
	public static void disableInizioSerata(){
		btnInizioSerata.setEnabled(false);
	}
	
	// metodo per abilitare il bottone OK
	public static void enableOK(){
		btnOk.setEnabled(true);
	}
	
	// metodo per disabilitare il bottone OK
	public static void disableOK(){
		btnOk.setEnabled(false);
	}
	
	// metodo per abilitare il bottone Annulla
	public static void enableAnnulla(){
		btnAnnulla.setEnabled(true);
	}
	
	// metodo per disabilitare il bottone Annulla
	public static void disableAnnulla(){
		btnAnnulla.setEnabled(false);
	}
	
	// metodo per abilitare il bottone Nuovo Tavolo
	public static void enableNuovoTavolo(){
		btnNuovoTavolo.setEnabled(true);
	}
	
	// metodo per disabilitare il bottone Nuovo Tavolo
	public static void disableNuovoTavolo(){
		btnNuovoTavolo.setEnabled(false);
	}
	
	// metodo per abilitare il bottone Stampa Conto
	public static void enableStampaConto(){
		btnStampaConto.setEnabled(true);
	}
	
	// metodo per disabilitare il bottone Stampa Conto
	public static void disableStampaConto(){
		btnStampaConto.setEnabled(false);
	}
	

	// scrivo i tavoli dell'ArrayList nella Combobox
	public static void writeTablesIntoCombobox(ArrayList<Table> occupied){
		for(int i=0; i<occupied.size(); i++){
			Table t = occupied.get(i);
			int numb = t.getTableNumb();
			comboBox.insertItemAt(numb, i);
		}
	}
	
	
	// metodo per leggere il numero di persone dalla casella di testo di Nuovo Tavolo
	public static int getPeopleNumb(){
		int people = Integer.parseInt(textFieldTavolo.getText().toString());
		return people;
	}
	
	
	// metodo per scrivere nella TextFieldResult
	public static void writeTextFieldResult(String text){
		textFieldResult.setText(text);
	}
	
	// metodo per scrivere nella TextFieldLabel
	public static void writeTextFieldLabel(String text){
		textFieldLabel.setText(text);
	}
	
	
	// metodo per visualizzare la lista del conto di un tavolo
	public static void viewCheckIntoTable(ArrayList<Conto> conto) {
		String col[] = {"Codice", "Nome", "Prezzo", "Tipo", "Quantità", "Stato"};
		String data[][] = null;
		DefaultTableModel model = new DefaultTableModel(data, col);
		table_Conto.setModel(model);
		for (int i=0; i<conto.size(); i++){
			String cod = conto.get(i).getDish();
			String name = conto.get(i).getName();
			float price = conto.get(i).getPrice();
			String type = conto.get(i).getType();
			int quant = conto.get(i).getQuantity();
			String state = conto.get(i).getState();
			Object obj[] = {cod, name, price, type, quant, state};
			model.insertRow(i, obj);
		}
	}
}
