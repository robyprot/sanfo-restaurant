package start;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.UIManager;

import model.admin.AdminMng;

import server.Server;
import view.DailyView;
import view.AdminView;
import view.LoginView;
import database.Database;
import database.JavaDBException;


// TODO: Auto-generated Javadoc
/**
 * Classe di partenza che contiene il main.
 * 
 * @author Mauro
 */
public class EntryPoint {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the sQL exception
	 * @throws JavaDBException the java db exception
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException, JavaDBException {
	
		// Java GUI Windows Style
		try{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}
		catch (Exception e){
			System.out.println("Unable to load Windows look and feel");
		}
	
		Server server = new Server();

		//DailyView view = new DailyView();
		//AdminView view = new AdminView();
		
		LoginView viewLogin = LoginView.getIstance();
		LoginView.centerTheGUIFrame(viewLogin);
		Database.initDB();
		viewLogin.writeLabelCaricamento("Caricamento Completato");
		
		/*
		GregorianCalendar cal = new GregorianCalendar();
		String day = AdminMng.checkFormat(cal.get(Calendar.DAY_OF_MONTH));
		String month = AdminMng.checkFormat(cal.get(Calendar.MONTH)+1);
		String year = AdminMng.checkFormat(cal.get(Calendar.YEAR));
		String date = year+ "-" +month+ "-" +day;
		System.out.println(date);
		String hours = AdminMng.checkFormat(cal.get(Calendar.HOUR_OF_DAY));
		String minutes = AdminMng.checkFormat(cal.get(Calendar.MINUTE));
		String second = AdminMng.checkFormat(cal.get(Calendar.SECOND));
		String time = hours+ ":" +minutes+ ":" +second;
		System.out.println(time);
		*/
	}
					
}


