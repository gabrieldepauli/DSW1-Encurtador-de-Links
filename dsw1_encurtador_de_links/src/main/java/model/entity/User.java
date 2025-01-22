package model.entity;

import java.util.LinkedList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

public class User {
	private String nome;
	private String email;
	private String senhaHash;
	private List<Link> links;
	
	public User(String nome, String email, String senha) {
		init(nome, email, hashPassword(senha));
	}
	
	public User(String nome, String email, String senha, boolean existUser) {
		if (existUser) {
			init(nome, email, senha);
		} else {
			init(nome, email, hashPassword(senha));
		}
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSenhaHash() {
		return senhaHash;
	}

	public void setSenhaHash(String senhaHash) {
		this.senhaHash = senhaHash;
	}

	public List<Link> getLinks() {
		return links;
	}
	
	public void setLinks(List<Link> links) {
		this.links = links;
	}
	
	public static boolean autenticate(User existUser, String email, String senha) {
		if (existUser != null) {
			return BCrypt.checkpw(senha, existUser.senhaHash) && email.equals(existUser.email);
		}
		
		return false;
	}
	
	private void init(String nome, String email, String senhaHash) {
		this.nome = nome;
		this.email = email;
		this.senhaHash = senhaHash;
		links = new LinkedList<Link>();
	}
	
	// Método para hash de senhas
    private static String hashPassword(String senha) {
        return BCrypt.hashpw(senha, BCrypt.gensalt());
    }
	
}
