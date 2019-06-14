<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro de Produto</title>
<link href="resources/css/style.css" rel="stylesheet">
<link
  href="https://fonts.googleapis.com/css?family=Josefin+Sans&display=swap"
  rel="stylesheet">
<script src="resources/js/jquery-3.2.1.min.js"></script>
<script src="resources/js/jquery.maskMoney.min.js"></script>
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
    <p>Cadastro/Atualização de Produtos</p>
    <div class="mensagem">
      <h6>${msg}</h6>
    </div>
    <div class="cadastro">
      <form action="salvarProduto" method="post" class="form-cadastro"
        id="form-cadastro"
        onsubmit="return validarCampos() ? true : false">
        <table>
          <tr>
            <td>Código:</td>
            <td><input type="text" readonly id="id" name="id"
              value="${prod.id}"></td>
          </tr>
          <tr>
            <td>Nome:</td>
            <td><input type="text" id="nome" name="nome"
              value="${prod.nome}" autofocus
              placeholder="Informe o nome do produto"></td>
          </tr>
          <tr>
            <td>Quantidade:</td>
            <td><input type="number" id="quantidade"
              name="quantidade" value="${prod.quantidade}"
              placeholder="Informe a quantidade do produto"></td>
          </tr>
          <tr>
            <td>Valor R$:</td>
            <td><input type="text" id="valor" name="valor"
              value="${prod.valorEmTexto}"
              placeholder="Informe o valor do produto"
              data-thousands="." data-decimal=","></td>
          </tr>
          <tr>
            <td />
            <td><button type="submit" value="Salvar" id="salvar">Salvar</button></td>
          </tr>
          <tr>
            <td />
            <td><button
                type="${empty prod.id ? 'reset' : 'submit'}"
                value="Cancelar" id="cancelar"
                onclick="document.getElementById('form-cadastro').action = 'salvarProduto?acao=reset'">Cancelar</button></td>
          </tr>
        </table>
      </form>
    </div>
    <br />
    <table class="tabela-usuarios">
      <tr>
        <th colspan="6">Lista de Produtos</th>
      </tr>
      <tr>
        <th>id</th>
        <th>nome</th>
        <th>quantidade</th>
        <th>valor r$</th>
        <th colspan="2">ações</th>
      </tr>
      <c:forEach items="${produtos}" var="prod">
        <tr>
          <td><c:out value="${prod.id}"></c:out></td>
          <td><c:out value="${prod.nome}"></c:out></td>
          <td><c:out value="${prod.quantidade}"></c:out></td>
          <td><fmt:formatNumber type="number" maxFractionDigits="2"
              value="${prod.valor}" /></td>
          <td><a href="salvarProduto?acao=editar&prod=${prod.id}"><img
              alt="Editar" src="resources/img/edit.png" width="20px"
              title="Editar"></a></td>
          <td><a href="salvarProduto?acao=delete&prod=${prod.id}"><img
              alt="Excluir"
              onclick="return confirm('Confirmar a exclusão do produto: ${prod.nome}?')"
              src="resources/img/trash.png" width="20px" title="Excluir"></a></td>
        </tr>
      </c:forEach>
    </table>
  </div>
  <script type="text/javascript">
			function validarCampos() {
				if ((document.getElementById('form-cadastro').action).indexOf('reset') > 0) {
					return true
				}

				if (!document.getElementById('nome').value) {
					alert('Informe o Nome!!')
					return false
				} else if (!document.getElementById('quantidade').value) {
					alert('Informe a Quantidade!!')
					return false
				} else if (!document.getElementById('valor').value) {
					alert('Informe o Valor R$!!')
					return false
				}
				return true
			}
			
			$(() => {
				$('#valor').maskMoney();
			})
		</script>
</body>
</html>