package bookstore.order.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import bookstore.book.domain.Book;
import bookstore.order.domain.Order;
import bookstore.order.domain.OrderItem;
import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;

public class OrderDao {
	private QueryRunner qr = new TxQueryRunner();
	/**
	 * ��Ӷ���
	 * @param order
	 */
	public void addOrder(Order order){
		try{
			String sql="insert into orders values(?,?,?,?,?,?)";
			/*
			 * ����util��Dateת����sql��Timestamp
			 */
			Timestamp timestamp = new Timestamp(order.getOrdertime().getTime());
			Object[] params = {order.getOid(),timestamp,
					order.getTotal(),order.getState(),order.getOwner().getUid(),
					order.getAddress()};
			qr.update(sql,params);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	/**
	 * ���붩����Ŀ
	 * @param orderItemList
	 */
	public void addOrderItemList(List<OrderItem> orderItemList){
		/**
		 * QueryRunner���batch(String sql,Object[][] params)
		 * ����params�Ƕ��һά����
		 * ÿ��һά���鶼��sql��һ��ִ��һ�Σ����һά�����ִ�ж��
		 */
		try{ 
			String sql = "insert into orderitem values(?,?,?,?,?)";
			/*
			 * ��orderItemLsitת���ɶ�ά����
			 * 	��һ��orderItem����ת����һ��һά����
			 */
			Object[][] params = new Object[orderItemList.size()][];
			//ѭ������orderItemList��ʹ��ÿ��orderItem����Ϊparams��ÿһ��һά���鸳ֵ
			for(int i = 0;i<orderItemList.size();i++){
				OrderItem item = orderItemList.get(i);
				params[i] = new Object[]{item.getIid(),item.getCount(),
						item.getSubtotal(),item.getOrder().getOid(),
						item.getBook().getBid()
				};
				
			}
			qr.batch(sql, params);//ִ��������
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * ��uid��ѯ����
	 * @param uid
	 * @return
	 */
	public List<Order> findByUid(String uid) {
		/*
		 * 1��ͨ��uid��ѯ����ǰ�û�������List<Order>
		 * 2��ѭ������ÿ��Order��Ϊ�������������OrderItem
		 */
		try{
			/*
			 * 1���õ���ǰ�û������ж���
			 */
			String sql = "select * from orders where uid=?";
			List<Order> orderList = qr.query(sql, new BeanListHandler<Order>(Order.class),uid);
			/*
			 * 2��ѭ������ÿ��Order��Ϊ��������Լ����еĶ�����Ŀ
			 */
			for(Order order : orderList){
				loadOrderItems(order);//Ϊorder�����������������Ŀ
			}
			/*
			 * 3�����ض����б�
			 */
			return orderList;
		} catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	/**
	 * ����ָ���Ķ������еĶ�����Ŀ
	 * @param order
	 * @throws SQLException 
	 */
	private void loadOrderItems(Order order) throws SQLException {
		/*
		 * ��ѯ���ű�orderitem��book
		 */
		String sql = "select * from orderitem i,book b  where i.bid=b.bid and oid=?";
		/*
		 * ��Ϊһ�н������Ӧ�Ĳ�����һ��javabean,���Բ�����ʹ��BeanListHandler,����MapListHandler
		 */
		List<Map<String,Object>> mapList = qr.query(sql, new MapListHandler(),order.getOid());
		/*
		 * mapList�Ƕ��map,ÿ��map��Ӧһ�н����
		 * һ�У�
		 * (iid=809691ECBCD14395BEECE381BC13A3CA,count=2,subtotal=24.40,oid=268AB441D4C349A1B53F3D14EE8DC171,bid=1,bname=javase1,price=12.20,author=zs,image=xxx,cid=1}
		 *������
		 *��Ҫʹ��һ��Map������������OrderItem��Book��Ȼ���ٽ������ߵĹ�ϵ����book���ø�OrderItem��
		 */
		/*
		 * ѭ������ÿ��Map��ʹ��map������������Ȼ������ϵ�����ս��һ��OrderItem����OrderItem��������
		 */
		List<OrderItem> orderItemList = toOrderItemList(mapList);
		order.setOrderItemList(orderItemList);
	}
	
	/**
	 * ��mapList��ÿ��mapת�����������󣬲�������ϵ
	 * @param mapList
	 * @return
	 */
	private List<OrderItem> toOrderItemList(List<Map<String, Object>> mapList) {
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		for(Map<String,Object> map:mapList){
			OrderItem item = toOrderItem(map);
			orderItemList.add(item);
		}
		return orderItemList;
	}
	
	/**
	 * ��һ��Mapת����һ��OrderItem
	 * @param map
	 * @return
	 */
	private OrderItem toOrderItem(Map<String, Object> map) {
		OrderItem orderItem = CommonUtils.toBean(map, OrderItem.class);
		Book book = CommonUtils.toBean(map, Book.class);
		orderItem.setBook(book);
		return orderItem;
	}
	
	/**
	 * ���ض���
	 */
	public Order load(String oid) {
		try{
			/*
			 * 1���õ���ǰ�û������ж���
			 */
			String sql = "select * from orders where oid=?";
			Order order = qr.query(sql, new BeanHandler<Order>(Order.class),oid);
			/*
			 * 2��Ϊorder��������������Ŀ
			 */
				loadOrderItems(order);
			/*
			 * 3�����ض����б�
			 */
			return order;
		} catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * ͨ��oid��ѯ����״̬
	 */
	public int getStateByOid(String oid){
		try {
			String sql = "select state from orders where oid=?";
			//����ǲ����ڵı���count(*) ��Ҫת��һ�� �����֪��
			return (Integer) qr.query(sql, new ScalarHandler(),oid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * �޸Ķ���״̬
	 * @param oid
	 * @param state
	 * @return
	 */
	public void updateState(String oid,int state){
		try {
			String sql = "update orders set state=? where oid=?";
			qr.update(sql,state,oid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
