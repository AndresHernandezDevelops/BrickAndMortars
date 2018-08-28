package Bean;

import javax.xml.bind.annotation.XmlType;

//@XmlRootElement(name="PurchaseOrderItem")
@XmlType(propOrder={"bid", "id", "price", "quan"})
public class PurchaseOrderItemBean {

	/* Items on order
	* id : purchase order id
	* bid: unique identifier of Book
	* price: unit price
	*/
	
	private int id;
	private String bid;
	private double price;
	private int quan;
	
	public PurchaseOrderItemBean(int id, String bid, double sprice, int quan) {
		super();
		this.id = id;
		this.bid = bid;
		this.price = sprice;
		this.quan = quan;
	}
	
	public PurchaseOrderItemBean()
	{
		this(0, "", 0, 0);
	}
	
	//@XmlElement(name="id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	//@XmlElement(name="bid")
	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	//@XmlElement(name="price")
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuan() {
		return quan;
	}

	public void setQuan(int quan) {
		this.quan = quan;
	}
	
	
}
