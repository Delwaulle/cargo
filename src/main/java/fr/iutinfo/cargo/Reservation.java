package fr.iutinfo.cargo;

public class Reservation {
	private String idUser;
	private Integer idTrajet;
	private Integer accepte = 0;

	public Reservation(String idUser, Integer idTrajet, Integer accepte) {
		this.idUser = idUser;
		this.idTrajet = idTrajet;
		this.accepte = accepte;
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public Integer getIdTrajet() {
		return idTrajet;
	}

	public void setIdTrajet(Integer idTrajet) {
		this.idTrajet = idTrajet;
	}

	public String getAccepte() {
		if (accepte == 0) {
			return "En attente";
		} else if (accepte < 0) {
			return "Refusé";
		}

		else {
			return "Accepté";
		}
	}

	public void setAccepte(Integer accepte) {
		this.accepte = accepte;
	}
}
