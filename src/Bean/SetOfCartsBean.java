package Bean;

import java.util.HashMap;
import java.util.Map;

import Bean.CartBean;


public class SetOfCartsBean {

	private Map<String, CartBean> carts;
	private static SetOfCartsBean instance = new SetOfCartsBean();
	
	private SetOfCartsBean(){
		this.carts = new HashMap<String, CartBean>();
	}
	
	public static SetOfCartsBean getInstance()
	{
		return instance;
	}
	
	public CartBean addCart(String ID){
		if(carts.containsKey(ID))
			return carts.get(ID);
		else{
			CartBean newCart = new CartBean();
			carts.put(ID, newCart);
			return newCart;
		}
	}
	
	public CartBean retrieveCart(String ID){
		if(carts.containsKey(ID))
			return carts.get(ID);
		else{
			return null;
		}
	}
	
	public boolean removeCart(String ID){
		if(carts.containsKey(ID)){
			carts.remove(ID);
			return true;
		}
		else
			return false;
	}
	
	public boolean emptyCart(String ID){
		if(carts.containsKey(ID)){
			carts.put(ID, new CartBean());
			return true;
		}
		else
			return false;
	}
}
