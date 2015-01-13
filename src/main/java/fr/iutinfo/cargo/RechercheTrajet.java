package fr.iutinfo.cargo;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/servlet/RechercheTrajet")
public class RechercheTrajet extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	ArrayList<Trajet> liste = new ArrayList<Trajet>();
    	PrintWriter out = res.getWriter();
		
    }
}