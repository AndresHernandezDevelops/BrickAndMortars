package Controller;

import java.io.IOException;
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

    private void lookup(HttpServletRequest request){
    	String loginButtonParameter = request.getParameter("LoginButton");
    	String usernameParameter = request.getParameter("username");
    	String passwordParameter = request.getParameter("password");
    	try{
	    	if(loginButtonParameter != null && loginButtonParameter.equals("true")) {
	    		System.out.println("login button pressed, username=" + usernameParameter + " and password=" + passwordParameter);
	    		boolean status = login.lookup(usernameParameter, passwordParameter);
	    		if(status)
	    			request.getSession().setAttribute("username", usernameParameter);
	    		else{
	    			request.getSession().setAttribute("username", "anonymous");
	    			System.out.println("could not find credentials, sorry!");
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
		
		lookup(request);
	}

}
