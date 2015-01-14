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

@WebServlet("/servlet/CreerProfil2")
public class CreerProfil2 extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		
		
		Utilisateur u = new Utilisateur(req.getParameter("login"), req.getParameter("nom"),req.getParameter("prenom"),req.getParameter("numTel"),req.getParameter("mail"));
		OutilBDD o = new OutilBDD();
		o.ajouterUtilisateur(u, req.getParameter("mdp"));



	}
}