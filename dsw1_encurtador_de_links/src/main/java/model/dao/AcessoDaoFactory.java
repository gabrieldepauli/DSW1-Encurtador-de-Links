package model.dao;

public class AcessoDaoFactory {
	
	public AcessoDao factory() {
		return new AcessoDaoImp();
	}
	
}
