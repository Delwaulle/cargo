package fr.iutinfo.cargo;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConsultationProfil {
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		out.println("<html><head><meta charset=UTF-8>");
		out.println("<link rel=stylesheet type=text/css href=style.css>");
		out.println("<title>Liste des trajets</title></head>");
		out.println("<body>");

		
		OutilBDD db = new OutilBDD();
		String iduser = req.getParameter("iduser");
		Utilisateur user = db.recupererUtilisateur(iduser);
		
		res.setContentType("text/html");
		out.println("<html><head><meta charset=UTF-8>");
		out.println("<link rel=stylesheet type=text/css href=style.css>");
		out.println("<title>Select</title></head>");
		out.println("<body><center>");
		out.println("<h1>"+iduser+"</h1>");
		out.println("Nom : "+ user.getNom());
		out.println("Prenom : "+ user.getPrenom());
		out.println("Numéro de téléphone : "+ user.getNumtel());
		out.println("Adresse mail : "+ user.getMail());
		out.println("</center></body><html>");
		
	}
}
