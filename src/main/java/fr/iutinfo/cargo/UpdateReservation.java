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
import javax.servlet.http.HttpSession;

import fr.iutinfo.cargo.OutilBDD;
import fr.iutinfo.cargo.Reservation;
import fr.iutinfo.cargo.Trajet;
import fr.iutinfo.cargo.Utilisateur;

@WebServlet("/servlet/UpdateReservation")
public class UpdateReservation extends HttpServlet {

	public void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		OutilBDD db = new OutilBDD();
	}
}