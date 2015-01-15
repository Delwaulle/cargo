package fr.iutinfo.cargo;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;


@SuppressWarnings("serial")
@WebServlet("/servlet/Authentification")
public class Authentification extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		out.println("<html><head><meta charset=UTF-8>");
		out.println("<link rel=stylesheet type=text/css href=style.css>");
		out.println("<title>Erreur</title></head>");
		out.println("<body><center>");
		out.println("<h1>Login :</h1>");
		
		try {
		  Class.forName("org.sqlite.JDBC");
		  Connection con = DriverManager.getConnection("jdbc:sqlite:database.db");
		  PreparedStatement ps =con.prepareStatement("SELECT * FROM cargouser WHERE iduser=? and mdp=?");
		  ps.setString(1,req.getParameter("login"));
		  ps.setString(2,req.getParameter("mdp"));
		  ResultSet rs = ps.executeQuery();
		  if(rs.next()) {
		      HttpSession session = req.getSession(true);
		      Utilisateur u = new Utilisateur(rs.getString("iduser"),rs.getString("nom"),rs.getString("prenom"),rs.getString("numtel"),rs.getString("mail"));
		      session.setAttribute("iduser", u);
		      con.close();
		      res.sendRedirect("Home");
		  } else { 
		      con.close();
		      out.println("Login/mdp incorrect");
		  }
		  
		  out.println("</center></body>");
		}
		catch (Exception e) {
			out.println("Erreur : " + e);
		}
	}
}