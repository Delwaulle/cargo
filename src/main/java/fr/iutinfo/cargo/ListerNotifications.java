package fr.iutinfo.cargo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/servlet/ListerNotifications")
public class ListerNotifications  extends HttpServlet{
	@Override
	public void service(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		HttpSession s = req.getSession(true);
		Utilisateur u = (Utilisateur) s.getAttribute("iduser");
		String login="";
		
		try{
			login = u.getIduser();
		}catch(Exception e){
			res.sendRedirect("../login.html");
		}
		
		OutilBDD obdd=new OutilBDD();
		
		ArrayList<Notifications>  notifications=obdd.recupererNotifications(login);
		out.println("<html>");
		out.println("<head><link href=../bootstrap/css/bootstrap.min.css type=text/css rel=stylesheet> <link href=../avis.css type=text/css rel=stylesheet>  <Title> Avis </Title></head>");
		out.println("<body>");
		out.println("<table class=table table-striped><thead><tr>");
		out.println("<th>#</th>");
		out.println("<th>idnotif</th>");
		out.println("<th>Expediteur</th>");
		out.println("<th>Message</th>");
		out.println("</tr></thead>");
		
		out.println("<tbody class=zebra-striped>");
		
		for(int i=0;i<notifications.size();i++){
			out.println("<tr>");
			out.println("<td>"+i+"</td>");
			out.println("<td>"+notifications.get(i).getIdnotif()+"</td>");
			out.println("<td>"+notifications.get(i).getExpediteur()+"</td>");
			out.println("<td>"+notifications.get(i).getMessage()+"</td>");
			out.println("</tr>");
		}
		
		out.println("</tbody>");
		out.println("</body>");
		out.println("</table>");
		
		
	}
}