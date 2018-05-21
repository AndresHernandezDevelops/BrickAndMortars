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
		String query = String.format("select * from LOGIN where username = '" + username + "'");
		System.out.println("the login lookup query is: " + query);
		boolean result = false;
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		System.out.println("the login lookup number of results is: " + r.getFetchSize());
		if(r.next())
			result = true;
		r.close();
		p.close();
		con.close();
		return result;	
	}
	
	public boolean register(String username, String password, String firstname, String lastname, String address, String postalcode, String province, String country, String phone) throws SQLException{
		boolean result = false;
		if(!this.lookup(username, password)) {
			String loginCommand = String.format("insert into login (USERNAME, PASSWORD) "
					+ "values ('" + username + "', '" + password + "')");
			String addressCommand = String.format("insert into address (USERNAME, STREET, PROVINCE, COUNTRY, ZIP, PHONE, FIRSTNAME, LASTNAME) "
					+ "values ('" + username + "', '" + address + "', '" + province + "', '" + country + "', '" + postalcode + "', '" + phone + "', '" + firstname + "', '" + lastname + "')");
			System.out.println("the register command is: " + loginCommand + " ---- as well as: " + addressCommand);
			
			Connection con = this.ds.getConnection();
			PreparedStatement p = con.prepareStatement(loginCommand);
			p.executeUpdate();
			p = con.prepareStatement(addressCommand);
			p.executeUpdate();
			//r.close();
			p.close();
			con.close();
			return true;
		}
		return result;	
	}
	
}
