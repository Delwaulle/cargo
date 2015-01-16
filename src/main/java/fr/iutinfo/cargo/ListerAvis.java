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
		out.println("<html>");
		out.println("<head><meta charset=UTF-8><link href=../bootstrap/css/bootstrap.min.css type=text/css rel=stylesheet> <link href=../img.css type=text/css rel=stylesheet><link href=../avis.css type=text/css rel=stylesheet>  <Title> Avis </Title></head>");
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
			out.println("<tr>");
			out.println("<td>"+i+"</td>");
			out.println("<td>"+avis.get(i).getConducteur()+"</td>");
			int note=avis.get(i).getNote();
			out.println("<td>");
			for(int j=0;j<note;j++){
				out.println("<img src =../img/star.png alt=/>");
			}
			out.println("<h2>/5</h2></td>");
			out.println("<td>"+this.getAvis(avis.get(i).getAvis())+"</td>");
			out.println("<td>"+avis.get(i).getPassager()+"</td>");
			out.println("</tr>");
		}
		out.println("</tbody>");
		out.println("</body>");
		out.println("</table>");
		
		
	}
	
	public String getAvis(String avis){
		String str="";
		for(int i=0;i<avis.length();i++){
			if(i%50==0 && i!=0){
				str+=avis.charAt(i)+"<br>";
			}else 
				str+=avis.charAt(i);
		}
		return str;
	}
}
