package Bean;

public class ReviewBean {

	
	private String bID;
	private String name;
	private String review;
	private String time;
	private int rating;
	
	public ReviewBean(){
		this("","","","",0);
	}
	
	public ReviewBean(String bID, String name, String review, String time, int rating) {
		super();
		this.bID = bID;
		this.name = name;
		this.review = review;
		this.time = time;
		this.rating = rating;
	}

	public String getbID() {
		return bID;
	}

	public void setbID(String bID) {
		this.bID = bID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	
}
