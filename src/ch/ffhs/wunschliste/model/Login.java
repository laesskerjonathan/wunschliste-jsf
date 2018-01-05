package ch.ffhs.wunschliste.model;

//Beispiel von: https://www.journaldev.com/7252/jsf-authentication-login-logout-database-example

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.synth.SynthSeparatorUI;


@ManagedBean
@SessionScoped
public class Login implements Serializable {

	private static final long serialVersionUID = 1094801825228386363L;
	
	private String password;
	private String msg;
	private String user;	

	//validate login
	public String validateUsernamePassword() {
		System.out.println("Validiere Passwort");
		//System.out.println("user: "+ user + " | passwort : " + password);
		boolean valid = LoginDAO.validate(user, password);
		if (valid) {
			System.out.println("Passwort ist OK!");
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("username", user);
			return "dashboard";
		} else {
			System.out.println("Passwort NICHT ok!");
			FacesContext facesContext = FacesContext.getCurrentInstance();
			FacesMessage facesMessage = new FacesMessage("Nutzername und/oder Passwort falsch!");
			facesContext.addMessage(null, facesMessage);
			facesContext.addMessage("login-form:login_passwort", facesMessage);
		
			return "index";
		}
	}
	
	/*
	//validate login
		public String validateUsernamePassword() {
			System.out.println("Validiere Passwort");
			boolean valid = LoginDAO.validate(user, password);
			if (valid) {
				//LoginDAO.setPresentUser(user);
				System.out.println("Passwort ist OK!");
				HttpSession session = SessionUtils.getSession();
				session.setAttribute("username", user);
				return "dashboard";
			} else {
				System.out.println("Passwort NICHT ok!");
				FacesContext facesContext = FacesContext.getCurrentInstance();
				FacesMessage facesMessage = new FacesMessage("Nutzername und/oder Passwort falsch!");
				facesContext.addMessage(null, facesMessage);
				facesContext.addMessage("login-form:login_passwort", facesMessage);
			
				return "index";
			}
		}*/

	//logout event, invalidate session
	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "index";
	}
	
	
	public String getpassword() {
		return password;
	}

	public void setpassword(String password) {
		this.password = password;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
}
