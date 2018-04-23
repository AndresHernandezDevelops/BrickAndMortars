package listener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import Model.BookStore;

/**
 * Application Lifecycle Listener implementation class top10Books
 *
 */
@WebListener
public class top10Books implements HttpSessionAttributeListener {

	
	private TreeMap <String, Integer> unitsSold = new TreeMap<String, Integer>();
	private TreeMap <Integer, ArrayList<String>> bookFrequencies = new TreeMap<Integer, ArrayList<String>>();
	
	private BookStore bookStore;
    /**
     * Default constructor. 
     */
    public top10Books() throws ServletException{
    	try {
			this.bookStore = new BookStore();
			unitsSold = this.bookStore.unitsSold();//comes sorted by units sold as per the query in the DAO
			ArrayList <String> bookList = new ArrayList<String>();
			Collection <String> keys = unitsSold.keySet();
			Collection <Integer> values = unitsSold.values();
			Iterator<String> keysIt = keys.iterator();
			Iterator<Integer> valuesIt = values.iterator();
			int currentFreq;
			int lastFreq=0;
			String bID;
			while(keysIt.hasNext()){
				bID = keysIt.next();
				currentFreq = valuesIt.next();
				if(currentFreq==lastFreq)
					bookList.add(bID);
				else{
					bookFrequencies.put(lastFreq, bookList);//first round it will add the empty keyset < 0 , empty arraylist>
					bookList.clear();//nuke the booklist
					lastFreq = currentFreq;//overwrite the last val
					bookList.add(bID);
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    }
	
}
