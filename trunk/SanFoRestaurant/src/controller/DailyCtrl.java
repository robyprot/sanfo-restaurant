package controller;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import model.admin.AdminMng;
import model.daily.ClientMngDaily;
import model.daily.DailyMng;
import model.daily.FoodMngDaily;
import model.daily.NearTableMngDaily;
import model.daily.OrderMngDaily;
import model.daily.ReservationMngDaily;
import model.daily.RoomMngDaily;
import model.daily.SingleOrderMngDaily;
import model.daily.Table;
import model.daily.TableMngDaily;
import model.daily.TablesCheckMngDaily;
import model.daily.Waiter;
import model.daily.WaiterMngDaily;
import start.Conto;
import view.AdminView;
import view.DailyView;
import database.Database;
import database.JavaDBException;

public class DailyCtrl implements ActionListener {
	 
	public DailyMng dailyMng;
	public String buttonPressed = null;
	public int tableNumb;
	
	
	// constructor
	public DailyCtrl(DailyMng dailyMng){
		this.dailyMng = dailyMng;
	}
	
	public DailyCtrl() throws ClassNotFoundException, SQLException, JavaDBException {
		this.dailyMng = DailyMng.getIstance();
	}
	
	
	

	
	public void actionPerformed(ActionEvent e){
		// estraggo la sorgente dell'evento
		Object sourceObj = e.getSource();	
		
		// se proviene da un BOTTONE, creo un bottone e individuo quale dei bottoni presenti l'ha generato
		if (sourceObj.getClass().toString().equalsIgnoreCase("class javax.swing.JButton")){		
			JButton source = (JButton) e.getSource();											
			
			// Bottone INIZIO SERATA
			if (source.getText().equalsIgnoreCase("Inizio Serata")) {										
				System.out.println("Click Inizio Serata");
				DailyView.start();
				DailyView.disableInizioSerata();
				DailyView.enableAnnulla();
			}
			
			// Bottone ANNULLA
			if (source.getText().equalsIgnoreCase("Annulla")) {										
				System.out.println("Click Annulla");
				buttonPressed = "Annulla";
				DailyView.start();
			}
			
			// Bottone NUOVO TAVOLO
			if (source.getText().equalsIgnoreCase("Nuovo Tavolo")) {										
				System.out.println("Click Nuovo Tavolo");
				buttonPressed = "Nuovo_Tavolo";
				DailyView.enableTextTavolo();
				DailyView.disableStampaConto();
				DailyView.disableCombobox();
			}
			
			// Bottone STAMPA CONTO
			if (source.getText().equalsIgnoreCase("Stampa Conto")) {										
				System.out.println("Click Stampa Conto");
				buttonPressed = "Stampa_Conto";
				DailyView.enableCombobox();
				DailyView.disableNuovoTavolo();
				DailyView.disableTextTavolo();
				try {
					DailyMng d = DailyMng.getIstance();							// istanza di DailyMng
					ArrayList<Table> occup = d.tableMng.getOccupiedTables();	// ritorno i tavoli occupati
					DailyView.writeTablesIntoCombobox(occup);					// scrivo nella combobox
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (JavaDBException e1) {
					e1.printStackTrace();
				}
			}
			
			// Bottone OK
			if (source.getText().equalsIgnoreCase("OK")) {
				System.out.println("Click OK");
				
				if (DailyView.isEnableTextTavolo()==true){			// OK - Nuovo Tavolo
					int people = DailyView.getPeopleNumb();
					//int table = DailyMng.getIstance().searchTable(people);
					DailyView.writeTextFieldLabel("Tavolo numero:");
					//DailyView.writeTextFieldResult(Integer.toString(table));		
				}
				
				if (DailyView.isEnableCombobox()==true){
					int table = tableNumb;
					ArrayList<Conto> conto = new ArrayList<Conto>();
					float total = 0;
					try {
						conto = dailyMng.printCheckTable(table);
						total = dailyMng.printTotal(conto);
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					} catch (JavaDBException e1) {
						e1.printStackTrace();
					}	
					DailyView.writeTextFieldLabel("Conto tavolo " +table+ " :");
					DailyView.writeTextFieldResult(Float.toString(total));
					DailyView.viewCheckIntoTable(conto);
				}
				
			}
			
		}
		
		
		// se proviene da un COMBOBOX
		if (sourceObj.getClass().toString().equalsIgnoreCase("class javax.swing.JComboBox")){
			JComboBox source = (JComboBox) sourceObj;
			// se è il ComboBoxConto
			if (source.getName().equalsIgnoreCase("ComboBoxConto")) {
				int number = Integer.parseInt(source.getSelectedItem().toString());
				System.out.println(number);
				tableNumb = number;
				DailyView.enableOK();
			}
			
		}
		
		
		// se proviene da una TEXTFIELD
		if (sourceObj.getClass().toString().equalsIgnoreCase("class javax.swing.JTextField")){
			JTextField source = (JTextField) sourceObj;
			// se è la TextField di Nuovo Tavolo
			if (source.getName().equalsIgnoreCase("TextFieldTavolo")) {
				DailyView.enableOK();
			}
		}
	}
	
	
	
	
	
}
