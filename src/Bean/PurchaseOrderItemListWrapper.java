package Bean;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="PurchaseOrderItemReport")
public class PurchaseOrderItemListWrapper {

	private int id;
	private List<PurchaseOrderItemBean> list;
	
	public PurchaseOrderItemListWrapper(int id, List<PurchaseOrderItemBean> list) {
		super();
		this.id = id;
		this.list = list;
	}
	
	public PurchaseOrderItemListWrapper()
	{
		this(0, new LinkedList<PurchaseOrderItemBean>());
	}

	@XmlAttribute(name="partNumber")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@XmlElement(name="PurchaseOrderItemList")
	public List<PurchaseOrderItemBean> getList() {
		return list;
	}

	public void setList(List<PurchaseOrderItemBean> list) {
		this.list = list;
	}
}
