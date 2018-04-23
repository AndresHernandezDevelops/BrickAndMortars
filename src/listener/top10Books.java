package listener;

import java.util.Collection;
import java.util.Iterator;
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

	
	private TreeMap <String, Integer> bookIndices = new TreeMap<String, Integer>();
	private TreeMap <Integer, String> bookFrequencies = new TreeMap<Integer, String>();
	
	private BookStore bookStore;
    /**
     * Default constructor. 
     */
    public top10Books() throws ServletException{
    	try {
			this.bookStore = new BookStore();
			TreeMap <String, Integer> unitsSold = this.bookStore.unitsSold();
			Collection <String> keys = unitsSold.keySet();
			Collection <Integer> values = unitsSold.values();
			Iterator<String> keysIt = keys.iterator();
			Iterator<Integer> valuesIt = values.iterator();
			Integer index = 0;
			while(keysIt.hasNext()){
				String bID = keysIt.next();
				bookFrequencies.put(valuesIt.next(), bID);
				bookIndices.put(bID, index);
				index++;
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
