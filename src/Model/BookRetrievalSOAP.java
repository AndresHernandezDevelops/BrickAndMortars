package Model;

import java.sql.SQLException;
import java.util.HashMap;

import Bean.BookBean;
import DAO.BookDAO;

public class BookRetrievalSOAP {

	//data from database
	private static HashMap<String, BookBean> data;
	private static String delimiter = ":";
	
	//constructor
	public BookRetrievalSOAP()
	{
		data = new HashMap<String, BookBean>();
		try {
			data = new BookDAO().retrieveAll();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//data.put("test1", new BookBean("test1", "test2", "test3", 12));
	}
	
	//gets book info from book id (required by project)
	//returns a string seprated by delimiter ':'
	public String getProductInfo(String productID)
	{
		BookBean tmp = data.get(productID);
		String bid = tmp.getbID();
		String cate = tmp.getCategory();
		int price = tmp.getPrice();
		
		if (bid.contains(delimiter))
		{
			String[] tmpchar = bid.split(delimiter);
			StringBuilder build = new StringBuilder();
			build.append(tmpchar[0]);
			int i = 1;
			for (i = 1; i < tmpchar.length; i++)
			{
				build.append("\\");
				build.append(tmpchar[i]);
			}
			cate = build.toString();
		}
		
		if (bid.contains(delimiter))
		{
			String[] tmpchar = cate.split(delimiter);
			StringBuilder build = new StringBuilder();
			build.append(tmpchar[0]);
			int i = 1;
			for (i = 1; i < tmpchar.length; i++)
			{
				build.append("\\");
				build.append(tmpchar[i]);
			}
			cate = build.toString();
		}
		
		String out = bid + delimiter + cate + delimiter + price;
		return out;
	}
	
	//need to make it update the hashmap every time changes occur in the database
}
