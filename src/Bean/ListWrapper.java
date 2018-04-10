package Bean;

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;


@XmlRootElement(name="sisReport")
public class ListWrapper {
	
	private String category;
	
	private List<BookBean> list;
	
	public ListWrapper()
	{
		this("", new LinkedList<BookBean>());
		
//		this.namePrefix = "";
//		this.list = new LinkedList<StudentBean>();
//		this.credit_taken = 0;
	}
	
	public ListWrapper(String category, List<BookBean> list) {
		this.category = category;
		this.list = list;
	}

	@XmlAttribute
	public String getNamePrefix() {
		return category;
	}

	public void setNamePrefix(String namePrefix) {
		this.category = namePrefix;
	}

	@XmlElement(name="studentList")
	public List<BookBean> getList() {
		return list;
	}

	public void setList(List<BookBean> list) {
		this.list = list;
	}
	
}
