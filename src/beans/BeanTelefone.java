package beans;

public class BeanTelefone {
	
	private Long id, usuario;
	private String numero, tipo;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Long usuario) {
		this.usuario = usuario;
	}
	
	public String getNumero() {
		return numero;
	}
	
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}