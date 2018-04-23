package Bean;

import javax.xml.bind.annotation.XmlType;

//@XmlRootElement(name="PurchaseOrderItem")
@XmlType(propOrder={"bid", "id", "price"})
public class PurchaseOrderItemBean {

	/* Items on order
	* id : purchase order id
	* bid: unique identifier of Book
	* price: unit price
	*/
	
	private int id;
	private String bid;
	private int price;
	
	public PurchaseOrderItemBean(int id, String bid, int price) {
		super();
		this.id = id;
		this.bid = bid;
		this.price = price;
	}
	
	public PurchaseOrderItemBean()
	{
		this(0, "", 0);
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
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	
}
