package bookstore.book.service;

import java.util.List;

import bookstore.book.dao.BookDao;
import bookstore.book.domain.Book;

public class BookService {
	private  BookDao bookDao = new BookDao();
	/**
	 * 查询所有图书
	 * @return
	 */
	public List<Book> findAll(){
		return bookDao.findAll();
	}
	/**
	 * 分类查询
	 * @param cid
	 * @return
	 */
	public List<Book> findByCategory(String cid) {
		return bookDao.findByCategory(cid);
	}
	public Book load(String bid) {
		
		return bookDao.finyByBid(bid);
	}
	
	/**
	 * 添加图书
	 */
	public void add(Book book) {
		bookDao.add(book);
	}
	
	public void delete(String bid){
		bookDao.delete(bid);
	}
	public void edit(Book book) {
		bookDao.edit(book);
	}
}
