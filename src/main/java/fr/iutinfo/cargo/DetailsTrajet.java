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
		OutilBDD db = new OutilBDD();
		Utilisateur u;

			out.println("<html> <head> <meta charset=UTF-8>");
			out.println("<title> Details du trajet </title> </head> <body>");
			out.println("<h1> Informations sur le trajet </h1> <br/> <br/>");
			out.println("Ville de depart : " + req.getParameter("depart")
					+ "<br/>");
			out.println("Ville d'arrivee : " + req.getParameter("arrivee")
					+ "<br/>");
			out.println("Date du trajet : " + req.getParameter("date")
					+ "<br/>");
			out.println("Heure de depart : " + req.getParameter("heureDepart")
					+ "<br/>");
			out.println("Heure d'arrivee : " + req.getParameter("heureArrivee")
					+ "<br/>");
			out.println("Nombre de places restants : "
					+ req.getParameter("nbPlace") + "<br/>");
			out.println("Prix : " + req.getParameter("prix"));
			out.println("<BR/>");
			
			out.println("</body></html>");
		
	}
}
