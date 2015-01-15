package fr.iutinfo.cargo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("/servlet/CreerProfil2")
public class CreerProfil2 extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");

		boolean valide = true;
		
		boolean idValide = true;
		String login = req.getParameter("identifiant");
		try {
			Class.forName("org.sqlite.JDBC");
			Connection con = DriverManager
					.getConnection("jdbc:sqlite:database.db");
			PreparedStatement ps = con
					.prepareStatement("SELECT * FROM cargouser WHERE iduser='"
							+ login + "';");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				con.close();
				idValide = false;
				valide = false;
			} else {
				con.close();
			}
		} catch (Exception e) {
			out.println("Erreur : " + e);
		}

		String nom = req.getParameter("nom");

		String prenom = req.getParameter("prenom");

		String numTel = req.getParameter("tel");
		boolean numValide = true;
		for (int i = 0; i < numTel.length(); i++) {
			if (numTel.charAt(i) < '0' || numTel.charAt(i) > '9') {
				valide = false;
				numValide = false;
			}
		}

		boolean adresseValide = true;
		String mail = req.getParameter("adresse");
		int at = 0;
		for (int i = 0; i < mail.length(); i++) {
			if (mail.charAt(i) == '@')
				at++;
		}
		if (at != 1) {
			adresseValide = false;
		}

		if (valide) {
			Utilisateur u = new Utilisateur(login, nom, prenom, numTel, mail);
			OutilBDD o = new OutilBDD();
			o.ajouterUtilisateur(u, req.getParameter("password"));
			res.sendRedirect("../login.html");
		} else {
			out.println("<center>");
			out.println("<h4>Veuillez corriger la (les) erreur(s) suivante(s) :</h4>");
			out.println("<table border=1 color=#FF0000>");
			if (!idValide)
				out.println("<tr><td>Login déjà utilisé.</td></tr>");
			if (!numValide)
				out.println("<tr><td>Numéro de téléphone invalide.</td></tr>");
			if (!adresseValide)
				out.println("<tr><td>Adresse mail invalide.</td></tr>");
			out.println("</table><br/>");
			out.println("<a href=\"../inscription.html\">Retour</a>");
			out.println("</center>");
		}
	}
}