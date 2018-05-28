package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import Bean.BookBean;
import Model.BookStore;
/**
 * Servlet implementation class Start
 */
@WebServlet(urlPatterns =  "/MainPage" )
public class MainPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	//bookstore object to access the various functions of a bookstore
	private BookStore bookStore;
	
	//maps to hold the list of results after calling queryTables method
	Map<String, BookBean> bookBeanList;
	
	//private variables to hold search keys from the jspx
	private String bID;
	private String title;
	private String category;
	private String thumbnail;
	
	public void init() throws ServletException{
		try {
			this.bookStore = new BookStore();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
    /**
     * @throws ServletException 
     * @see HttpServlet#HttpServlet()
     */
    public MainPage() throws ServletException {
        this.init();	
        
    }
    
    private void searchByCategory(HttpServletRequest request){
    	System.out.println("searching by category...");
		this.category = request.getParameter("category");
		System.out.println("category = " + category);
		this.bID = request.getParameter("bID");
		this.title = request.getParameter("title");
		try{
			bookBeanList = bookStore.searchByCategory(this.category);
		}catch (Exception e){
			e.printStackTrace();
		}
    }
    
    private void searchByText(HttpServletRequest request){
    	System.out.println("searching by text...");
		this.title = request.getParameter("searchByText");
		System.out.println("title = " + title);
		try{
			bookBeanList = bookStore.searchByTitle(this.title);
		}catch (Exception e){
			e.printStackTrace();
		}
    }

    //prints out to the jspx using ajax 
    private void serveJSP(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
		
			request.setAttribute("resultList", bookBeanList);
			
			PrintWriter rw = response.getWriter();
			try {
				Collection<BookBean> bbean = bookBeanList.values();
				Iterator<BookBean> bookIterator = bbean.iterator();
				
				LinkedList<BookBean> bookbeanll = new LinkedList<BookBean>(bbean);
				Gson testGson = new Gson();
				String out = testGson.toJson(bookbeanll);
				rw.println(out);
				System.out.println(out);
				/*rw.println("<table border='1'>");
				rw.println("<tr>");
				rw.println("<td>book ID</td>");
				rw.println("<td>title</td>");
				rw.println("<td>category</td>");
				rw.println("<td>price</td>");
				rw.println("</tr>");
				while (bookIterator.hasNext())
				{
					BookBean item = bookIterator.next();
					System.out.println("thumbnail filepath = " + item.getThumbnail());
					String bID = item.getbID();
					String title = item.getTitle();
					String category = item.getCategory();
					double price = item.getPrice();
					String thumbnail = item.getThumbnail();
					rw.println("<center>");
					rw.print("<br />" + bID);
					rw.print("<br />" + title);
					rw.print("<br />" + category);
					rw.print("<br />$" + String.format("%.2f", price));
					rw.print("<br /><img src=" + thumbnail + "/><br />");
					rw.println("</center>");
				}
				rw.println("</table>");*/
			} catch (Exception e) {
				e.printStackTrace();
			}
    }
    
    public void redirect(HttpServletRequest request, HttpServletResponse response, String target) throws IOException {
    	String redirection = request.getScheme() + "://" + request.getServerName() + ":"
				+ request.getServerPort() + request.getContextPath() + "/" + target;
		response.sendRedirect(redirection);
    }
    
    public void processParameters(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	String cartParameter = request.getParameter("cart");
    	String bookIDParameter = request.getParameter("bookID");
    	String loginParameter = request.getParameter("login");
    	String registerParamter = request.getParameter("register");
    	String searchByCategory = request.getParameter("category");
    	String searchByTextParameter = request.getParameter("searchByTextButton");

    	if(cartParameter != null && cartParameter.equals("true"))
    		this.redirect(request, response, "ShoppingCart");
    	else if(loginParameter != null && loginParameter.equals("true"))
    		this.redirect(request, response, "Login");
    	else if(registerParamter != null && registerParamter.equals("true")) 
    		this.redirect(request, response, "Register");
    	else if(searchByCategory != null){
    		this.searchByCategory(request);
    		serveJSP(request, response);
    	}
    	else if (searchByTextParameter != null && searchByTextParameter.equals("true")){
    		this.searchByText(request);
    		serveJSP(request, response);
    	}
    	else if(bookIDParameter != null && bookIDParameter.equals("true")){
    		request.setAttribute("book", bookBeanList.get(bookIDParameter));//setting the chosen book to a request variable and grabbing it from shopping cart
    		this.redirect(request, response, "Book");
    	}
    	else
    		System.out.println("didn't hit any if-statement in the mainpage");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("-----------------DOGET OF THE MAINPAGE!!!!!---------------");
		request.getRequestDispatcher("/MainPage.jspx").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//iterate and print out parameters
		System.out.println("-----------------DOPOST OF THE MAINPAGE!!!!!---------------");
		Enumeration<String> tmp = request.getParameterNames();
		while (tmp.hasMoreElements())
		{
			String tmp1 = tmp.nextElement();
			System.out.println(tmp1 + "=" + request.getParameter(tmp1));
		}
		processParameters(request, response);
	}

}
