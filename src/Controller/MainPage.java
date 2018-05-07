package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
				//request.setAttribute("kitty", bookIterator.next().getThumbnail());
				
				rw.println("<table border='1'>");
				rw.println("<tr>");
				rw.println("<td>book ID</td>");
				rw.println("<td>title</td>");
				rw.println("<td>category</td>");
				rw.println("<td>price</td>");
				rw.println("</tr>");
				while (bookIterator.hasNext())
				{
					BookBean item = bookIterator.next();
					System.out.println(item.getThumbnail());
					String bID = item.getbID();
					String title = item.getTitle();
					String category = item.getCategory();
					double price = item.getPrice();
					rw.println("<tr>");
					rw.print("<td>" + bID + "</td>");
					rw.print("<td>" + title + "</td>");
					rw.print("<td>" + category + "</td>");
					rw.print("<td>" + String.format("%.2f", price) + "</td>");
					rw.println("</tr>");
				}
				rw.println("</table");
			} catch (Exception e) {
				e.printStackTrace();
			}
    }
    
    public void processParameters(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	String cartParameter = request.getParameter("cart");
    	String bookIDParameter = request.getParameter("bookID");
    	String loginParameter = request.getParameter("login");
    	String registerParamter = request.getParameter("register");
    	String searchByCategoryParameter = request.getParameter("searchByCategory");
    	String searchByTextParameter = request.getParameter("searchByTextButton");

    	if(cartParameter != null && cartParameter.equals("true"))
    		request.getRequestDispatcher("ShoppingCart").forward(request, response);
    	else if(loginParameter != null && loginParameter.equals("true"))
    		request.getRequestDispatcher("Login").forward(request, response);
    	else if(registerParamter != null && registerParamter.equals("true"))
    		request.getRequestDispatcher("Register").forward(request, response);
    	else if(searchByCategoryParameter != null && searchByCategoryParameter.equals("true"))
    		this.searchByCategory(request);
    	else if (searchByTextParameter != null && searchByTextParameter.equals("true"))
    		this.searchByText(request);
    	else if(bookIDParameter != null && bookIDParameter.equals("true")){
    		request.setAttribute("book", bookBeanList.get(bookIDParameter));//setting the chosen book to a request variable and grabbing it from shopping cart
			request.getRequestDispatcher("Book").forward(request, response);
    	}
    	else
    		System.out.println("didn't hit any if-statement");
    	
    	serveJSP(request, response);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String target = "/MainPage.jspx";
		request.getRequestDispatcher(target).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//iterate and print out parameters
//		Enumeration<String> tmp = request.getParameterNames();
//		while (tmp.hasMoreElements())
//		{
//			String tmp1 = tmp.nextElement();
//			System.out.println(tmp1 + "=" + request.getParameter(tmp1));
//		}
		processParameters(request, response);
	}

}
