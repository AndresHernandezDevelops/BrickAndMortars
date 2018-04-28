package Bean;

import java.util.HashMap;
import java.util.Map;

import Bean.CartBean;


public class SetOfCartsBean {

	private static Map<String, CartBean> carts = new HashMap<String, CartBean>();
	
	private SetOfCartsBean(){
		
	}
	
	public static CartBean addCart(String ID){
		if(carts.containsKey(ID))
			return carts.get(ID);
		else{
			CartBean newCart = new CartBean();
			carts.put(ID, newCart);
			return newCart;
		}
	}
	
	public static CartBean retrieveCart(String ID){
		if(carts.containsKey(ID))
			return carts.get(ID);
		else{
			return null;
		}
	}
	
	public static boolean removeCart(String ID){
		if(carts.containsKey(ID)){
			carts.remove(ID);
			return true;
		}
		else
			return false;
	}
	
	public static boolean emptyCart(String ID){
		if(carts.containsKey(ID)){
			carts.put(ID, new CartBean());
			return true;
		}
		else
			return false;
	}
}
