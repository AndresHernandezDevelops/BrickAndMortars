package Controller;

import java.io.IOException;
import java.util.HashMap;

import Bean.BookBean;
import Bean.CartBean;
import Bean.SetOfCartsBean;
import Model.BookStore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Book
 */
@WebServlet("/Book")
public class Book extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	
	private BookBean book;
	private CartBean cart;

	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Book() 
    {
    	
    }
    
    public void init(HttpServletRequest request, HttpServletResponse response){
    	this.book = (BookBean) request.getAttribute("book");//from the attribute we had set in start
	    String ID = request.getSession().getId();
	    this.cart = SetOfCartsBean.addCart(ID);
    }
    
    public void addBook(){
	    cart.addBook(book);
    }
    
    public void goBack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	request.getRequestDispatcher("Start").forward(request, response);
    }
    
    public void goShoppingCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	request.getRequestDispatcher("ShoppingCart").forward(request, response);
    }
    
    public void addReview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	String reviewRatingParameter = request.getParameter("reviewRating");
    	String reviewerParameter = request.getParameter("reviewer");
    	String bID = this.book.getbID();
    	
    	//perfrom an insert on the review table which i have to create
    }
   
    public void processParameters(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	String addBookParameter = request.getParameter("addBook");
    	String reviewButtonParameter = request.getParameter("reviewButton");
    	String goBackParameter = request.getParameter("goBack");
    	String cartParameter = request.getParameter("cart");
    	
    	if(addBookParameter != null && addBookParameter.equals("true"))
    		this.addBook();
    	else if(cartParameter != null && cartParameter.equals("true"))
    		this.goShoppingCart(request, response);
    	else if(goBackParameter != null && goBackParameter.equals("true"))
    		this.goBack(request, response);
    	else if(reviewButtonParameter != null && reviewButtonParameter.equals("true")){
        	this.addReview(request, response);
    	}
    }

    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		init(request, response);
		request.getRequestDispatcher("/Book.jspx").forward(request, response);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processParameters(request, response);
	}

}
