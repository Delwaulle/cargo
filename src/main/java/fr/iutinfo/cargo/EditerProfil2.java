package fr.iutinfo.cargo;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;

@WebServlet("/servlet/EditerProfil2")
public class EditerProfil2 extends HttpServlet {

	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		out.println("<html><head><meta charset=UTF-8>");
		out.println("<link rel=stylesheet type=text/css href=style.css>");
		out.println("<title>Mise a jour</title></head>");
		out.println("<body><center>");
		out.println("<h1>Mise a jour</h1>");
		
		HttpSession session = req.getSession(true);
		Utilisateur u = (Utilisateur)session.getAttribute("iduser");
		if (u==null) res.sendRedirect("Home");
		
		OutilBDD db = new OutilBDD();
		
	    String nom = req.getParameter("nom");
	    String prenom = req.getParameter("prenom");
	    String numtel = req.getParameter("numtel");
	    String mail = req.getParameter("mail");
	    
	    db.updateProfil(nom, prenom, numtel, mail);
	    
	    out.println("Vos donnees ont bien été mise a jour !");
	    out.println("<br/><a href='..'>Retour au Home</a>");
	    
	    out.println("<body><center></html>");
	   
	}
	    		
}


