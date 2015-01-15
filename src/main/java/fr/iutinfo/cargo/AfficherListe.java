package fr.iutinfo.cargo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/servlet/AfficherListe")
public class AfficherListe extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		out.println("<html><head><meta charset=UTF-8>");
		out.println("<link rel=stylesheet type=text/css href=../index.css>");
		out.println("<title>Liste des trajets</title></head>");
		out.println("<body><center>");
		HttpSession session = req.getSession(true);
		Utilisateur u = (Utilisateur) session.getAttribute("iduser");
		List<Trajet> liste = new ArrayList<Trajet>();
		OutilBDD db = new OutilBDD();
		String depart = req.getParameter("depart");
		String arrivee = req.getParameter("arrivee");
		String date = req.getParameter("date");
		out.print(depart + " '" + arrivee + "' '" + date + "'");
		liste = db.recupererListeTrajets(null, depart, arrivee, date, null,
				null, null, null);
		out.println("</head><body>");
		out.println("<header class=\"entete\">");
		out.println("<div class=\"header-containt\">");
		out.println("<a href=\"Home\" class=\"logo\"><img src=\"../logo.jpg\" alt=\"CarGo !\"></a>");
		out.println("<nav class=\"site-menu\">");
		out.println("<ul class=\"menu-lien\">");
		out.println("<li class =\"menu-btn\">");
		out.println("<a href=\"ProposerTrajet\" class=\"prop-btn\">Proposer un trajet</a>");
		out.println("</li>");

		if (u == null) {
			out.println("<li class=\"inscription\">");
			out.println("<a href=\"../creerprofil.html\" class=\"inscription\">S'inscrire  </a>");
			out.println("<span \"pipe\"> | </span>");
			out.println("</li>");
			out.println("<li class=\"connexion\">");
			out.println("<a href=\"../login.html\" class=\"connexion\">Se connecter </a>");
			out.println("</li>");
		} else {
			out.println("<li class=\"inscription\">");
			out.println("Bonjour " + u.getIduser() + ".");
			out.println("<span \"pipe\"> | </span>");
			out.println("</li>");
			out.println("<li class=\"connexion\">");
			out.println("<a href=\"Deconnect\" class=\"connexion\">Se deconnecter </a>");
			out.println("</li>");
		}

		out.println("</ul>");
		out.println("</nav>");
		out.println("</div>");
		out.println("</header>");
		out.println("<div class=\"recherche\">");
		out.println("<h2>Je cherche une place libre</h2>");
		out.println("<FORM METHOD = \"POST\" ACTION = \"AfficherListe\">");
		out.println("<input type=\"text\" name=\"depart\" class=\"depart\" placeholder=\"Depart ?\"/>");
		out.println("<input type=\"text\" name=\"arrivee\" class=\"arrive\"  placeholder=\"Arrivee ?\"/>");
		out.println("<input type=\"date\" name=\"date\" placeholder=\"Date ?\"/>");
		out.println("<INPUT type = \"submit\" value = \"Rechercher\"/>");
		out.println("</FORM>");
		out.println("</div>");
		out.println("<table border='3'><tr>");
		out.print("<th>depart</TH>");
		out.print("<th>arrivee</TH>");
		out.print("<th>date</TH>");
		out.print("<th>heure depart</TH>");
		out.print("<th>heure arrivée</TH>");
		out.print("<th>nombre de places :</th>");
		out.print("<th>Prix :</TH>");
		out.print("<th></TH>");
		out.println("</tr>");
		for (int i = 0; i < liste.size(); i++) {
			out.println("<tr>");
			out.println("<td>" + liste.get(i).getVilleDepart() + "</td>");
			out.println("<td>" + liste.get(i).getVilleArrivee() + "</td>");
			out.println("<td>" + liste.get(i).getDateTrajet() + "</td>");
			out.println("<td>" + liste.get(i).getHeureDepart() + "</td>");
			out.println("<td>" + liste.get(i).getHeureArrivee() + "</td>");
			out.println("<td>" + liste.get(i).getNbPlace() + "</td>");
			out.println("<td>" + liste.get(i).getPrix() + "</td>");
			out.println("<td><FORM METHOD = \"POST\" ACTION = \"/servlet/DetailsTrajet\">");
			out.println("<INPUT type = \"hidden\" name =\"depart\" value =\""
					+ liste.get(i).getVilleDepart() + "\">");
			out.println("<INPUT type = \"hidden\" name =\"arrivee\" value =\""
					+ liste.get(i).getVilleArrivee() + "\">");
			out.println("<INPUT type = \"hidden\" name =\"date\" value =\""
					+ liste.get(i).getDateTrajet() + "\">");
			out.println("<INPUT type = \"hidden\" name =\"heureDepart\" value =\""
					+ liste.get(i).getHeureDepart() + "\">");
			out.println("<INPUT type = \"hidden\" name =\"heureArrivee\" value =\""
					+ liste.get(i).getHeureArrivee() + "\">");
			out.println("<INPUT type = \"hidden\" name =\"nbPlace\" value =\""
					+ liste.get(i).getNbPlace() + "\">");
			out.println("<INPUT type = \"hidden\" name =\"prix\" value =\""
					+ liste.get(i).getPrix() + "\">");
			out.println("<INPUT type = \"submit\" value = \"Details\"</td>");
			out.println("</tr>");
		}
		out.println("</table>");
		out.println("</center></body><html>");

	}
}