package bookstore.order.service;

import java.sql.SQLException;
import java.util.List;

import bookstore.order.dao.OrderDao;
import bookstore.order.domain.Order;
import cn.itcast.jdbc.JdbcUtils;

public class OrderService {
	private OrderDao orderDao = new OrderDao();
	
	/**
	 * ֧������
	 * @param oid
	 */
	public void zhiFu(String oid){
		/*
		 * 1����ȡ������״̬
		 *  ���״̬Ϊ1����ôִ������Ĵ���
		 *  ���״̬��Ϊ1����ô������ʲô������
		 */
		int state = orderDao.getStateByOid(oid);
		if(state == 1){
			//�޸Ķ���״̬Ϊ2
			orderDao.updateState(oid, 2);
		}
	}
	/**
	 * ��Ӷ���
	 * ��Ҫ��������
	 * @param order
	 * @return
	 */
	public void add(Order order){
		try{
			//��������
			JdbcUtils.beginTransaction();
			orderDao.addOrder(order);//���붩��
			orderDao.addOrderItemList(order.getOrderItemList());//���붩���е�������Ŀ
			//�ύ����
			JdbcUtils.commitTransaction();
		}catch(Exception e){
			//�ع�����
			try {
				JdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {
				throw new RuntimeException(e);
			}
			//ִ�гɹ� �����ݿ�δ�ı� ����Ҫ���쳣������
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * �ҵĶ���
	 * @param uid
	 * @return
	 */
	public List<Order> myOrders(String uid) {
		return orderDao.findByUid(uid);
	}
	
	/**
	 * ���ض���
	 * @param oid
	 * @return
	 */
	public Order load(String oid) {
		return orderDao.load(oid);
	}
	
	/**
	 * ȷ���ջ�
	 * @param oid
	 * @throws OrderException
	 */
	public void confirm(String oid) throws OrderException{
		/*
		 * 1��У�鶩��״̬���������3���׳��쳣
		 */
		int state =  orderDao.getStateByOid(oid);//��ȡ����״̬
		if(state!=3) throw new OrderException("����ȷ��ʧ�ܣ����쳣");
		
		/*
		 * 2���޸Ķ���״̬Ϊ4����ʾ���׳ɹ�
		 */
		orderDao.updateState(oid, 4);
	}
}
