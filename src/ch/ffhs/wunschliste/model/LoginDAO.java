package ch.ffhs.wunschliste.model;

//Beispiel von:https://www.journaldev.com/7252/jsf-authentication-login-logout-database-example

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {

	public static boolean validate(String user, String password) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement("Select user_name, user_password from users where user_name = ? and user_password = ?");
			ps.setString(1, user);
			ps.setString(2, password);
			System.out.println(ps.toString());
			ResultSet rs = ps.executeQuery();
			//System.out.println("Komme bis hier!");

			if (rs.next()) {
				PresentUser puser = new PresentUser();
				//puser.setVorname(rs.getString("user_vorname"));
				//System.out.println("War korrekt!");
				return true;
			}
			} catch (SQLException ex) {
				System.out.println("Login error -->" + ex.getMessage());
				return false;
			} finally {
				DataConnect.close(con);
			}
			//System.out.println("Komme auch bis hier!");
			return false;
	}
}
