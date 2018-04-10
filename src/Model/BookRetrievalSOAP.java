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
		try {
			data = new BookDAO().retrieveall();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//gets book info from book id (required by project)
	public BookBean getProductInfo(String productID)
	{
		return data.get(productID);
	}
	
	//need to make it update the hashmap every time changes occur in the database
}
