package fr.iutinfo.cargo;

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
						rs.getInt(6), rs.getInt(7), rs.getInt(8));
				liste.add(t);
				this.close();
			}
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
			int heureDepart, int heureArrivee, double prix) {

		return null;
	}

	public void ajouterTrajet(Trajet t) {

	}

	public void supprimerTrajet(Trajet t) {

	}

	public void creerTables() {
		try {
			this.connect();
			stmt.executeUpdate("CREATE TABLE cargouser(iduser varchar(20) primary key, mdp text);");
			stmt.executeUpdate("CREATE TABLE trajet(idtrajet serial, iduser varchar(20), villedepart text, villearrivee text, datetrajet date, hdep int, harr int, nbplace int, prix float,foreign key (iduser) references cargouser(iduser));");
			this.close();
		} catch (SQLException e) {
			e.printStackTrace();
			this.close();
		}
	}
}
