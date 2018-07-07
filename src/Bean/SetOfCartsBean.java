package Bean;

import java.util.HashMap;
import java.util.Map;

import Bean.CartBean;


public class SetOfCartsBean {

	private Map<String, CartBean> users;
	private Map<String, CartBean> carts;
	private static SetOfCartsBean instance = new SetOfCartsBean();
	
	private SetOfCartsBean(){
		this.carts = new HashMap<String, CartBean>();
		this.users = new HashMap<String, CartBean>();
	}
	
	public static SetOfCartsBean getInstance()
	{
		return instance;
	}
	
	public CartBean updateByLogin(String username, String ID)
	{
		CartBean cur = this.users.get(username);
		if (cur == null)
		{
			CartBean out = new CartBean();
			users.put(username, out);
			carts.remove(ID);
			return out;
		}
		else
		{
			return users.get(username);
		}
	}
	
	public CartBean updatebylogout (String ID)
	{
		CartBean out = new CartBean();
		carts.put(ID, out);
		return out;
	}
	
	public boolean emptyUserCart(String username){
		if(users.containsKey(username)){
			users.put(username, new CartBean());
			return true;
		}
		else
			return false;
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
