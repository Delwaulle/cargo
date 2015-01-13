package fr.iutinfo.cargo;

public class Trajet {
	private int idtrajet;
	private String iduser;
	private String villeDepart;
	private String villeArrivee;
	private String dateTrajet;
	private int heureDepart;
	private int heureArrivee;
	private double prix;

	public Trajet() {

	}

	public Trajet(int idtrajet, String iduser, String villeDepart,
			String villeArrivee, String dateTrajet, int heureDepart,
			int heureArrivee, double prix) {
		this.idtrajet = idtrajet;
		this.iduser = iduser;
		this.villeDepart = villeDepart;
		this.villeArrivee = villeArrivee;
		this.dateTrajet = dateTrajet;
		this.heureDepart = heureDepart;
		this.heureArrivee = heureArrivee;
		this.prix = prix;
	}

	public int getIdtrajet() {
		return idtrajet;
	}

	public void setIdtrajet(int idtrajet) {
		this.idtrajet = idtrajet;
	}

	public String getIduser() {
		return iduser;
	}

	public void setIduser(String iduser) {
		this.iduser = iduser;
	}

	public String getVilleDepart() {
		return villeDepart;
	}

	public void setVilleDepart(String villeDepart) {
		this.villeDepart = villeDepart;
	}

	public String getVilleArrivee() {
		return villeArrivee;
	}

	public void setVilleArrivee(String villeArrivee) {
		this.villeArrivee = villeArrivee;
	}

	public String getDateTrajet() {
		return dateTrajet;
	}

	public void setDateTrajet(String dateTrajet) {
		this.dateTrajet = dateTrajet;
	}

	public int getHeureDepart() {
		return heureDepart;
	}

	public void setHeureDepart(int heureDepart) {
		this.heureDepart = heureDepart;
	}

	public int getHeureArrivee() {
		return heureArrivee;
	}

	public void setHeureArrivee(int heureArrivee) {
		this.heureArrivee = heureArrivee;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

}
