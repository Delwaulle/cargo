package fr.iutinfo.cargo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ListeTrajet {
	private List<Trajet> liste = new ArrayList<Trajet>();
	private Connection con;
	private Statement stmt;

	public ListeTrajet() {
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(
					"jdbc:postgresql://psqlserv/n3p1", "clavelm", "moi");
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from trajet;");
			ResultSetMetaData metadata = rs.getMetaData();
			while (rs.next()) {
				for (int i = 1; i < metadata.getColumnCount() + 1; i++) {
					System.out.println(rs.getString(i));
					// Importer donnee table !
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public ArrayList<Trajet> RecupererListeTrajets() {

		return null;
	}

	public ArrayList<Trajet> RecupererListeTrajets(String iduser,
			String villeDepart, String villeArrivee, String dateTrajet,
			int heureDepart, int heureArrivee, double prix) {

		return null;
	}

	public void AjouterTrajet(Trajet t) {

	}
	
	public void SupprimerTrajet(Trajet t) {
		
	}
}
