package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Product;

public class ProductDAO {
	String connectionUrl = "jdbc:mysql://localhost:3306/pos_project";
    Connection connection = null;
    private List<Product> products= new ArrayList<Product>();
    public ProductDAO()
    {
    	try {
			this.connection = DriverManager.getConnection(connectionUrl,"root","");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
    }
    
    public List<Product> getAllProduct()
    
    {
    	
    	try {
			Statement statement = this.connection.createStatement();
			ResultSet productRS = statement.executeQuery("SELECT * FROM Product");
			
			while(productRS.next()) 
			{
				Long id = productRS.getLong("ID");
				String name = productRS.getString("Name");
				String description = productRS.getString("Description");
				Long categoryID = productRS.getLong("Category_ID");
				Long Brand_ID = productRS.getLong("Brand_ID");
				Long price = productRS.getLong("Price");
				Long stock = productRS.getLong("Stock");
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
    	
		return products;
    	
    }
}
