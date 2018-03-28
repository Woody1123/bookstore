package bookstore.category.dao;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import bookstore.category.domain.Category;
import cn.itcast.jdbc.TxQueryRunner;

public class CategoryDao {
	private QueryRunner qr = new TxQueryRunner();
	/**
	 * ��ѯ���з���
	 */
	public List<Category> findAll() {
		try {
			String sql = "select * from category";
			return qr.query(sql, new BeanListHandler<Category>(Category.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
	/**
	 * ��ӷ���
	 * @param category
	 */
	public void add(Category category) {
		try {
			String sql = "insert into category values(?,?)";
			//��Ϊ�ǿɱ�����������Ҳ��
			qr.update(sql, category.getCid(),category.getCname());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * ɾ������
	 * @param cid
	 */
	public void delete(String cid) {
		try {
			String sql = "delete from category where cid=?";
			//��Ϊ�ǿɱ�����������Ҳ��
			qr.update(sql,cid);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * ���ط���
	 * @param cid
	 * @return
	 */
	public Category load(String cid) {
		try {
			String sql = "select * from category where cid=?";
			return qr.query(sql, new BeanHandler<Category>(Category.class),cid);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * �޸ķ���
	 * @param category
	 */
	public void edit(Category category) {
		try {
			String sql = "update category set cname=? where cid=?";
			qr.update(sql,category.getCname(),category.getCid());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
