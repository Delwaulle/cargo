package fr.iutinfo.cargo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/servlet/PosterProposition")
public class PosterProposition extends HttpServlet{

	public void service( HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		res.setContentType( "text/html" );
		HttpSession s = req.getSession(true);
		String login = (String) s.getAttribute("Username");
		/*if(login==null){
			res.sendRedirect("../login.html");
		}*/
		
		String villeD=req.getParameter("villeD");
		String villeA=req.getParameter("villeA");
		String date=req.getParameter("date");
		int nbPlaces=Integer.parseInt(req.getParameter("nbPlaces"));
		double prix=Double.parseDouble(req.getParameter("prix"));
		String heureA=req.getParameter("villeD");
		int heureD=Integer.parseInt(req.getParameter("heureD"));
		
		OutilBDD obdd = new OutilBDD();
		obdd.ajouterTrajet(login, villeD, villeA, date, heureD, null, nbPlaces, prix);
	}
}
