package servlet;

/*abaixo da versão do tomcat 10 usamos javax*/
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoLoginRepository;
import model.ModelLogin;

// Os chamados controller são as servlets
@WebServlet("/ServletLogin") // notação que identifica que é uma servlet e faz o mapeamento de URL que vem da
								// tela
public class ServletLogin extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private DaoLoginRepository daoLoginRepository = new DaoLoginRepository();

	public ServletLogin() {
		super();

	}

	// Recebe os dados pela url por parâmetros
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/*
	 * -------------------------------1° Recebe os dados por um
	 * formulário---------------------------
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String url = request.getParameter("url");

		/*-------------------2° Cria objeto e seta os parametros recebitos do formulario no objeto-------------------------*/
		try {
			if (login != null && !login.isEmpty() && senha != null && !senha.isEmpty()) {
				ModelLogin modelLogin = new ModelLogin();
				modelLogin.setLogin(login);
				modelLogin.setSenha(senha);

				if (daoLoginRepository.validarAutenticacao(modelLogin)) {

					request.getSession().setAttribute("usuario", modelLogin.getLogin());
					String formPrincipal = "principal/Principal.jsp";
					if (url == null || url.equals("null")) {
						url = formPrincipal;
					}
					RequestDispatcher redirecionar = request.getRequestDispatcher(url);
					redirecionar.forward(request, response);
				} else {
					String formLogin = "index.jsp";
					RequestDispatcher redirecionar = request.getRequestDispatcher(formLogin);
					request.setAttribute("msg", "Login ou senha não cadastrados");
					redirecionar.forward(request, response);
			   }
			} /*------------3° redirecionamento da pagina caso login esteja errado-------------------*/

		} catch (SQLException | ServletException | IOException e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);

		}
	}
}