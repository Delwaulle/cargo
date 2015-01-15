package fr.iutinfo.cargo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/servlet/PageReservation")
public class PageReservation extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		HttpSession session = req.getSession(true);
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		OutilBDD db = new OutilBDD();
		Utilisateur u = (Utilisateur) session.getAttribute("iduser");
		String login="";
		try{
		login = u.getIduser();
		}catch(Exception e){
			res.sendRedirect("../login.html");
		}
		List<Reservation> listeResas = db.recupererReservations(login);
		if (login != null) {
			Trajet tmp;
			out.println("<html> <head> <meta charset=UTF-8>");
			out.println("<head><link href=../bootstrap/css/bootstrap.min.css type=text/css rel=stylesheet> <link href=../avis.css type=text/css rel=stylesheet>  <Title> Avis </Title></head>");
			out.println("<title> Details du trajet </title> </head> <body>");
			out.println("<table class=table table-striped><thead><tr>");
			out.println("<th>#</th>");
			out.println("<th>Ville de départ</th>");
			out.println("<th>Ville d'arrivée</th>");
			out.println("<th>Date</th>");
			out.println("<th>Etat</th>");
			out.println("</tr></thead>");
			for(int i=0;i<listeResas.size();i++){
				tmp = db.recupererTrajetAt(listeResas.get(i).getIdTrajet());
				out.println("<tr>");
				out.println("<td>"+i+"</td>");
				out.println("<td>"+tmp.getVilleDepart()+"</td>");
				out.println("<td>"+tmp.getVilleArrivee()+"</td>");
				out.println("<td>"+tmp.getDateTrajet()+"</td>");
				out.println("<td>"+listeResas.get(i).getAccepte()+"</td>");
				out.println("</tr>");
			}
			out.println("</tbody>");
			out.println("</table>");
		}
		out.println("</body></html>");
	}
}
