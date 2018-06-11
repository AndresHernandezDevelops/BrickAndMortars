package Bean;

public class UBStatsBean {
	String username;
	String zip;
	int spent;
	
	public UBStatsBean(String username, String zip, int spent) {
		super();
		this.username = username;
		this.zip = zip;
		this.spent = spent;
	}
	
	public UBStatsBean()
	{
		this("","",0);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public int getSpent() {
		return spent;
	}

	public void setSpent(int spent) {
		this.spent = spent;
	}
}
