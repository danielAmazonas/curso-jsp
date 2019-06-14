package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanProduto;
import dao.DaoProduto;

@WebServlet("/salvarProduto")
public class Produto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoProduto daoProduto = new DaoProduto();

	public Produto() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao") != null ? request.getParameter("acao") : "listartodos";
		String prod = request.getParameter("prod");
		if ("delete".equalsIgnoreCase(acao)) {
			daoProduto.delete(prod);
			RequestDispatcher view = request.getRequestDispatcher("cadastroproduto.jsp");
			request.setAttribute("produtos", daoProduto.listar());
			view.forward(request, response);
		} else if ("editar".equalsIgnoreCase(acao)) {
			BeanProduto produto = daoProduto.consultar(prod);
			RequestDispatcher view = request.getRequestDispatcher("cadastroproduto.jsp");
			request.setAttribute("prod", produto);
			view.forward(request, response);
		} else if ("listartodos".equalsIgnoreCase(acao)) {
			RequestDispatcher view = request.getRequestDispatcher("cadastroproduto.jsp");
			request.setAttribute("produtos", daoProduto.listar());
			view.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");

		if (acao != null && "reset".equalsIgnoreCase(acao)) {
			RequestDispatcher view = request.getRequestDispatcher("cadastroproduto.jsp");
			request.setAttribute("produtos", daoProduto.listar());
			view.forward(request, response);
		} else {
			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String quantidade = request.getParameter("quantidade");
			String valor = request.getParameter("valor");

			BeanProduto produto = new BeanProduto();
			produto.setId(!id.isEmpty() ? Long.parseLong(id) : null);
			produto.setNome(nome);
			produto.setQuantidade(Integer.parseInt(quantidade));
			
			String valorParse = valor.replaceAll("\\.", "");
			valorParse = valorParse.replaceAll("\\,", ".");
			produto.setValor(Double.parseDouble(valorParse));

			boolean podeInserir = true;
			
			if (nome == null || nome.isEmpty()) {
				podeInserir = false;
				request.setAttribute("msg", "Nome do Produto deve ser informado!!");
			} else if (quantidade == null || quantidade.isEmpty()) {
				podeInserir = false;
				request.setAttribute("msg", "Quantidade do Produto deve ser informada!!");
			} else if (valor == null || valor.isEmpty()) {
				podeInserir = false;
				request.setAttribute("msg", "Valor R$ deve ser informado!!");
			} else if (id == null || id.isEmpty() && !daoProduto.validarNome(nome)) {
				podeInserir = false;
				request.setAttribute("msg", "Nome já existe para outro Produto!!");
			}

			if (id == null || id.isEmpty() && daoProduto.validarNome(nome) && podeInserir) {
				daoProduto.salvar(produto);
			} else if (id != null && !id.isEmpty()) {
				if (!daoProduto.validarNomeUpdate(nome, id)) {
					request.setAttribute("msg", "Nome já existe para outro Produto!!");
				} else {
					daoProduto.atualizar(produto);
				}
			}

			if (!podeInserir) {
				request.setAttribute("prod", produto);
			}

			RequestDispatcher view = request.getRequestDispatcher("cadastroproduto.jsp");
			request.setAttribute("produtos", daoProduto.listar());
			view.forward(request, response);
		}
	}
}
