package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Bean.UBStatsBean;
import DAO.UBStatsDAO;
import Model.BookStore;
import listener.Top10Books;

/**
 * Servlet implementation class Analytics
 */
@WebServlet("/Administrator")
public class Analytics extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BookStore bookStore;
	private Top10Books top10Books;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Analytics() throws ServletException{
    	this.init();
    }

	public void init() throws ServletException{
		try {
			this.bookStore = new BookStore();
			this.top10Books = new Top10Books();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
    
    private void export(HttpServletRequest request, HttpServletResponse response){
    	String f = "export/" + request.getSession().getId() + ".xml";
		String fileName = this.getServletContext().getRealPath("/" + f);
		request.setAttribute("fileName", f);
		String requestURL = request.getRequestURL().toString();
		String xmlLink = requestURL.substring(0, 38) + f;
		try{
			PrintWriter responseWriter = response.getWriter();
			bookStore.export("", fileName, this.getServletContext().getRealPath("/"));
			responseWriter.println("The exported XML can be found at: " 
					 + "<a href= " + xmlLink + ">"+ f +"</a>");
		}
		catch (Exception e){
			e.printStackTrace();
		}
    }   
    
    public void processParameters(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	String monthlyReportParameter = request.getParameter("monthlyReport");
    	String UBStatsParam = request.getParameter("UBStats");
    	
		if (monthlyReportParameter != null && monthlyReportParameter.equals("true"))
			export(request, response);
		else if (UBStatsParam != null && UBStatsParam.equals("true"))
			exportUBStats(request, response);
    }
    
	private void exportUBStats(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		Gson gson = new Gson();
		try {
			PrintWriter out = response.getWriter();
			List<UBStatsBean> list = bookStore.getUserStats();
			out.print(gson.toJson(list));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String target = "/Administrator.jspx";
		request.getRequestDispatcher(target).forward(request, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processParameters(request, response);
			//testing
//			top10Books.getTop10();
//			request.setAttribute("bID", "b004");
//			request.setAttribute("bID", "b004");
//			request.setAttribute("bID", "b004");
//			request.setAttribute("bID", "b004");
//			request.setAttribute("bID", "b004");
//			top10Books.getTop10();
			
	}
}
