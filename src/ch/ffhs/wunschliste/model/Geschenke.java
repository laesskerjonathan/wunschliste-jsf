package ch.ffhs.wunschliste.model;

public class Geschenke {

	private Integer geschenk_id;
	private String titel;
	private Integer person;
	private String beschreibung;
	private Boolean erledigt;

	public Geschenke() {
	}

	public Geschenke(Integer geschenk_id, String titel, Integer person, String beschreibung,
			Boolean erledigt) {
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

	public Integer getPerson() {
		return person;
	}

	public void setPerson(Integer person) {
		this.person = person;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	public Boolean getErledigt() {
		return erledigt;
	}

	public void setErledigt(Boolean erledigt) {
		this.erledigt = erledigt;
	}
}
