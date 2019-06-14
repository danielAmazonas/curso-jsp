package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanTelefone;
import beans.BeanUsuario;
import dao.DaoTelefone;
import dao.DaoUsuario;

@WebServlet("/salvarTelefones")
public class Telefones extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoUsuario daoUsuario = new DaoUsuario();
	private DaoTelefone daoTelefone = new DaoTelefone();

	public Telefones() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		String user = request.getParameter("user");
		
		if (user != null) {
			BeanUsuario usuario = daoUsuario.consultar(user);
			if ("addFone".equalsIgnoreCase(acao)) {
				request.getSession().setAttribute("userEscolhido", usuario);
				request.setAttribute("userEscolhido", usuario);

				RequestDispatcher view = request.getRequestDispatcher("telefones.jsp");
				request.setAttribute("telefones", daoTelefone.listar(usuario.getId()));

				view.forward(request, response);
			} else if ("deleteFone".equalsIgnoreCase(acao)) {
				String foneId = request.getParameter("foneId");
				daoTelefone.delete(foneId);

				RequestDispatcher view = request.getRequestDispatcher("telefones.jsp");
				request.setAttribute("telefones", daoTelefone.listar(usuario.getId()));
				request.setAttribute("msg", "Removido com sucesso!!");

				view.forward(request, response);
			}
		} else {
			RequestDispatcher view = request.getRequestDispatcher("cadastrousuario.jsp");
			request.setAttribute("usuarios", daoUsuario.listar());
			view.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BeanUsuario usuario = (BeanUsuario) request.getSession().getAttribute("userEscolhido");
		String numero = request.getParameter("numero");
		String tipo = request.getParameter("tipo");

		BeanTelefone telefone = new BeanTelefone();
		telefone.setNumero(numero);
		telefone.setTipo(tipo);
		telefone.setUsuario(usuario.getId());

		String acao = request.getParameter("acao");

		if (acao == null || (acao != null && !acao.equalsIgnoreCase(acao))) {
			if (numero == null || (numero != null && numero.isEmpty())) {
				RequestDispatcher view = request.getRequestDispatcher("telefones.jsp");
				request.setAttribute("telefones", daoTelefone.listar(usuario.getId()));
				request.setAttribute("msg", "Informe o número do telefone!!");
				view.forward(request, response);
			} else {
				daoTelefone.salvar(telefone);

				request.getSession().setAttribute("userEscolhido", usuario);
				request.setAttribute("userEscolhido", usuario);

				RequestDispatcher view = request.getRequestDispatcher("telefones.jsp");
				request.setAttribute("telefones", daoTelefone.listar(usuario.getId()));
				request.setAttribute("msg", "Salvo com sucesso!!");
				view.forward(request, response);
			}
		} else {
			RequestDispatcher view = request.getRequestDispatcher("cadastrousuario.jsp");
			request.setAttribute("usuarios", daoUsuario.listar());
			view.forward(request, response);
		}
	}
}
