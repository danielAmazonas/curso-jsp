<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="myprefix" uri="WEB-INF/testetag.tld"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Curso JSP</title>
<link href="resources/css/style.css" rel="stylesheet">
<link
  href="https://fonts.googleapis.com/css?family=Josefin+Sans&display=swap"
  rel="stylesheet">
</head>
<body>
  <div class="container">
    <h5>Projeto Didático - JSP + JSTL + Servlet + JDBC</h5>
    <span style="color: green">Login: admin ★ Senha: admin</span>
    <p>Autenticação</p>
    <div class="login">
      <form action="LoginServlet" method="post" class="form-login">
        <table>
          <tr>
            <td><label>Login:</label></td>
            <td><input type="text" id="login" name="login"
              autofocus></td>
          </tr>
          <tr>
            <td><label>Senha:</label></td>
            <td><input type="password" id="senha" name="senha"></td>
          </tr>
          <tr>
            <td />
            <td><button type="submit" value="Logar">Logar</button></td>
          </tr>
        </table>
      </form>
    </div>
  </div>
</body>
</html>