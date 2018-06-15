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
	
	//constructor that initializes the datasource object
	public BookDAO() throws ClassNotFoundException{
		
		try{
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public BookBean getBookByBid (String bid) throws SQLException
    {
		String query = String.format("select * from BOOK where bid = '%s'", bid);

        Connection con = this.ds.getConnection();
        PreparedStatement p = con.prepareStatement(query);
        ResultSet r = p.executeQuery();
        if (r.next())
        {
            String bID = r.getString("BID");
            String title = r.getString("TITLE");
            int price = r.getInt("PRICE");
            String category = r.getString("CATEGORY");
            String thumbnail = r.getString("THUMBNAIL");
            return new BookBean(bID, title, category, price, thumbnail);
        }

        return null;
    }
	
	public HashMap<String, BookBean> retrieveAll () throws SQLException
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
			String thumbnail = r.getString("THUMBNAIL");
			rv.put(bID,new BookBean(bID, title, category, price, thumbnail));
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
			String thumbnail = r.getString("THUMBNAIL");
			rv.put(bID,new BookBean(bID, title, category, price, thumbnail));
		}
		r.close();
		p.close();
		con.close();
		return rv;	
	}
	
	public Map<String, BookBean> searchByTitle(String title) throws SQLException{
		String query = String.format("select * from BOOK where title like '%%%s%%'", title);
		
		Map<String, BookBean> rv = new HashMap<String, BookBean>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while(r.next())
		{
			String bID = r.getString("BID");
			String category = r.getString("CATEGORY");
			String titleDB = r.getString("TITLE");
			int price = r.getInt("PRICE");
			String thumbnail = r.getString("THUMBNAIL");
			rv.put(bID,new BookBean(bID, titleDB, category, price, thumbnail));
		}
		r.close();
		p.close();
		con.close();
		return rv;	
	}
	
}