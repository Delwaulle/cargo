package fr.iutinfo.cargo;

public class Utilisateur {
	private String iduser;
	private String nom;
	private String prenom;
	private String numtel;
	private String mail;
	
	public Utilisateur(String iduser, String nom, String prenom, String numtel, String mail){
		this.iduser=iduser;
		this.nom=nom;
		this.prenom=prenom;
		this.numtel=numtel;
		this.mail=mail;
				
	}

	public String getIduser() {
		return iduser;
	}

	public void setIduser(String iduser) {
		this.iduser = iduser;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNumtel() {
		return numtel;
	}

	public void setNumtel(String numtel) {
		this.numtel = numtel;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
	
}
