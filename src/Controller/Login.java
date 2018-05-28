package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.LoginDAO;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private LoginDAO login;
	
	
    /**
     * @throws ClassNotFoundException 
     * @see HttpServlet#HttpServlet()
     */
    public Login() throws ClassNotFoundException {
        login = new LoginDAO();
    }
    
    public void redirect(HttpServletRequest request, HttpServletResponse response, String target) throws IOException {
    	String redirection = request.getScheme() + "://" + request.getServerName() + ":"
				+ request.getServerPort() + request.getContextPath() + "/" + target;
		response.sendRedirect(redirection);
    }

    private void login(HttpServletRequest request, HttpServletResponse response){
    	String loginButtonParameter = request.getParameter("loginButton");
    	String usernameParameter = request.getParameter("username");
    	String passwordParameter = request.getParameter("password");
    	
    	try{
    		PrintWriter out = response.getWriter();
	    	if(loginButtonParameter != null && loginButtonParameter.equals("true")) {
	    		System.out.println("login button pressed, username=" + usernameParameter + " and password=" + passwordParameter);
	    		boolean status = login.login(usernameParameter, passwordParameter);
	    		if(status) {
	    			request.getSession().setAttribute("username", usernameParameter);
	    			this.redirect(request, response, "MainPage");
	    		}
	    		else{
	    			request.getSession().setAttribute("username", "anonymous");
	    			response.setContentType("text/html");
					out.println("<script type=\"text/javascript\">");
					out.println("alert('invalid username or password');");
					out.println("location='Login';");
					out.println("</script>");
	    		}
	    	}
    	}
    	catch (Exception e){
    		e.printStackTrace();
    	}
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/Login.jspx").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		System.out.println(request.getParameter("password"));
		login(request, response);
	}

}
