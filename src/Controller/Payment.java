package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.CartBean;
import Bean.SetOfCartsBean;

/**
 * Servlet implementation class Payment
 */
@WebServlet("/Payment")
public class Payment extends HttpServlet {
	private static final long serialVersionUID = 1L; 
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Payment() {
        super();
    }

	public void processParameters(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String username = request.getSession().getAttribute("username").toString();
		String fname = request.getAttribute("fname").toString(); //fix lname and fname once the jspx has a form to submit
		String lname = request.getAttribute("lname").toString();
		CartBean currentCart;
		
		if(username != null) 
			currentCart = SetOfCartsBean.getInstance().getCartByUsername(username);
		else
			currentCart = SetOfCartsBean.getInstance().retrieveCart(request.getSession().getId());
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String target = "/Payment.jspx";
		request.getRequestDispatcher(target).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processParameters(request, response);
	}

}
