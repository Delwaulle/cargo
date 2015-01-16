package fr.iutinfo.cargo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/servlet/ValiderProposition")
public class ValiderProposition extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		HttpSession s = req.getSession(true);
		Utilisateur u = (Utilisateur) s.getAttribute("iduser");
		String login = u.getIduser();
		if(login==null){ res.sendRedirect(""); }

		out.println("<html>");
		out.println("<head><meta charset=UTF-8><Title> Aperçu trajet </Title></head>");
		String villeD = req.getParameter("villeD");
		String villeA = req.getParameter("villeA");
		String date = req.getParameter("date");
		String nbPlaces = req.getParameter("nbPlaces");
		String prix = req.getParameter("prix");
		String heureD = req.getParameter("heureD");
		System.out.println(villeD);
		if(villeD==null ||villeA ==null || date==null){
			out.println("fuck");
			res.sendRedirect("/servlet/ProposerTrajet");
		}
		out.println("<table>" + "<tr><th>" + "Pseudo</th><th>"
				+ "Ville de départ</th> <th>" + "Ville d'arrivée</th><th>"
				+ "Date</th> <th>" + "Heure de départ</th> <th>"
				+ "Nombre de place(s) disponible(s)</th> <th>"
				+ "Prix</th><td>" + login
				+ "</td><td>" + villeD + "</td><td>" + villeA + "</td><td>"
				+ date + "</td><td>" + heureD + "</td><td>" + nbPlaces
				+ "</td><td>" + prix + "</td></tr><tr><td>");
		out.println("<FORM method=get action=../servlet/PosterProposition>");

		out.println("<input type =hidden name=villeD value=" + villeD + ">");

		out.println("<input type =hidden name=villeA value=" + villeA + ">");

		out.println("<input type =hidden name=date value=" + date + ">");

		out.println("<input type =hidden name=nbPlaces value=" + nbPlaces + ">");
		out.println("<input type =hidden name=heureD value=" + heureD + ">");

		out.println("<input type=hidden name=prix value=" + prix + ">");
		out.println("<INPUT type=submit value=Poster la proposition>");
		out.println("</FORM>");
		out.println("</html>");
	}

}
