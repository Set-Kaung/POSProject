package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Product;

public class ProductDAO {
	String connectionURL = "jdbc:mysql://localhost:3306/pos_project";
	Connection connection = null;
	
	public ProductDAO()
	{
		try {
			connection = DriverManager.getConnection(connectionURL,"root","");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public List<Product> getAllProduct()
	{
		
		List<Product> products = new ArrayList<Product>();
		
		
		try {
			Statement statement = this.connection.createStatement();
			ResultSet RS = statement.executeQuery("SELECT * FROM Product");
			
			while(RS.next()) 
			{
				Long ID = RS.getLong("ID");
				String name = RS.getString("Name");
				String des = RS.getString("Description");
				Long brandID = RS.getLong("Brand_ID");
				Long categoryID = RS.getLong("Category_ID");
				Double price = RS.getDouble("Price");
				Long stock = RS.getLong("Stock");
				Long barcode = RS.getLong("Barcode");
				
				Product product = new Product(ID,name,des,brandID,categoryID,price,stock,barcode);
				products.add(product);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return products;
		
	}
	
	public void addProduct(Product product) 
	{
		String query = "INSERT INTO Product (Name,Description,Brand_ID,Category_ID,Price,Stock,Barcode) Values(?,?,?,?,?,?,?)";
		try {
			PreparedStatement statement = this.connection.prepareStatement(query);
			statement.setString(1, product.getName());
			statement.setString(2, product.getDescription());
			statement.setLong(3, product.getBrandID());
			statement.setLong(4, product.getCategoryID());
			statement.setDouble(5, product.getPrice());
			statement.setLong(6, product.getStock());
			statement.setLong(7,product.getBarcode());
			
			statement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void deleteProduct(Long ID) 
	{
		String query = "DELETE FROM Product WHERE ID=?";
		try 
		{
			PreparedStatement statement = this.connection.prepareStatement(query);
			statement.setLong(1, ID);
			statement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateProduct(Product product) 
	{
		String query= "UPDATE Product SET Name = ?,Description = ?, Brand_ID = ?,Category_ID = ?,Price = ?,Stock = ?,Barcode = ? WHERE ID = ?";
		try 
		{
			PreparedStatement statement = this.connection.prepareStatement(query);
			statement.setString(1, product.getName());
			statement.setString(2, product.getDescription());
			statement.setLong(3, product.getBrandID());
			statement.setLong(4, product.getCategoryID());
			statement.setDouble(5, product.getPrice());
			statement.setLong(6, product.getStock());
			statement.setLong(7,product.getBarcode());
			statement.setLong(8, product.getID());
			statement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
}
