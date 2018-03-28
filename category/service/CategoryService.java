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
	 * ��ѯ���з���
	 */
	public List<Category> findAll() {
		return categoryDao.findAll();
	}
	
	public void add(Category category) {
		categoryDao.add(category);
	}
	
	/**
	 * ɾ������
	 * @param cid
	 * @throws CategoryException 
	 */
	public void delete(String cid) throws CategoryException {
		//��ȡ�÷�����ͼ��ı���
		int count = bookDao.getCountByCid(cid);
		//����÷����´���ͼ�飬����ɾ�����׳��쳣
		if(count>0) throw new CategoryException("�÷��໹���飬����ɾ");
		//ɾ���÷���
		categoryDao.delete(cid);
	}
	/**
	 * ���ط���
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
