package ch.ffhs.wunschliste.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class FreundeBean implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<Freunde> getFreunde() throws ClassNotFoundException, SQLException {

		Connection connect = null;

		String url = "jdbc:mysql://localhost:3306/wunschliste";

		String username = "root";
		String password = "";
		//System.out.println("Führe FreundeBean aus!");
		try {

			Class.forName("com.mysql.jdbc.Driver");

			connect = DriverManager.getConnection(url, username, password);
			//System.out.println("Connection established"+connect);

		} catch (SQLException ex) {
			System.out.println("in exec");
			System.out.println(ex.getMessage());
		}
		//System.out.println("Nach Try-Catch");
		List<Freunde> freunde = new ArrayList<>();
		PreparedStatement pstmt = connect
				.prepareStatement("select freund_id, nutzername, vorname, nachname, email from freunde");
		//System.out.println("Führe Query aus!");
		ResultSet result = pstmt.executeQuery();
		//System.out.println("Führe While-Schleife aus!");
		while (result.next()) {

			Freunde freund = new Freunde();
			freund.setFreund_id(result.getInt("freund_id"));
			freund.setNutzername(result.getString("nutzername"));
			freund.setVorname(result.getString("vorname"));
			freund.setNachname(result.getString("nachname"));
			freund.setEmail(result.getString("email"));
			freunde.add(freund);
			/*
			System.out.println(result.getInt("freund_id"));
			System.out.println(result.getString("nutzername"));
			System.out.println(result.getString("vorname"));
			System.out.println(result.getString("nachname"));
			System.out.println(result.getString("email"));
			*/

		}

		// close resources
		result.close();
		pstmt.close();
		connect.close();

		return freunde;

	}
	
}
