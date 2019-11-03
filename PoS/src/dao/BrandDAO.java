package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Brand;


public class BrandDAO {
	String connectionUrl = "jdbc:mysql://localhost:3306/pos_project";
    Connection connection = null;
    
    public BrandDAO()
    {
    	try {
			this.connection = DriverManager.getConnection(connectionUrl,"root","");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
    }
    
    public List<Brand> getAllBrand()
    {
    	List<Brand> brands = new ArrayList<Brand>();
    	
    	
    	try 
    	{
    		Statement statement = this.connection.createStatement();
    		
    		ResultSet brandRS = statement.executeQuery("SELECT * FROM Brand");
    		
    		while(brandRS.next()) 
    		{
    			Long id = brandRS.getLong("ID");
    			String name = brandRS.getString("Name");
    			
    			Brand brand = new Brand(id,name);
    			
    			brands.add(brand);
    		}
    		statement.close();
    		brandRS.close();
    		
    	}
    	catch(Exception e) 
    	{
    		e.printStackTrace();
    	}
    	return brands;
    	
    }
    
    public void addBrand(String name) 
    {
    	try {
			PreparedStatement statement = this.connection.prepareStatement("INSERT INTO Brand (Name) Values(?)");
    		statement.setString(1,name);
    		statement.executeUpdate();
    		statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    public void updateBrand(Long ID, String name) 
    {
    	PreparedStatement statement;
		try {
			statement = this.connection.prepareStatement("UPDATE Brand SET Name=? WHERE ID=?");
			statement.setString(1,name);
	    	statement.setLong(2, ID);
	    	statement.executeUpdate();
	    	statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    }
    public void deleteBrand(Long ID) 
    {
    	PreparedStatement statement;
		try {
			statement = this.connection.prepareStatement("DELETE FROM Brand WHERE ID=?");
			statement.setLong(1, ID);
	    	statement.executeUpdate();
	    	statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
