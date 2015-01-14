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
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		OutilBDD db = new OutilBDD();
		Utilisateur u;
		if (req.getParameter("idUser") != null) {
			u = db.recupererUtilisateur(req.getParameter("idUser"));
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
			out.println("Utilisateur : " + u.getPrenom() + " " + u.getPrenom());
			out.println("<BR/>");
			out.println("Tel : " + u.getNumtel());
			out.println("Mail : " + u.getMail());
		}
		out.println("</body></html>");
	}
}