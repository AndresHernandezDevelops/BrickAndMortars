package Bean;

public class BookBean {
	
	//declaring private variables which will hold values of the book's properties.
	private String bID;
	private String title;
	private String category;
	private int price;
	
	//default constructor needed for webservices
	public BookBean()
	{
		this("","","",0);
	}
	
	//constructor
	public BookBean(String bID, String title, String category, int price)
	{
		this.bID = bID;
		this.title = title;
		this.category = category;
		this.price = price;
	}
	
	//getters and setters
	public String getbID() {
		return bID;
	}

	public void setbID(String bID) {
		this.bID = bID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
