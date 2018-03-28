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
		 * 1����session�л�ȡ�û���Ϣ
		 * 2���ж����session�д����û���Ϣ������
		 * 3�����򣬱��������Ϣ��ת����login.jsp��ʾ
		 */
		HttpServletRequest httpRequest = (HttpServletRequest) req;
		User user = (User) httpRequest.getSession().getAttribute("session_user");
		if(user != null){
			filterChain.doFilter(req, res);
		} else{
			httpRequest.setAttribute("msg", "��û��¼");
			httpRequest.getRequestDispatcher("/jsps/user/login.jsp").forward(httpRequest,res);
		}
	}

	public void destroy() {

	}
}