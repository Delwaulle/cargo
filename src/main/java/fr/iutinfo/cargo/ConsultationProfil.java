package fr.iutinfo.cargo;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("/servlet/consultationProfil")
public class ConsultationProfil extends HttpServlet {

	public static String cond="";
	public void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		HttpSession session = req.getSession(true);
		OutilBDD db = new OutilBDD();
		HttpSession s = req.getSession(true);
		Utilisateur u = (Utilisateur) s.getAttribute("iduser");
		cond = req.getParameter("idcond");
		System.out.println("cc"+cond);
		String login = "";
		try {
			login = u.getIduser();
		} catch (Exception e) {
			res.sendRedirect("../login.html");
		}

		Utilisateur conducteur = db.recupererUtilisateur(cond);
		
		res.setContentType("text/html");
		
		
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
		out.println("<div class='blabla'");
		
		out.println("<h1> Informations sur Conducteur  </h1> ");
		
		out.println("<h2>Nom </h2>");
		out.println("<h3>"+conducteur.getNom()+"</h3><br>");
		out.println("<h2>Prenom </h2>");
		out.println("<h3>"+conducteur.getPrenom()+"</h3><br>");
		out.println("<h2>Numero tel </h2>");
		out.println("<h3>"+conducteur.getNumtel()+"</h3><br>");
		out.println("<h2>Adresse mail </h2>");
		out.println("<h3>"+conducteur.getMail()+"</h3><br><br><br><br>");
	
		
		

		out.println("<form method=\"post\" action=\"/servlet/ajouterAvisBDD\">");
		out.println("<div class=input>"
				+ "<label>Noter ce conducteur :</label>"
				+ "<select name=note>" + "<option value=1>1</option>"
				+ "<option value=2>2</option>" + "<option value=3>3</option>"
				+ "<option value=4>4</option>" + "<option value=5>5</option>"
				+ "</select>" + "</div>");
		out.println("<p>");
		out.println("<label for=\"ameliorer\">Donner un avis sur ce conducteur :</label><br />");
		out.println("<textarea name=\"ameliorer\" id=\"ameliorer\"></textarea>");
		out.println("</p>");
		out.println("<INPUT type = \"hidden\" name=idconducteur value = \""+cond+"\"/>");

		out.println("<INPUT type = \"submit\" value = \"Noter\"/>");
		
		out.println("<li>");
		out.println("<a href=/servlet/ListerAvis> Consulter les avis</a></li>");
		out.println("</form>");
		out.println("</div>");
		

	
		
		
		
		out.println("</div></body></html>");
		
	}
}
