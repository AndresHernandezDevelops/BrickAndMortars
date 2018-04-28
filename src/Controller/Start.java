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
@WebServlet(urlPatterns = { "/Start", "/Startup", "/Startup/*", "/Start/*" })
public class Start extends HttpServlet {
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
    public Start() throws ServletException {
        this.init();
        
    }
    
    private void queryTables(HttpServletRequest request) {
    	String searchByCategoryParameter = request.getParameter("searchByCategory");
    	String searchByTextParameter = request.getParameter("searchByTextButton");
    	String category = request.getParameter("category");
    	if (searchByCategoryParameter != null && searchByCategoryParameter.equals("true"))
    	{
    		System.out.println("1");
    		this.category = category;
    		this.bID = request.getParameter("bID");
    		this.title = request.getParameter("title");
    		try{
    			bookBeanList = bookStore.searchByCategory(this.category);
    		}catch (Exception e){
    			e.printStackTrace();
    		}
    	}
    	else if (searchByTextParameter != null && searchByTextParameter.equals("true"))
    	{
    		System.out.println("2");
    		this.title = request.getParameter("searchByText");
    		try{
    			bookBeanList = bookStore.searchByTitle(this.title);
    		}catch (Exception e){
    			e.printStackTrace();
    		}
    	}
		
		//sample code to debug parameters, DONT DELETE this
//		System.out.println(this.category);
//		Enumeration<String> tmp = request.getParameterNames();
//		while (tmp.hasMoreElements())
//		{
//			String tmp1 = tmp.nextElement();
//			System.out.println(tmp1 + "=" + request.getParameter(tmp1));
//		}
		
		
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
					int price = item.getPrice();
					rw.println("<tr>");
					rw.print("<td>" + bID + "</td>");
					rw.print("<td>" + title + "</td>");
					rw.print("<td>" + category + "</td>");
					rw.print("<td>" + price + "</td>");
					rw.println("</tr>");
				}
				rw.println("</table");
			} catch (Exception e) {
				e.printStackTrace();
			}
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
		//testing the shopping cart
//		Enumeration<String> tmp = request.getParameterNames();
//		while (tmp.hasMoreElements())
//		{
//			String tmp1 = tmp.nextElement();
//			System.out.println(tmp1 + "=" + request.getParameter(tmp1));
//		}
		if(request.getParameter("cart").equals("true"))
			request.getRequestDispatcher("ShoppingCart").forward(request, response);	
		else if(request.getParameter("bookID")!= null){
			request.setAttribute("book", bookBeanList.get(request.getParameter("bookID")));//setting the chosen book to a request variable and grabbing it from shopping cart
			request.getRequestDispatcher("Book").forward(request, response);
		}
		else{
			queryTables(request);
			serveJSP(request, response);
		}
	}

}
