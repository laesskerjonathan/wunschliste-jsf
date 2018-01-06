package ch.ffhs.wunschliste.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "user")
@RequestScoped
public class User {

	private String user_name;
	private String user_prename;
	private String user_lastname;
	private String user_email;	
	private String user_password;
	private String user_password_confirm;
	
	public User() {
		
	}
    
	

	public String getUser_name() {
		return user_name;
	}



	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}



	public String getUser_prename() {
		return user_prename;
	}



	public void setUser_prename(String user_prename) {
		this.user_prename = user_prename;
	}



	public String getUser_lastname() {
		return user_lastname;
	}



	public void setUser_lastname(String user_lastname) {
		this.user_lastname = user_lastname;
	}



	public String getUser_email() {
		return user_email;
	}



	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}



	public String getUser_password() {
		return user_password;
	}



	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}


	public String getUser_password_confirm() {
		return user_password_confirm;
	}



	public void setUser_password_confirm(String user_password_confirm) {
		this.user_password_confirm = user_password_confirm;
	}



	public String add() {
		System.out.println("Führe Add aus");
		boolean valide_daten = true;
		if (user_email == null || user_email.equals("")) {
			System.out.println("1 ungültig");
			valide_daten = false;
			FacesContext facesContext = FacesContext.getCurrentInstance();
			FacesMessage facesMessage = new FacesMessage("Geben sie eine gültige E-Mail-Adresse an");
			facesContext.addMessage(null, facesMessage);
			facesContext.addMessage("registerForm:registrierung_email", facesMessage);
		}
		if (user_name == null || user_name.equals("")) {
			System.out.println("2 ungültig");
			valide_daten = false;
			FacesContext facesContext = FacesContext.getCurrentInstance();
			FacesMessage facesMessage = new FacesMessage("Geben sie einen Nutzernamen an");
			facesContext.addMessage(null, facesMessage);
			facesContext.addMessage("registerForm:registrierung_nutzername", facesMessage);
		}
		if (user_prename == null || user_prename.equals("")) {
			System.out.println("3 ungültig");
			valide_daten = false;
			FacesContext facesContext = FacesContext.getCurrentInstance();
			FacesMessage facesMessage = new FacesMessage("Geben sie einen Vornamen an");
			facesContext.addMessage(null, facesMessage);
			facesContext.addMessage("registerForm:registrierung_vorname", facesMessage);
		}
		if (user_lastname == null || user_lastname.equals("")) {
			System.out.println("4 ungültig");
			valide_daten = false;
			FacesContext facesContext = FacesContext.getCurrentInstance();
			FacesMessage facesMessage = new FacesMessage("Geben sie einen Nachnamen an");
			facesContext.addMessage(null, facesMessage);
			facesContext.addMessage("registerForm:registrierung_nachname", facesMessage);
		}
		if (user_password == null || user_password.equals("")) {
			System.out.println("5 ungültig");
			valide_daten = false;
			FacesContext facesContext = FacesContext.getCurrentInstance();
			FacesMessage facesMessage = new FacesMessage("Geben sie ein Passwort an");
			facesContext.addMessage(null, facesMessage);
			facesContext.addMessage("registerForm:registrierung_passwort", facesMessage);
		}
		if (!user_password.equals(user_password_confirm)) {
			System.out.println("6 ungültig");
			System.out.println("user_password:" + user_password + "|user_password_confirm:" + user_password_confirm + "|");
			valide_daten = false;
			FacesContext facesContext = FacesContext.getCurrentInstance();
			FacesMessage facesMessage = new FacesMessage("Die Passwörter Stimmen nicht überein");
			facesContext.addMessage(null, facesMessage);
			facesContext.addMessage("registerForm:registrierung_passwort_confirm", facesMessage);
		}
        if (valide_daten) {
        	System.out.println("Daten waren Valide");
            PreparedStatement ps = null;
            Connection con = null;
            try {
            	con = DataConnect.getConnection();
                if (con != null) {
                	System.out.println("Connection war nicht null");
                    String sql = "INSERT INTO Users(user_name, user_prename, user_lastname, user_email, user_password) VALUES(?,?,?,?,?)";
                    System.out.println(sql);
                    ps = con.prepareStatement(sql);
                    System.out.println("Werte:  1)" + user_name + "2)" + user_prename + "3)" + user_lastname + "4)" + user_email + "5)" + user_password);
                    ps.setString(1, user_name);
                    ps.setString(2, user_prename);
                    ps.setString(3, user_lastname);
                    ps.setString(4, user_email);
                    ps.setString(5, user_password);
                    ps.executeUpdate();
                    System.out.println(ps.toString());
                    System.out.println("Data Added Successfully");
                }
          
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                try {
                    con.close();
                    ps.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
      return "index";
    }
}
