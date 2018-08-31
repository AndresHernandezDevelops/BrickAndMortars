package Model;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import Bean.PurchaseOrderItemBean;
import Bean.PurchaseOrderItemListWrapper;
import DAO.PurchaseOrderItemDAO;

@Path("prrest")
public class ProductRetrievalREST {

	
	private static HashMap<Integer, List<PurchaseOrderItemBean>> data;
	private static ProductRetrievalREST instance = new ProductRetrievalREST();
	
	//do not call constructor call getInstance() to get the instance same as the REST
	public ProductRetrievalREST()
	{
		data = new HashMap<Integer, List<PurchaseOrderItemBean>>();
		try {
			data = new PurchaseOrderItemDAO().RetrieveAllPurchaseOrderItems();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//call to get instance of the object
	public static ProductRetrievalREST getInstance()
	{
		return instance;
	}
	
	@GET
	@Path("/orders/")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes("text/plain")
	public PurchaseOrderItemListWrapper getOrdersByPartNumber(@QueryParam("partNumber")int partNumber)
	{
		PurchaseOrderItemListWrapper outList = new PurchaseOrderItemListWrapper();
		List<PurchaseOrderItemBean> tmpList = data.get(partNumber);
		Iterator<PurchaseOrderItemBean> it = tmpList.iterator();
		outList.setId(partNumber);
		while(it.hasNext())
		{
			outList.getList().add(it.next());
		}
		
		return outList;
	}
	
	//call this to add a data entry
	public String addEntry(int sid, String bid, double sprice, int quan)
	{
		PurchaseOrderItemBean tmp = new PurchaseOrderItemBean(sid, bid, sprice, quan);
		if (data.containsKey(sid))
		{
			List<PurchaseOrderItemBean> val = data.get(sid);
			val.add(tmp);
		}
		else
		{
			LinkedList<PurchaseOrderItemBean> tlist = new LinkedList<PurchaseOrderItemBean>();
			tlist.add(tmp);
			data.put(sid, tlist);
		}
		return sid + "," + sid + " " + bid + " " + sprice + " " + quan;
	}
}
