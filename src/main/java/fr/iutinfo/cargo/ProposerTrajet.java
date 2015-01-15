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
public class ProposerTrajet extends HttpServlet {

	@Override
	public void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		HttpSession s = req.getSession(true);
		Utilisateur u = (Utilisateur) s.getAttribute("iduser");
		String login="";
		try{
		login = u.getIduser();
		}catch(Exception e){
			res.sendRedirect("../servlet/Home");
		
		}
		if(login==null){ res.sendRedirect("../servlet/Home"); }
		out.println("<html>");
		out.println("<head><link href=../bootstrap/css/bootstrap.min.css type=text/css rel=stylesheet><link href=../bouton.css type=text/css rel=stylesheet><Title> Proposer un trajet </Title></head>");
		out.println("<div class=container-fluid>");
		out.println("<p class=lead> Entrez un trajet </p>");
		out.println("<form method=get action=../servlet/ValiderProposition");
		out.println("<div class=input>"+
				"<label>Ville de d�part :</label>"+
				"<div>"+
				" <input type=text class=form-control name=villeD placeholder=Ex:&nbsp;Lille>"+
				//"</div>"+
				"</div>");

		out.println("<div class=input>"+
				"<label>Ville d'arriv�e :</label>"+
				"<div>"+
				"<input type=text class=form-control name=villeA placeholder=Ex:&nbsp;Paris>"+
				"</div>"+
				"</div>");

		out.println("<div class=input>"+
				"<label>Date :</label>"+
				"<div>"+
				"<input type=text class=form-control name=date placeholder=jj/mm/aaaa>"+
				"</div>"+
				"</div>");

		out.println("<div class=input>"+
				"<label>Nombre de place(s) disponible(s) :</label>"+
				"<select name=nbPlaces>"+
				"<option value=1>1</option>"+
				"<option value=2>2</option>"+
				"<option value=3>3</option>"+
				"<option value=4>4</option>"+
				"<option value=5>5</option>"+
				"</select>"+
				"</div>");

		out.println("<div class=form-group>"+
				"<label>Heure de d�part :</label>"+
				"<select class=form-control name=heureD>"+
				"<option value=1>1h</option>"+
				"<option value=2>2h</option>"+
				"<option value=3>3h</option>"+
				"<option value=4>4h</option>"+
				"<option value=5>5h</option>"+
				"<option value=6>6h</option>"+
				"<option value=7>7h</option>"+
				"<option value=8>8h</option>"+
				"<option value=9>9h</option>"+
				"<option value=10>10h</option>"+
				"<option value=11>11h</option>"+
				"<option value=12>12h</option>"+
				"<option value=13>13h</option>"+
				"<option value=14>14h</option>"+
				"<option value=15>15h</option>"+
				"<option value=16>16h</option>"+
				"<option value=17>17h</option>"+
				"<option value=18>18h</option>"+
				"<option value=19>19h</option>"+
				"<option value=20>20h</option>"+
				"<option value=21>21h</option>"+
				"<option value=22>22h</option>"+
				"<option value=23>23h</option>"+
				"<option value=0>minuit</option>"+
				"</select>"+
				"</div>");


		out.println("<div class=form-group>"+
				"<label>Prix :</label>"+
				"<div class=input>"+
				"<input type=text name=prix placeholder=Ex:&nbsp;10,5>"+
				"<span class=add-on>&euro;</span>"+
				"</div>"+
				"</div>");
		
		out.println("<div>"+
				"<div class=boutton>"+
				"<button type=button class=btn btn-warning>annuler</button>"+
				"</div>"+
				"<div class=boutton>"+
				"<button id=suivant type=submit class=btn btn-warning >Suivant</button>"+
				"</div>"+
				"</div>");
		out.println("</form>");
		out.println("</div>");
		out.println("</html>");
	}

}