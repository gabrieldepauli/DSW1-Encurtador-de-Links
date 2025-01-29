package br.edu.ifsp.encurtador.model.entity;

public class Link {
	
	private int id;
	private String urlOriginal;
	private String urlEncurtada;
	
	public Link() {}

	public Link(int id, String urlOriginal, String urlEncurtada) {
		super();
		this.id = id;
		this.urlOriginal = urlOriginal;
		this.urlEncurtada = urlEncurtada;
	}
	
	public Link(String urlOriginal, String urlEncurtada) {
		this(-1, urlOriginal, urlEncurtada);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrlOriginal() {
		return urlOriginal;
	}

	public void setUrlOriginal(String urlOriginal) {
		this.urlOriginal = urlOriginal;
	}

	public String getUrlEncurtada() {
		return urlEncurtada;
	}

	public void setUrlEncurtada(String urlEncurtada) {
		this.urlEncurtada = urlEncurtada;
	}
}
