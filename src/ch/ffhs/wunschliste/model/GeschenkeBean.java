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
public class GeschenkeBean implements Serializable {

	private static final long serialVersionUID = 6081417964063918994L;

	public List<Geschenke> getGeschenke() throws ClassNotFoundException, SQLException {

		Connection connect = null;

		String url = "jdbc:mysql://localhost:3306/wunschliste";

		String username = "root";
		String password = "";

		try {

			Class.forName("com.mysql.jdbc.Driver");

			connect = DriverManager.getConnection(url, username, password);
			// System.out.println("Connection established"+connect);

		} catch (SQLException ex) {
			System.out.println("in exec");
			System.out.println(ex.getMessage());
		}

		List<Geschenke> geschenke = new ArrayList<>();
		PreparedStatement pstmt = connect
				.prepareStatement("select geschenk_id, titel, person, beschreibung, erledigt from geschenke");
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			Geschenke geschenk = new Geschenke();
			Integer id = rs.getInt("geschenk_id");
			System.out.print(id + " ");
			geschenk.setGeschenk_id(id);
			String titel = rs.getString("titel");
			System.out.println(titel);
			geschenk.setTitel(titel);
			geschenk.setPerson(rs.getInt("person"));
			geschenk.setBeschreibung(rs.getString("beschreibung"));
			geschenk.setErledigt(rs.getBoolean("erledigt"));
			geschenke.add(geschenk);

		}

		// close resources
		rs.close();
		pstmt.close();
		connect.close();

		return geschenke;

	}
	
}
