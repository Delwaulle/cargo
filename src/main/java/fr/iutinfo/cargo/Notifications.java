package fr.iutinfo.cargo;

public class Notifications {
	private Integer idnotif;
	private String expediteur;
	private String destinataire;
	private String message;
	public Notifications(Integer idnotif, String expediteur,
			String destinataire, String message) {
		super();
		this.idnotif = idnotif;
		this.expediteur = expediteur;
		this.destinataire = destinataire;
		this.message = message;
	}
	public Integer getIdnotif() {
		return idnotif;
	}
	public void setIdnotif(Integer idnotif) {
		this.idnotif = idnotif;
	}
	public String getExpediteur() {
		return expediteur;
	}
	public void setExpediteur(String expediteur) {
		this.expediteur = expediteur;
	}
	public String getDestinataire() {
		return destinataire;
	}
	public void setDestinataire(String destinataire) {
		this.destinataire = destinataire;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
