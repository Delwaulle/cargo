package fr.iutinfo.cargo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OutilBDD {
	private Connection con;
	private Statement stmt;

	public OutilBDD() {	

	}
	
	private void connect(){
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(
					"jdbc:postgresql://psqlserv/n3p1", "clavelm", "moi");
			stmt = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Trajet> recupererListeTrajets() {
		ArrayList<Trajet> liste = new ArrayList<Trajet>();
		this.connect();
		ResultSet rs;
		try {
			rs = stmt.executeQuery("select * from trajet;");
			ResultSetMetaData metadata = rs.getMetaData();
			while (rs.next()) {
				for (int i = 1; i < metadata.getColumnCount() + 1; i++) {
					System.out.println(rs.getString(i));
					// importer la base
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		this.close();
		
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
}
