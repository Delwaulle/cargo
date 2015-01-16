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
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

@WebServlet("/servlet/HistoriqueTrajet")
public class HistoriqueTrajet extends HttpServlet {

	public void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		out.println("<html><link rel=stylesheet type=text/css href=style.css>");
		out.println("<head><title>Historique</title></head>");
		out.println("<body><center>");
		out.println("<h1>Historique de vos trajets</h1>");
		HttpSession s = req.getSession(true);
		Utilisateur u = (Utilisateur) s.getAttribute("iduser");
		OutilBDD o = new OutilBDD();
		if (u==null) res.sendRedirect("..");
		List<Reservation> listreserv = new ArrayList<Reservation>();
		listreserv = o.recupererReservations(u.getIduser(), 1);
		out.println("<table border='3'><tr>");
		out.print("<th>id Trajet</TH>");
		out.print("<th>date</TH>");
		out.print("<th>villeD</TH>");
		out.print("<th>villeA</TH>");
		out.print("<th>Prix :</TH>");
		out.println("</tr>");
		List<Trajet> listetrajet = new ArrayList<Trajet>();
		for (int i = 0; i < listreserv.size(); i++) {
			listetrajet = o.recupererListeTrajets(listreserv.get(i).getIdTrajet(), null, null, null, null, null, null, null,null);
			out.println("<tr>");
			out.println("<td class=info>" + listetrajet.get(0).getIdtrajet() + "</td>");
			out.println("<td class=info>" + listetrajet.get(0).getIduser() + "</td>");
			out.println("<td class=info>" + listetrajet.get(0).getDateTrajet() + "</td>");
			out.println("<td class=info>" + listetrajet.get(0).getHeureDepart() + "</td>");
			out.println("<td class=info>" + listetrajet.get(0).getVilleDepart() + "</td>");
			out.println("<td class=info>" + listetrajet.get(0).getVilleArrivee() + "</td>");
			out.println("<td class=info>" + listetrajet.get(0).getPrix() + "</td>");
	    	out.println("</tr>");
		}		
		out.println("</table></center></body></html>");
	}
}
