package Bean;

import java.util.LinkedList;
import java.util.List;

public class UserStatsWrapper {

	private List<UserStats> users;

	public UserStatsWrapper(List<UserStats> users) {
		super();
		this.users = users;
	}
	
	public UserStatsWrapper()
	{
		this(new LinkedList<UserStats>());
	}
	
	public void addUser(UserStats user)
	{
		users.add(user);
	}
}
