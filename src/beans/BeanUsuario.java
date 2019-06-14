package beans;

public class BeanUsuario {

	private Long id;
	private String login, senha, nome, cep, rua, bairro, cidade, uf, ibge, fotoBase64, contentTypeFoto,
			curriculoBase64, contentTypeCurriculo, fotoBase64Miniatura, sexo, perfil;
	private boolean ativo;

	private String fotoUsuario, curriculoUsuario;
	private boolean atualizarImagem = true;
	private boolean atualizarCurriculo = true;

	public String getFotoUsuario() {
		fotoUsuario = "data:" + contentTypeFoto + ";base64," + fotoBase64;
		return fotoUsuario;
	}

	public String getCurriculoUsuario() {
		curriculoUsuario = "data:" + contentTypeCurriculo + ";base64," + curriculoBase64;
		return curriculoUsuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getIbge() {
		return ibge;
	}

	public void setIbge(String ibge) {
		this.ibge = ibge;
	}

	public String getFotoBase64() {
		return fotoBase64;
	}

	public void setFotoBase64(String fotoBase64) {
		this.fotoBase64 = fotoBase64;
	}

	public String getContentTypeFoto() {
		return contentTypeFoto;
	}

	public void setContentTypeFoto(String contentTypeFoto) {
		this.contentTypeFoto = contentTypeFoto;
	}

	public String getCurriculoBase64() {
		return curriculoBase64;
	}

	public void setCurriculoBase64(String curriculoBase64) {
		this.curriculoBase64 = curriculoBase64;
	}

	public String getContentTypeCurriculo() {
		return contentTypeCurriculo;
	}

	public void setContentTypeCurriculo(String contentTypeCurriculo) {
		this.contentTypeCurriculo = contentTypeCurriculo;
	}
	
	public String getFotoBase64Miniatura() {
		return fotoBase64Miniatura;
	}
	
	public void setFotoBase64Miniatura(String fotoBase64Miniatura) {
		this.fotoBase64Miniatura = fotoBase64Miniatura;
	}

	public boolean isAtualizarImagem() {
		return atualizarImagem;
	}

	public void setAtualizarImagem(boolean atualizarImagem) {
		this.atualizarImagem = atualizarImagem;
	}

	public boolean isAtualizarCurriculo() {
		return atualizarCurriculo;
	}

	public void setAtualizarCurriculo(boolean atualizarCurriculo) {
		this.atualizarCurriculo = atualizarCurriculo;
	}
	
	public String getSexo() {
		return sexo;
	}
	
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	public String getPerfil() {
		return perfil;
	}
	
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	
	public boolean isAtivo() {
		return ativo;
	}
	
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
}
