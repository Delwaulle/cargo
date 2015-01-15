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

@WebServlet("/servlet/ListerAvis")
public class ListerAvis  extends HttpServlet{
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
		ArrayList<Avis>  avis=obdd.recupererAvis(login);
		ArrayList<String> avisTronc=new ArrayList<String>();
		out.println("<html>");
		out.println("<head><link href=../bootstrap/css/bootstrap.min.css type=text/css rel=stylesheet> <link href=../avis.css type=text/css rel=stylesheet>  <Title> Avis </Title></head>");
		out.println("<body>");
		out.println("<table class=table table-striped><thead><tr>");
		out.println("<th>#</th>");
		out.println("<th>Conducteur</th>");
		out.println("<th>Note</th>");
		out.println("<th>Avis</th>");
		out.println("<th>Auteur</th>");
		out.println("</tr></thead>");
		
		out.println("<tbody class=zebra-striped>");
		for(int i=0;i<avis.size();i++){
			for(int j=0;j<avis.get(i).getAvis().length();j++){
				if(j%20==0){
					avisTronc.add("<br>"+avis.get(i).getAvis());
				}else 
					avisTronc.add(avis.get(i).getAvis());
			}
		}
		for(int i=0;i<avis.size();i++){
			out.println("<tr>");
			out.println("<td>"+i+"</td>");
			out.println("<td>"+avis.get(i).getConducteur()+"</td>");
			out.println("<td>"+avis.get(i).getNote()+"</td>");
			out.println("<td>"+avisTronc.get(i)+"</td>");
			out.println("<td>"+avis.get(i).getPassager()+"</td>");
			out.println("</tr>");
		}
		out.println("</tbody>");
		out.println("</body>");
		out.println("</table>");
		
		
	}
}
