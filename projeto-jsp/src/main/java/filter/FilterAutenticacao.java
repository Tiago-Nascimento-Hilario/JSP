package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import connection.SingleConection;

@WebFilter(urlPatterns = { "/Principal/*" }) /* Intercepta toda requisição vierem do projeto ou mapeamento */
public class FilterAutenticacao extends HttpFilter implements Filter {

	private static Connection connection;

	public FilterAutenticacao() {
		super();

	}

	/* Encerra o processo quando o servidor é parado */
	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
				
		}
	}

	/*
	 * Intercepta as requisições e as resposta do sistema tudo que fizer no sitema
	 * vai passar por qui
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		try {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession();
			
			String usuarioLogado =  (String) session.getAttribute("usuario");
			String urlParaAutenticar = req.getServletPath();/*URL que esta sendo acessada*/
			
			if (usuarioLogado == null || (usuarioLogado != null && usuarioLogado.isEmpty()) && !urlParaAutenticar.contains("ServletLogin")) {
				
				RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url=" + urlParaAutenticar);
				request.setAttribute("msg", "Por favor façao login");
				redireciona.forward(request, response);
				return;/*Para a execução e redireciona para login*/
			}else {
				
				chain.doFilter(request, response);
				}
			
			connection.commit();
			
		}catch (Exception e) {
			e.printStackTrace();
			
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
			
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	/* Inicia processo ou recurso quando o servidor sobre o projeto */
	public void init(FilterConfig fConfig) throws ServletException {
		connection = SingleConection.getConnection();
	}

}
