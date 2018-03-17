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
	
	//maps to hold the list of results
	Map<String, BookBean> bookBeanList;
	
	//rpivate variables to hold search keys from the jspx
	private String bID;
	private String title;
	private String category;
	//private int price;
	
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
    	this.category = request.getParameter("namePrefix");
		this.bID = request.getParameter("bID");
		this.title = request.getParameter("title");
		//future implementation; fetch any other parameters offered by the request object.
		
		try{
			bookBeanList = bookStore.searchByCategory(this.category);
		}catch (Exception e){
			e.printStackTrace();
		}
    }

    //prints out to the jspx using ajax 
    private void serveJSP(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
		String searchByCategoryParameter = request.getParameter("searchByCategory");
		
		if (searchByCategoryParameter != null && searchByCategoryParameter.equals("true")) {
			//System.out.println("Just hit the Search by category button");//debugging
			
			PrintWriter rw = response.getWriter();
			try {
				Collection<BookBean> bbean = bookBeanList.values();
				Iterator<BookBean> bookIterator = bbean.iterator();
				rw.println("<table border='1'>");
				rw.println("<tr>");
				rw.println("<td>Id</td>");
				rw.println("<td>Name</td>");
				rw.println("<td>Credits taken</td>");
				rw.println("<td>Credits to graduate</td>");
				rw.println("<td>Credits end of term</td>");
				rw.println("</tr>");
				while (bookIterator.hasNext())
				{
					BookBean item = bookIterator.next();
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
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String target = "/MainPage.jspx";
		request.getRequestDispatcher(target).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		queryTables(request);
		serveJSP(request, response);;
	}

}
