package fr.iutinfo.cargo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

@WebServlet("/servlet/ReserverTrajet")
public class ReserverTrajet extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		HttpSession s = req.getSession(true);
		Utilisateur u = (Utilisateur) s.getAttribute("iduser");
		Trajet trajet;
		String login="";
		try{
			login = u.getIduser();
		}catch(Exception e){
			res.sendRedirect("../login.html");
		}
		OutilBDD db = new OutilBDD();
		db.reserverTrajet(login, Integer.parseInt(req.getParameter("idTrajet")));
		trajet = db.recupererTrajetAt(Integer.parseInt(req.getParameter("idTrajet")));
		db.envoieNotification(login, trajet.getIduser(), "L utilisateur " + login + " a réservé une place pour votre trajet");
		res.sendRedirect("/servlet/PageReservation");
	}
}