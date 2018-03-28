package bookstore.book.web.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookstore.book.domain.Book;
import bookstore.book.service.BookService;
import bookstore.category.domain.Category;
import bookstore.category.service.CategoryService;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

public class AdminBookServlet extends BaseServlet {
	private BookService bookService = new BookService();
	private CategoryService categoryService = new CategoryService();
	/**
	 * ���ͼ��֮ǰ
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String addPre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * ��ѯ���з��࣬���浽request��ת����add.jsp
		 * add.jsp�а����еķ���ʹ�������б���ʾ�ڱ���
		 */
		request.setAttribute("categoryList", categoryService.findAll());
		return "f:/adminjsps/admin/book/add.jsp";
	}
		
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String load(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1����ȡ����bid��ͨ��bid����service�����õ�book����
		 * 2����ȡ���з��࣬���浽request����
		 * 3������book��request���У�ת����desc.jsp
		 */
		request.setAttribute("book", bookService.load(request.getParameter("bid")));
		request.setAttribute("categoryList", categoryService.findAll());
		return "f:/adminjsps/admin/book/desc.jsp";
	}
	/**
	 * �鿴����ͼ��
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("bookList", bookService.findAll());
		return "f:/adminjsps/admin/book/list.jsp";
	}
	
	/**
	 * ɾ��ͼ��
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bid = request.getParameter("bid");
		bookService.delete("bid");
		bookService.delete(bid);
		return findAll(request,response);
	}
	
	/**
	 * �޸�ͼ��
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ͼƬ�ֶβ�������
		Book book = CommonUtils.toBean(request.getParameterMap(),Book.class);
		//��Ҫת��
		String bname = new String(book.getBname().getBytes("iso8859-1"),"utf-8");
		System.out.println(bname);
		//���Ի�ȡ�ֶ�
		//System.out.println(book.getPrice());  
		Category category = CommonUtils.toBean(request.getParameterMap(), Category.class);
		book.setCategory(category);
		book.setBname(bname);
		bookService.edit(book);
		//������������һ����Լ�����
		System.out.println(book.getBname());
		return findAll(request,response);
	}
}
