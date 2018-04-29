package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import Bean.ReviewBean;

public class ReviewDAO {

	
	DataSource ds;
	
	//constructor that initializes the datasource object
	public ReviewDAO() throws ClassNotFoundException{
		
		try{
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public Map<String, ReviewBean> searchReviews(String bookID) throws SQLException
	{
		String query = String.format("select * from REVIEW where bID = " + bookID);
		
		HashMap<String, ReviewBean> rv = new HashMap<String, ReviewBean>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while(r.next())
		{
			String bID = r.getString("BID");
			String user = r.getString("NAME");
			String time = r.getString("DAY");
			String review = r.getString("REVIEW");
			int rating = r.getInt("RATING");
			
			rv.put(bID + user + time,new ReviewBean(bID, user, time, review, rating));
		}
		r.close();
		p.close();
		con.close();
		return rv;
	}
	
	public void addReview(String bID, String username, String time, String review, int rating) throws SQLException
	{
		String query = String.format("insert into review (bid, username, day, review, rating)"
				+ " VALUES ('" + bID + "', '" + username + "', '" + time + "', '" + review + "', '" +  rating + "')");
		System.out.println("debugging the addReview query: " + query);
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		p.executeUpdate();
		p.close();
		con.close();
	}
}
