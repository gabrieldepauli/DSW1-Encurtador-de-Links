package br.edu.ifsp.encurtador.model.entity;

public class Link {
	
	private int id;
	private String urlOriginal;
	private String urlEncurtada;
	private boolean privateLink;
	private User creator;
	
	public Link() {}

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

	public boolean isPrivateLink() {
		return privateLink;
	}

	public void setPrivateLink(boolean privateLink) {
		this.privateLink = privateLink;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}
}
