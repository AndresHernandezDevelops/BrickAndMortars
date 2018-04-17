package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import Bean.PurchaseOrderItemBean;

public class PurchaseOrderItemDAO {
	
	DataSource ds;

	public PurchaseOrderItemDAO() {
		super();
		try{
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	public HashMap<Integer, PurchaseOrderItemBean>RetrievePurchaseOrderItemsByPart (int part) throws SQLException
	{
		String query = String.format("select * from POItem where id=%d", part);
		
		HashMap<Integer, PurchaseOrderItemBean> rv = new HashMap<Integer, PurchaseOrderItemBean>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while(r.next())
		{
			String bid = r.getString("BID");
			int id = r.getInt("ID");
			int price = r.getInt("PRICE");
			rv.put(id,new PurchaseOrderItemBean(id, bid, price));
		}
		r.close();
		p.close();
		con.close();
		return rv;
	}
	
	public HashMap<Integer, List<PurchaseOrderItemBean>>RetrieveAllPurchaseOrderItems () throws SQLException
	{
		String query = String.format("select * from POItem");
		
		HashMap<Integer, List<PurchaseOrderItemBean>> rv = new HashMap<Integer, List<PurchaseOrderItemBean>>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while(r.next())
		{
			String bid = r.getString("BID");
			int id = r.getInt("ID");
			int price = r.getInt("PRICE");
			if (rv.containsKey(id))
			{
				List<PurchaseOrderItemBean> tmp = rv.get(id);
				tmp.add(new PurchaseOrderItemBean(id, bid, price));
			}
			else
			{
				LinkedList<PurchaseOrderItemBean> tmp = new LinkedList<PurchaseOrderItemBean>();
				tmp.add(new PurchaseOrderItemBean(id, bid, price));
				rv.put(id, tmp);
			}
		}
		r.close();
		p.close();
		con.close();
		return rv;
	}
}
