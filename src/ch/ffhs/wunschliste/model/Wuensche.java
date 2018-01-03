package ch.ffhs.wunschliste.model;

public class Wuensche {
	private Integer wunsch_id;
	private String titel;
	private String beschreibung;
	private String link;
	private Integer prioritaet;

	public Wuensche() {
		
	}
	
	public Wuensche(Integer wunsch_id, String titel, String beschreibung, String link, Integer prioritaet) {
		this.wunsch_id = wunsch_id;
		this.titel = titel;
		this.beschreibung = beschreibung;
		this.link = link;
		this.prioritaet = prioritaet;
	}

	public Integer getWunsch_id() {
		return wunsch_id;
	}

	public void setWunsch_id(Integer wunsch_id) {
		this.wunsch_id = wunsch_id;
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

	public Integer getPrioritaet() {
		return prioritaet;
	}

	public void setPrioritaet(Integer prioritaet) {
		this.prioritaet = prioritaet;
	}
	
	
}
