package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Category;
import model.Staff;

public class CategoryDAO {
	String connectionUrl = "jdbc:mysql://localhost:3306/pos_project";
    Connection connection = null;
    
    public CategoryDAO()
    {
    	try {
			this.connection = DriverManager.getConnection(connectionUrl,"root","");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
    }
    
    public List<Category> getAllCategory()
    {
    	List<Category> categories = new ArrayList<Category>();
    	
    	
    	try 
    	{
    		Statement statement = this.connection.createStatement();
    		
    		ResultSet categoryRS = statement.executeQuery("SELECT * FROM Category");
    		
    		while(categoryRS.next()) 
    		{
    			Long id = categoryRS.getLong("ID");
    			String name = categoryRS.getString("Name");
    			
    			Category category = new Category(id,name);
    			
    			categories.add(category);
    		}
    		statement.close();
    		categoryRS.close();
    		
    	}
    	catch(Exception e) 
    	{
    		e.printStackTrace();
    	}
    	return categories;
    	
    }
    
    public void addCategory(String name) 
    {
    	try 
    	{
    		
    		PreparedStatement statement = this.connection.prepareStatement("INSERT INTO Category (Name) Values(?)");
    		statement.setString(1,name);
    		statement.executeUpdate();
    		statement.close();
    	}
    	catch(SQLException e) 
    	{
    		e.printStackTrace();
    	}
    }
    
    public void updateCategory(Long ID,String name) 
    {
    	try {
			PreparedStatement statement = this.connection.prepareStatement("UPDATE Category SET Name=? where ID=? ");
			statement.setString(1,name);
			statement.setLong(2,ID);
			statement.executeUpdate();
			
			statement.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
    	
    	
    }
    public void deleteCategory(Long ID) 
    {
    	try 
    	{
    		PreparedStatement statement = this.connection.prepareStatement("DELETE FROM Category where ID=?");
    		statement.setLong(1, ID);
    		statement.executeUpdate();
    		
    		statement.close();
    		
    	}
    	catch (Exception e) {
			e.printStackTrace();
		}
    }
}
