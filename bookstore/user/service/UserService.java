package bookstore.user.service;

import bookstore.user.dao.UserDao;
import bookstore.user.domain.User;

public class UserService {
	private UserDao userDao = new UserDao(); 
	/**
	 * ע�Ṧ��
	 * @param form
	 * @throws UserException 
	 */
	public void regist(User form) throws UserException{
		//У���û���
		User user = userDao.findByUsername(form.getUsername());
		if(user != null) throw new UserException("�û��ѱ�ע��");
		
		//У��email
		user  = userDao.findByUsername(form.getEmail());
		if(user != null) throw new UserException("Email�ѱ�ע��");
		
		//�����û������ݿ�
		userDao.add(form);
	}
	/**
	 * �����
	 * @throws UserException 
	 */
	public void active(String code) throws UserException{
		/*
		 * 1��ʹ��code��ѯ���ݿ⣬�õ�user
		 */
		User user = userDao.findByCode(code);
		/*
		 * 2�����user�����ڣ�˵�����������
		 */
		if(user == null) throw new UserException("��������Ч");
		/*
		 * 3��У���û���״̬�Ƿ�Ϊ����״̬������Ѽ��˵���Ƕ��μ���׳��쳣
		 */
		if(user.isState()) throw new UserException("�Ѿ�������ˣ���Ҫ���μ���");
		
		/*
		 * 4���޸��û���״̬
		 */
		userDao.updateState(user.getUid(), true);
	}
	/**
	 * ��¼����
	 * @throws UserException 
	 */
	public User login(User form) throws UserException{
		/*
		 * 1��ʹ��username��ѯ���õ�User
		 * 2�����userΪnull���׳��쳣���û������ڣ�
		 * 3���Ƚ�form��user�����룬�����ͬ���׳��쳣���������
		 * 4���鿴�û���״̬����Ϊfalse���׳��쳣����δ���
		 * 5������user
		 */
		User user =userDao.findByUsername(form.getUsername());
		if(user == null) throw new UserException("�û���������");
		if(!user.getPassword().equals(form.getPassword()))
			throw new UserException("�������");
		if(!user.isState()) throw new UserException("��δ����");
		//�����û��������� user��Ϊ�Ѿ��ɹ���¼���Է��ظ�ȫ����Ϣ
		return user;
	}
}
