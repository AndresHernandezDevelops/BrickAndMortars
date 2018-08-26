package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Bean.CartBean;
import Bean.SetOfCartsBean;

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
    
    public void redirect(HttpServletRequest request, HttpServletResponse response, String target) throws IOException {
    	String redirection = request.getScheme() + "://" + request.getServerName() + ":"
				+ request.getServerPort() + request.getContextPath() + "/" + target;
		response.sendRedirect(redirection);
    }
    
    public void processParameters(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	String updateParameter = request.getParameter("update");
    	String checkoutParameter = request.getParameter("checkout");
    	
    	if(updateParameter != null  && updateParameter.equals("true"))
    		this.redirect(request, response, "UpdateQuantity");
    	else if(checkoutParameter != null && updateParameter.equals("true"))
    		this.redirect(request, response, "Checkout");
    	else
    		System.out.println("did not hit an if statement in the shopping cart");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new Gson();
		
		//CartBean cartbean = SetOfCartsBean.getInstance().updateByLogin(request.getParameter("username"), request.getSession().getId());
		String usr = (String) request.getSession().getAttribute("username");
		CartBean cartbean;
		if (usr == null)
		{
			cartbean = SetOfCartsBean.getInstance().retrieveCart(request.getSession().getId());
		}
		else
		{
			cartbean = SetOfCartsBean.getInstance().getCartByUsername(usr);
		}
		String cart = gson.toJson(cartbean);
		//request.setAttribute("cart", cart);
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
