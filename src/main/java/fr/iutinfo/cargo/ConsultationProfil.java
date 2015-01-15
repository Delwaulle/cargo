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
@WebServlet("/servlet/consultationProfil")
public class ConsultationProfil extends HttpServlet {

	public void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		out.println("<html><head><meta charset=UTF-8>");
		out.println("<link rel=stylesheet type=text/css href=style.css>");
		out.println("<title>Liste des trajets</title></head>");
		out.println("<body>");
		HttpSession session = req.getSession(true);
		OutilBDD db = new OutilBDD();
		HttpSession s = req.getSession(true);
		Utilisateur u = (Utilisateur) s.getAttribute("iduser");
		String cond = req.getParameter("idcond");
		String login = "";
		try {
			login = u.getIduser();
		} catch (Exception e) {
			res.sendRedirect("../login.html");
		}

		Utilisateur conducteur = db.recupererUtilisateur(cond);

		res.setContentType("text/html");
		out.println("<html><head><meta charset=UTF-8>");
		out.println("<link rel=stylesheet type=text/css href=style.css>");
		out.println("<title>Select</title></head>");
		out.println("<body><center>");
		out.println("<h1>" + conducteur.getIduser() + "</h1><br>");
		out.println("Nom : " + conducteur.getNom() + "<br>");
		out.println("Prenom : " + conducteur.getPrenom() + "<br>");
		out.println("Numéro de téléphone : " + conducteur.getNumtel() + "<br>");
		out.println("Adresse mail : " + conducteur.getMail() + "<br>");

		out.println("<form method=\"post\" action=\"/servlet/ajouterAvisBDD\">");
		out.println("<div class=input>"
				+ "<label>Noter ce conducteur :</label>"
				+ "<select name=nbPlaces>" + "<option value=1>1</option>"
				+ "<option value=2>2</option>" + "<option value=3>3</option>"
				+ "<option value=4>4</option>" + "<option value=5>5</option>"
				+ "</select>" + "</div>");
		out.println("<p>");
		out.println("<label for=\"ameliorer\">Comment pensez-vous que je pourrais améliorer mon site ?</label><br />");
		out.println("<textarea name=\"ameliorer\" id=\"ameliorer\"></textarea>");
		out.println("</p>");
		out.println("<INPUT type = \"submit\" value = \"Noter\"/>");
		out.println("</form>");
		out.println("</center></body><html>");

	}
}
