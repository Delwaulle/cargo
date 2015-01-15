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
		HttpSession session = req.getSession(true);
		if(session != null){
			OutilBDD db = new OutilBDD();
			db.reserverTrajet(req.getParameter("idUser"), Integer.parseInt(req.getParameter("idTrajet")));
			JOptionPane.showMessageDialog(null,"Le trajet a été posté","Succès", JOptionPane.INFORMATION_MESSAGE);
			res.sendRedirect("/servlet/PageReservation");
		}
		else{
			res.sendRedirect("/servlet/login.html");
		}
	}
}