package fr.iutinfo.cargo;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/servlet/RechercheTrajet")
public class RechercheTrajet extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	ArrayList<Trajet> liste = new ArrayList<Trajet>();
    	PrintWriter out = res.getWriter();
		OutilBDD db = new OutilBDD();
		
		String depart = req.getParameter("depart");
		String arrivee = req.getParameter("arrivee");
		String date = req.getParameter("date");
		
		liste = db.recupererListeTrajets(null, depart, arrivee, date, null, null, null, null);
		
		res.setContentType("text/html");
		out.println("<html><head><meta charset=UTF-8>");
		out.println("<link rel=stylesheet type=text/css href=style.css>");
		out.println("<title>Select</title></head>");
		out.println("<body><center>");
		out.println("");
		out.println("<br/><a href='Menu'>Retour</a>");
		out.println("</center></body><html>");
    }
}