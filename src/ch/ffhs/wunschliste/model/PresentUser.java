package ch.ffhs.wunschliste.model;

public class PresentUser {
	private Integer freund_id;
	private String nutzername;
	private String vorname;
	private String nachname;
	private String email;
	
	public PresentUser() {
		
	}

	public Integer getFreund_id() {
		return freund_id;
	}

	public void setFreund_id(Integer freund_id) {
		this.freund_id = freund_id;
	}

	public String getNutzername() {
		return nutzername;
	}

	public void setNutzername(String nutzername) {
		this.nutzername = nutzername;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
