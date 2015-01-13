package fr.iutinfo.cargo;

public class Trajet {
	private Integer idtrajet;
	private String iduser;
	private String villeDepart;
	private String villeArrivee;
	private String dateTrajet;
	private Integer heureDepart;
	private Integer heureArrivee;
	private Double prix;
	private Integer nbPlace;

	public Trajet() {

	}

	public Trajet(Integer idtrajet, String iduser, String villeDepart,
			String villeArrivee, String dateTrajet, Integer heureDepart,
			Integer heureArrivee, Integer nbPlace, Double prix) {
		this.idtrajet = idtrajet;
		this.iduser = iduser;
		this.villeDepart = villeDepart;
		this.villeArrivee = villeArrivee;
		this.dateTrajet = dateTrajet;
		this.heureDepart = heureDepart;
		this.heureArrivee = heureArrivee;
		this.setNbPlace(nbPlace);
		this.prix = prix;
	}

	public Integer getIdtrajet() {
		return idtrajet;
	}

	public void setIdtrajet(Integer idtrajet) {
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

	public Integer getHeureDepart() {
		return heureDepart;
	}

	public void setHeureDepart(Integer heureDepart) {
		this.heureDepart = heureDepart;
	}

	public Integer getHeureArrivee() {
		return heureArrivee;
	}

	public void setHeureArrivee(Integer heureArrivee) {
		this.heureArrivee = heureArrivee;
	}

	public Double getPrix() {
		return prix;
	}

	public void setPrix(Double prix) {
		this.prix = prix;
	}

	public Integer getNbPlace() {
		return nbPlace;
	}

	public void setNbPlace(Integer nbPlace) {
		this.nbPlace = nbPlace;
	}

}
