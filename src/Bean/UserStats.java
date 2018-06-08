package Bean;

public class UserStats {

	private String user;
	private double spent;
	private String postalCode;
	
	public UserStats (String user, double spent, String postalCode)
	{
		this.user = user;
		this.spent = spent;
		this.postalCode = postalCode;
	}
	
	public UserStats ()
	{
		this("",0,"");
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public double getSpent() {
		return spent;
	}

	public void setSpent(double spent) {
		this.spent = spent;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
}
