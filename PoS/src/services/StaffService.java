package services;

import java.util.List;

import model.Staff;

public interface StaffService {
	List<Staff> getAllStaffs();
	
	void insertStaff(String username, char[] password);
}
