package Controller;

import java.io.IOException;
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
    }

	public void processParameters(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		String username = request.getSession().getAttribute("username").toString();
		String fname = request.getAttribute("fname").toString(); //fix lname and fname once the jspx has a form to submit
		String lname = request.getAttribute("lname").toString();
		CartBean currentCart;
		String usrnme;
		
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
			int count = po.updatePO(usrnme, fname, lname, "APPROVED");
			if (count == -1) {
				
			}
			po.updatePOItem(usrnme, currentCart, count);
	
			response.getWriter().print(count);
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
