/**
 * 
 */
package server;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import database.JavaDBException;

/**
 * @author Marco
 *
 */
public class ServerProtocolMngTest {

	/**
	 * Test method for {@link server.ServerProtocolMng#processInput(java.lang.String)}.
	 * @throws SQLException 
	 * @throws JavaDBException 
	 */
	@Test
	public void testProcessInput() throws JavaDBException, SQLException {
		ServerProtocolMng serverProtocolMng = new ServerProtocolMng();
		assertEquals("AUT-3", serverProtocolMng.processInput("AUT-Rossi-Mario-RossiMario"));
		assertEquals("STP", serverProtocolMng.processInput("STP"));
		assertEquals("UPF-update non presenti", serverProtocolMng.processInput("UPD-menu-tevoli"));
		assertEquals("ASO", serverProtocolMng.processInput("RSO"));
		assertEquals("ASO", serverProtocolMng.processInput("SNO-1-P1-2-/"));
		assertEquals("STP", serverProtocolMng.processInput("SOF"));
		assertEquals("ASD", serverProtocolMng.processInput("RDO"));
		assertEquals("ASD", serverProtocolMng.processInput("RDO-1-P1-2-/"));
	}

	/**
	 * Test method for {@link server.ServerProtocolMng#autentication(java.lang.String)}.
	 * @throws SQLException 
	 * @throws JavaDBException 
	 */
	@Test
	public void testAutentication() throws JavaDBException, SQLException {
		ServerProtocolMng serverProtocolMng = new ServerProtocolMng();	
		assertEquals("AUT-3", serverProtocolMng.autentication("AUT-Rossi-Mario-RossiMario"));
	}

}
