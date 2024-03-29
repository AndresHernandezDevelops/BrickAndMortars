package Model;

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import DAO.*;
import Bean.*;

public class BookStore {

	//private DAO objects used for accessing the database
	private BookDAO books;
	private EventTypeDAO events;
	private ReviewDAO reviews;
	private UBStatsDAO uStats;
	
	//constructor
	public BookStore() throws ClassNotFoundException
	{
		books = new BookDAO();
		events = new EventTypeDAO();
		reviews = new ReviewDAO();
		uStats = new UBStatsDAO();
	}
	
	public List<UBStatsBean> getUserStats() throws Exception
	{
		try {
			return uStats.getUserStats();
		}
		catch (Exception e)
		{
			throw new Exception();
		}
	}
	
	//main page search by category
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
	
	//main page search bar
	public Map<String, BookBean> searchByTitle(String title) throws Exception
	{
		try{
			return books.searchByTitle(title);
		}
		catch (Exception e)
		{
			throw new Exception();
		}
	}
	
	//returns the reviews for the book page
	public Map<String, ReviewBean> searchReviews(String bID) throws Exception
	{
		try{
			return reviews.searchReviews(bID);
		}
		catch (Exception e)
		{
			throw new Exception();
		}
	}
	
	public void addReview(String bID, String username, String review, int rating) throws Exception
	{
		try{
			//get the date to the second in the form of YYYYmmddHHmmss and pass it to this method as well
			Calendar cal = Calendar.getInstance();
			Date result = cal.getTime();
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			String time = dateFormat.format(result).toString();
			System.out.println("testing the add review date and time: " + time);
			reviews.addReview(bID, username, time, review, rating);
		}
		catch (Exception e)
		{
			throw new Exception();
		}
	}
	
	//will call this in the export to report the books sold in the past month
	public Map<String, PurchaseBean> searchLastMonth() throws Exception
	{
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		Date result = cal.getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		String lastMonth = dateFormat.format(result).toString();
		try{
			return events.searchLastMonth(lastMonth);
		}
		catch (Exception e)
		{
			//e.printStackTrace(Syste);
			throw new Exception();
		}
	}
	
	//book report for the admins
	public void export(String lastMonth, String filename, String f) throws Exception{
		List<PurchaseBean> list = new ArrayList<PurchaseBean>();
		Collection<PurchaseBean> sbean = this.searchLastMonth().values();
		Iterator<PurchaseBean> item = sbean.iterator();
		while(item.hasNext())
		{
			list.add(item.next());
		}
		PurchaseListWrapper lw = new PurchaseListWrapper(list);
		
		JAXBContext jc = JAXBContext.newInstance(lw.getClass());
		Marshaller marshaller = jc.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
		StringWriter sw = new StringWriter();
		sw.write("\n");
		
		//debugging the xsd
		/*SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = sf.newSchema(new File(f+"/export/SIS.xsd"));
		marshaller.setSchema(schema);*/
		
		
		marshaller.marshal(lw, new StreamResult(sw));

		//System.out.println(sw.toString()); // for debugging
		//System.out.println(filename);
		FileWriter fw = new FileWriter(filename);
		fw.write(sw.toString());
		fw.close();
	}
	
	public TreeMap<String, Integer> unitsSold() throws Exception
	{
		try{
			return events.unitsSold();
		}
		catch (Exception e)
		{
			throw new Exception();
		}
	}
	
	
}
