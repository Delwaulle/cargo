


@WebServlet("/servlet/Index")
public class Index extends HttpServlet{
	public void service(HttpServletRequest req,HttpServletResponse res)
		throws ServletException, IOException{
			PrintWriter out = res.getWriter();
			res.setContentType("text/html");
			
			
			//Entête de la page
			
			out.println("<head>");
			out.prinln("<title>La page d'authentification</title>" +
					"<meta charset='utf-8'>" +
					"<link rel="stylesheet" href="index.css" >");
			
			