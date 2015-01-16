package fr.iutinfo.cargo;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;

@WebServlet("/servlet/DetailsTrajet")
public class DetailsTrajet extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		
		
		HttpSession session = req.getSession(true);
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
	    Utilisateur u = (Utilisateur) session.getAttribute("iduser");
		OutilBDD db = new OutilBDD();
		
		
		
		
		
		out.println("<!DOCTYPE html>");
		out.println("<html lang=\"fr\">");
		out.println("<head>");
		out.println("<title>Détails trajet</title>");
		out.println("<meta charset='utf-8'>");
		out.println("<link rel=\"stylesheet\" href=\"../index.css\" >");
		out.println("</head>" +
				"<body>");
		out.println("<header class=\"entete\">");
		out.println("<div class=\"header-containt\">");
		out.println("<a href='../..' class=\"logo\"><img src=\"../logo.jpg\" alt=\"CarGo !\"></a>");
		out.println("<nav class=\"site-menu\">");
		out.println("<ul class=\"menu-lien\">");
		out.println("<li class =\"menu-btn\">");
		out.println("<a href=\"servlet/ProposerTrajet\" class=\"prop-btn\">Proposer un trajet</a>");
		out.println("</li>");
		
		if(u == null){
			out.println("<li class=\"inscription\">");
			out.println("<a href=\"../inscription.html\" class=\"inscription\">S'inscrire  </a>");
			out.println("<span \"pipe\"> | </span>");
			out.println("</li>");
			out.println("<li class=\"connexion\">");
			out.println("<a href=\"../login.html\" class=\"connexion\">Se connecter </a>");
			out.println("</li>");
			
			
		} else {
			out.println("<li class=\"inscription\">");
			out.println("<ul id=\"menu-demo2\">");
			out.println("<li class='bonjour'><a href=\"#\">Bonjour "+ u.getIduser() +".</a>");
			out.println("<ul>");
			out.println("<li><a href=/servlet/ListerNotifications>Notifications</a></li>");
			out.println("<li><a href=/servlet/EditerProfil>Editer Profil</a></li>");
			out.println("<li><a href=/servlet/PageReservation>Reservation</a></li>");
			out.println("<li><a href=/servlet/HistoriqueTrajet>Historique trajets</a></li>");
			out.println("<li><a href=\"servlet/Deconnect\">Se deconnecter </a></li>");
			
			out.println("</ul>");
			out.println("</li>");
			out.println("</ul>");
			 
			
			
		}
		
			out.println("</ul>");
			out.println("</nav>");
			out.println("</div>");
			out.println("</header>");
			out.println("<div class='info'>");
			out.println("<table border='1px solid black'>");
			out.println("<h1> Informations sur le trajet </h1> ");
			out.println("<tr>");
			out.println("<th>Ville de depart  </th>");
			out.println("<th>Ville d'arrivee </th>");
			out.println("<th>Date du trajet   </th>");
			out.println("<th>Heure de depart  </th>");
			out.println("<th>Heure d'arrivee  </th>");
			out.println("<th>Nombre de places restants  </th>");
			out.println("<th>Prix  </th>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>"+req.getParameter("depart")+"</td>");
			out.println("<td>"+req.getParameter("arrivee")+"</td>");
			out.println("<td>"+req.getParameter("date")+"</td>");
			out.println("<td>"+req.getParameter("heureDepart")+"</td>");
			out.println("<td>"+req.getParameter("heureArrivee")+"</td>");
			out.println("<td>"+req.getParameter("nbPlace")+"</td>");
			out.println("<td>"+req.getParameter("prix")+"</td>");
			out.println("</tr>");
			
			out.println("</table>");
			out.println("</div></body></html>");
		
	}
}
