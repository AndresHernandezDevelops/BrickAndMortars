package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.BookBean;
import Bean.CartBean;
import Bean.SetOfCartsBean;

import DAO.PurchaseOrderItemDAO;

/**
 * Servlet implementation class Payment
 */
@WebServlet("/Payment")
public class Payment extends HttpServlet {
	private static final long serialVersionUID = 1L; 
	PurchaseOrderItemDAO po;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Payment() {
    	super();
    	po = new PurchaseOrderItemDAO();
    }

	public void processParameters(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		String username = (String) request.getSession().getAttribute("username");
		String name = request.getParameter("name");
		int i = name.indexOf(" ");
		String fname = name.substring(0, i); //fix lname and fname once the jspx has a form to submit
		String lname = name.substring(i + 1);
		CartBean currentCart;
		String usrnme;
		PrintWriter out = response.getWriter();
		
		if(username != null) 
		{
			currentCart = SetOfCartsBean.getInstance().getCartByUsername(username);
			usrnme = username;
		}			
		else
		{
			currentCart = SetOfCartsBean.getInstance().retrieveCart(request.getSession().getId());
			usrnme = "****";
		}
			int count = po.updatePO(usrnme, fname, lname, "ORDERED");
			if (count == -1) {
				out.println("<script type=\"text/javascript\">");
				   out.println("alert('cart empty!');");
				   out.println("</script>");
			}
			po.updatePOItem(usrnme, currentCart, count);
			currentCart.clearCart();
			response.getWriter().println(count);
			System.out.print(count);
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
		try {
			processParameters(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
