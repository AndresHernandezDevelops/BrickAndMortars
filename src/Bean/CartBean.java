package Bean;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import Bean.BookBean;

public class CartBean {

	private Map<BookBean, Integer> books;
	private double subtotal;
	private double total;
	
//	public CartBean(){
//		//this(new Map<BookBean, Integer>);
//	}

	public CartBean(Map<BookBean, Integer> books) {
		this.books = books;
		this.subtotal = 0;
		this.total = 0;
	}
	
	public void addBook(BookBean book){
		if(books.containsKey(book))
			books.put(book, books.get(book) + 1);
		else
			books.put(book, 1);
	}
	
	public void removeBook(BookBean book){
		if(books.get(book)==1)
			books.remove(book, 1);
		else
			books.put(book, books.get(book) - 1);
	}
	
	public double getSubtotal(){
		float result = 0;
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
