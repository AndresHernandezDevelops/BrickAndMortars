package DAO;

import Bean.LoginBean;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class LoginDAO {

	DataSource ds;
	
	public LoginDAO() throws ClassNotFoundException{
		
		try{
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public boolean lookup(String username, String password) throws SQLException{
		String query = String.format("select * from LOGIN where username = " + username + " and password = " + password);
		System.out.println("the login lookup query is: " + query);
		boolean result = false;
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		System.out.println("the login lookup number of results is: " + r.getFetchSize());
		if(r.getFetchSize() == 1)
			result = true;
		r.close();
		p.close();
		con.close();
		return result;	
	}
	
}
