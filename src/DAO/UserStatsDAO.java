package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import Bean.BookBean;
import Bean.UserStatsWrapper;

public class UserStatsDAO {

	DataSource ds;
	
	public UserStatsDAO()
	{
		try{
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public UserStatsWrapper getUserStats () throws SQLException
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
		return null;
	}
}
