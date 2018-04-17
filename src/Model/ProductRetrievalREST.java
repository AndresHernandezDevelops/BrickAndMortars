package Model;

import java.sql.SQLException;
import java.util.HashMap;
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
	public List<PurchaseOrderItemBean> getOrdersByPartNumber(@DefaultValue("1") @QueryParam("partNumber")int partNumber)
	{
		return data.get(partNumber);
	}
}
