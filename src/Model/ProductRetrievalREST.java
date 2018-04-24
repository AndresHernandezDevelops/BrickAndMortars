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

	
	HashMap<Integer, List<PurchaseOrderItemBean>> data;
	
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
	
	@GET
	@Path("/orders/")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes("text/plain")
	public PurchaseOrderItemListWrapper getOrdersByPartNumber(@DefaultValue("1") @QueryParam("partNumber")int partNumber)
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
	
	@POST
	@Path("/orders/addEntry/")
	@Consumes("text/plain")
	public String addEntry(@FormParam("id") String sid, @FormParam("bid") String bid, @FormParam("price") String sprice)
	{
		System.out.println(sid);
		System.out.println(bid);
		System.out.println(sprice);
		int id = Integer.parseInt(sid);
		int price = Integer.parseInt(sprice);
		PurchaseOrderItemBean tmp = new PurchaseOrderItemBean(id, bid, price);
		if (data.containsKey(id))
		{
			List<PurchaseOrderItemBean> val = data.get(id);
			val.add(tmp);
		}
		else
		{
			LinkedList<PurchaseOrderItemBean> tlist = new LinkedList<PurchaseOrderItemBean>();
			tlist.add(tmp);
			data.put(id, tlist);
		}
		System.out.println(data.containsKey(id));
		return id + "," + id + " " + bid + " " + price;
	}
}
