package model.dao;

public class UserDaoFactory {

	public UserDao factory() {
		return new UserDaoImp();
	}
	
}
