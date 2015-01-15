package fr.iutinfo.cargo;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@SuppressWarnings("serial")
@WebServlet("/servlet/Home")
public class Home extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
	    HttpSession session = req.getSession(true);
	    Utilisateur u = (Utilisateur) session.getAttribute("iduser");
	    
	    
		out.println("<!DOCTYPE html>");
		out.println("<html lang=\"fr\">");
		out.println("<head>");
		out.println("<title>La page d'accueil</title>");
		out.println("<meta charset='utf-8'>");
		out.println("<link rel=\"stylesheet\" href=\"../index.css\" >");
		out.println("</head><body>");
		out.println("<header class=\"entete\">");
		out.println("<div class=\"header-containt\">");
		out.println("<a href=\"index2.html\" class=\"logo\"><img src=\"../logo.jpg\" alt=\"CarGo !\"></a>");
		out.println("<nav class=\"site-menu\">");
		out.println("<ul class=\"menu-lien\">");
		out.println("<li class =\"menu-btn\">");
		out.println("<a href=\"ProposerTrajet\" class=\"prop-btn\">Proposer un trajet</a>");
		out.println("</li>");
		
		if(u == null){
			out.println("<li class=\"inscription\">");
			out.println("<a href=\"../creerprofil.html\" class=\"inscription\">S'inscrire  </a>");
			out.println("<span \"pipe\"> | </span>");
			out.println("</li>");
			out.println("<li class=\"connexion\">");
			out.println("<a href=\"../login.html\" class=\"connexion\">Se connecter </a>");
			out.println("</li>");
		} else {
			out.println("<li class=\"inscription\">");
			out.println("Bonjour "+ u.getIduser() +".");
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
		out.println("<input type=text name=\"date\" placeholder=jj/mm/aaaa>");
		out.println("<INPUT type = \"submit\" value = \"Rechercher\"/>");
		out.println("</FORM>");
		
		out.println("</div>");

		out.println("<div class=\"image\">");

		out.println("<img src=\"../image.jpg\" alt=\"map\">");

		out.println("</div>");
				

		out.println("<footer>");
		out.println("<div class=\"barre-menu-haut\">");
		out.println("<h3>Infos Pratiques<h3>");
		out.println("<h3>A propos<h3>");
		out.println("<h3>NewsLetter<h3>");
		
		out.println("</div>");
		out.println("</footer>");
		out.println("</body>");
		out.println("</html>");


	}
}