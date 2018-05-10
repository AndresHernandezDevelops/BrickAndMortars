package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShoppingCart
 */
@WebServlet("/ShoppingCart")
public class ShoppingCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShoppingCart() {
        
    }
    
    public void processParameters(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	String updateParameter = request.getParameter("update");
    	String checkoutParameter = request.getParameter("checkout");
    	
    	if(updateParameter != null  && updateParameter.equals("true"))
    		this.getServletContext().getRequestDispatcher("UpdateQuantity").forward(request, response);
    	else if(checkoutParameter != null && updateParameter.equals("true"))
    		request.getRequestDispatcher("/Checkout.jspx").forward(request, response);
    	else
    		System.out.println("did not hit an if statement in the shopping cart parameters");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String target = "/ShoppingCart.jspx";
		request.getRequestDispatcher(target).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processParameters(request, response);
	}

}
