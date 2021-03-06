package bookstore.cart.domain;

import java.math.BigDecimal;

import bookstore.book.domain.Book;

/**
 * 购物车条目类
 * @author Administrator
 *
 */
public class CartItem {
	private Book book;//商品
	private int count;//数量
	
	/**
	 * 小计方法
	 * @return
	 * 处理了二进制运算问题
	 */
	public double getSubtotal(){//小计，没有对应的成员
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
