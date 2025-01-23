package model.dao;

public class LinkDaoFactory {

	public LinkDao factory() {
		return new LinkDaoImp();
	}
	
}
