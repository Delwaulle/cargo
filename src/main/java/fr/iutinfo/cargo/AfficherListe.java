package fr.iutinfo.cargo;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("/servlet/AfficherListe")
public class AfficherListe extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		out.println("<html><head><meta charset=UTF-8>");
		out.println("<link rel=stylesheet type=text/css href=../index.css>");
		out.println("<link href=../bootstrap/css/bootstrap.min.css type=text/css rel=stylesheet>");
		out.println("<title>Liste des trajets</title></head>");
		out.println("<body><center>");
		HttpSession session = req.getSession(true);
		Utilisateur u = (Utilisateur) session.getAttribute("iduser");
		List<Trajet> liste = new ArrayList<Trajet>();
		OutilBDD db = new OutilBDD();
		String depart = req.getParameter("depart");
		String arrivee = req.getParameter("arrivee");
		String date = req.getParameter("date");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		boolean dateConforme=false;
        Date d = new Date();
        try {
            d = sdf.parse(date);
            dateConforme=true;
        } catch (Exception e) {
        	dateConforme=false;
        }
        session.setAttribute("date",null);
		liste = db.recupererListeTrajets(null,null, depart, arrivee, date, null,
				null, null, null);
		out.println("</head><body>");
		out.println("<header class=\"entete\">");
		out.println("<div class=\"header-containt\">");
		out.println("<a href=\"..\" class=\"logo\"><img src=\"../logo.jpg\" alt=\"CarGo !\"></a>");
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
		if(dateConforme)
			out.println("<input type=text name=\"date\" placeholder=jj/mm/aaaa>");
		else
			out.println("<input type=text color:red name=\"date\" placeholder=Erreur&nbsp;format&nbsp;date>");
		out.println("<INPUT type = \"submit\" value = \"Rechercher\"/>");
		out.println("</FORM>");
		out.println("</div>");
		//Si la liste contient au moins un resultat, alors on l'affiche
		if(liste.size()>0){
			out.println("<table border='3'><tr>");
			out.print("<th></TH>");
			out.print("<th>depart</TH>");
			out.print("<th>arrivee</TH>");
			out.print("<th>date</TH>");
			out.print("<th>heure depart</TH>");
			out.print("<th>nombre de places :</th>");
			out.print("<th>Prix :</TH>");
			out.print("<th></TH>");
			out.print("<th></TH>");
			out.println("</tr>");
			for (int i = 0; i < liste.size(); i++) {
				out.println("<tr>");
				out.println("<td><FORM METHOD = \"GET\" ACTION = \"/servlet/consultationProfil\">");
				out.println("<INPUT class=form-control type = \"hidden\" name =\"idcond\" value =\""+liste.get(i).getIduser() + "\">");
		    	out.println("<INPUT type = \"submit\" value = \"Voir profil du conducteur\"></FORM></td>");
				out.println("<td class=info>" + liste.get(i).getVilleDepart() + "</td>");
				out.println("<td class=info>" + liste.get(i).getVilleArrivee() + "</td>");
				out.println("<td class=info>" + liste.get(i).getDateTrajet() + "</td>");
				out.println("<td class=info>" + liste.get(i).getHeureDepart() + "</td>");
				out.println("<td class=info>" + liste.get(i).getNbPlace() + "</td>");
				out.println("<td class=info>" + liste.get(i).getPrix() + "</td>");
				out.println("<td><FORM METHOD = \"POST\" ACTION = \"/servlet/DetailsTrajet\">");
		    	out.println("<INPUT type = \"hidden\" name =\"depart\" value =\""+liste.get(i).getVilleDepart() + "\">");
		    	out.println("<INPUT type = \"hidden\" name =\"arrivee\" value =\""+liste.get(i).getVilleArrivee() + "\">");
		    	out.println("<INPUT type = \"hidden\" name =\"date\" value =\""+liste.get(i).getDateTrajet() + "\">");
		    	out.println("<INPUT type = \"hidden\" name =\"heureDepart\" value =\""+liste.get(i).getHeureDepart() + "\">");
		    	out.println("<INPUT type = \"hidden\" name =\"nbPlace\" value =\""+liste.get(i).getNbPlace() + "\">");
		    	out.println("<INPUT type = \"hidden\" name =\"prix\" value =\""+liste.get(i).getPrix() + "\">");
		    	out.println("<INPUT type = \"submit\" value = \"Details\"></FORM></td>");
		    	out.println("<td><FORM METHOD = \"GET\" ACTION = \"/servlet/ReserverTrajet\">");
		    	out.println("<INPUT type = \"hidden\" name =\"idTrajet\" value =\""+ liste.get(i).getIdtrajet() + "\">");
		    	out.println("<INPUT type = \"submit\" value = \"Reserver\"></FORM></td>");
		    	out.println("</tr>");
			}
			out.println("</table>");
		}
		//Sinon on retourne un message
		else {
			out.println("Aucune recherche ne correspond aux termes de recherche spécifiés");
		}
		out.println("</center></body><html>");

	}
}