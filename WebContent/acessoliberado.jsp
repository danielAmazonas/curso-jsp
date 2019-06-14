<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bem Vindo - Curso JSP</title>
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
    <h2>Seja bem vindo ao sistema em jsp</h2>
    <div style="flex-direction: row">
      <table>
        <tr>
          <td style="padding-right: 25px"><a
            href="salvarUsuario?acao=listartodos"
            style="text-decoration: none"> <img
              alt="Cadastro de usuários" title="Cadastro de usuários"
              src="resources/img/man.png" width="100" class="avatar">
          </a></td>
          <td><a href="salvarProduto?acao=listartodos"
            style="text-decoration: none"> <img
              alt="Cadastro de produtos" title="Cadastro de produtos"
              src="resources/img/box.png" width="100" class="avatar">
          </a></td>
        </tr>
        <tr>
          <td>Cad. Usuário</td>
          <td>Cad. Produto</td>
        </tr>

      </table>

    </div>
  </div>
</body>
</html>