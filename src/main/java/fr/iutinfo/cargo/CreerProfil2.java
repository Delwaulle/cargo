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

		HttpSession s = req.getSession(true);

		String login = req.getParameter("login");

		boolean valide = true;

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
				out.println("Login déjà utilisé.<br/>");
				s.setAttribute("erreurLogin", true);
				valide = false;
			} else {
				con.close();
			}
		} catch (Exception e) {
			out.println("Erreur : " + e);
		}

		String nom = req.getParameter("nom");

		String prenom = req.getParameter("prenom");

		String numTel = req.getParameter("numTel");
		boolean numValide = true;
		for (int i = 0; i < numTel.length(); i++) {
			if (numTel.charAt(i) < '0' || numTel.charAt(i) > '9') {
				valide = false;
				numValide = false;
			}
		}
		if (!numValide) {
			out.println("Numéro de téléphone invalide.</br>");
			s.setAttribute("erreurNum", true);
		}

		String mail = req.getParameter("mail");
		int at = 0;
		for (int i = 0; i < mail.length(); i++) {
			if (mail.charAt(i) == '@')
				at++;
		}
		if (at != 1) {
			out.println("Adresse mail invalide.</br>");
			s.setAttribute("erreurAdresse", true);
		}

		if (valide) {
			Utilisateur u = new Utilisateur(login, nom, prenom, numTel, mail);
			OutilBDD o = new OutilBDD();
			o.ajouterUtilisateur(u, req.getParameter("mdp"));
			res.sendRedirect("../login.html");
		} else {
			s.setAttribute("erreur", true);
			res.sendRedirect("CreerProfil");
		}
	}
}