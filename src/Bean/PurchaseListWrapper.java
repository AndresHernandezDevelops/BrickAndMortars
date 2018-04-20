package Bean;

import java.util.LinkedList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="PurchaseListWrapper")
public class PurchaseListWrapper {
	
	private List<PurchaseBean> list;

	public PurchaseListWrapper(List<PurchaseBean> list) {
		super();
		this.list = list;
	}
	
	public PurchaseListWrapper(){
		this(new LinkedList<PurchaseBean>());
	}

	@XmlElement(name="list")
	public List<PurchaseBean> getList() {
		return list;
	}

	public void setList(List<PurchaseBean> list) {
		this.list = list;
	}
	
	
	
}
