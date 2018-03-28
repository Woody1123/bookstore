package bookstore.user.web.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bookstore.user.domain.User;

public class LoginFilter implements Filter {

	public LoginFilter() {
		super();
	}

	private FilterConfig filterConfig;

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
		/*
		 * 1、从session中获取用户信息
		 * 2、判断如过session中存在用户信息，放行
		 * 3、否则，保存错误信息，转发到login.jsp显示
		 */
		HttpServletRequest httpRequest = (HttpServletRequest) req;
		User user = (User) httpRequest.getSession().getAttribute("session_user");
		if(user != null){
			filterChain.doFilter(req, res);
		} else{
			httpRequest.setAttribute("msg", "还没登录");
			httpRequest.getRequestDispatcher("/jsps/user/login.jsp").forward(httpRequest,res);
		}
	}

	public void destroy() {

	}
}