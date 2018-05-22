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
    
    private void register(HttpServletRequest request, HttpServletResponse response){
    	String registerButtonParameter = request.getParameter("registerButton");
    	String usernameParameter = request.getParameter("username");
    	String passwordParameter = request.getParameter("password");
    	String addressParameter = request.getParameter("address");
    	String firstnameParameter = request.getParameter("firstname");
    	String lastnameParameter = request.getParameter("lastname");
    	String postalcodeParameter = request.getParameter("postalcode");
    	String provinceParameter = request.getParameter("province");
    	String countryParameter = request.getParameter("country");
    	String phoneParameter = request.getParameter("phone");
    	
    	
    	System.out.println(registerButtonParameter);
    	System.out.println(usernameParameter);
    	System.out.println(passwordParameter);
    	System.out.println(addressParameter);
    	System.out.println(firstnameParameter);
    	System.out.println(lastnameParameter);
    	System.out.println(postalcodeParameter);
    	System.out.println(provinceParameter);
    	System.out.println(countryParameter);
    	System.out.println(phoneParameter);
    	
    	
    	try{
    		if(registerButtonParameter != null && registerButtonParameter.equals("true")){
    			boolean status = register.register(usernameParameter, passwordParameter, firstnameParameter, lastnameParameter, addressParameter, postalcodeParameter, provinceParameter, countryParameter, phoneParameter);
    			if(status) {
	    			request.getSession().setAttribute("username", usernameParameter);
	    			String redirection=request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/MainPage";
	    			response.sendRedirect(redirection);
    			}
	    		else{
	    			//request.getSession().setAttribute("username", "anonymous");
	    			System.out.println("could not register! username already exist");
	    			request.getSession().setAttribute("error", "User already exists!");
	    			this.getServletContext().getRequestDispatcher("/Register.jspx").forward(request, response);
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
		register(request, response);
	}

}
