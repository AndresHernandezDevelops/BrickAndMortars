package Model;

import java.sql.SQLException;
import java.util.HashMap;

import Bean.BookBean;
import DAO.BookDAO;

public class BookRetrievalSOAP {

	//data from database
	HashMap<String, BookBean> data;
	
	//constructor
	public BookRetrievalSOAP()
	{
		data = new HashMap<String, BookBean>();
		try {
			data = new BookDAO().retrieveall();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		data.put("test1", new BookBean("test1", "test2", "test3", 12));
	}
	
	//gets book info from book id (required by project)
	public BookBean getProductInfo(String productID)
	{
		return data.get(productID);
	}
	
	//need to make it update the hashmap every time changes occur in the database
}
