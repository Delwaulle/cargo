package fr.iutinfo.cargo;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;


@SuppressWarnings("serial")
@WebServlet("/servlet/Deconnect")
public class Deconnect extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		out.println("<html><head><meta charset=UTF-8>");
		out.println("<link rel=stylesheet type=text/css href=style.css>");
		out.println("<title>Erreur</title></head>");
		out.println("<body><center>");
		out.println("<h1>Login :</h1>");
		
		try {
			HttpSession session = req.getSession(true);
			session.invalidate();
			res.sendRedirect("Home");
		} catch (Exception e) {
			out.println("Erreur : " + e);
		}
	}
}