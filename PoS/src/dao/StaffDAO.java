package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Staff;
import services.PBKDF2Hasher;

public class StaffDAO {
	String connectionUrl = "jdbc:mysql://localhost:3306/pos_project";
    Connection connection = null;
    
    PBKDF2Hasher hasher = new PBKDF2Hasher();
    
    public StaffDAO() 
    {
    	try {
			this.connection = DriverManager.getConnection(connectionUrl,"root","");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
    }
    
    public List<Staff> getAllStaff()
    {
    	List<Staff> staffs = new ArrayList<Staff>();
    	
    	try 
    	{
    		Statement statement = this.connection.createStatement();
    		
    		ResultSet staffRS = statement.executeQuery("SELECT name,password FROM Staff");
    		
    		while(staffRS.next()) 
    		{
    			String name = staffRS.getString("Name");
    			String password = staffRS.getString("Password");
    			
    			Staff staff = new Staff(name,password);
    			
    			staffs.add(staff);
    		}
    		statement.close();
    		staffRS.close();
    		
    	}
    	catch(Exception e) 
    	{
    		e.printStackTrace();
    	}
    	
    	return staffs;
    	
    }
    
    public void insertStaff(String username, char [] password) 
    {
    	try {
			
			String hashedPassword = hasher.hash(password);
			
			PreparedStatement statement = this.connection.prepareStatement("INSERT INTO Staff (Name,Password) VALUES(?,?)");
			statement.setString(1, username);
			statement.setString(2, hashedPassword);
			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    }
    
    
}
