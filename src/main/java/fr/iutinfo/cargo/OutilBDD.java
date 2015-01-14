package fr.iutinfo.cargo;

import java.util.List;
import java.sql.*;
import java.util.ArrayList;

public class OutilBDD {
	private Connection con;
	private Statement stmt;

	public OutilBDD() {

	}

	private void connect() {
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:database.db");
			stmt = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Trajet> recupererListeTrajets() {
		ArrayList<Trajet> liste = new ArrayList<Trajet>();
		ResultSet rs;
		try {
			this.connect();
			rs = stmt.executeQuery("select * from trajet;");
			while (rs.next()) {
				Trajet t = new Trajet(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getInt(6), rs.getInt(7), rs.getInt(8),
						rs.getDouble(9), rs.getString(10));
				liste.add(t);

			}
			this.close();
		} catch (SQLException e) {
			e.printStackTrace();
			this.close();
		}
		return (ArrayList<Trajet>) liste;
	}

	private void close() {
		try {
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public ArrayList<Trajet> recupererListeTrajets(String iduser,
			String villeDepart, String villeArrivee, String dateTrajet,
			Integer heureDepart, Integer heureArrivee, Integer nbPlace,
			Double prix) {
		ArrayList<Trajet> liste = new ArrayList<Trajet>();
		ResultSet rs;
		String where = " ";
		if (iduser != null && !iduser.equals("")) {
			where += "iduser like '" + iduser + "%' and ";
		}
		if (villeDepart != null && !villeDepart.equals("")) {
			where += "villedepart like '" + villeDepart + "%' and ";
		}
		if (villeArrivee != null && !villeArrivee.equals("")) {
			where += "villearrivee like '" + villeArrivee + "%' and ";
		}
		if (dateTrajet != null && !dateTrajet.equals("")) {
			where += "datetrajet like '" + dateTrajet + "%' and ";
		}
		if (heureDepart != null) {
			where += "hdep = " + heureDepart + " and ";
		}
		if (heureArrivee != null) {
			where += "harr = " + heureArrivee + " and ";
		}
		if (nbPlace != null) {
			where += "nbplace = " + nbPlace + " and ";
		}
		if (prix != null) {
			where += "prix = " + prix + " and ";
		}
		try {
			this.connect();
			String requete = "select * from trajet where " + where;
			requete = requete.substring(0, requete.length() - 5);
			System.out.println(requete);
			rs = stmt.executeQuery(requete);
			while (rs.next()) {
				Trajet t = new Trajet(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getInt(6), rs.getInt(7), rs.getInt(8),
						rs.getDouble(9), rs.getString(10));
				liste.add(t);

			}
			this.close();
		} catch (SQLException e) {
			e.printStackTrace();
			this.close();
		}
		return (ArrayList<Trajet>) liste;

	}

	public void ajouterTrajet(String iduser, String villeDepart,
			String villeArrivee, String dateTrajet, Integer heureDepart,
			Integer heureArrivee, Integer nbPlace, Double prix, String voiture) {
		try {
			this.connect();
			String requete = "insert into trajet (iduser, villedepart, villearrivee, datetrajet, hdep, harr, nbplace, prix, voiture) values ('"
					+ iduser
					+ "','"
					+ villeDepart
					+ "','"
					+ villeArrivee
					+ "','"
					+ dateTrajet
					+ "',"
					+ heureDepart
					+ ","
					+ heureArrivee
					+ ","
					+ nbPlace
					+ ","
					+ prix
					+ ",'"
					+ voiture
					+ "')";
			System.out.println(requete);
			stmt.executeUpdate(requete);
			this.close();
		} catch (SQLException e) {
			e.printStackTrace();
			this.close();
		}
	}

	public void supprimerTrajet(Trajet t) {
		try {
			this.connect();
			stmt.executeUpdate("DELETE from trajet where idtrajet="
					+ t.getIdtrajet() + ";");
			this.close();
		} catch (Exception e) {

			e.printStackTrace();
			this.close();
		}
	}

	public void ajouterUtilisateur(String iduser, String mdp) {
		try {
			this.connect();
			String requete = "insert into cargouser(iduser, mdp) values('"
					+ iduser + "','" + mdp + "')s";
			System.out.println(requete);
			stmt.executeUpdate(requete);
			this.close();
		} catch (SQLException e) {
			e.printStackTrace();
			this.close();
		}
	}

	public void ajouterUtilisateur(Utilisateur u, String mdp) {
		try {
			this.connect();
			String requete = " insert into cargouser values('" + u.getIduser()
					+ "','" + mdp + "','" + u.getNom() + "','" + u.getPrenom()
					+ "','" + u.getNumtel() + "','" + u.getMail() + "')";
			System.out.println(requete);
			stmt.executeUpdate(requete);
			this.close();
		} catch (SQLException e) {
			e.printStackTrace();
			this.close();
		}
	}

	/*---------------------------------------------------------------------------------------*/

	// ATTENTION, LES MDPs NE SONT PAS RECUPERES !!
	public List<String> recupererListeNomUtilisateur() {
		List<String> liste = new ArrayList<String>();
		ResultSet rs;
		try {
			this.connect();
			rs = stmt.executeQuery("select iduser from cargouser;");
			while (rs.next()) {
				liste.add(rs.getString(1));

			}
			this.close();
		} catch (SQLException e) {
			e.printStackTrace();
			this.close();
		}
		return liste;
	}

	// ATTENTION, LES MDPs NE SONT PAS RECUPERES !!
	public ArrayList<Utilisateur> recupererListeUtilisateur() {
		ArrayList<Utilisateur> liste = new ArrayList<Utilisateur>();
		ResultSet rs;
		try {
			this.connect();
			rs = stmt
					.executeQuery("select iduser,nom,prenom,numtel,mail from cargouser;");
			while (rs.next()) {
				Utilisateur u = new Utilisateur(rs.getString(1),
						rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5));
				liste.add(u);

			}
			this.close();
		} catch (SQLException e) {
			e.printStackTrace();
			this.close();
		}
		return (ArrayList<Utilisateur>) liste;
	}

	public Utilisateur recupererUtilisateur(String idUser) {
		ResultSet rs;
		Utilisateur u = null;
		try {
			this.connect();
			rs = stmt
					.executeQuery("select iduser,nom,prenom,numtel,mail from cargouser where = iduser = '"
							+ idUser + "';");
			u = new Utilisateur(rs.getString(1), rs.getString(2),
					rs.getString(3), rs.getString(4), rs.getString(5));
			this.close();
		} catch (SQLException e) {
			e.printStackTrace();
			this.close();
		}
		return u;
	}

	public void updateProfil(String iduser, String nom, String prenom,
			String numtel, String mail) {
		boolean argumentNonNUll = false;
		String requete = "set ";
		if (nom != null) {
			requete += "nom = '" + nom + "'";
			argumentNonNUll = true;
		}
		if (argumentNonNUll) {
			requete += ",";
			argumentNonNUll = false;
		}
		if (prenom != null) {
			requete += "prenom = '" + prenom + "'";
			argumentNonNUll = true;
		}
		if (argumentNonNUll) {
			requete += ",";
			argumentNonNUll = false;
		}
		if (numtel != null) {
			requete += "numtel = '" + numtel + "'";
			argumentNonNUll = true;
		}
		if (argumentNonNUll) {
			requete += ",";
			argumentNonNUll = false;
		}
		if (mail != null) {
			requete += "mail = '" + mail + "'";
			argumentNonNUll = true;
		}
		if (argumentNonNUll) {
			requete += ",";
			argumentNonNUll = false;
		}

	}

	/*---------------------------------------------------------------------------------------*/

	public void creerTables() {
		try {
			this.connect();

			stmt.executeUpdate("CREATE TABLE relation(iduser varchar(20),idtrajet INTEGER,foreign key (iduser) references cargouser(iduser),foreign key (idtrajet) references trajet(idtrajet),PRIMARY KEY(idtrajet,iduser))");
			stmt.executeUpdate("CREATE TABLE cargouser(iduser varchar(20) primary key, mdp text, nom text, prenom text, numtel text, mail text);");
			stmt.executeUpdate("CREATE TABLE trajet(idtrajet INTEGER PRIMARY KEY AUTOINCREMENT, iduser varchar(20), villedepart text, villearrivee text, datetrajet date, hdep int, harr int, nbplace int, prix float,voiture text,foreign key (iduser) references cargouser(iduser));");

			this.close();
		} catch (SQLException e) {
			e.printStackTrace();
			this.close();
		}
	}

	public void supprimerToutesLesTables() {
		try {
			this.connect();
			stmt.executeUpdate("DROP TABLE relation");
			stmt.executeUpdate("DROP TABLE cargouser;");
			stmt.executeUpdate("DROP TABLE trajet;");
			this.close();
		} catch (SQLException e) {
			e.printStackTrace();
			this.close();
		}
	}

	public void remettreAZeroLaBDD() {
		supprimerToutesLesTables();
		creerTables();
		System.out.println("Database reinitialisee");
	}

	public static void main(String[] args) {
		new OutilBDD().remettreAZeroLaBDD();

	}

}
