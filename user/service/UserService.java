package bookstore.user.service;

import bookstore.user.dao.UserDao;
import bookstore.user.domain.User;

public class UserService {
	private UserDao userDao = new UserDao(); 
	/**
	 * 注册功能
	 * @param form
	 * @throws UserException 
	 */
	public void regist(User form) throws UserException{
		//校验用户名
		User user = userDao.findByUsername(form.getUsername());
		if(user != null) throw new UserException("用户已被注册");
		
		//校验email
		user  = userDao.findByUsername(form.getEmail());
		if(user != null) throw new UserException("Email已被注册");
		
		//插入用户到数据库
		userDao.add(form);
	}
	/**
	 * 激活功能
	 * @throws UserException 
	 */
	public void active(String code) throws UserException{
		/*
		 * 1、使用code查询数据库，得到user
		 */
		User user = userDao.findByCode(code);
		/*
		 * 2、如果user不存在，说明激活码错误
		 */
		if(user == null) throw new UserException("激活码无效");
		/*
		 * 3、校验用户的状态是否为激活状态，如果已激活，说明是二次激活，抛出异常
		 */
		if(user.isState()) throw new UserException("已经激活过了，不要二次激活");
		
		/*
		 * 4、修改用户的状态
		 */
		userDao.updateState(user.getUid(), true);
	}
	/**
	 * 登录功能
	 * @throws UserException 
	 */
	public User login(User form) throws UserException{
		/*
		 * 1、使用username查询，得到User
		 * 2、如果user为null，抛出异常（用户不存在）
		 * 3、比较form和user的密码，如果不同，抛出异常（密码错误）
		 * 4、查看用户的状态，若为false，抛出异常（尚未激活）
		 * 5、返回user
		 */
		User user =userDao.findByUsername(form.getUsername());
		if(user == null) throw new UserException("用户名不存在");
		if(!user.getPassword().equals(form.getPassword()))
			throw new UserException("密码错误");
		if(!user.isState()) throw new UserException("尚未激活");
		//表单仅用户名和密码 user因为已经成功登录所以返回更全的信息
		return user;
	}
}
