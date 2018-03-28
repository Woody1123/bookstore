package bookstore.book.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookstore.book.service.BookService;
import cn.itcast.servlet.BaseServlet;

public class BookServlet extends BaseServlet {
	private BookService bookService = new BookService();
	public String load(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1���õ�����bid
		 * 2����ѯ�õ�book
		 * 3�����棬ת����desc.jsp
		 */
		request.setAttribute("book", bookService.load(request.getParameter("bid")));
		return "f:jsps/book/desc.jsp";
	}
	/**
	 * ��ѯ����ͼ��
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("bookList", bookService.findAll());
		return "f:/jsps/book/list.jsp";
	}
	/**
	 * �������ѯ
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findByCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cid =request.getParameter("cid");
		request.setAttribute("bookList", bookService.findByCategory(cid));
		return "f:/jsps/book/list.jsp";
	}

}
