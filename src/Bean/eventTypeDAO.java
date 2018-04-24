package Bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class eventTypeDAO {

	DataSource ds;
	
	
	public eventTypeDAO() throws ClassNotFoundException{
		try{
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		} catch (Exception e){
			e.printStackTrace();
		}
	}


	//this method needs to return a different type of value for the map, not a student bean. will fix later
	public Map<String, PurchaseBean> searchLastMonth(String lastMonth) throws SQLException{
		String query = String.format("select * from visitevent where day>='%s' and eventtype='PURCHASE'", lastMonth);
				/*("select B.TITLE, B.CATEGORY, count(E.BID) AS UNITS_SOLD "
				+ "from VISITEVENT E, BOOK B "
				+ "where E.BID=B.BID and E.DAY >= '%%%s%%' and E.EVENTTYPE = 'PURCHASE' "
				+ "group by b.title, b.category;", lastMonth);*/
		System.out.println(query);
		Map<String, PurchaseBean> rv = new HashMap<String, PurchaseBean>();
		Connection con = this.ds.getConnection();
		//System.out.println("hit");
		PreparedStatement p = con.prepareStatement(query);
		//System.out.println("hit1");
		ResultSet r = p.executeQuery();
		//System.out.println("hit2");
		while(r.next())
		{
			String day = r.getString("DAY");
			String bID = r.getString("BID");
			String eventType = r.getString("EVENTTYPE");
			rv.put(day + bID + eventType,new PurchaseBean(day, bID,eventType));
		}
		r.close();
		p.close();
		con.close();
		return rv;
	}
	
	public TreeMap<String, Integer> unitsSold() throws SQLException{
		String query = String.format("select bID, count(eventtype) as unitsSold from visitevent where eventtype='PURCHASE' group by bid order by unitsSold");
		System.out.println(query);
		TreeMap<String, Integer> rv = new TreeMap<String, Integer>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while(r.next())
		{
			String bID = r.getString("bID");
			int unitsSold = r.getInt("unitsSold");
			rv.put(bID, unitsSold);
		}
		r.close();
		p.close();
		con.close();
		return rv;
	}
}
