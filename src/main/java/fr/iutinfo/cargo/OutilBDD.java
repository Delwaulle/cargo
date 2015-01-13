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
						rs.getInt(6), rs.getInt(7), rs.getInt(8),
						rs.getDouble(9));
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
			int heureDepart, int heureArrivee, int nbPlace, double prix) {
		ArrayList<Trajet> liste = new ArrayList<Trajet>();
		ResultSet rs;
		String where = " ";
		boolean dejaUneCondition = false;
		if (iduser != null) {
			where += "iduser like %" + iduser + " ";
			dejaUneCondition = true;
		}
		if (dejaUneCondition) {
			where += "and ";
		}
		if (villeDepart != null) {
			where += "villedepart like %" + villeDepart + " ";
			dejaUneCondition = true;
		}
		if (dejaUneCondition) {
			where += "and ";
		}
		if (villeArrivee != null) {
			where += "villearrivee like %" + villeArrivee + " ";
			dejaUneCondition = true;
		}
		if (dejaUneCondition) {
			where += "and ";
		}
		if (dateTrajet != null) {
			where += "datetrajet like %" + dateTrajet + " ";
			dejaUneCondition = true;
		}
		if (dejaUneCondition) {
			where += "and ";
		}
		if ((Integer) heureDepart != null) {
			where += "hdep = " + heureDepart + " ";
			dejaUneCondition = true;
		}
		if (dejaUneCondition) {
			where += "and ";
		}
		if ((Integer) heureArrivee != null) {
			where += "harr = " + heureArrivee + " ";
			dejaUneCondition = true;
		}
		if (dejaUneCondition) {
			where += "and ";
		}
		if ((Integer) nbPlace != null) {
			where += "nbplace = " + nbPlace + " ";
			dejaUneCondition = true;
		}
		if (dejaUneCondition) {
			where += "and ";
		}
		if ((Double) prix != null) {
			where += "prix = " + prix + " ";
			dejaUneCondition = true;
		}
		try {
			this.connect();
			rs = stmt.executeQuery("select * from trajet where "+where);
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
		return (ArrayList<Trajet>) liste;

	}

	public void ajouterTrajet(Trajet t) {
		try {
			this.connect();
			String requete = "insert into trajet (iduser, villedepart, villearrivee, datetrajet, hdep, harr, nbplace, prix) values ('" + t.getIduser() + "','" + t.getVilleDepart() + "','"
					+ t.getVilleArrivee() + "','" + t.getDateTrajet() + "',"
					+ t.getHeureDepart() + "," + t.getHeureArrivee() + ","
					+ t.getNbPlace() + "," + t.getPrix() + ")";
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
			stmt.executeUpdate("DELETE from trajet where idtrajet="+t.getIdtrajet()+";");
			this.close();
		} catch (Exception e) {
			
			e.printStackTrace();
			this.close();
		}		
	}

	public void creerTables() {
		try {
			this.connect();
			stmt.executeUpdate("CREATE TABLE cargouser(iduser varchar(20) primary key, mdp text);");
			stmt.executeUpdate("CREATE TABLE trajet(idtrajet INTEGER PRIMARY KEY AUTOINCREMENT, iduser varchar(20), villedepart text, villearrivee text, datetrajet date, hdep int, harr int, nbplace int, prix float,foreign key (iduser) references cargouser(iduser));");
			this.close();
		} catch (SQLException e) {
			e.printStackTrace();
			this.close();
		}
	}
	
}
