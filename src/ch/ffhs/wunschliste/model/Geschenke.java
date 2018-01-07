package ch.ffhs.wunschliste.model;

public class Geschenke {

	private Integer geschenk_id;
	private String titel;
	private String person;
	private String beschreibung;
	private String erledigt;

	public Geschenke() {
	}

	public Geschenke(Integer geschenk_id, String titel, String person, String beschreibung,
			String erledigt) {
		this.geschenk_id = geschenk_id;
		this.titel = titel;
		this.person = person;
		this.beschreibung = beschreibung;
		this.erledigt = erledigt;
	}

	public Integer getGeschenk_id() {
		return geschenk_id;
	}

	public void setGeschenk_id(Integer geschenk_id) {
		this.geschenk_id = geschenk_id;
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


	public void setPerson(String person) {
		this.person = person;
	}

	public void setErledigt(String erledigt) {
		this.erledigt = erledigt;
	}

	public String getPerson() {
		return person;
	}

	public String getErledigt() {
		return erledigt;
	}
}
