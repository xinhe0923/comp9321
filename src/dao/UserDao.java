package dao;

import java.util.List;

import dto.User;

public interface UserDao {

	/**
	 * register a new user if user.getID ==0 otherwise update changes made to
	 * input user
	 * 
	 * @param user
	 */
	public void saveOrUpdate(User user);

	/**
	 * get a user by username and password
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public User getLoginUser(String username, String password);

	/**
	 * get user by username
	 * 
	 * @param username
	 * @return
	 */
	public User getUserByUsername(String username);

	/**
	 * get user by uuid, this should only be used in email verification related
	 * operations
	 * 
	 * @param uuid
	 * @return
	 */
	public User getUserByUuid(String uuid);
	
	public User getUserById(int id);

	public List<User> getAllUsers();

}
