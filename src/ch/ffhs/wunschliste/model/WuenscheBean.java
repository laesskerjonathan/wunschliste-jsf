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
public class WuenscheBean implements Serializable  {

	private static final long serialVersionUID = 2L;
	
	public List<Wuensche> getwuensche() throws ClassNotFoundException, SQLException {

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
		List<Wuensche> wuensche = new ArrayList<>();
		PreparedStatement pstmt = connect
				.prepareStatement("select wunsch_id, titel, beschreibung, link, prioritaet from wuensche");
		//System.out.println("Führe Query aus!");
		ResultSet result = pstmt.executeQuery();
		//System.out.println("Führe While-Schleife aus!");
		while (result.next()) {

			Wuensche wunsch = new Wuensche();
			wunsch.setWunsch_id(result.getInt("wunsch_id"));
			wunsch.setTitel(result.getString("titel"));
			wunsch.setBeschreibung(result.getString("beschreibung"));
			wunsch.setLink(result.getString("link"));
			wunsch.setPrioritaet(result.getInt("prioritaet"));
			wuensche.add(wunsch);
		}

		// close resources
		result.close();
		pstmt.close();
		connect.close();

		return wuensche;

	}
	
}

