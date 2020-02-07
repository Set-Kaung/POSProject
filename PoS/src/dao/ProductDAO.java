package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.ProductDTO;
import model.Product;

public class ProductDAO {
	String connectionURL = "jdbc:mysql://localhost:3306/pos_project";
	Connection connection = null;
	
	public ProductDAO()
	{
		try {
			connection = DriverManager.getConnection(connectionURL,"root","FrostyAF");
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
	public void restockProduct(Long ID,Long stock) 
	{
		String query = "UPDATE Product set Stock=? WHERE ID=?";
		try 
		{
			PreparedStatement statement = this.connection.prepareStatement(query);
			statement.setLong(1, stock);
			statement.setLong(2,ID);
			statement.executeUpdate();
			
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public ProductDTO getProductByBarcode(Long barcode) 
	{
		String query = "SELECT Name,Price FROM Product WHERE Barcode=?";
		String name = null;
		Double price = null;
		
		try 
		{
			PreparedStatement statement = this.connection.prepareStatement(query);
			statement.setLong(1, barcode);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) 
			{
				name = rs.getString("Name");
				price = rs.getDouble("Price");
			}
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		return new ProductDTO(name,price);
		
}
	public static void main(String[] args) {
		ProductDAO dao = new ProductDAO();
//		String name = dao.getProductByBarcode(112233L);
//		System.out.println(name);
	}
}