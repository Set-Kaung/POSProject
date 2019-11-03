package services;

import java.util.List;
import java.util.Scanner;

import model.Staff;

public class LoginValidation 
{

	String name, password;
	StaffService service = new StaffServiceImpl();
	PBKDF2Hasher hasher = new PBKDF2Hasher();

	public boolean isValid(String name, char[] password)
	{
		boolean valid = false;
		List<Staff> staffs = service.getAllStaffs();
		for (Staff staff : staffs) {
			this.name = staff.getName();
			this.password = staff.getPassword();
			boolean truePass = hasher.checkPassword(password, this.password);
			System.out.println("Username:" + name + " Password:" + password);

			if (this.name.equals(name) && truePass) 
			{
				valid = true;
				break;
			}

		}

		return valid;
	}

	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter username");
		String username = sc.nextLine();
		System.out.println("Enter password");
		String password = sc.nextLine();
		LoginValidation validation = new LoginValidation();

	}

}
