package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
import model.daily.Order;
import model.daily.SingleOrder;
import model.daily.Table;
import start.Conto;
import start.SingOrd;
import controller.DailyCtrl;
import database.JavaDBException;


// TODO: Auto-generated Javadoc
/**
 * Classe Serata view che contiene tutti i componenti grafici per interfacciarsi con il cameriere
 * e tutti i metodi per gestirli.
 * 
 * @author Mauro
 */
public class DailyView extends JFrame  {

	/** The rows. */
	private static int rows = 0;
	
	/** The controller. */
	private DailyCtrl controller;
	
	/** The content pane. */
	private JPanel contentPane;
	
	/** The scroll pane_nascosto. */
	private JScrollPane scrollPane_nascosto;
	
	/** The scroll pane_nascosto_ conto. */
	private JScrollPane scrollPane_nascosto_Conto;
	
	/** The table sing ord. */
	private static JTable tableSingOrd;
	
	/** The table_ conto. */
	private static JTable table_Conto;
	
	/** The scroll pane. */
	private JScrollPane scrollPane;
	
	/** The scroll pane conto. */
	private JScrollPane scrollPaneConto;
	
	/** The combo box. */
	private static JComboBox comboBox;
	
	/** The text field tavolo. */
	private static JTextField textFieldTavolo;
	
	/** The btn inizio serata. */
	private static JButton btnInizioSerata;
	
	/** The btn fine serata. */
	private static JButton btnFineSerata;
	
	/** The btn nuovo tavolo. */
	private static JButton btnNuovoTavolo;
	
	/** The btn stampa conto. */
	private static JButton btnStampaConto;
	
	/** The btn ok. */
	private static JButton btnOk;
	
	/** The text field label. */
	private static JTextField textFieldLabel;
	
	/** The text field result. */
	private static JTextField textFieldResult;
	
	/** The btn annulla. */
	private static JButton btnAnnulla;
	
	/** The text field2 tavolo. */
	private static JTextField textField2Tavolo;
	
	/** The text field2 persone. */
	private static JTextField textField2Persone;
	
	/** The btn scegli tavolo. */
	private static JButton btnScegliTavolo;
	
	/** The lbl nuovo tavolo. */
	private static JLabel lblNuovoTavolo;
	
	/** The lbl stampa conto. */
	private static JLabel lblStampaConto;
	
	/** The lbl scegli tavolo_tavolo. */
	private static JLabel lblScegliTavolo_tavolo;
	
	/** The lbl scegli tavolo_persone. */
	private static JLabel lblScegliTavolo_persone;
	
	/** The text field ip server. */
	private static JTextField textFieldIPServer;
	
	/** The btn servita. */
	private static JButton btnServita;
	
	/** The btn cancellata. */
	private static JButton btnCancellata;
	
	/** The btn salva db. */
	private static JButton btnSalvaDB;
	
	/** The btn vis tavoli. */
	private static JButton btnVisTavoli;
	
	/** The btn vis ordinazioni. */
	private static JButton btnVisOrdinazioni;
	
	/** The btn vis single ordinazioni. */
	private static JButton btnVisSingle;
	private JLabel lblNewLabel;
	
	
	
	
	/**
	 * Create the frame.
	 *
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the sQL exception
	 * @throws JavaDBException the java db exception
	 */
	public DailyView() throws ClassNotFoundException, SQLException, JavaDBException {
		setTitle("Serata");
		
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
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		//scrollPane.setBounds(692, 63, 637, 571);
		scrollPane.setBounds(692, 63, screenSize.width-37-692, 571);
		contentPane.add(scrollPane);
		
		scrollPane_nascosto = new JScrollPane();
		scrollPane.setViewportView(scrollPane_nascosto);
		scrollPane_nascosto.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_nascosto.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		tableSingOrd = new JTable();
		scrollPane_nascosto.setViewportView(tableSingOrd);
		String col[] = {"I", "Tavolo", "Cod Piatto", "Quantità", "Note", "Stato"};
		String dataset[][] = null;
		DefaultTableModel model = new DefaultTableModel(dataset, col);
		tableSingOrd.setModel(model);
		
		btnInizioSerata = new JButton("Inizio Serata");
		btnInizioSerata.setBounds(30, 23, 136, 42);
		contentPane.add(btnInizioSerata);
	
		btnFineSerata = new JButton("Fine Serata");
		btnFineSerata.setBounds(213, 23, 136, 42);
		btnFineSerata.setEnabled(false);
		contentPane.add(btnFineSerata);
		
		btnNuovoTavolo = new JButton("Nuovo Tavolo");
		btnNuovoTavolo.setBounds(30, 106, 136, 23);
		btnNuovoTavolo.setEnabled(false);
		contentPane.add(btnNuovoTavolo);
		
		comboBox = new JComboBox();
		comboBox.setToolTipText("\r\n");
		comboBox.setBounds(213, 161, 136, 23);
		comboBox.setName("ComboBoxConto");
		comboBox.setEnabled(false);
		
		
		btnStampaConto = new JButton("Stampa Conto");
		btnStampaConto.setBounds(30, 161, 136, 23);
		btnStampaConto.setEnabled(false);
		contentPane.add(btnStampaConto);
		contentPane.add(comboBox);
		
		textFieldTavolo = new JTextField();
		textFieldTavolo.setBounds(213, 106, 136, 23);
		textFieldTavolo.setEditable(false);
		textFieldTavolo.setColumns(10);
		textFieldTavolo.setName("TextFieldTavolo");
		contentPane.add(textFieldTavolo);
		
		
		btnOk = new JButton("OK");
		btnOk.setBounds(425, 87, 136, 42);
		btnOk.setEnabled(false);
		contentPane.add(btnOk);
		
		textFieldLabel = new JTextField();
		textFieldLabel.setBounds(91, 375, 177, 20);
		contentPane.add(textFieldLabel);
		textFieldLabel.setColumns(10);
		
		textFieldResult = new JTextField();
		textFieldResult.setColumns(10);
		textFieldResult.setBounds(309, 375, 136, 20);
		contentPane.add(textFieldResult);
		
		scrollPaneConto = new JScrollPane();
		scrollPaneConto.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneConto.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPaneConto.setBounds(91, 406, 555, 190);
		contentPane.add(scrollPaneConto);
		
		scrollPane_nascosto_Conto = new JScrollPane();
		scrollPaneConto.setViewportView(scrollPane_nascosto_Conto);
		scrollPane_nascosto_Conto.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane_nascosto_Conto.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		table_Conto = new JTable();
		scrollPane_nascosto_Conto.setViewportView(table_Conto);
		
		
		btnAnnulla = new JButton("Annulla");
		btnAnnulla.setBounds(425, 151, 136, 42);
		btnAnnulla.setEnabled(false);
		contentPane.add(btnAnnulla);
		
		btnScegliTavolo = new JButton("Scegli Tavolo");
		btnScegliTavolo.setEnabled(false);
		btnScegliTavolo.setBounds(30, 216, 136, 23);
		contentPane.add(btnScegliTavolo);
		
		textField2Tavolo = new JTextField();
		textField2Tavolo.setName("TextField2Tavolo");
		textField2Tavolo.setEditable(false);
		textField2Tavolo.setColumns(10);
		textField2Tavolo.setBounds(213, 216, 136, 23);
		contentPane.add(textField2Tavolo);
		
		textField2Persone = new JTextField();
		textField2Persone.setName("TextField2Persone");
		textField2Persone.setEditable(false);
		textField2Persone.setColumns(10);
		textField2Persone.setBounds(213, 273, 136, 23);
		contentPane.add(textField2Persone);
		
		lblNuovoTavolo = new JLabel("Inserisci numero di persone:");
		lblNuovoTavolo.setBounds(213, 82, 157, 23);
		lblNuovoTavolo.setVisible(false);
		contentPane.add(lblNuovoTavolo);
		
		lblStampaConto = new JLabel("Seleziona il tavolo:");
		lblStampaConto.setBounds(213, 140, 157, 23);
		lblStampaConto.setVisible(false);
		contentPane.add(lblStampaConto);
		
		lblScegliTavolo_tavolo = new JLabel("Inserisci il tavolo:");
		lblScegliTavolo_tavolo.setBounds(213, 195, 157, 23);
		lblScegliTavolo_tavolo.setVisible(false);;
		contentPane.add(lblScegliTavolo_tavolo);
		
		lblScegliTavolo_persone = new JLabel("Inserisci numero di persone:");
		lblScegliTavolo_persone.setBounds(213, 250, 157, 23);
		lblScegliTavolo_persone.setVisible(false);
		contentPane.add(lblScegliTavolo_persone);
		
		textFieldIPServer = new JTextField();
		//textFieldIPServer.setBounds(1161, 11, 168, 20);
		textFieldIPServer.setBounds(screenSize.width-37-168, 11, 168, 20);
		textFieldIPServer.setEditable(false);
		contentPane.add(textFieldIPServer);
		textFieldIPServer.setColumns(10);
		
		JLabel lblIPServer = new JLabel("IP Server:");
		//lblIPServer.setBounds(1083, 11, 68, 20);
		lblIPServer.setBounds(screenSize.width-215-68, 11, 68, 20);
		contentPane.add(lblIPServer);
		
		btnServita = new JButton("Ordinazione Servita");
		btnServita.setEnabled(false);
		btnServita.setBounds(692, 659, 157, 23);
		contentPane.add(btnServita);
		
		btnSalvaDB = new JButton("Salvataggio su DB");
		btnSalvaDB.setEnabled(false);
		btnSalvaDB.setBounds(1051, 659, 168, 23);
		contentPane.add(btnSalvaDB);
		
		btnVisTavoli = new JButton("Visualizza Tavoli");
		btnVisTavoli.setEnabled(false);
		btnVisTavoli.setBounds(510, 372, 136, 23);
		contentPane.add(btnVisTavoli);
		
		btnVisOrdinazioni = new JButton("Visualizza Ordinazioni");
		btnVisOrdinazioni.setEnabled(false);
		btnVisOrdinazioni.setBounds(510, 338, 136, 23);
		contentPane.add(btnVisOrdinazioni);
		
		JLabel labelSingoleOrd = new JLabel("Singole Ordinazioni in tempo reale:");
		labelSingoleOrd.setBounds(692, 32, 241, 20);
		contentPane.add(labelSingoleOrd);
		
		btnVisSingle = new JButton("Visualizza Singole Ord");
		btnVisSingle.setEnabled(false);
		btnVisSingle.setBounds(510, 304, 136, 23);
		contentPane.add(btnVisSingle);
		
		btnCancellata = new JButton("Ordinazione Cancellata");
		btnCancellata.setEnabled(false);
		btnCancellata.setBounds(872, 659, 157, 23);
		contentPane.add(btnCancellata);
		
		lblNewLabel = new JLabel(new ImageIcon(getClass().getResource("logoRistorante.png")));
		lblNewLabel.setBounds(10, 607, 100, 83);
		contentPane.add(lblNewLabel);
		

		
		
		
		btnInizioSerata.addActionListener(controller);
		btnFineSerata.addActionListener(controller);
		btnNuovoTavolo.addActionListener(controller);
		btnStampaConto.addActionListener(controller);
		comboBox.addActionListener(controller);
		btnOk.addActionListener(controller);
		btnAnnulla.addActionListener(controller);
		textFieldTavolo.addActionListener(controller);
		btnScegliTavolo.addActionListener(controller);
		btnServita.addActionListener(controller);
		btnSalvaDB.addActionListener(controller);
		btnVisTavoli.addActionListener(controller);
		btnVisOrdinazioni.addActionListener(controller);
		textField2Persone.addActionListener(controller);
		btnVisSingle.addActionListener(controller);
		btnCancellata.addActionListener(controller);
		this.setVisible(true);
	}
	
	
	
	
	
	
	/**
	 * Setta a 0 la variabile rows che rappresenta la riga i-esima.
	 */
	public static void setRowsZero(){
		DefaultTableModel model = (DefaultTableModel) tableSingOrd.getModel();
		model.setRowCount(0);
		rows = 0;
	}
	
	
	/**
	 * Decrementa di 1 la variabile rows che rappresenta la riga i-esima.
	 */
	public static void decrementRows(){
		rows = rows-1;
	}
	
	
	/**
	 * Abilitazione iniziale.
	 */
	public static void start(){
		DailyCtrl.isAnnulla = true;
		btnNuovoTavolo.setEnabled(true);
		btnStampaConto.setEnabled(true);
		btnFineSerata.setEnabled(true);
		btnScegliTavolo.setEnabled(true);
		btnVisTavoli.setEnabled(true);
		btnVisOrdinazioni.setEnabled(true);
		btnVisSingle.setEnabled(true);
		DailyView.disableCombobox();
		DailyView.disableTextTavolo();
		DailyView.disableOK();
		DailyView.disableTextTavolo2();
		DailyView.disableTextPersone2();
		DailyView.enableLabelNuovoTavolo();
		DailyView.enableLabelStampaConto();
		DailyView.enableLabelScegliTavolo_tavolo();
		DailyView.enableLabelScegliTavolo_persone();
		DailyView.enableSalvaDB();
		DailyView.enableServita();
		DailyView.enableCancellata();
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
		textField2Persone.setText("");
		textField2Tavolo.setText("");
		DailyCtrl.isAnnulla = false;
	}
	
	
	
	/**
	 * Riabilitazione dopo la pressione del tasto FineSerata.
	 */
	public static void init(){
		btnNuovoTavolo.setEnabled(false);
		btnStampaConto.setEnabled(false);
		btnFineSerata.setEnabled(false);
		btnScegliTavolo.setEnabled(false);
		btnVisTavoli.setEnabled(false);
		btnVisOrdinazioni.setEnabled(false);
		btnInizioSerata.setEnabled(true);
		btnAnnulla.setEnabled(false);
		DailyView.disableCombobox();
		DailyView.disableTextTavolo();
		DailyView.disableOK();
		DailyView.disableTextTavolo2();
		DailyView.disableTextPersone2();
		DailyView.disableLabelNuovoTavolo();
		DailyView.disableLabelStampaConto();
		DailyView.disableLabelScegliTavolo_tavolo();
		DailyView.disableLabelScegliTavolo_persone();
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
		textField2Persone.setText("");
		textField2Tavolo.setText("");
	}
	
	
	
	
	
	/**
	 * Metodo per abilitare la casella di testo Tavolo.
	 */
	public static void enableTextTavolo(){
		textFieldTavolo.setEditable(true);
	}
	
	
	/**
	 * Metodo per disabilitare la casella di testo Tavolo.
	 */
	public static void disableTextTavolo(){
		textFieldTavolo.setEditable(false);
	}
	
	
	/**
	 * Metodo per sapere se la casella di testo Tavolo è abilitata.
	 *
	 * @return true, se testo Tavolo è abilitata
	 */
	public static boolean isEnableTextTavolo(){
		return textFieldTavolo.isEditable();
	}
	
	
	/**
	 * Metodo per abilitare la Combobox di Stampa Conto.
	 */
	public static void enableCombobox(){
		comboBox.setEnabled(true);
	}

	
	/**
	 * Metodo per disabilitare la Combobox di Stampa Conto.
	 */
	public static void disableCombobox(){
		comboBox.setEnabled(false);
	}
	
	
	/**
	 * Metodo per sapere se la Combobox di Stampa Conto è abilitata.
	 *
	 * @return true, se la combobox è abilitata
	 */
	public static boolean isEnableCombobox(){
		return comboBox.isEnabled();
	}
	
	
	/**
	 * Metodo per abilitare il bottone Inizio Serata.
	 */
	public static void enableInizioSerata(){
		btnInizioSerata.setEnabled(true);
	}
	
	
	/**
	 * Metodo per disabilitare il bottone Inizio Serata.
	 */
	public static void disableInizioSerata(){
		btnInizioSerata.setEnabled(false);
	}
	
	
	/**
	 * Metodo per abilitare il bottone Fine Serata.
	 */
	public static void enableFineSerata(){
		btnFineSerata.setEnabled(true);
	}
	
	
	/**
	 * Metodo per disabilitare il bottone Fine Serata.
	 */
	public static void disableFineSerata(){
		btnFineSerata.setEnabled(false);
	}
	
	
	/**
	 * Metodo per abilitare il bottone OK.
	 */
	public static void enableOK(){
		btnOk.setEnabled(true);
	}
	
	
	/**
	 * Metodo per disabilitare il bottone OK.
	 */
	public static void disableOK(){
		btnOk.setEnabled(false);
	}
	
	
	/**
	 * Metodo per abilitare il bottone Annulla.
	 */
	public static void enableAnnulla(){
		btnAnnulla.setEnabled(true);
	}
	
	
	/**
	 * Metodo per disabilitare il bottone Annulla.
	 */
	public static void disableAnnulla(){
		btnAnnulla.setEnabled(false);
	}
	
	
	/**
	 * Metodo per abilitare il bottone Nuovo Tavolo.
	 */
	public static void enableNuovoTavolo(){
		btnNuovoTavolo.setEnabled(true);
	}
	
	
	/**
	 * Metodo per disabilitare il bottone Nuovo Tavolo.
	 */
	public static void disableNuovoTavolo(){
		btnNuovoTavolo.setEnabled(false);
	}
	
	
	/**
	 * Metodo per abilitare il bottone Stampa Conto.
	 */
	public static void enableStampaConto(){
		btnStampaConto.setEnabled(true);
	}
	
	
	/**
	 * Metodo per disabilitare il bottone Stampa Contoo.
	 */
	public static void disableStampaConto(){
		btnStampaConto.setEnabled(false);
	}
	
	
	/**
	 * Metodo per abilitare la casella di testo Tavolo2.
	 */
	public static void enableTextTavolo2(){
		textField2Tavolo.setEditable(true);
	}
	
	
	/**
	 * Metodo per disabilitare la casella di testo Tavolo2.
	 */
	public static void disableTextTavolo2(){
		textField2Tavolo.setEditable(false);
	}
	
	
	/**
	 * Metodo per sapere se la casella di testo Tavolo2 è abilitata.
	 *
	 * @return true, se text tavolo2 è abilitata
	 */
	public static boolean isEnableTextTavolo2(){
		return textField2Tavolo.isEditable();
	}
	
	
	/**
	 * Metodo per abilitare la casella di testo Persone2.
	 */
	public static void enableTextPersone2(){
		textField2Persone.setEditable(true);
	}
	
	
	/**
	 * Metodo per disabilitare la casella di testo Persone2.
	 */
	public static void disableTextPersone2(){
		textField2Persone.setEditable(false);
	}
	
	
	/**
	 * Metodo per sapere se la casella di testo Persone2 è abilitata.
	 *
	 * @return true, se text persone2 è abilitata
	 */
	public static boolean isEnableTextPersone2(){
		return textField2Persone.isEditable();
	}
	
	
	/**
	 * Metodo per abilitare il bottone Scegli Tavolo.
	 */
	public static void enableScegliTavolo(){
		btnScegliTavolo.setEnabled(true);
	}
	
	
	/**
	 * Metodo per disabilitare il bottone Scegli Tavolo.
	 */
	public static void disableScegliTavolo(){
		btnScegliTavolo.setEnabled(false);
	}
	
	
	/**
	 * Metodo per rendere visibile la label NuovoTavolo.
	 */
	public static void enableLabelNuovoTavolo(){
		lblNuovoTavolo.setVisible(true);
	}
	
	
	/**
	 * Metodo per rendere nascosta la label NuovoTavolo.
	 */
	public static void disableLabelNuovoTavolo(){
		lblNuovoTavolo.setVisible(false);
	}
	
	
	/**
	 * Metodo per rendere visibile la label StampaConto.
	 */
	public static void enableLabelStampaConto(){
		lblStampaConto.setVisible(true);
	}
	
	
	/**
	 * Metodo per rendere nascosta la label StampaConto.
	 */
	public static void disableLabelStampaConto(){
		lblStampaConto.setVisible(false);
	}
	
	
	/**
	 * Metodo per rendere visibile la label ScegliTavolo_tavolo.
	 */
	public static void enableLabelScegliTavolo_tavolo(){
		lblScegliTavolo_tavolo.setVisible(true);
	}
	
	
	/**
	 * Metodo per rendere nascosta la label ScegliTavolo_tavolo.
	 */
	public static void disableLabelScegliTavolo_tavolo(){
		lblScegliTavolo_tavolo.setVisible(false);
	}
	
	
	/**
	 * Metodo per rendere visibile la label ScegliTavolo_persone.
	 */
	public static void enableLabelScegliTavolo_persone(){
		lblScegliTavolo_persone.setVisible(true);
	}
	
	
	/**
	 * Metodo per rendere nascosta la label ScegliTavolo_persone.
	 */
	public static void disableLabelScegliTavolo_persone(){
		lblScegliTavolo_persone.setVisible(false);
	}
	
	
	/**
	 * Metodo per abilitare il bottone SalvaDB.
	 */
	public static void enableSalvaDB(){
		btnSalvaDB.setEnabled(true);
	}
	
	
	/**
	 * Metodo per disabilitare il bottone SalvaDB.
	 */
	public static void disableSalvaDB(){
		btnSalvaDB.setEnabled(false);
	}
	
	
	/**
	 * Metodo per abilitare il bottone Servita.
	 */
	public static void enableServita(){
		btnServita.setEnabled(true);
	}
	
	
	/**
	 * Metodo per disabilitare il bottone Servita.
	 */
	public static void disableServita(){
		btnServita.setEnabled(false);
	}
	
	
	/**
	 * Metodo per abilitare il bottone VisTavoli.
	 */
	public static void enableVisTavoli(){
		btnVisTavoli.setEnabled(true);
	}
	
	
	/**
	 * Metodo per disabilitare il bottone VisTavoli.
	 */
	public static void disableVisTavoli(){
		btnVisTavoli.setEnabled(false);
	}
	
	
	/**
	 * Metodo per abilitare il bottone VisOrdinazioni.
	 */
	public static void enableVisOrdinazioni(){
		btnVisOrdinazioni.setEnabled(true);
	}
	
	
	/**
	 * Metodo per disabilitare il bottone VisOrdinazioni.
	 */
	public static void disableVisOrdinazioni(){
		btnVisOrdinazioni.setEnabled(false);
	}
	
	
	/**
	 * Metodo per abilitare il bottone VisSingle.
	 */
	public static void enableVisSingle(){
		btnVisSingle.setEnabled(true);
	}
	
	
	/**
	 * Metodo per disabilitare il bottone VisSingle.
	 */
	public static void disableVisSingle(){
		btnVisSingle.setEnabled(false);
	}
	
	
	/**
	 * Metodo per abilitare il bottone Cancellata.
	 */
	public static void enableCancellata(){
		btnCancellata.setEnabled(true);
	}
	
	
	/**
	 * Metodo per disabilitare il bottone Cancellata.
	 */
	public static void disableCancellata(){
		btnCancellata.setEnabled(false);
	}
	
	
	
	
	/**
	 * Scrivo i tavoli dell'ArrayList nella Combobox.
	 *
	 * @param occupied tavoli occupati
	 */
	public static void writeTablesIntoCombobox(ArrayList<Table> occupied){
		for(int i=0; i<occupied.size(); i++){
			Table t = occupied.get(i);
			int numb = t.getTableNumb();
			comboBox.insertItemAt(numb, i);
		}
	}
	
	
	
	/**
	 * Metodo per leggere il numero di persone dalla casella di testo di Nuovo Tavolo.
	 *
	 * @return il numero di persone
	 */
	public static int getPeopleNumb(){
		int people = 0;
		String p = textFieldTavolo.getText().toString();		// estraggo la stringa persone
		boolean isNumber = isNumeric(p);						// controllo se equivale ad un numero
		if (isNumber==true) {									
			people = Integer.parseInt(p);						// se si effettuo la conversione altrimenti rimane = 0
		}					
		return people;
	}
	
	
	
	/**
	 * Metodo per leggere il numero di persone dalla casella di testo di Scegli Tavolo riferita alle persone.
	 *
	 * @return il numero di persone
	 */
	public static int getPeopleNumb2(){
		int people = 0;
		String p = textField2Persone.getText().toString();		// estraggo la stringa persone
		boolean isNumber = isNumeric(p);						// controllo se equivale ad un numero
		if (isNumber==true) {									
			people = Integer.parseInt(p);						// se si effettuo la conversione altrimenti rimane = 0
		}					
		return people;
	}
	
	
	
	/**
	 * Metodo per leggere il numero del tavolo dalla casella di testo di Scegli Tavolo riferita al tavolo.
	 *
	 * @return il numero del tavolo
	 */
	public static int getTableNumb2(){
		int table = 0;
		String t = textField2Tavolo.getText().toString();		// estraggo la stringa tavolo
		boolean isNumber = isNumeric(t);						// controllo se equivale ad un numero
		if (isNumber==true) {									
			table = Integer.parseInt(t);						// se si effettuo la conversione altrimenti rimane = 0
		}					
		return table;
	}
	
	
	
	/**
	 * Metodo che data una stringa mi dice se equivale ad un intero.
	 *
	 * @param s la stringa da controllare
	 * @return true, se è un numero
	 */
	public static boolean isNumeric(String s) {
		boolean numerico = true;
		char[] sequenza = s.toCharArray();
		for (int i=0; i< sequenza.length; i++) {
			try {
				Integer.parseInt(Character.toString(sequenza[i]));
			} catch (Exception e) {
				numerico = false;
			}
		}
		return numerico;
	}


	
	/**
	 * Metodo per scrivere nella TextFieldResult.
	 *
	 * @param text testo da scrivere
	 */
	public static void writeTextFieldResult(String text){
		textFieldResult.setText(text);
	}
	
	
	/**
	 * Metodo per scrivere nella TextFieldLabel.
	 *
	 * @param text testo da scrivere
	 */
	public static void writeTextFieldLabel(String text){
		textFieldLabel.setText(text);
	}
	
	
	/**
	 * Metodo per scrivere nella TextFieldIPServer.
	 *
	 * @param text testo da scrivere
	 */
	public static void writeTextFieldIPServer(String text){
		textFieldIPServer.setText(text);
	}
	
	
	
	/**
	 * Metodo per visualizzare la lista del conto di un tavolo.
	 *
	 * @param conto lista conto
	 * @param totale il totale
	 * @param sconto lo sconto da applicare
	 */
	public static void viewCheckIntoTable(ArrayList<Conto> conto, float totale, int sconto, int people, int coperto) {
		String col[] = {"Codice", "Nome", "Prezzo", "Tipo", "Quantità", "Stato"};
		String data[][] = null;
		DefaultTableModel model = new DefaultTableModel(data, col);
		table_Conto.setModel(model);
		int index = 0;
		for (int i=0; i<conto.size(); i++){
			String cod = conto.get(i).getDish();
			String name = conto.get(i).getName();
			float price = conto.get(i).getPrice();
			String type = conto.get(i).getType();
			String quant = String.valueOf(conto.get(i).getQuantity());
			String state = conto.get(i).getState();
			Object obj[] = {cod, name, price, type, quant, state};
			model.insertRow(i, obj);
			index = i;
		}

		if(totale>0){
			int totcoperto = coperto * people; 
			Object o[] = {"", "Coperto:", totcoperto, "", "", ""};
			model.insertRow(index+1, o);
			Object o1[] = {"", "Totale:", totale, "", "", ""};
			model.insertRow(index+2, o1);
			if(sconto>0){
				Object o2[] = {"", "Sconto %:", sconto, "", "", ""};
				model.insertRow(index+3, o2);
				totale = totale - sconto*totale/100;
				Object o3[] = {"", "Totale:", totale, "", "", ""};
				model.insertRow(index+4, o3);
			}
		}
	}
	
	
	
	/**
	 * Metodo per visualizzare la stringa della singola ordinazione nella JTable.
	 *
	 * @param sing stringa singola ordinazione
	 * @throws SQLException the sQL exception
	 */
	public static void viewSingOrd(SingOrd sing) throws SQLException{
		int a = sing.getA();
		String num = sing.getNum();
		String code = sing.getCode();
		String quantity = sing.getQuantity();
		String note = sing.getNote();
		String status = sing.getStatus();
		Object obj[] = {a, num, code, quantity, note, status};
		DefaultTableModel model = (DefaultTableModel) tableSingOrd.getModel();
		TableColumn colonna = tableSingOrd.getColumnModel().getColumn(0);
		colonna.setMinWidth(0);
		colonna.setMaxWidth(0);
		colonna.setPreferredWidth(0);
		model.insertRow(rows, obj);
		rows++;
	}
	
	
	
	/**
	 * Metodo che ritorna l'identificativo della singola ordinazione selezionata nella JTable e la elimina dalla visualizzazione.
	 *
	 * @return identificativo singola ordinazione selezionata 
	 */
	public static int getSingOrdSelected(){
		DefaultTableModel model = (DefaultTableModel)tableSingOrd.getModel();
		int indexRow = tableSingOrd.getSelectedRow();						// posizione della riga selezionata
		int a = (Integer) model.getValueAt(indexRow, 0);					// estraggo il valore I
		System.out.println(a);
		model.removeRow(indexRow);
		tableSingOrd.repaint();
		return a;
	}
	
	
	
	/**
	 * Metodo che visualizza la lista tavoli sulla JTable.
	 *
	 * @param tables lista tavoli
	 */
	public static void viewTablesIntoJTable(ArrayList<Table> tables) {
		String col[] = {"Numero", "Posti", "Sala", "Stato", "Cameriere", "Unito"};
		String data[][] = null;
		DefaultTableModel model = new DefaultTableModel(data, col);
		table_Conto.setModel(model); 
		for (int i=0; i<tables.size(); i++){
			Table t = tables.get(i);
			int numb = t.getTableNumb();
			int seats = t.getSeats();
			int content = t.getContent();
			String state = t.getState();
			int waiterCode = t.getWaiterCode();
			String joined = t.getJoined();
			Object obj[] = {numb, seats, content, state, waiterCode, joined};
			model.insertRow(i, obj);
		}
	}
	
	
	
	/**
	 * Metodo che visualizza la lista ordinazioni sulla JTable.
	 *
	 * @param orders le ordinazioni
	 */
	public static void viewOrdersIntoJTable(ArrayList<Order> orders) {
		String col[] = {"Numero", "Tavolo", "Num Coperti", "Stato"};
		String data[][] = null;
		DefaultTableModel model = new DefaultTableModel(data, col);
		table_Conto.setModel(model); 
		for (int i=0; i<orders.size(); i++){
			Order o = orders.get(i);
			int numb = o.getOrderNumb();
			int table = o.getRelativeTo();
			int seats = o.getSeatsNumb();
			String state = o.getStateOrd();
			Object obj[] = {numb, table, seats, state};
			model.insertRow(i, obj);
		}
	}
	
	
	
	/**
	 * Metodo che visualizza la lista singole ordinazioni sulla JTable.
	 *
	 * @param singleOrders le singole ordinazioni
	 */
	public static void viewSingleOrdersIntoJTable(ArrayList<SingleOrder> singleOrders) {
		String col[] = {"Num Ordinazione", "Quantità", "Cod Piatto", "Stato", "Note"};
		String data[][] = null;
		DefaultTableModel model = new DefaultTableModel(data, col);
		table_Conto.setModel(model); 
		for (int i=0; i<singleOrders.size(); i++){
			SingleOrder s = singleOrders.get(i);
			int numb = s.getOrdNumb();
			int quantity = s.getQuantity();
			String dish = s.getDish();
			String state = s.getState();
			String notes = s.getNotes();
			Object obj[] = {numb, quantity, dish, state, notes};
			model.insertRow(i, obj);
		}
	}
}