package fr.iutinfo.cargo;

import java.util.List;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

@SuppressWarnings("finally")
public class OutilBDD {
	private Connection con;
	private Statement stmt;
	private boolean booleen;

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
						rs.getDouble(9));
				liste.add(t);

			}
			this.close();
		} catch (SQLException e) {
			e.printStackTrace();
			this.close();
		}
		return liste;
	}

	private void close() {
		try {
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public ArrayList<Trajet> recupererListeTrajets(Integer idTrajet, String iduser,
			String villeDepart, String villeArrivee, String dateTrajet,
			Integer heureDepart, Integer heureArrivee, Integer nbPlace,
			Double prix) {
		ArrayList<Trajet> liste = new ArrayList<Trajet>();
		ResultSet rs;
		String where = " ";
		if (idTrajet != null && !idTrajet.equals("")) {
			where += "idtrajet like '" + idTrajet + "%' and ";
		}
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
						rs.getDouble(9));
				liste.add(t);

			}
			this.close();
		} catch (SQLException e) {
			e.printStackTrace();
			this.close();
		}
		return liste;

	}

	public Trajet recupererTrajetAt(Integer idtrajet) {
		ResultSet rs;
		Trajet trajet = null;
		try {
			this.connect();
			rs = stmt.executeQuery("select * from trajet where idtrajet ='" + idtrajet + "';");
			trajet = new Trajet(rs.getInt(1), rs.getString(2), rs.getString(3),
					rs.getString(4), rs.getString(5), rs.getInt(6),
					rs.getInt(7), rs.getInt(8), rs.getDouble(9));
			this.close();
		} catch (SQLException e) {
			e.printStackTrace();
			this.close();
		} finally {
			return trajet;
		}
	}

	public boolean reserverTrajet(String iduser, Integer idtrajet) {
		booleen = true;
		try {
			this.connect();
			String requete = "insert into relation values ('" + iduser + "',"
					+ idtrajet + ",0)";
			System.out.println(requete);
			stmt.executeUpdate(requete);
			JOptionPane.showMessageDialog(null,
					"Votre réservation a été soumise\n au conducteur",
					"Succès", JOptionPane.INFORMATION_MESSAGE);
			this.close();
		} catch (SQLException e) {
			booleen = false;
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Echec de la réservation",
					"Attention", JOptionPane.ERROR_MESSAGE);
			this.close();

		} finally {
			return booleen;
		}
	}

	public ArrayList<Reservation> recupererReservations(String iduser) {
		ArrayList<Reservation> listeResas = new ArrayList<Reservation>();
		ResultSet rs;
		try {
			this.connect();
			rs = stmt.executeQuery("select * from relation where iduser = '"
					+ iduser + "';");
			while (rs.next()) {
				listeResas.add(new Reservation(rs.getString(1), rs.getInt(2),
						rs.getInt(3)));
			}
			this.close();
		} catch (SQLException e) {
			e.printStackTrace();
			this.close();
		}
		return listeResas;
	}

	public ArrayList<Reservation> recupererReservations(String iduser,
			Integer accepte) {
		ArrayList<Reservation> liste = new ArrayList<Reservation>();
		ResultSet rs;
		try {
			this.connect();
			rs = stmt.executeQuery("select * from relation where iduser ='"
					+ iduser + "' and accepte = " + accepte + ";");
			while (rs.next()) {
				liste.add(new Reservation(rs.getString(1), rs.getInt(2), rs
						.getInt(3)));
			}
			this.close();
		} catch (SQLException e) {
			e.printStackTrace();
			this.close();
		}
		return liste;
	}

	public boolean ajouterTrajet(String iduser, String villeDepart,
			String villeArrivee, String dateTrajet, Integer heureDepart,
			Integer heureArrivee, Integer nbPlace, Double prix, String voiture) {
		booleen = true;
		try {
			this.connect();
			String requete = "insert into trajet (iduser, villedepart, villearrivee, datetrajet, hdep, harr, nbplace, prix) values ('"
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
					+ heureArrivee + "," + nbPlace + "," + prix + ")";
			System.out.println(requete);
			stmt.executeUpdate(requete);
			JOptionPane.showMessageDialog(null, "Le trajet a été posté",
					"Succès", JOptionPane.INFORMATION_MESSAGE);
			this.close();
		} catch (SQLException e) {
			booleen = false;
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Echec de l'ajout du trajet",
					"Attention", JOptionPane.ERROR_MESSAGE);
			this.close();
		} finally {
			return booleen;
		}
	}

	public boolean supprimerTrajet(Trajet t) {
		booleen = true;
		try {
			this.connect();
			stmt.executeUpdate("DELETE from trajet where idtrajet="
					+ t.getIdtrajet() + ";");
			this.close();
		} catch (Exception e) {
			booleen = false;
			e.printStackTrace();
			this.close();
		} finally {
			return booleen;
		}

	}

	public boolean ajouterUtilisateur(String iduser, String mdp) {
		booleen = true;
		try {
			this.connect();
			String requete = "insert into cargouser(iduser, mdp) values('"
					+ iduser + "','" + mdp + "')s";
			System.out.println(requete);
			stmt.executeUpdate(requete);
			this.close();
		} catch (SQLException e) {
			booleen = false;
			e.printStackTrace();
			this.close();
		} finally {
			return booleen;
		}
	}

	public boolean ajouterUtilisateur(Utilisateur u, String mdp) {
		booleen = true;
		try {
			this.connect();
			String requete = " insert into cargouser values('" + u.getIduser()
					+ "','" + mdp + "','" + u.getNom() + "','" + u.getPrenom()
					+ "','" + u.getNumtel() + "','" + u.getMail() + "')";
			System.out.println(requete);
			stmt.executeUpdate(requete);
			this.close();
		} catch (SQLException e) {
			booleen = false;
			e.printStackTrace();
			this.close();
		} finally {
			return booleen;
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
					.executeQuery("select iduser,nom,prenom,numtel,mail from cargouser where iduser = '"
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

	public boolean updateProfil(String nom, String prenom, String numtel,
			String mail) {
		booleen = true;
		try {
			this.connect();

			boolean argumentNonNUll = false;
			String requete = "update Utilisateur set ";
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
			System.out.println(requete);
			stmt.executeUpdate(requete);
			this.close();
		} catch (SQLException e) {
			booleen = false;
			e.printStackTrace();
			this.close();
		} finally {
			return booleen;
		}

	}

	/*---------------------------------------------------------------------------------------*/
	public ArrayList<Avis> recupererAvis(String conducteur) {
		ArrayList<Avis> liste = new ArrayList<Avis>();
		ResultSet rs;
		try {
			this.connect();
			rs = stmt.executeQuery("select * from avis where conducteur ='"
					+ conducteur + "' ;");
			while (rs.next()) {
				Avis avis = new Avis(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getInt(5));
				liste.add(avis);
			}
			this.close();
		} catch (SQLException e) {
			e.printStackTrace();
			this.close();
		}
		return liste;
	}

	public boolean ajouterAvis(String conducteur, String passager, String avis,
			Integer note) {
		booleen = true;
		try {
			this.connect();
			String requete = "insert into avis (conducteur,passager,avis,note) values ('"
					+ conducteur
					+ "','"
					+ passager
					+ "','"
					+ avis
					+ "',"
					+ note + ")";
			System.out.println(requete);
			stmt.executeUpdate(requete);
			this.close();
		} catch (SQLException e) {
			booleen = false;
			e.printStackTrace();
			this.close();
		} finally {
			return booleen;
		}
	}

	public ArrayList<Notifications> recupererNotifications(String destinataire) {
		ArrayList<Notifications> liste = new ArrayList<Notifications>();
		ResultSet rs;
		try {
			this.connect();
			rs = stmt
					.executeQuery("select * from notification where destinataire ='"
							+ destinataire + "' ;");
			while (rs.next()) {
				Notifications notif = new Notifications(rs.getInt(1),
						rs.getString(2), rs.getString(3), rs.getString(4));
				liste.add(notif);
			}
			this.close();
		} catch (SQLException e) {
			e.printStackTrace();
			this.close();
		}
		return liste;
	}

	public boolean envoieNotification(String expediteur, String destinataire,
			String message) {
		booleen = true;
		try {
			this.connect();
			String requete = "insert into notification (expediteur,destinataire,message) values ('"
					+ expediteur
					+ "','"
					+ destinataire
					+ "','"
					+ message
					+ "')";
			System.out.println(requete);
			stmt.executeUpdate(requete);
			this.close();
		} catch (SQLException e) {
			booleen = false;
			e.printStackTrace();
			this.close();
		} finally {
			return booleen;
		}
	}
	
	

	public void creerTables() {
		try {
				stmt.executeUpdate("CREATE DATABASE database.db");
			this.connect();
				stmt.executeUpdate("CREATE TABLE IF NOT EXISTS relation(iduser varchar(20),idtrajet INTEGER, accepte Integer, foreign key (iduser) references cargouser(iduser),foreign key (idtrajet) references trajet(idtrajet),PRIMARY KEY(idtrajet,iduser))");
				stmt.executeUpdate("CREATE TABLE IF NOT EXISTS cargouser(iduser varchar(20) primary key, mdp text, nom text, prenom text, numtel text, mail text);");
				stmt.executeUpdate("CREATE TABLE IF NOT EXISTS trajet(idtrajet INTEGER PRIMARY KEY AUTOINCREMENT, iduser varchar(20), villedepart text, villearrivee text, datetrajet date, hdep int, harr int, nbplace int, prix float,foreign key (iduser) references cargouser(iduser));");
				stmt.executeUpdate("CREATE TABLE IF NOT EXISTS avis(idavis INTEGER PRIMARY KEY AUTOINCREMENT,conducteur varchar(20),passager varchar(20),avis text,note integer,foreign key (conducteur) references cargouser(iduser),foreign key (passager) references cargouser(iduser));");
				stmt.executeUpdate("CREATE TABLE IF NOT EXISTS notification(idnotif INTEGER PRIMARY KEY AUTOINCREMENT,expediteur text, destinataire text, message text,foreign key (expediteur) references cargouser(iduser),foreign key (destinataire) references cargouser(iduser));");
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
			stmt.executeUpdate("DROP TABLE avis;");
			stmt.executeUpdate("DROP TABLE notification;");
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
