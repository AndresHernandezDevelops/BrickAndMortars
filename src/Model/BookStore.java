package Model;

import java.util.Map;

import DAO.*;
import Bean.*;

public class BookStore {

	//private DAO object used for accessing the database
	private BookDAO books;
	
	//constructor
	public BookStore() throws ClassNotFoundException
	{
		books = new BookDAO();	
	}
	
	//a method used to retrieve the books by category from the DAO object.
	public Map<String, BookBean> searchByCategory(String category) throws Exception
	{
		try{
			return books.searchByCategory(category);
		}
		catch (Exception e)
		{
			throw new Exception();
		}
	}
	
	
}
