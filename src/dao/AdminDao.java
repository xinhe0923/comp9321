package dao;

import dto.Admin;

public interface AdminDao {
	public void saveOrUpdate(Admin admin);

	public Admin getAdaminByUsernameAndPassword(String username, String password);

	public Admin getAdminByUsername(String username);
}
