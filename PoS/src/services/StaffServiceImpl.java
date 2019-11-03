package services;

import java.util.List;

import dao.StaffDAO;
import model.Staff;

public class StaffServiceImpl implements StaffService{
	StaffDAO dao = new StaffDAO();

	@Override
	public List<Staff> getAllStaffs() {
		List<Staff> staffs = dao.getAllStaff();
		
		return staffs;
	}

	@Override
	public void insertStaff(String username, char[] password) {
		dao.insertStaff(username, password);
		
	}
	
	
	

}
