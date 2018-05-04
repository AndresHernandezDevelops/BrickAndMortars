package Bean;


public class BookBean {
	
	//declaring private variables which will hold values of the book's properties.
	private String bID;
	private String title;
	private String category;
	private String thumbnail;
	private double price;
	
	//default constructor needed for webservices
	public BookBean()
	{
		this("","","",0,"");
	}
	
	//constructor
	public BookBean(String bID, String title, String category, double price, String thumbnail)
	{
		this.bID = bID;
		this.title = title;
		this.category = category;
		this.price = price;
		this.thumbnail = thumbnail;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	
	
}