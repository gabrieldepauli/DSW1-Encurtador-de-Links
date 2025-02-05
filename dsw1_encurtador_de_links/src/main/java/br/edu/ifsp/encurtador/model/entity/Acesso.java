package br.edu.ifsp.encurtador.model.entity;

public class Acesso {
	
	private Integer id;
	private Integer linkId;
	private String ipCliente;
	
	public Acesso() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLinkId() {
		return linkId;
	}

	public void setLinkId(Integer linkId) {
		this.linkId = linkId;
	}

	public String getIpCliente() {
		return ipCliente;
	}

	public void setIpCliente(String ipCliente) {
		this.ipCliente = ipCliente;
	}
}
