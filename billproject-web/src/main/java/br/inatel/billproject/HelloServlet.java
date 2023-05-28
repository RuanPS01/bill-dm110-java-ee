package br.inatel.billproject;

import java.io.IOException;
import java.io.PrintWriter;

//import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/helloServlet")
public class HelloServlet extends HttpServlet {

	private static final long serialVersionUID = -24118939727042992L;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<h1>Hello from Servlet!!!</h1>");
		if(name == null) {
			out.println("<h2>No name to say hi</h2>");
		} else {
			out.println("<h2>" + name + "</h2>");
		}
		out.println("Current date: " + new java.util.Date());
	}

}
