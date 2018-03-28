package bookstore.cart.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookstore.book.domain.Book;
import bookstore.book.service.BookService;
import bookstore.cart.domain.Cart;
import cn.itcast.servlet.BaseServlet;
import bookstore.cart.domain.*;
public class CartServlet extends BaseServlet {
	/**
	 * ��ӹ�����Ŀ
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1���õ���
		 * 2���õ���Ŀ���õ�ͼ���������
		 * 3������Ŀ��ӵ�����
		 */
		/*
		 * 1���õ���
		 */
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		/*
		 * �����ݵ�ֻ��bid������
		 * 2���õ���Ŀ
		 * 	�õ�ͼ�������
		 *  �ȵõ�ͼ���bid��Ȼ����Ҫͨ��bid��ѯ���ݿ�õ�book
		 *  ����������
		 */
		String bid = request.getParameter("bid");
		Book book = new BookService().load(bid);
		int count = Integer.parseInt(request.getParameter("count"));
		CartItem cartItem = new CartItem();
		cartItem.setBook(book);
		cartItem.setCount(count);
		
		/*
		 * 3������Ŀ��ӵ�����
		 */
		cart.add(cartItem);
		return "f:/jsps/cart/list.jsp";
	}
	/**
	 * ��չ�����Ŀ
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * 1���õ���
		 * 2�����ó���clear
		 */
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		cart.clear();
		return "f:/jsps/cart/list.jsp";
	}
	/**
	 * ɾ��������Ŀ
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1���õ���
		 * 2���õ�Ҫɾ����bid
		 */
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		String bid = request.getParameter("bid");
		cart.delete(bid);
		return "f:/jsps/cart/list.jsp";
	}

}
