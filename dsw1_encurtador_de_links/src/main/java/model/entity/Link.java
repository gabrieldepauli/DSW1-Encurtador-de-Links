package model.entity;

public class Link {
	
	private int id; // Utilizando AUTO-INCREMENT no banco de dados
	private String url_original;
	private String url_encurtada;
	
	public Link() {}

	public Link(int id, String url_original, String url_encurtada) {
		super();
		this.id = id;
		this.url_original = url_original;
		this.url_encurtada = url_encurtada;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl_original() {
		return url_original;
	}

	public void setUrl_original(String url_original) {
		this.url_original = url_original;
	}

	public String getUrl_encurtada() {
		return url_encurtada;
	}

	public void setUrl_encurtada(String url_encurtada) {
		this.url_encurtada = url_encurtada;
	}
}
