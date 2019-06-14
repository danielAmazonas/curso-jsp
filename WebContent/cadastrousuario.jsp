<%@page import="beans.BeanUsuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro de Usuário</title>
<link href="resources/css/style.css" rel="stylesheet">
<link
  href="https://fonts.googleapis.com/css?family=Josefin+Sans&display=swap"
  rel="stylesheet">
<script src="resources/js/jquery-3.2.1.min.js"></script>
</head>

<body>
  <div class="container">
    <div style="flex-direction: row">
      <table>
        <tr>
          <td style="padding-right: 35px"><a
            href="acessoliberado.jsp"><img
              src="resources/img/house.png" width="40" alt="Início"
              title="Início"></a></td>
          <td><a href="index.jsp"><img
              src="resources/img/logout.png" width="40" alt="Sair"
              title="Sair"></a></td>
        </tr>
        <tr>
          <td>Início</td>
          <td>Sair</td>
        </tr>
      </table>
    </div>
    <p>Cadastro/Atualização de Usuários</p>
    <div class="mensagem">
      <h6>${msg}</h6>
    </div>
    <div class="cadastro">
      <form action="salvarUsuario" method="post" class="form-cadastro"
        id="form-cadastro"
        onsubmit="return validarCampos() ? true : false"
        enctype="multipart/form-data">
        <table>
          <tr>
            <td>Código:</td>
            <td style="padding-right: 50px"><input type="text"
              readonly id="id" name="id" value="${user.id}"></td>
            <td>CEP:</td>
            <td><input type="text" id="cep" name="cep"
              onblur="consultaCep()" value="${user.cep}" tabindex="7"
              placeholder="Informe um CEP válido"></td>
          </tr>
          <tr>
            <td>Login:</td>
            <td><input type="text" id="login" name="login"
              value="${user.login}" autofocus tabindex="1"
              placeholder="Crie um login para o usuário" maxlength="10"></td>
            <td>Rua:</td>
            <td><input type="text" id="rua" name="rua"
              value="${user.rua}" tabindex="8"
              placeholder="Informe o nome da Rua"></td>
          </tr>
          <tr>
            <td>Senha:</td>
            <td><input type="password" id="senha" name="senha"
              value="${user.senha}" tabindex="2"
              placeholder="Defina uma senha para o usuário"></td>
            <td>Cidade:</td>
            <td><input type="text" id="cidade" name="cidade"
              value="${user.cidade}" tabindex="9"
              placeholder="Informe o nome da Cidade"></td>
          </tr>
          <tr>
            <td>Nome:</td>
            <td><input type="text" id="nome" name="nome"
              value="${user.nome}" tabindex="3"
              placeholder="Informe o nome do usuário"></td>
            <td>Bairro:</td>
            <td><input type="text" id="bairro" name="bairro"
              value="${user.bairro}" tabindex="10"
              placeholder="Informe o nome do Bairro"></td>
          </tr>
          <tr>
            <td>Foto:</td>
            <td>
              <div style="margin-top: 5px">
                <label id="labelFoto" for="foto" tabindex="4">Selecionar
                  uma foto »</label> <input type="file" id="foto" name="foto"">
                <br /> <span id="file-name-foto"></span>
              </div>
            </td>
            <td>Estado:</td>
            <td><input type="text" id="uf" name="uf"
              value="${user.uf}" tabindex="11"
              placeholder="Informe uma UF válida"></td>
          </tr>
          <tr>
            <td>Currículo:</td>
            <td>
              <div style="margin-top: 5px">
                <label id="labelCurriculo" for="curriculo" tabindex="5">Selecionar
                  currículo »</label> <input type="file" id="curriculo"
                  name="curriculo"> <br /> <span
                  id="file-name-curriculo"></span>
              </div>
            </td>
            <td>IBGE:</td>
            <td><input type="text" id="ibge" name="ibge" readonly
              value="${user.ibge}"></td>
          </tr>
          <tr>
            <td>Sexo:</td>
            <td>
              <input type="radio" id="sexo" name="sexo"
                class="radio" value="masculino" tabindex="6"
                <%if (request.getAttribute("user") != null) {
                  BeanUsuario usuario = (BeanUsuario) request.getAttribute("user");
                  if ("masculino".equalsIgnoreCase(usuario.getSexo())) {
                    out.print(" checked ");
                  }
                }
                %>><span>Masculino</span>
              <input type="radio" id="sexo" name="sexo"
                class="radio" value="feminino"
                <%if (request.getAttribute("user") != null) {
                  BeanUsuario usuario = (BeanUsuario) request.getAttribute("user");
                  if ("feminino".equalsIgnoreCase(usuario.getSexo())) {
                    out.print(" checked ");
                  }
                }
                %>><span>Feminino</span></td>
            <td>Ativo:</td>
            <td><input type="checkbox" id="ativo" name="ativo"
              tabindex="12" class="check"
              <%if (request.getAttribute("user") != null) {
				BeanUsuario usuario = (BeanUsuario) request.getAttribute("user");
				if (usuario.isAtivo()) {
					out.print(" checked ");
				}
			}%>></td>
          </tr>
          <tr>
            <td />
            <td />
            <td>Perfil:</td>
            <td>
              <select id="perfil" name="perfil" tabindex="13">
                <option value="nao_informado" <%if (request.getAttribute("user") != null) {
                  BeanUsuario usuario = (BeanUsuario) request.getAttribute("user");
                  if ("nao_informado".equalsIgnoreCase(usuario.getPerfil())) {
                    out.print(" selected ");
                  }
                }
                %>>[--SELECIONE--]
                <option value="administrador" <%if (request.getAttribute("user") != null) {
                  BeanUsuario usuario = (BeanUsuario) request.getAttribute("user");
                  if ("administrador".equalsIgnoreCase(usuario.getPerfil())) {
                    out.print(" selected ");
                  }
                }
                %>>Administrador(a)
                <option value="secretario" <%if (request.getAttribute("user") != null) {
                  BeanUsuario usuario = (BeanUsuario) request.getAttribute("user");
                  if ("secretario".equalsIgnoreCase(usuario.getPerfil())) {
                    out.print(" selected ");
                  }
                }
                %>>Secretário(a)
                <option value="gerente" <%if (request.getAttribute("user") != null) {
                  BeanUsuario usuario = (BeanUsuario) request.getAttribute("user");
                  if ("gerente".equalsIgnoreCase(usuario.getPerfil())) {
                    out.print(" selected ");
                  }
                }
                %>>Gerente
                <option value="padrao" <%if (request.getAttribute("user") != null) {
                  BeanUsuario usuario = (BeanUsuario) request.getAttribute("user");
                  if ("padrao".equalsIgnoreCase(usuario.getPerfil())) {
                    out.print(" selected ");
                  }
                }
                %>>Padrão
              </select>
            </td>
          </tr>
          <tr>
            <td />
            <td />
            <td />
            <td><button type="submit" value="Salvar" id="salvar"
                tabindex="14">Salvar</button></td>
          </tr>
          <tr>
            <td />
            <td />
            <td />
            <td><button
                type="${empty user.id ? 'reset' : 'submit'}"
                value="Cancelar" id="cancelar"
                onclick="document.getElementById('form-cadastro').action = 'salvarUsuario?acao=reset'"
                tabindex="15">Cancelar</button></td>
          </tr>
        </table>
      </form>
    </div>
    <br />
    <form method="post" action="servletPesquisa">
      <table>
        <tr>
          <td>Descrição:</td>
          <td><input type="text" id="descricaoconsulta" name="descricaoconsulta" class="descricao"></td>
          <td><input type="submit" value="Pesquisar" class="pesquisar"></td>
        </tr>  
      </table>
    </form>
    <br />
    <table class="tabela-usuarios">
      <tr>
        <th colspan="9">Lista de Usuários</th>
      </tr>
      <tr>
        <th>id</th>
        <th>foto</th>
        <th>currículo</th>
        <th>login</th>
        <th>nome</th>
        <th>ativo</th>
        <th colspan="3">ações</th>
      </tr>
      <c:forEach items="${usuarios}" var="user">
        <tr>
          <td><c:out value="${user.id}"></c:out></td>
          <c:if test="${user.fotoBase64Miniatura != null}">
            <td><a
              href="salvarUsuario?acao=download&tipo=imagem&user=${user.id}"><img
                src="${user.fotoBase64Miniatura}" width="40" height="40"
                alt="Foto Usuário" title="Foto Usuário"></a></td>
          </c:if>
          <c:if test="${user.fotoBase64Miniatura == null}">
            <td><img src="resources/img/man.png" width="40"
              height="40" alt="Usuário sem Foto"
              title="Usuário sem Foto"
              onclick="alert('Usuário não possui Foto!')"></td>
          </c:if>
          <c:if test="${user.curriculoBase64.isEmpty() == false}">
            <td><a
              href="salvarUsuario?acao=download&tipo=curriculo&user=${user.id}"><img
                src="resources/img/pdf.png" width="40" height="40"
                alt="Currículo" title="Currículo"></a></td>
          </c:if>
          <c:if test="${user.curriculoBase64 == null}">
            <td><img src="resources/img/empty-set.png" width="40"
              height="40" alt="Sem Currículo" title="Sem Currículo"
              onclick="alert('Usuário não possui Currículo!')"></td>
          </c:if>
          <td><c:out value="${user.login}"></c:out></td>
          <td><c:out value="${user.nome}"></c:out></td>
          <c:if test="${user.ativo == true}">
            <td><img src="resources/img/on.png" width="40"
              height="40" alt="ativo" title="Ativo"></td>
          </c:if>
          <c:if test="${user.ativo == false}">
            <td><img src="resources/img/off.png" width="40"
              height="40" alt="desativado" title="Desativado"></td>
          </c:if>
          <td><a
            href="salvarTelefones?acao=addFone&user=${user.id}"><img
              alt="Telefones" src="resources/img/phone.png" width="20px"
              title="Telefones"></a></td>
          <td><a href="salvarUsuario?acao=editar&user=${user.id}"><img
              alt="Editar" src="resources/img/edit.png" width="20px"
              title="Editar"></a></td>
          <td><a href="salvarUsuario?acao=delete&user=${user.id}"><img
              alt="Excluir"
              onclick="return confirm('Confirmar a exclusão do usuário: ${user.nome}?')"
              src="resources/img/trash.png" width="20px" title="Excluir"></a></td>
        </tr>
      </c:forEach>
    </table>
  </div>
  <script type="text/javascript">
			let $inputFoto = document.getElementById('foto'), $fileFoto = document
					.getElementById('file-name-foto'), $labelFoto = document
					.getElementById('labelFoto')

			$inputFoto.addEventListener('change', function() {
				$labelFoto.textContent = 'Arquivo Selecionado 🗸'
				$labelFoto.style.backgroundColor = 'green'
				$labelFoto.style.border = '1px solid hsla(90, 100%, 40%, 1)'
				$fileFoto.textContent = this.files[0].name + ' ⩰ '
						+ Math.floor(this.files[0].size / 1000) + ' kb'
			})

			$('#labelFoto').keyup(function(event) {
				if (event.keyCode === 13) {
					$('#foto').click()
				}
			})

			let $inputCurriculo = document.getElementById('curriculo'), $fileCurriculo = document
					.getElementById('file-name-curriculo'), $labelCurriculo = document
					.getElementById('labelCurriculo')

			$inputCurriculo.addEventListener('change', function() {
				$labelCurriculo.textContent = 'Arquivo Selecionado 🗸'
				$labelCurriculo.style.backgroundColor = 'green'
				$labelCurriculo.style.border = '1px solid hsla(90, 100%, 40%, 1)'
				$fileCurriculo.textContent = this.files[0].name + ' ⩰ '
						+ Math.floor(this.files[0].size / 1000) + ' kb'
			})

			$('#labelCurriculo').keyup(function(event) {
				if (event.keyCode === 13) {
					$('#curriculo').click()
				}
			})

			function validarCampos() {
				if ((document.getElementById('form-cadastro').action).indexOf('reset') > 0) {
					return true
				}

				if (!document.getElementById('login').value) {
					alert('Informe o Login!!')
					return false
				} else if (!document.getElementById('senha').value) {
					alert('Informe a Senha!!')
					return false
				} else if (!document.getElementById('nome').value) {
					alert('Informe o Nome!!')
					return false
				}
				return true
			}
			function consultaCep() {
				let cep = $('#cep').val()

				$.getJSON('https://viacep.com.br/ws/' + cep + '/json/?callback=?',
						function(dados) {
							if (!('erro' in dados)) {
								$('#rua').val(dados.logradouro)
								$('#bairro').val(dados.bairro)
								$('#cidade').val(dados.localidade)
								$('#uf').val(dados.uf)
								$('#ibge').val(dados.ibge)
							} else {
								$('#cep').val('')
								$('#rua').val('')
								$('#bairro').val('')
								$('#cidade').val('')
								$('#uf').val('')
								$('#ibge').val('')
								alert('CEP não encontrado.')
							}
						})
			}
		</script>
</body>
</html>