package fr.iutinfo.cargo;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;

@WebServlet("/servlet/DetailsTrajet")
public class DetailsTrajet extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		
		HttpSession session = req.getSession(true);
		Trajet t = (Trajet) session.getAttribute("idtrajet");
		
		
		
			out.println("<html> <head> <meta charset=UTF-8>");
			out.println("<title> Details du trajet </title> </head> <body>");
		
			
			out.println("<h1> Informations sur le trajet </h1> <br/> <br/>");
			
			out.println("Ville de depart : " + t.getVilleDepart() + "<br/>");
			out.println("Ville d'arrivee : " + t.getVilleArrivee() + "<br/>");
			out.println("Date du trajet : " + t.getDateTrajet() + "<br/>");
			out.println("Heure de depart : " + t.getHeureDepart() + "<br/>");
			out.println("Heure d'arrivee : " + t.getHeureArrivee() + "<br/>");
			out.println("Nombre de places restants : " + t.getNbPlace() + "<br/>");
			out.println("Prix : " + t.getPrix());
		
			out.println("</body></html>");
		
		
	}
}