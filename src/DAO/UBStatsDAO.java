package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import Bean.UBStatsBean;

public class UBStatsDAO {
	DataSource ds;

	public UBStatsDAO() {
		try{
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public List<UBStatsBean> getUserStats() throws SQLException
	{
		String query = String.format("select '*****' as USERNAME, A.ZIP, SUM(P.PRICE) AS SPENT\r\n" + 
				"from ADDRESS as A, POITEM as P\r\n" + 
				"WHERE A.USERNAME = P.USERNAME\r\n" + 
				"GROUP BY A.USERNAME, A.ZIP");
		
		List<UBStatsBean> list = new LinkedList<UBStatsBean>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while(r.next())
		{
			String username = r.getString("USERNAME");
			String zip = r.getString("ZIP");
			int spent = r.getInt("SPENT");
			UBStatsBean uStats = new UBStatsBean(username, zip, spent);
			list.add(uStats);
		}
		
		return list;
	}
}
