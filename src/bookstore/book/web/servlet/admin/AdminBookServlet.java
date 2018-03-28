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
	 * 添加图书之前
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String addPre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 查询所有分类，保存到request域，转发到add.jsp
		 * add.jsp中把所有的分类使用下拉列表显示在表单中
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
		 * 1、获取参数bid，通过bid调用service方法得到book对象
		 * 2、获取所有分类，保存到request域中
		 * 3、保存book到request域中，转发到desc.jsp
		 */
		request.setAttribute("book", bookService.load(request.getParameter("bid")));
		request.setAttribute("categoryList", categoryService.findAll());
		return "f:/adminjsps/admin/book/desc.jsp";
	}
	/**
	 * 查看所有图书
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
	 * 删除图书
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
	 * 修改图书
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//图片字段不在其中
		Book book = CommonUtils.toBean(request.getParameterMap(),Book.class);
		//需要转化
		String bname = new String(book.getBname().getBytes("iso8859-1"),"utf-8");
		System.out.println(bname);
		//可以获取字段
		//System.out.println(book.getPrice());  
		Category category = CommonUtils.toBean(request.getParameterMap(), Category.class);
		book.setCategory(category);
		book.setBname(bname);
		bookService.edit(book);
		//不仅是乱码而且还会自己增加
		System.out.println(book.getBname());
		return findAll(request,response);
	}
}
