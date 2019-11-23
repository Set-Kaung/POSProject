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
 String connectionURL = "jdbc:mysql://localhost:3306/pos_project";
 Connection connection = null;
 
 public BrandDAO() 
 {
	 try 
	 {
		 this.connection = DriverManager.getConnection(connectionURL,"root","");
		 
	 }
	 catch(Exception e) 
	 {
		 e.printStackTrace();
	 }
 }
 
 public List<Brand> getAllBrand()
 {
	 
	 List<Brand> brands = new ArrayList<Brand>();
	 
	 String sql = "SELECT * FROM Brand";
	 try 
	 {
		 Statement statement = this.connection.createStatement();
		 ResultSet brandRS = statement.executeQuery(sql);
		 while(brandRS.next()) {
			 
			 
		 Long ID = brandRS.getLong("ID");
		 String name = brandRS.getString("Name");
		 
		 Brand brand = new Brand(ID,name);
		 brands.add(brand);
		
		 }
		 
		 
	 }
	 catch(Exception e) 
	 {
		 e.printStackTrace();
	 }
	 
	 finally 
	 {
		
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
	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 }
 
 public static void main(String[] args) {
	BrandDAO dao = new BrandDAO();
	List<Brand> brands = dao.getAllBrand();
	
	for(Brand brand:brands) 
	{
		System.out.println(brand.getName());
	}
}
 
}
