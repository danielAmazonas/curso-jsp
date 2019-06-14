<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro de Telefones</title>
<link href="resources/css/style.css" rel="stylesheet">
<link
  href="https://fonts.googleapis.com/css?family=Josefin+Sans&display=swap"
  rel="stylesheet">
</head>

<body>
  <div class="container">
    <div style="flex-direction: row">
      <table>
        <tr>
          <td style="padding-right: 25px"><a
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
    <p>Lista de Telefones</p>
    <div class="mensagem">
      <h6>${msg}</h6>
    </div>
    <div class="cadastro">
      <form action="salvarTelefones" method="post" class="form-cadastro"
        id="form-cadastro"
        onsubmit="return validarCampos() ? true : false">
        <table>
          <tr>
            <td>Usuário:</td>
            <td><input type="text" readonly id="id" name="id"
              value="${userEscolhido.id}"></td>
          </tr>
          <tr>
            <td>Nome:</td>
            <td><input type="text" readonly id="nomeUser"
              name="nomeUser" value="${userEscolhido.nome}"></td>
          </tr>
          <tr>
            <td>Número:</td>
            <td><input type="text" id="numero" name="numero"
              tabindex="1" autofocus placeholder="Informe um número"></td>
          </tr>
          <tr>
            <td>Tipo:</td>
            <td><select id="tipo" name="tipo" tabindex="2">
                <option>Casa</option>
                <option>Celular Pessoal</option>
                <option>Trabalho</option>
                <option>Celular Funcional</option>
            </select></td>
          </tr>
          <tr>
            <td />
            <td><button type="submit" value="Salvar" id="salvar"
                tabindex="3">Salvar</button></td>
          </tr>
          <tr>
            <td />
            <td><button type="submit" value="voltar" id="voltar"
                onclick="document.getElementById('form-cadastro').action = 'salvarTelefones?acao=voltar'"
                tabindex="4">Voltar</button></td>
          </tr>
        </table>
      </form>
    </div>
    <br />
    <table class="tabela-usuarios">
      <tr>
        <th colspan="7">Telefones Cadastrados</th>
      </tr>
      <tr>
        <th>id</th>
        <th>número</th>
        <th>tipo</th>
        <th colspan="1">ações</th>
      </tr>
      <c:forEach items="${telefones}" var="fone">
        <tr>
          <td><c:out value="${fone.id}"></c:out></td>
          <td><c:out value="${fone.numero}"></c:out></td>
          <td><c:out value="${fone.tipo}"></c:out></td>
          <td><a
            href="salvarTelefones?acao=deleteFone&user=${fone.usuario}&foneId=${fone.id}"><img
              alt="Excluir"
              onclick="return confirm('Confirmar a exclusão do telefone: ${fone.numero}?')"
              src="resources/img/trash.png" width="20px" title="Excluir"></a></td>
        </tr>
      </c:forEach>
    </table>
  </div>
  <script type="text/javascript">
			function validarCampos() {
				if (!document.getElementById('numero').value) {
					//alert('Informe o Número!!')
					//return false
				} else if (!document.getElementById('tipo').value) {
					alert('Informe o Tipo!!')
					return false
				}
				return true
			}
		</script>
</body>
</html>