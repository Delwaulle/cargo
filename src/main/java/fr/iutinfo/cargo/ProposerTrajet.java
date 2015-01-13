package fr.iutinfo.cargo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/servlet/ProposerTrajet")
public class ProposerTrajet extends HttpServlet{
	
	public void service( HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		res.setContentType( "text/html" );
		HttpSession s = req.getSession(true);
		String login = (String) s.getAttribute("Username");
		/*if(login==null){
			res.sendRedirect("../login.html");
		}*/
		out.println("<head><Title> Proposer un trajet </Title></head>");
	//	out.println("<H1> Bonjour "+login +"</h1>");
		String villeD;
		String villeA;
		String date;
		int nbPlaces;
		String prix;
		int heureA;
		int heureD;
			out.println("<FORM method=get action=../servlet/ValiderProposition");
			
			out.println("<BR>");
			out.println("Ville de départ : <input type =text name=villeD value=\'\'>");
			out.println("<BR>");
			
			out.println("Ville d'arrivée : <input type =text name=villeA value=\'\'>");
			out.println("<BR>");
			
			out.println("Date : <input type =date name=date value=\'\'>");
			out.println("<BR>");
			
			out.println("Nombre de place(s) disponible(s) : <input type =text name=nbPlaces value=\'\'>");
			out.println("<BR>");
			
			out.println("Heure de départ : <input type =text name=heureD value=\'\'>");
			out.println("<BR>");
			
			
			out.println("Prix : <input type=text name=heureD value=\'\'>");
			out.println("<BR>");
			
			out.println(" <INPUT type=submit value=Suivant>");
			out.println("</FORM>");
	}
	

}
