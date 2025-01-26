package model.entity;

public class Acesso {
	
	private int id; // Utilizando AUTO-INCREMENT no banco de dados
	private String ip_cliente;
	
	public Acesso() {}
	
	public Acesso(int id, String ip_cliente) {
		super();
		this.id = id;
		this.ip_cliente = ip_cliente;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIp_cliente() {
		return ip_cliente;
	}

	public void setIp_cliente(String ip_cliente) {
		this.ip_cliente = ip_cliente;
	}
	
	
	
}
