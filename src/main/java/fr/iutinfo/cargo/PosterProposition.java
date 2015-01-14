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

@WebServlet("/servlet/PosterProposition")
public class PosterProposition extends HttpServlet {

	public void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		HttpSession s = req.getSession(true);
		String login = (String) s.getAttribute("Username");
		/*
		 * if(login==null){ res.sendRedirect("../login.html"); }
		 */
		login = "loic";
		String villeD = req.getParameter("villeD");
		String villeA = req.getParameter("villeA");
		String date = req.getParameter("date");
		String heureA = req.getParameter("villeD");

		int nbPlaces;
		double prix;
		int heureD;
		try {
			nbPlaces = Integer.parseInt(req.getParameter("nbPlaces"));
			prix = Double.parseDouble(req.getParameter("prix"));
			heureD = Integer.parseInt(req.getParameter("heureD"));
		} catch (NumberFormatException e) {
			nbPlaces = 0;
			prix = 0.0;
			heureD = 0;
		}

		OutilBDD obdd = new OutilBDD();
		out.println("VILLE DEPART : " + villeD);
		obdd.ajouterTrajet(login, villeD, villeA, date, heureD, null, nbPlaces,
				prix);
		ArrayList<Trajet> liste = obdd.recupererListeTrajets();
		for (int i = 0; i < liste.size(); i++) {
			out.println(liste.get(i).getVilleDepart());
			out.println(liste.get(i).getVilleArrivee());
			out.println(liste.get(i).getDateTrajet());
		}
	}
}
