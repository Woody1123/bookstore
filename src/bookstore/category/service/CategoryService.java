package bookstore.category.service;

import java.util.List;

import bookstore.book.dao.BookDao;
import bookstore.category.dao.CategoryDao;
import bookstore.category.domain.Category;
import bookstore.category.web.servlet.admin.CategoryException;

public class CategoryService {
	private CategoryDao categoryDao = new CategoryDao();
	private BookDao bookDao = new BookDao();
	
	/**
	 * 查询所有分类
	 */
	public List<Category> findAll() {
		return categoryDao.findAll();
	}
	
	public void add(Category category) {
		categoryDao.add(category);
	}
	
	/**
	 * 删除分类
	 * @param cid
	 * @throws CategoryException 
	 */
	public void delete(String cid) throws CategoryException {
		//获取该分类下图书的本数
		int count = bookDao.getCountByCid(cid);
		//如果该分类下存在图书，不让删除，抛出异常
		if(count>0) throw new CategoryException("该分类还有书，不能删");
		//删除该分类
		categoryDao.delete(cid);
	}
	/**
	 * 加载分类
	 * @param cid
	 * @return
	 */
	public Category load(String cid) {
		
		return categoryDao.load(cid);
	}

	public void edit(Category category) {
		categoryDao.edit(category);
	}

}
