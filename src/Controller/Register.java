package Controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.LoginDAO;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private LoginDAO register;
	
    /**
     * @throws ClassNotFoundException 
     * @see HttpServlet#HttpServlet()
     */
    public Register() throws ClassNotFoundException {
        register = new LoginDAO();
    }
    
    private void register(HttpServletRequest request){
    	String registerButtonParameter = request.getParameter("registerButton");
    	String usernameParameter = request.getParameter("username");
    	String passwordParameter = request.getParameter("password");
    	System.out.println("registerButtonParameter");
    	System.out.println("usernameParameter");
    	System.out.println("passwordParameter");
    	Enumeration<String> tmp = request.getParameterNames();
		while (tmp.hasMoreElements())
		{
			String tmp1 = tmp.nextElement();
			System.out.println(tmp1 + "=" + request.getParameter(tmp1));
		}
    	
    	try{
    		if(registerButtonParameter != null && registerButtonParameter.equals("true")){
    			System.out.println("register button pressed, username=" + usernameParameter + " and password=" + passwordParameter);
    			boolean status = register.register(usernameParameter, passwordParameter);
    			if(status)
	    			request.getSession().setAttribute("username", usernameParameter);
	    		else{
	    			request.getSession().setAttribute("username", "anonymous");
	    			System.out.println("could not register! username already exist");
	    		}
    		
    		}
    		
    	} catch (Exception e){
    		e.printStackTrace();
    	}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/Register.jspx").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		register(request);
	}

}
