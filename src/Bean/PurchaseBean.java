package Bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="PurchaseBean")
public class PurchaseBean {
	
	private String day;
	private String bID;
	private String eventType;
	
	public PurchaseBean()
	{
		this("00000000", "", "null");
	}
	
	public PurchaseBean(String day, String bID, String eventType) {
		super();
		this.day = day;
		this.bID = bID;
		this.eventType = eventType;
	}

	@XmlElement(name="day")
	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	@XmlElement(name="bID")
	public String getbID() {
		return bID;
	}

	public void setbID(String bID) {
		this.bID = bID;
	}

	@XmlElement(name="eventType")
	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	
	
	
}
