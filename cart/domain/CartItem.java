package bookstore.cart.domain;

import java.math.BigDecimal;

import bookstore.book.domain.Book;

/**
 * ���ﳵ��Ŀ��
 * @author Administrator
 *
 */
public class CartItem {
	private Book book;//��Ʒ
	private int count;//����
	
	/**
	 * С�Ʒ���
	 * @return
	 * �����˶�������������
	 */
	public double getSubtotal(){//С�ƣ�û�ж�Ӧ�ĳ�Ա
		BigDecimal d1 = new BigDecimal(book.getPrice()+"");
		BigDecimal d2 = new BigDecimal(count+"");
		return d1.multiply(d2).doubleValue();
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public CartItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CartItem(Book book, int count) {
		super();
		this.book = book;
		this.count = count;
	}
	
}
