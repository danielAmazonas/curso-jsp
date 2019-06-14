package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanUsuario;
import dao.DaoUsuario;

@WebServlet("/servletPesquisa")
public class Pesquisa extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoUsuario daoUsuario = new DaoUsuario();

	public Pesquisa() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String descricao = request.getParameter("descricaoconsulta");
		if (descricao != null && !descricao.trim().isEmpty()) {
			List<BeanUsuario> listaPesquisa = daoUsuario.listar(descricao);
			RequestDispatcher view = request.getRequestDispatcher("cadastrousuario.jsp");
			request.setAttribute("usuarios", listaPesquisa);
			view.forward(request, response);
		} else {
			RequestDispatcher view = request.getRequestDispatcher("cadastrousuario.jsp");
			request.setAttribute("usuarios", daoUsuario.listar());
			view.forward(request, response);
		}
	}

}
