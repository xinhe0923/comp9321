package service;

import dao.AdminDao;
import dao.AdminDaoImpl;
import dto.Admin;


public class AdminService {
	private AdminDao adminDao = AdminDaoImpl.getInstance();

	public Admin login(String username, String password) {
		Admin admin = adminDao.getAdaminByUsernameAndPassword(username,
		        password);
		if (admin ==null){
			return null;
		}
		return admin;
	}
	
	public boolean register(Admin admin){
		Admin exist_admin = adminDao.getAdminByUsername(admin.getUsername());
		if (exist_admin != null) {
			return false;
		}
		return true;
	}

}
