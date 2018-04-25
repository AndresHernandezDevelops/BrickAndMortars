package listener;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.annotation.WebListener;




import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
public class Top10Books implements ServletRequestAttributeListener {
	
	private static TreeMap <String, Integer> unitsSold;
	private static TreeMap <Integer, List<String>> bookFrequencies;
	
	private BookStore bookStore;
    /**
     * Default constructor. 
     */
	public Top10Books() throws ServletException{
    	try {
    		unitsSold = new TreeMap<String, Integer>();
    		bookFrequencies = new TreeMap<Integer, List<String>>();
			this.bookStore = new BookStore();
			unitsSold = this.bookStore.unitsSold();//comes sorted by units sold as per the query in the DAO
			
			//ArrayList <String> bookList = new ArrayList<String>();
			Collection <String> keys = unitsSold.keySet();
			Iterator<String> keysIt = keys.iterator();
			int currentFreq;
			int lastFreq=0;
			String bID;
			while(keysIt.hasNext()){
				bID = keysIt.next();
				int freq = unitsSold.get(bID);
				if (bookFrequencies.containsKey(freq))
				{
					List<String> list = bookFrequencies.get(freq);
					list.add(bID);
				}
				else
				{
					LinkedList<String> list = new LinkedList<String>();
					list.add(bID);
					bookFrequencies.put(freq, list);
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
     * @see ServletRequestAttributeListener#attributeRemoved(ServletRequestAttributeEvent)
     */
    public void attributeRemoved(ServletRequestAttributeEvent arg0)  { 
    }

	/**
     * @see ServletRequestAttributeListener#attributeAdded(ServletRequestAttributeEvent)
     */
    public void attributeAdded(ServletRequestAttributeEvent arg0)  {
    	//System.out.println("hit the added: "  + arg0.getName() + " " + arg0.getValue());
         if(arg0.getName().equals("bID")) {
        	 String bID = arg0.getValue().toString();
        	 int previousFreq = unitsSold.get(bID);
        	 unitsSold.put(bID, previousFreq + 1);
        	 if(bookFrequencies.containsKey(previousFreq + 1)) {//when the prev freq was already the highest frequency
        		 List<String> list = bookFrequencies.get(previousFreq + 1);
        		 list.add(bID);
        	 }else 
        	 {   
	        	 ArrayList <String> bookList = new ArrayList<String>();
	    		 bookList.add(bID);
	    		 bookFrequencies.put(previousFreq + 1, bookList);
        	 }
    		 List<String> list = bookFrequencies.get(previousFreq);
    		 int index = list.indexOf(bID);
    		 //System.out.println(previousFreq + 1);
    		 list.remove(index);
    		 //System.out.println(bookFrequencies.get(previousFreq + 1).toString());
         }
    }

	/**
     * @see ServletRequestAttributeListener#attributeReplaced(ServletRequestAttributeEvent)
     */
    public void attributeReplaced(ServletRequestAttributeEvent arg0)  {
    	//System.out.println("hit the replaced: "  + arg0.getName() + " " + arg0.getValue());
    	 if(arg0.getName().equals("bID")) {
	       	 String bID = (String) arg0.getServletRequest().getAttribute("bID");
	       	 //bookFrequencies.get(unitsSold.get(bID)).remove(bID); 
	       	 int previousFreq = unitsSold.get(bID);
	       	 unitsSold.put(bID, previousFreq + 1);
		       	if(bookFrequencies.containsKey(previousFreq + 1)) {//when the prev freq was already the highest frequency
		   		 List<String> list = bookFrequencies.get(previousFreq + 1);
		   		 list.add(bID);
		   	 }else 
		   	 {   
		       	 ArrayList <String> bookList = new ArrayList<String>();
		   		 bookList.add(bID);
		   		 bookFrequencies.put(previousFreq + 1, bookList);
		   	 }
			 List<String> list = bookFrequencies.get(previousFreq);
			 int index = list.indexOf(bID);
			 //System.out.println(previousFreq + 1);
			 list.remove(index);
			 //System.out.println(bookFrequencies.get(previousFreq + 1).toString());
    	 }
    	 
    }
    
    public LinkedList<String> getTop10(){
    	LinkedList<String> top10 = new LinkedList<String>();
    	TreeMap <Integer, List<String>> top10Frequencies = (TreeMap<Integer, List<String>>) this.bookFrequencies.clone();
    	int j = 0;
    	for(int i = 0; i < 10;) {
    		Map.Entry<Integer, List<String>> topKey =  top10Frequencies.pollLastEntry();
    		if(topKey.getValue().size() == 0 || j >= 10)
    			break;
    		i = i + topKey.getValue().size();
    		Iterator<String> it = topKey.getValue().iterator();
    		while(it.hasNext()) {
    			top10.add(it.next());
    			j++;
    			if(j >= 10)
    				break;
    		}
    	}
    	//System.out.println(top10.toString());
    	return top10;
    }
	
}
