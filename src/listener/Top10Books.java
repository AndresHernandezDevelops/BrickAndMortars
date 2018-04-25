package listener;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.annotation.WebListener;




import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
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
	
	private TreeMap <String, Integer> unitsSold = new TreeMap<String, Integer>();
	private TreeMap <Integer, ArrayList<String>> bookFrequencies = new TreeMap<Integer, ArrayList<String>>();
	
	private BookStore bookStore;
    /**
     * Default constructor. 
     */
	public Top10Books() throws ServletException{
    	try {
    		System.out.println("hit the constructor");
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
				if(currentFreq==lastFreq) {
					bookList.add(bID);
				}
				else {
					bookFrequencies.put(lastFreq, bookList);//first round it will add the empty keyset < 0 , empty arraylist>
					bookList.clear();//nuke the booklist
					lastFreq = currentFreq;//overwrite the last val
					bookList.add(bID);
				}
			}
			//in the loop we dealt with the values at the previous iteration, now we add the last remaining list (current values)
			bookFrequencies.put(lastFreq, bookList);
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
    	System.out.println("hit the removed");
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletRequestAttributeListener#attributeAdded(ServletRequestAttributeEvent)
     */
    public void attributeAdded(ServletRequestAttributeEvent arg0)  {
    	System.out.println("hit the added");
         if(arg0.getName().equals("bkID")) {
        	 String bID = arg0.getValue().toString();
        	 bookFrequencies.get(unitsSold.get(bID)).remove(bID); 
        	 int previousFreq = unitsSold.get(bID);
        	 unitsSold.put(bID, previousFreq + 1);
        	 if(bookFrequencies.containsKey(previousFreq + 1)) {//when the prev freq was already the highest frequency
        		 ArrayList <String> bookList = new ArrayList<String>();
        		 bookList.add(bID);
        		 bookFrequencies.put(previousFreq + 1, bookList);
        	 }else 
        		 bookFrequencies.get(previousFreq + 1).add(bID);       	 
         }
    }

	/**
     * @see ServletRequestAttributeListener#attributeReplaced(ServletRequestAttributeEvent)
     */
    public void attributeReplaced(ServletRequestAttributeEvent arg0)  {
    	System.out.println("hit the replaced");
    	 if(arg0.getName().equals("bkID")) {
       	 String bID = arg0.getValue().toString();
       	 bookFrequencies.get(unitsSold.get(bID)).remove(bID); 
       	 int previousFreq = unitsSold.get(bID);
       	 unitsSold.put(bID, previousFreq + 1);
       	 if(bookFrequencies.containsKey(previousFreq + 1)) {//when the prev freq was already the hgihest frequency
       		 ArrayList <String> bookList = new ArrayList<String>();
       		 bookList.add(bID);
       		 bookFrequencies.put(previousFreq + 1, bookList);
       	 }else 
       		 bookFrequencies.get(previousFreq + 1).add(bID);
        }
    }
    
    public ArrayList<String> getTop10(){
    	ArrayList<String> top10 = new ArrayList<String>();
    	TreeMap <Integer, ArrayList<String>> top10Frequencies = (TreeMap<Integer, ArrayList<String>>) this.bookFrequencies.clone();
    	int j = 0;
    	for(int i = 0; i < 10;) {
    		Map.Entry<Integer, ArrayList<String>> topKey =  top10Frequencies.pollLastEntry();
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
    	return top10;
    }
	
}
