package DAO;

import Bean.*;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BookDAO {
	
	DataSource ds;
	BookBean bb;
	
	//constructor that initializes the datasource object
	public BookDAO() throws ClassNotFoundException{
		
		try{
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public HashMap<String, BookBean> retrieval () throws SQLException
	{
		String query = String.format("select * from BOOK");
		
		HashMap<String, BookBean> rv = new HashMap<String, BookBean>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while(r.next())
		{
			String bID = r.getString("BID");
			String title = r.getString("TITLE");
			int price = r.getInt("PRICE");
			String category = r.getString("CATEGORY");
			rv.put(bID,new BookBean(bID, title, category, price));
		}
		r.close();
		p.close();
		con.close();
		return rv;
	}
	
	//retrieves all the books which belong to a certain category from the actual database
	public Map<String, BookBean> searchByCategory(String category) throws SQLException{
		String query = String.format("select * from BOOK where category like '%%%s%%'", category);
		
		Map<String, BookBean> rv = new HashMap<String, BookBean>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while(r.next())
		{
			String bID = r.getString("BID");
			String title = r.getString("TITLE");
			int price = r.getInt("PRICE");
			rv.put(bID,new BookBean(bID, title, category, price));
		}
		r.close();
		p.close();
		con.close();
		return rv;
		
	}
	
	//this method needs to return a different type of value for the map, not a student bean. will fix later
	public Map<String, BookBean> searchLastMonth(String lastMonth) throws SQLException{
		String query = String.format("select B.TITLE, B.CATEGORY, count(E.BID) AS UNITS_SOLD "
				+ "from VISITEVENT E, BOOK B "
				+ "where E.BID=B.BID and E.DAY >= '%%%s%%' and E.EVENTTYPE = 'PURCHASE' "
				+ "group by b.title, b.category;", lastMonth);
		
		Map<String, BookBean> rv = new HashMap<String, BookBean>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while(r.next())
		{
			String bID = r.getString("BID");
			String title = r.getString("TITLE");
			int price = r.getInt("PRICE");
			rv.put(bID,new BookBean(bID, title, lastMonth, price));
		}
		r.close();
		p.close();
		con.close();
		return rv;
		
	}
}