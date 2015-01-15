package fr.iutinfo.cargo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("/servlet/ajouterAvisBDD")
public class ajouterAvisBDD extends HttpServlet{
	public void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		HttpSession session = req.getSession(true);
		OutilBDD o = new OutilBDD();
		HttpSession s = req.getSession(true);
		Utilisateur u = (Utilisateur) s.getAttribute("iduser");
		String cond = req.getParameter("idcond");
		String login = "";
		try {
			login = u.getIduser();
		} catch (Exception e) {
			res.sendRedirect("../login.html");
		}
		o.ajouterAvis(req.getParameter("idconducteur"), login, req.getParameter("ameliorer"), Integer.parseInt(req.getParameter("note")));

	}
	
}
