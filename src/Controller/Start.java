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
		
		//sample code to debug parameters, dont delete this
		System.out.println(this.category);
		Enumeration<String> tmp = request.getParameterNames();
		while (tmp.hasMoreElements())
		{
			String tmp1 = tmp.nextElement();
			System.out.println(tmp1 + "=" + request.getParameter(tmp1));
		}
		//future implementation; fetch any other parameters offered by the request object.
		
		
    }

    //prints out to the jspx using ajax 
    private void serveJSP(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
		String searchByCategoryParameter = request.getParameter("searchByCategory");
		String searchByTextParameter = request.getParameter("searchByTextButton");
		if (searchByCategoryParameter != null && searchByCategoryParameter.equals("true")) {
			
			request.setAttribute("categoryResultList", bookBeanList);
			
			//server-side population of results, will get rid of it once narbeh we figure out the json alterntive
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
		else if(searchByTextParameter != null && searchByTextParameter.equals("true")){
			request.setAttribute("categoryResultList", bookBeanList);
			
			//server-side population of results, will get rid of it once narbeh we figure out the json alterntive
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
    }
//    
//    private void export(HttpServletRequest request, HttpServletResponse response){
//    	String f = "export/" + request.getSession().getId() + ".xml";
//		String fileName = this.getServletContext().getRealPath("/" + f);
//		request.setAttribute("fileName", f);
//		String target = "/Done.jspx";
//		//storing the filename in the context level variable for later use
//		//this.getServletContext().setAttribute(FILENAME, fileName);
//		try{
//			bookStore.export("", fileName, this.getServletContext().getRealPath("/"));
////			<a href="${pageScope.request.contextPath}${requestScope['fileName']}">${requestScope['fileName']}</a>
//			request.getRequestDispatcher(target).forward(request, response);
//		}
//		catch (Exception e){
//			e.printStackTrace();
//		}
//    }

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
		queryTables(request);
		serveJSP(request, response);
	}

}
