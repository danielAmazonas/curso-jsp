package servlet;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.codec.binary.Base64;

import beans.BeanUsuario;
import dao.DaoUsuario;

@WebServlet("/salvarUsuario")
@MultipartConfig
public class Usuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoUsuario daoUsuario = new DaoUsuario();

	public Usuario() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		String user = request.getParameter("user");
		if (acao != null && "delete".equalsIgnoreCase(acao) && user != null) {
			daoUsuario.delete(user);
			RequestDispatcher view = request.getRequestDispatcher("cadastrousuario.jsp");
			request.setAttribute("usuarios", daoUsuario.listar());
			view.forward(request, response);
		} else if (acao != null && "editar".equalsIgnoreCase(acao)) {
			BeanUsuario usuario = daoUsuario.consultar(user);
			RequestDispatcher view = request.getRequestDispatcher("cadastrousuario.jsp");
			request.setAttribute("user", usuario);
			view.forward(request, response);
		} else if (acao != null && "listartodos".equalsIgnoreCase(acao)) {
			RequestDispatcher view = request.getRequestDispatcher("cadastrousuario.jsp");
			request.setAttribute("usuarios", daoUsuario.listar());
			view.forward(request, response);
		} else if (acao != null && "download".equalsIgnoreCase(acao)) {
			BeanUsuario usuario = daoUsuario.consultar(user);
			if (usuario != null) {
				String contentType = "";
				byte[] fileBytes = null;

				String tipo = request.getParameter("tipo");

				/* Converte a base64 da imagem do banco para byte[] */
				if ("imagem".equalsIgnoreCase(tipo)) {
					contentType = usuario.getContentTypeFoto();
					fileBytes = new Base64().decodeBase64(usuario.getFotoBase64());
				} else if ("curriculo".equalsIgnoreCase(tipo)) {
					contentType = usuario.getContentTypeCurriculo();
					fileBytes = new Base64().decodeBase64(usuario.getCurriculoBase64());
				}

				response.setHeader("Content-Disposition", "attachment;filename=arquivo." + contentType.split("\\/")[1]);

				/* Coloca os bytes em um objeto de entrada para processar */
				InputStream inputStream = new ByteArrayInputStream(fileBytes);

				/* Início da resposta para o navegador */
				int read = 0;
				byte[] bytes = new byte[1024];
				OutputStream outputStream = response.getOutputStream();

				while ((read = inputStream.read(bytes)) != -1) {
					outputStream.write(bytes, 0, read);
				}
				outputStream.flush();
				outputStream.close();
			}
		} else {
			RequestDispatcher view = request.getRequestDispatcher("cadastrousuario.jsp");
			request.setAttribute("usuarios", daoUsuario.listar());
			view.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");

		if (acao != null && "reset".equalsIgnoreCase(acao)) {
			RequestDispatcher view = request.getRequestDispatcher("cadastrousuario.jsp");
			request.setAttribute("usuarios", daoUsuario.listar());
			view.forward(request, response);
		} else {
			String id = request.getParameter("id");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			String nome = request.getParameter("nome");
			String cep = request.getParameter("cep");
			String rua = request.getParameter("rua");
			String bairro = request.getParameter("bairro");
			String cidade = request.getParameter("cidade");
			String uf = request.getParameter("uf");
			String ibge = request.getParameter("ibge");
			String sexo = request.getParameter("sexo");
			String perfil = request.getParameter("perfil");

			// System.out.println(request.getParameter("ativo")); //on está marcado, null
			// desmarcado

			BeanUsuario usuario = new BeanUsuario();
			// usuario.setId(!id.isEmpty() ? Long.parseLong(id) : null);
			usuario.setId((id != null && !id.isEmpty()) ? Long.parseLong(id) : null);
			usuario.setLogin(login);
			usuario.setSenha(senha);
			usuario.setNome(nome);
			usuario.setCep(cep);
			usuario.setRua(rua);
			usuario.setBairro(bairro);
			usuario.setCidade(cidade);
			usuario.setUf(uf);
			usuario.setIbge(ibge);
			if (request.getParameter("ativo") != null && "on".equalsIgnoreCase(request.getParameter("ativo"))) {
				usuario.setAtivo(true);
			} else {
				usuario.setAtivo(false);
			}
			usuario.setSexo(sexo);
			usuario.setPerfil(perfil);
			/* INICIO - File upload de imagens e pdf */

			if (ServletFileUpload.isMultipartContent(request)) {
				/* Processa Foto */
				Part imagemFoto = request.getPart("foto");
				if (imagemFoto != null && imagemFoto.getInputStream().available() > 0) {
					String fotoBase64 = new Base64().encodeBase64String(converteStreamParaByte(imagemFoto.getInputStream()));
					usuario.setFotoBase64(fotoBase64);
					usuario.setContentTypeFoto(imagemFoto.getContentType());

					/* Início miniatura imagem */
					// Transformar em um bufferedImage
					byte[] imageByteDecode = new Base64().decodeBase64(fotoBase64);
					BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageByteDecode));
					// Pega o tipo da imagem
					int type = bufferedImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : bufferedImage.getType();
					// Cria a imagem em miniatura
					BufferedImage resizedImage = new BufferedImage(40, 40, type);
					Graphics2D graphics2d = resizedImage.createGraphics();
					graphics2d.drawImage(bufferedImage, 0, 0, 40, 40, null);
					graphics2d.dispose();
					// Escreve a imagem novamente
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					ImageIO.write(resizedImage, "png", baos);
					// Monta novamente a base64 completa da miniatura
					String miniaturaBase64 = "data:image/png;base64," + DatatypeConverter.printBase64Binary(baos.toByteArray());
					usuario.setFotoBase64Miniatura(miniaturaBase64);
					/* Fim miniatura imagem */
				} else {
					usuario.setAtualizarImagem(false);
				}

				/*
				 * Usar se o form tiver apenas o campo de upload de arquivo try { List<FileItem>
				 * fileItems = new ServletFileUpload(new
				 * DiskFileItemFactory()).parseRequest(request); for (FileItem fileItem :
				 * fileItems) { if ("foto".equals(fileItem.getFieldName())) { String fotoBase64
				 * = new Base64().encodeBase64String(fileItem.get()); String contentType =
				 * fileItem.getContentType(); usuario.setFotoBase64(fotoBase64);
				 * usuario.setContentType(contentType); } } } catch (FileUploadException e) {
				 * e.printStackTrace(); }
				 */

				/* Processa PDF */
				Part curriculoPdf = request.getPart("curriculo");
				if (curriculoPdf != null && curriculoPdf.getInputStream().available() > 0) {
					String curriculoBase64 = new Base64()
							.encodeBase64String(converteStreamParaByte(curriculoPdf.getInputStream()));
					usuario.setCurriculoBase64(curriculoBase64);
					usuario.setContentTypeCurriculo(curriculoPdf.getContentType());
				} else {
					usuario.setAtualizarCurriculo(false);
				}
			}

			/* FIM - File upload de imagens e pdf */

			boolean podeInserir = true;

			if (login == null || login.isEmpty()) {
				podeInserir = false;
				request.setAttribute("msg", "Login deve ser informado!!");
			} else if (senha == null || senha.isEmpty()) {
				podeInserir = false;
				request.setAttribute("msg", "Senha deve ser informada!!");
			} else if (nome == null || nome.isEmpty()) {
				podeInserir = false;
				request.setAttribute("msg", "Nome deve ser informado!!");
			} else if (id == null || id.isEmpty() && !daoUsuario.validarLogin(login)) {
				podeInserir = false;
				request.setAttribute("msg", "Login já existe para outro Usuário!!");
			} else if (id == null || id.isEmpty() && !daoUsuario.validarSenha(senha)) {
				podeInserir = false;
				request.setAttribute("msg", "A Senha já existe para outro Usuário!!");
			}

			if (id == null
					|| id.isEmpty() && daoUsuario.validarLogin(login) && daoUsuario.validarSenha(senha) && podeInserir) {
				daoUsuario.salvar(usuario);
			} else if (id != null && !id.isEmpty()) {
				if (!daoUsuario.validarLoginUpdate(login, id)) {
					request.setAttribute("msg", "Login já existe para outro Usuário!!");
				} else if (!daoUsuario.validarSenhaUpdate(senha, id)) {
					request.setAttribute("msg", "Senha já existe para outro Usuário!!");
				} else {
					daoUsuario.atualizar(usuario);
				}
			}

			if (!podeInserir) {
				request.setAttribute("user", usuario);
			}

			RequestDispatcher view = request.getRequestDispatcher("cadastrousuario.jsp");
			request.setAttribute("usuarios", daoUsuario.listar());
			request.setAttribute("msg", "Salvo com sucesso!!");
			view.forward(request, response);
		}
	}

	/* Converter Stream para Array de Byte */
	private byte[] converteStreamParaByte(InputStream imagem) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int reads;
		try {
			reads = imagem.read();
			while (reads != -1) {
				baos.write(reads);
				reads = imagem.read();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return baos.toByteArray();
	}
}
