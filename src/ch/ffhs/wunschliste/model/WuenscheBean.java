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

	public String titel;
	public String beschreibung;
	public String link;
	public String prioritaet;

	private static final long serialVersionUID = 2L;
	
	public List<Wuensche> getWuensche() throws ClassNotFoundException, SQLException {

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

	public String createWunsch() throws ClassNotFoundException, SQLException {
		System.out.println("hallo");
		System.out.println(titel);
		System.out.println(beschreibung);
		System.out.println(link);
		System.out.println(prioritaet);
		//List<Wuensche> geschenke = null;
		//geschenke = getWuensche();
		//Geschenke selectedGeschenk = geschenke.get(hiddenIndex);
		//System.out.println(selectedGeschenk.getGeschenk_id());
		insert();
		return "self";
	}


    /**
     * Connect to the test.db database
     *
     * @return the Connection object
     */
    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:mysql://localhost:3306/wunschliste";
		String username = "root";
		String password = "";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(conn);
        return conn;
    }
 
    /**
     * Delete a warehouse specified by the id
     *
     * @param id
     */
    public void insert() {
        String sql = "INSERT INTO wuensche(titel, beschreibung, link, prioritaet) VALUES('" +
        		titel + "','" + beschreibung + "','" + link + "','" + prioritaet +  "');";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
 
            // execute the delete statement
            pstmt.executeUpdate();
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getPrioritaet() {
		return prioritaet;
	}

	public void setPrioritaet(String prioritaet) {
		this.prioritaet = prioritaet;
	}
 
}

