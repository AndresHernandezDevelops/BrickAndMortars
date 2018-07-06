package Bean;

import java.util.HashMap;

import DAO.BookDAO;

public class BookInstances {

	private static BookInstances instance = new BookInstances();
	private HashMap<String, BookBean> bookInstances;
	private BookDAO bDAO;
	
	private BookInstances()
	{
		try {
			this.bDAO = new BookDAO();
			this.bookInstances = this.bDAO.retrieveAll();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static BookInstances getInstance()
	{
		return instance;
	}
	
	public BookBean getBook(String bid)
	{
		return this.bookInstances.get(bid);
	}
}
