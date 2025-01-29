package br.edu.ifsp.encurtador.model.entity;

public class Acesso {
	
	private int id;
	private String ipCliente;
	
	public Acesso() {}
	
	public Acesso(int id, String ipCliente) {
		super();
		this.id = id;
		this.ipCliente = ipCliente;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIpCliente() {
		return ipCliente;
	}

	public void setIpCliente(String ipCliente) {
		this.ipCliente = ipCliente;
	}
}
