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
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		out.println("<html><head><meta charset=UTF-8>");
		out.println("<link rel=stylesheet type=text/css href=style.css>");
		out.println("<title>Liste des trajets</title></head>");
		out.println("<body>");
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
		out.println("Votre avis a été posté avec succès !");
		out.println("<form method=\"post\" action=\"/servlet/consultationProfil\">");
		out.println("<INPUT type = \"hidden\" name=\"idcond\" value = \""+req.getParameter("idconducteur")+"\"/>");
		out.println("<INPUT type = \"submit\" value = \"OK\"/>");
		out.println("</form>");
	}
	
}
