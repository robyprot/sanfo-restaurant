/**
 * 
 */
package model.daily;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import database.JavaDBException;

import start.Conto;

/**
 * @author Marco
 *
 */
public class DailyMngTest {

	/**
	 * Test method for {@link model.daily.DailyMng#printTotal(java.util.ArrayList)}.
	 * @throws JavaDBException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void testPrintTotal() throws ClassNotFoundException, SQLException, JavaDBException {
		DailyMng dailyMng = DailyMng.getIstance();
		ArrayList<Conto> conto = new ArrayList<Conto>();
		conto.add(new Conto("P1", "Lasagna", 6.5f , "Primo", 1, "Servita"));
		conto.add(new Conto("P2", "Maccheroni", 4.0f , "Primo", 1, "Servita"));
		conto.add(new Conto("S1", "Arrosto", 3.5f , "Primo", 1, "Servita"));
		assertEquals(14.0f, dailyMng.printTotal(conto), 1.0f);
	}

	
}
