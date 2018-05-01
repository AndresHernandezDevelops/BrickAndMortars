package Model;

import java.sql.SQLException;
import java.util.HashMap;

import Bean.BookBean;
import DAO.BookDAO;

public class BookRetrievalSOAP {

	//data from database
	private static HashMap<String, BookBean> data;
	private static String delimiter = ":";
	private static BookRetrievalSOAP instance;
	
	//constructor DO NOT EVER CALL
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
		BookRetrievalSOAP.instance = this;
		//data.put("test1", new BookBean("test1", "test2", "test3", 12));
	}

	//call this to get an object of the object that is the same as the SOAP
	public static BookRetrievalSOAP getInstance()
	{
		return instance;
	}
	
	//call when data needs to be updated
	public void updatedata (String bid, String title, String cate, int price, String thumb)
	{
		BookBean tmp = new BookBean(bid, title, cate, price, thumb);
		data.put(bid, tmp);
	}
	
	//gets book info from book id (required by project)
	//returns a string seprated by delimiter ':'
	public String getProductInfo(String productID)
	{
		BookBean tmp = data.get(productID);
		String bid = tmp.getbID();
		String title = tmp.getTitle();
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
		
		if (title.contains(delimiter))
		{
			String[] tmpchar = title.split(delimiter);
			StringBuilder build = new StringBuilder();
			build.append(tmpchar[0]);
			int i = 1;
			for (i = 1; i < tmpchar.length; i++)
			{
				build.append("\\");
				build.append(tmpchar[i]);
			}
			title = build.toString();
		}
		
		if (cate.contains(delimiter))
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
		
		String out = bid + delimiter + title + delimiter + cate + delimiter + price;
		return out;
	}
	
	//need to make it update the hashmap every time changes occur in the database (made method u guys need to apply it)
}
