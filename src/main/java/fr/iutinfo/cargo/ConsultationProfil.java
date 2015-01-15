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
public class ConsultationProfil extends HttpServlet{

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
		String iduser =((Utilisateur) (session.getAttribute("iduser"))).getIduser();
		Utilisateur user = db.recupererUtilisateur(iduser);

		res.setContentType("text/html");
		out.println("<html><head><meta charset=UTF-8>");
		out.println("<link rel=stylesheet type=text/css href=style.css>");
		out.println("<title>Select</title></head>");
		out.println("<body><center>");
		out.println("<h1>" + iduser + "</h1><br>");
		out.println("Nom : " + user.getNom()+"<br>");
		out.println("Prenom : " + user.getPrenom()+"<br>");
		out.println("Numéro de téléphone : " + user.getNumtel()+"<br>");
		out.println("Adresse mail : " + user.getMail()+"<br>");
		out.println("</center></body><html>");

	}
}
