package fr.iutinfo.cargo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servlet/CreerProfil")
public class CreerProfil extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		out.println("<html><head><meta charset=UTF-8>");
		out.println("<link rel=stylesheet type=text/css href=style.css>");
		out.println("<title>Création de votre profil</title></head>");
		out.println("<body>");

		out.println("<html>");
		out.println("<head><meta charset=\"UTF-8\">");
		out.println("<link rel=stylesheet type=text/css href=style.css>");
		out.println("<title>Suite de votre profil</title>");
		out.println("</head>");
		out.println("<body><center><h1>");
		out.println("<font color=\"DARKRED\">Créons votre profil</font>");
		out.println("<FORM METHOD=\"POST\" ACTION=\"CreerProfil2\">");
		out.println("Nom :");
		out.println("<INPUT type=\"text\" value=\"\"name=\"nom\" autofocus>");
		out.println("<br/>");
		out.println("Prénom :");
		out.println("<INPUT type=\"text\" value=\"\" name=\"prenom\">");
		out.println("<br/>");
		out.println("Numero de Telephone");
		out.println("<INPUT type=\"tel\" value=\"\" name=\"numTel\">");
		out.println("<br/>");
		out.println("Adresse Mail");
		out.println("<INPUT type=\"text\" value=\"\" name=\"mail\">");
		out.println("<br/>");
		out.println("<INPUT type=\"hidden\" value="+req.getParameter("login")+" name=\"login\">");
		out.println("<br/>");
		out.println("<INPUT type=\"hidden\" value="+req.getParameter("mdp")+" name=\"mdp\">");
		out.println("<br/>");
		out.println("<INPUT type=\"submit\" value=\"creer profil\">");
		out.println("</FORM>");
		out.println("</center>");
		out.println("</body></html>");
		
		



	}
}