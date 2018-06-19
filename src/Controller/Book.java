package Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import Bean.BookBean;
import Bean.CartBean;
import Bean.ReviewBean;
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
	private BookStore bookStore;
	private String bID;
	Map<String, ReviewBean> bookBeanList;
	
    /**
     * @throws ServletException 
     * @see HttpServlet#HttpServlet()
     */
    public Book() throws ServletException {
    	this.init();
    }
    
    public void init(HttpServletRequest request, HttpServletResponse response){
    	this.book = (BookBean) request.getAttribute("book");//from the attribute we had set in start
    	String ID = request.getSession().getId();
	    this.cart = SetOfCartsBean.getInstance().addCart(ID);
    	try {
			this.bookStore = new BookStore();
			this.bookBeanList = this.bookStore.searchReviews(book.getbID());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
    }
    
    public void addBook(){
	    cart.addBook(book);
    }
    
    private void searchById(HttpServletRequest request) {
    	System.out.println("searching by ID..");
		this.bID = request.getParameter("bID");
		try{
			//bookBeanList = bookStore.;
		}catch (Exception e){
			e.printStackTrace();
		}
    }
    
    public void addToCart(String session, String bid)
    {
    	CartBean cart = SetOfCartsBean.getInstance().retrieveCart(session);
    	cart.addBook(book);
    }
    
    public void removeFromCart(String session, String bid)
    {
    	CartBean cart = SetOfCartsBean.getInstance().retrieveCart(session);
    	cart.removeBook(book);
    }
    
    public void setBookAmount(String session, String bid, int amount)
    {
    	CartBean cart = SetOfCartsBean.getInstance().retrieveCart(session);
    	cart.setBookAmount(book, amount);
    }
    
    public void redirect(HttpServletRequest request, HttpServletResponse response, String target) throws IOException {
    	String redirection = request.getScheme() + "://" + request.getServerName() + ":"
				+ request.getServerPort() + request.getContextPath() + "/" + target;
		response.sendRedirect(redirection);
    }
    
    public void addReview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	String usernameParameter = request.getParameter("username");
    	String reviewParameter = request.getParameter("review");
    	int ratingParameter = Integer.parseInt(request.getParameter("rating"));
    	String bID = this.book.getbID();	
    	
    	try {
			this.bookStore.addReview(bID, usernameParameter, reviewParameter, ratingParameter);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//perfrom an insert on the review table, must create a method in the DAO class.
    }
   
    public void processParameters(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	String addBookParameter = request.getParameter("addBook");
    	String reviewButtonParameter = request.getParameter("reviewButton");
    	String goBackParameter = request.getParameter("goBack");
    	String cartParameter = request.getParameter("cart");
    	
    	if(addBookParameter != null && addBookParameter.equals("true"))
    		this.addBook();
    	else if(cartParameter != null && cartParameter.equals("true"))
    		this.redirect(request, response, "ShoppingCart");
    	else if(goBackParameter != null && goBackParameter.equals("true"))
    		this.redirect(request, response, "MainPage");
    	else if(reviewButtonParameter != null && reviewButtonParameter.equals("true")){
        	this.addReview(request, response);
    	}
    }

    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		init(request, response);
		addToCart(request.getSession().getId(), request.getParameter(bID));
		removeFromCart(request.getSession().getId(), request.getParameter(bID));
		setBookAmount(request.getSession().getId(), request.getParameter(bID), 1);
		//query db
		//request.setAttribute("img", "bid");
		request.getRequestDispatcher("/Book.jspx").forward(request, response);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processParameters(request, response);
	}

}
