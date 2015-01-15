package fr.iutinfo.cargo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

@WebServlet("/servlet/PosterProposition")
public class PosterProposition extends HttpServlet {

	public void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		HttpSession s = req.getSession(true);
		Utilisateur u = (Utilisateur) s.getAttribute("iduser");
		String login = u.getIduser();
		if(login==null){ res.sendRedirect("../servlet/Home"); }
		String villeD = req.getParameter("villeD");
		String villeA = req.getParameter("villeA");
		String date = req.getParameter("date");
		String heureA = req.getParameter("villeD");
		String voiture = req.getParameter("voiture");
		int nbPlaces;
		double prix;
		int heureD;
		try {
			nbPlaces = Integer.parseInt(req.getParameter("nbPlaces"));
			prix = Double.parseDouble(req.getParameter("prix"));
			heureD = Integer.parseInt(req.getParameter("heureD"));
		} catch (NumberFormatException e) {
			System.out.println("catch");
			nbPlaces = 0;
			prix = 0.0;
			heureD = 0;
		}

		OutilBDD obdd = new OutilBDD();
		obdd.ajouterTrajet(login, villeD, villeA, date, heureD, null, nbPlaces,
				prix,voiture);
		ArrayList<Trajet> liste = obdd.recupererListeTrajets();
		for (int i = 0; i < liste.size(); i++) {
			out.println(liste.get(i).getVilleDepart());
			out.println(liste.get(i).getVilleArrivee());
			out.println(liste.get(i).getDateTrajet());
		}
		res.sendRedirect("Home");
	}
}
