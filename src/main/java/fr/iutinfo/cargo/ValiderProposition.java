package fr.iutinfo.cargo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/servlet/ValiderProposition")
public class ValiderProposition extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		HttpSession s = req.getSession(true);
		Utilisateur u = (Utilisateur) s.getAttribute("iduser");
		String login = u.getIduser();
		if(login==null){ res.sendRedirect(""); }

		String villeD = req.getParameter("villeD");
		String villeA = req.getParameter("villeA");
		String date = req.getParameter("date");
		String nbPlaces = req.getParameter("nbPlaces");
		String prix = req.getParameter("prix");
		String heureD = req.getParameter("heureD");
		System.out.println(villeD);
		if(villeD==null ||villeA ==null || date==null){
			out.println("fuck");
			res.sendRedirect("/servlet/ProposerTrajet");
		}
		
		
		out.println("<!DOCTYPE html>");
		out.println("<html lang=\"fr\">");
		out.println("<head>");
		out.println("<title>Recapitulatif</title>");
		out.println("<meta charset='utf-8'>");
		out.println("<link rel=\"stylesheet\" href=\"../index.css\" >");
		out.println("<link rel=\"stylesheet\" href=\"../poster.css\" >");
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
			out.println("<li><a href=\"Deconnect\">Se deconnecter </a></li>");
			
			out.println("</ul>");
			out.println("</li>");
			out.println("</ul>");
			 
			
			
		}
		
		
		/*out.println("<table>" + "<tr><th>" + "Pseudo</th><th>"
				+ "Ville de départ</th> <th>" + "Ville d'arrivée</th><th>"
				+ "Date</th> <th>" + "Heure de départ</th> <th>"
				+ "Nombre de place(s) disponible(s)</th> <th>"
				+ "Prix</th></tr><tr><td>" + login
				+ "</td><td>" + villeD + "</td><td>" + villeA + "</td><td>"
				+ date + "</td><td>" + heureD + "</td><td>" + nbPlaces
				+ "</td><td>" + prix + "</td></tr><td>");
		
		*/
		
		
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
		out.println("<th>Nombre de places disponible  </th>");
		out.println("<th>Prix  </th>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>"+req.getParameter("depart")+"</td>");
		out.println("<td>"+req.getParameter("arrivee")+"</td>");
		out.println("<td>"+req.getParameter("date")+"</td>");
		out.println("<td>"+req.getParameter("heureDepart")+"</td>");
		out.println("<td>"+req.getParameter("nbPlace")+"</td>");
		out.println("<td>"+req.getParameter("prix")+"</td>");
		out.println("</tr>");
		
		out.println("</table>");
		out.println("</div>");
		
		
		
		
		
		
		out.println("<FORM method=get action=../servlet/PosterProposition>");

		out.println("<input type =hidden name=villeD value=" + villeD + ">");

		out.println("<input type =hidden name=villeA value=" + villeA + ">");

		out.println("<input type =hidden name=date value=" + date + ">");

		out.println("<input type =hidden name=nbPlaces value=" + nbPlaces + ">");
		out.println("<input type =hidden name=heureD value=" + heureD + ">");

		out.println("<input type=hidden name=prix value=" + prix + ">");
		out.println("<INPUT type=submit value=Poster la proposition>");
		out.println("</FORM>");
		out.println("</body></html>");
	}

}
