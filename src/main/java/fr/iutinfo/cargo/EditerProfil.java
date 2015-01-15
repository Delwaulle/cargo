package fr.iutinfo.cargo;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;

@WebServlet("/servlet/EditerProfil")
public class EditerProfil {
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		out.println("<link rel=stylesheet type=text/css href=style.css>");
		out.println("<title>Maj</title></head>");
		out.println("<body><center>");
		out.println("<h1>Mettre a jour votre profil :</h1>");
		
		HttpSession session = req.getSession(true);
		Utilisateur u = (Utilisateur)session.getAttribute("iduser");
		if (u==null) res.sendRedirect("Home");
	
		out.println("<FORM METHOD='get' ACTION='EditerProfil2'>");
		out.println("<table border='3'>");
		
		out.print("<tr><TH>login</TH><TH>mdp</TH><TH>nom</TH><TH>prenom</TH><TH>adresse</TH>");
		out.println("</tr>");
		
		out.println("<TR>");
		out.print("<TD><INPUT type='text' value='"+u.nom+"' name=nom></TD>");
		out.print("<TD><INPUT type='text' value='"+u.prenom+"' name=prenom></TD>");
		out.print("<TD><INPUT type='text' value='"+u.numtel+"' name=numtel></TD>");
		out.print("<TD><INPUT type='text' value='"+u.mail+"' name=mail></TD>");
		out.println("</TR>");
		
		out.println("</table><INPUT type='submit' value='Mettre a jour'></FORM></center></body>");
	}
}
	


