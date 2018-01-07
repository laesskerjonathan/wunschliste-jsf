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
	
	private String searchString;
	private boolean showMyFriends = true;
	private int currentFriend;
	private boolean iHaveFriends;

	public boolean iHaveFriends() {
		boolean haveFriends = false;
		try {
			 haveFriends = !getFreunde().isEmpty();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return haveFriends;
		
	}
	
	public List<Freunde> getFreunde() throws ClassNotFoundException, SQLException {

		Connection connect = null;

		String url = "jdbc:mysql://localhost:3306/wunschliste";

		String username = "root";
		String password = "";
		System.out.println("Führe FreundeBean aus!");
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
				.prepareStatement("select freund_id, nutzername, vorname, nachname, email from freunde WHERE hinzugefuegt = 1");
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
	
	public String changeTableToResults() {
		showMyFriends = false;
		System.out.println(showMyFriends);
		return "asdf";
	}
	
	public String changeTableToMyFriends() {
		showMyFriends = true;
		System.out.println(showMyFriends);
		return "asdf";
	}
	
	public List<Freunde> searchForFriends() throws ClassNotFoundException, SQLException {

		System.out.println(searchString);
		Connection connect = null;

		String url = "jdbc:mysql://localhost:3306/wunschliste";

		String username = "root";
		String password = "";
		System.out.println("Führe FreundeBean aus!");
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
				.prepareStatement("select freund_id, nutzername, vorname, nachname, email from freunde " +
						"WHERE (nutzername LIKE '%" + searchString + "%' OR vorname LIKE '%" + searchString +
						"%' OR nachname LIKE '%" + searchString + "%' OR email like '%" + searchString + "%'" +
						") AND hinzugefuegt = 0");
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
		iHaveFriends();
		return freunde;


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

	public void loeschen() {
        String sql = "UPDATE freunde SET hinzugefuegt = 0 WHERE freund_id = ?";
 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
 
            // set the corresponding param
            pstmt.setInt(1, currentFriend);
            // execute the delete statement
            pstmt.executeUpdate();
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	public void hinzufuegen() {
        String sql = "UPDATE freunde SET hinzugefuegt = 1 WHERE freund_id = ?";
 
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
 
            // set the corresponding param
            pstmt.setInt(1, currentFriend);
            // execute the delete statement
            pstmt.executeUpdate();
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	public boolean isShowMyFriends() {
		return showMyFriends;
	}

	public void setShowMyFriends(boolean showMyFriends) {
		this.showMyFriends = showMyFriends;
	}

	public int getCurrentFriend() {
		return currentFriend;
	}

	public void setCurrentFriend(int currentFriend) {
		this.currentFriend = currentFriend;
	}

	public boolean isiHaveFriends() {
		return iHaveFriends;
	}

	public void setiHaveFriends(boolean iHaveFriends) {
		this.iHaveFriends = iHaveFriends;
	}
	
	
}
