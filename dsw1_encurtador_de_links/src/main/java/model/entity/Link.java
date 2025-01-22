package model.entity;

public class Link {
	private String id; // Utilizando AUTO-INCREMENT no banco de dados
	private String url;
	
	public Link() {
	}
	
	public Link(String id, String url) {
		super();
		this.url = url;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}	
	
}
