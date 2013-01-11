/**
 * 
 */
package model.daily;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import database.JavaDBException;

/**
 * @author Marco
 *
 */
public class TableMngDailyTest {

	/**
	 * Test method for {@link model.daily.TableMngDaily#searchFreeTable(int)}.
	 * @throws JavaDBException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void testSearchFreeTable() throws ClassNotFoundException, SQLException, JavaDBException {
		TableMngDaily tableMng =  TableMngDaily.getIstance();
		assertEquals("10-7", tableMng.searchFreeTable(12));
		assertEquals("1-2-5", tableMng.searchFreeTable(10));
		assertEquals("8", tableMng.searchFreeTable(2));
		
	}

}
