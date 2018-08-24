package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import Bean.BookBean;
import Bean.CartBean;
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
	
	public int updatePO(String username, String firstname, String lastname, String status) throws SQLException{
		int count = 1;
		String insertPO = String.format("insert into PO (username, lname, fname, status) "
				+ "values ('" + username + "', '" + lastname + "' + '" + firstname + "' + '" + status + "')");
		String query = String.format("select count from PO ORDER BY DESC");
		System.out.println("this is what was inserted" + insertPO);
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(insertPO);
		p.executeUpdate();
		PreparedStatement q = con.prepareStatement(query);
		ResultSet r = q.executeQuery();
		if(r.next()) {
			count = r.getInt("COUNT");
		}
		else
			count = -1;
		r.close();
		p.close();
		con.close();
		return count;
	}
	
	public boolean updatePOItem (String username, CartBean currentCart, int count) throws SQLException
	{
		String insertPOItem = String.format("INSERT INTO POItem (username, bid, quantity, price, count) VALUES");
		Map<BookBean, Integer> cart = currentCart.getbooks();
		Set<BookBean> keys = cart.keySet();
		if(keys.isEmpty())
		{
			return false;
		}
		Iterator<BookBean> iterator = keys.iterator();
		while(iterator.hasNext()) {
			BookBean book = iterator.next();
			int qty = cart.get(book);
			String bid = book.getbID();
			double price = book.getPrice();
			insertPOItem += " ('" + username + "', '" + bid + "', "+ qty + ", " + price + ", " + count + "),";
		}
		insertPOItem = insertPOItem.substring(0, insertPOItem.length() - 1);
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(insertPOItem);
		p.executeUpdate();
		p.close();
		con.close();
		return true;
	}
}
