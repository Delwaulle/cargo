package fr.iutinfo.cargo;

public class Avis {
	private Integer idavis;
	private String conducteur;
	private String passager;
	private String avis;
	private Integer note;

	public Avis(Integer idavis, String conducteur, String passager,
			String avis, Integer note) {
		super();
		this.idavis = idavis;
		this.conducteur = conducteur;
		this.passager = passager;
		this.avis = avis;
		this.note = note;
	}

	public Integer getIdavis() {
		return idavis;
	}

	public void setIdavis(Integer idavis) {
		this.idavis = idavis;
	}

	public String getConducteur() {
		return conducteur;
	}

	public void setConducteur(String conducteur) {
		this.conducteur = conducteur;
	}

	public String getPassager() {
		return passager;
	}

	public void setPassager(String passager) {
		this.passager = passager;
	}

	public String getAvis() {
		return avis;
	}

	public void setAvis(String avis) {
		this.avis = avis;
	}

	public Integer getNote() {
		return note;
	}

	public void setNote(Integer note) {
		this.note = note;
	}

}
