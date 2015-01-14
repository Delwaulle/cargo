package fr.iutinfo.cargo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servlet/AfficherListe")
public class AfficherListe extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		out.println("<html><head><meta charset=UTF-8>");
		out.println("<link rel=stylesheet type=text/css href=style.css>");
		out.println("<title>Liste des trajets</title></head>");
		out.println("<body>");

		List<Trajet> liste = new ArrayList<Trajet>();
		OutilBDD db = new OutilBDD();
		String depart = req.getParameter("depart");
		String arrivee = req.getParameter("arrivee");
		String date = req.getParameter("date");
		out.print(depart +" '"+arrivee+"' '"+date+"'");
		db.creerTables();
		liste = db.recupererListeTrajets(null, depart, arrivee, date, null, null, null, null);

		res.setContentType("text/html");
		out.println("<html><head><meta charset=UTF-8>");
		out.println("<link rel=stylesheet type=text/css href=style.css>");
		out.println("<title>Select</title></head>");
		out.println("<body><center>");
		out.println("<table border='3'><tr>");
		out.print("<th>EN ATTENTE PROFIL UTILISATEUR</TH>");
	    out.print("<th>depart</TH>");
	    out.print("<th>arrivee</TH>");
	    out.print("<th>date</TH>");
	    out.print("<th>heure depart</TH>");
	    out.print("<th>heure arrivée</TH>");
	    out.print("<th>nombre de places :</th>");
	    out.print("<th>Prix :</TH>");
	    out.println("</tr>");
	    for(int i = 0; i < liste.size(); i++){
	    	out.println("<tr>");
	    	out.println("<td>null</td>");
	    	out.println("<td>" + liste.get(i).getVilleDepart() + "</td>");
	    	out.println("<td>" + liste.get(i).getVilleArrivee() + "</td>");
	    	out.println("<td>" + liste.get(i).getDateTrajet() + "</td>");
	    	out.println("<td>" + liste.get(i).getHeureDepart() + "</td>");
	    	out.println("<td>" + liste.get(i).getHeureArrivee() + "</td>");
	    	out.println("<td>" + liste.get(i).getPrix() + "</td>");
	    	out.println("<td><FORM METHOD = \"POST\" ACTION = \"/servlet/DetailsTrajet\">");
	    	out.println("<INPUT type = \"hidden\" name =\"idUser\" value =\""+liste.get(i).getIduser() + "\">");
	    	out.println("<INPUT type = \"hidden\" name =\"depart\" value =\""+liste.get(i).getVilleDepart() + "\">");
	    	out.println("<INPUT type = \"hidden\" name =\"arrivee\" value =\""+liste.get(i).getVilleArrivee() + "\">");
	    	out.println("<INPUT type = \"hidden\" name =\"date\" value =\""+liste.get(i).getDateTrajet() + "\">");
	    	out.println("<INPUT type = \"hidden\" name =\"heureDepart\" value =\""+liste.get(i).getHeureDepart() + "\">");
	    	out.println("<INPUT type = \"hidden\" name =\"heureArrivee\" value =\""+liste.get(i).getHeureArrivee() + "\">");
	    	out.println("<INPUT type = \"hidden\" name =\"nbPlace\" value =\""+liste.get(i).getNbPlace() + "\">");
	    	out.println("<INPUT type = \"hidden\" name =\"prix\" value =\""+liste.get(i).getPrix() + "\">");
	    	out.println("<INPUT type = \"submit\" value = \"Envoyer\"</td>");
	    	out.println("</tr>");
	    }
	    out.println("</table>");
		out.println("</center></body><html>");
		
	}
}