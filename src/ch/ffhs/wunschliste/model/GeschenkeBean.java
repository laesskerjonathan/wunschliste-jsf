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
	
	private int hiddenIndex;

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
	
	public String deleteGeschenk() throws ClassNotFoundException, SQLException {
		System.out.println("hallo");
		System.out.println(hiddenIndex);
		List<Geschenke> geschenke = null;
		geschenke = getGeschenke();
		Geschenke selectedGeschenk = geschenke.get(hiddenIndex);
		System.out.println(selectedGeschenk.getGeschenk_id());
		delete(selectedGeschenk.getGeschenk_id());
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
    public void delete(int id) {
        String sql = "DELETE FROM geschenke WHERE geschenk_id = ?";
 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
 
            // set the corresponding param
            pstmt.setInt(1, id);
            // execute the delete statement
            pstmt.executeUpdate();
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
 
    /**
     * @param args the command line arguments
     */
	
	
	public int getHiddenIndex() {
		return hiddenIndex;
	}

	public void setHiddenIndex(int hiddenIndex) {
		this.hiddenIndex = hiddenIndex;
	}
	
	
	
	
}
