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
		List<Reservation> listeResas = db.recupererReservations(((Utilisateur)session.getAttribute("iduser")).getIduser());
		Utilisateur u;
		if (req.getParameter("idUser") != null) {
			Trajet tmp;
			u = db.recupererUtilisateur(req.getParameter("idUser"));
			out.println("<html> <head> <meta charset=UTF-8>");
			out.println("<title> Details du trajet </title> </head> <body>");
			for(Reservation r : listeResas){
				out.println("<div>");
				tmp = db.recupererTrajetAt(r.getIdTrajet());
				out.println("Départ : " + tmp.getVilleDepart() + " Ville arrivée : " + tmp.getVilleArrivee() + "date : " + tmp.getDateTrajet() + " Etat : "+ r.getAccepte());
				out.println("</div>");
			}
			out.println("");
		}
		out.println("</body></html>");
	}
}
