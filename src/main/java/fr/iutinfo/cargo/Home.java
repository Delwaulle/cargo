package fr.iutinfo.cargo;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@SuppressWarnings("serial")
@WebServlet("")
public class Home extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
	    HttpSession session = req.getSession(true);
	    Utilisateur u = (Utilisateur) session.getAttribute("iduser");
	    OutilBDD o = new OutilBDD();
	    
	    o.creerTables();
	    
		out.println("<!DOCTYPE html>");
		out.println("<html lang=\"fr\">");
		out.println("<head>");
		out.println("<title>La page d'accueil</title>");
		out.println("<meta charset='utf-8'>");
		out.println("<link rel=\"stylesheet\" href=\"../index.css\" >");
		out.println("</head><body>");
		out.println("<header class=\"entete\">");
		out.println("<div class=\"header-containt\">");
		out.println("<a href=\"\" class=\"logo\"><img src=\"../logo.jpg\" alt=\"CarGo !\"></a>");
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
			out.println("<li><a href=\"#\">Notifications</a></li>");
			out.println("<li><a href=\"#\">Voir Profil</a></li>");
			out.println("<li><a href=\"#\">Historique trajets</a></li>");
			out.println("<li><a href=\"servlet/Deconnect\">Se deconnecter </a></li>");
			
			out.println("</ul>");
			out.println("</li>");
			out.println("</ul>");
			 
			/*out.println("<script language='JavaScript'> "+
			"function ChangeUrl(formulaire) { "+
			   
			  " if (formulaire.ListeUrl.selectedIndex != 0) "+
			      "{" +
			     "var url;" +
			   "  url = formulaire.ListeUrl.options[formulaire.ListeUrl.selectedIndex].value;"+
			     "window.open(url,'_blank');"+ 
			      " } "+
			  " } "+
			"</script> "+
			"<FORM >"+ 
			"<SELECT NAME='ListeUrl' SIZE=1 onChange='ChangeUrl(this.form)'  > "+
			"<OPTION SELECTED VALUE=''>-Menu Déroulant De Liens-</option> "+
			 " <option value='Lien 1'>Titre 1</option> "+
			 " <option value='Lien 2'>Titre 2</option> "+
			 " <option value='Lien 3'>Titre 3</option>"+
			 " <option value='Lien 4'>Titre 4</option>  "+
			"</SELECT> "+
			"</FORM>");*/
		
		}
		
		out.println("</ul>");
		out.println("</nav>");
		out.println("</div>");
		out.println("</header>");
		out.println("<div class=\"recherche\">");
		out.println("<h2>Je cherche une place libre</h2>");
		out.println("<FORM METHOD = \"POST\" ACTION = \"servlet/AfficherListe\">");
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
		out.println("<h3><a href=contact.html class=contact>Contact </a></h3>");
		out.println("<h3>NewsLetter<h3>");
		
		out.println("</div>");
		out.println("</footer>");
		out.println("</body>");
		out.println("</html>");


	}
}