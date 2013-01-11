/**
 * 
 */
package model.login;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import database.JavaDBException;

/**
 * @author Marco
 *
 */
public class LoginMngTest {

	/**
	 * Test method for {@link model.login.LoginMng#identityVerification(java.lang.String, java.lang.String)}.
	 * @throws JavaDBException 
	 * @throws SQLException 
	 */
	@Test
	public void testIdentityVerification() throws SQLException, JavaDBException {
		LoginMng loginMng = LoginMng.getIstance();
		//String[] credential = new String[3];
		//credential[0] = "Rossi";
		//credential[1] = "Mario";
		//credential[2] = "RossiMario";
		assertEquals("Waiter", loginMng.identityVerification("RossiMario", "RossiMario"));
 	}

	/**
	 * Test method for {@link model.login.LoginMng#autentication(java.lang.String[])}.
	 * @throws SQLException 
	 * @throws JavaDBException 
	 */
	@Test
	public void testAutentication() throws JavaDBException, SQLException {
		String[] credential = new String[3];
		credential[0] = "Rossi";
		credential[1] = "Mario";
		credential[2] = "RossiMario";
		assertEquals("3", LoginMng.autentication(credential));		
	}

}
