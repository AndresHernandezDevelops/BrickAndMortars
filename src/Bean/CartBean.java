package Bean;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import Bean.BookBean;

public class CartBean {

	private Map<BookBean, Integer> books;

	
	public CartBean(){
		this(new HashMap<BookBean, Integer>());
	}

	public CartBean(HashMap<BookBean, Integer> books) {
		this.books = books;
	}
	
	public void addBook(BookBean book){
		if(books.containsKey(book))
			books.put(book, books.get(book) + 1);
		else
			books.put(book, 1);
	}
	
	public void addBookAmount(BookBean book, int amount){
		if(books.containsKey(book))
			books.put(book, books.get(book) + amount);
		else
			books.put(book, amount);
	}
	
	public void removeBook(BookBean book){
		if(books.get(book)==1)
			books.remove(book, 1);
		else
			books.put(book, books.get(book) - 1);
	}
	
	public void setBookAmount(BookBean book, int amount) {
		if (amount > 0)
		books.put(book, amount);
	}
	
	public double getSubtotal(){
		double result = 0;
		Iterator <Map.Entry<BookBean, Integer>> it = books.entrySet().iterator();
		while(it.hasNext())
		{
			Map.Entry<BookBean, Integer> entry = it.next();
		    result = result + (entry.getKey().getPrice() * entry.getValue());
		}
		return result;
	}
	
	public double getTotal(){
		return this.getSubtotal() * 1.13;
	}
	
	
}
