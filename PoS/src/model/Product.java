package model;

public class Product {
	Long ID;
	String name;
	String description;
	Long brandID;
	String brandName;
	String categoryName;
	Long categoryID;
	Double price;
	Long stock;
	Long barcode;
	
	
	

	public Long getBarcode() {
		return barcode;
	}

	public void setBarcode(Long barcode) {
		this.barcode = barcode;
	}

	public Product(Long ID, String name, String description, Long brandID, Long categoryID, Double price, Long stock,Long barcode) {
		super();
		this.ID = ID;
		this.name = name;
		this.description = description;
		this.brandID = brandID;
		this.categoryID = categoryID;
		this.price = price;
		this.stock = stock;
		this.barcode = barcode;
	}

	
	public Product(String name, String description, Long brandID, Long categoryID, Double price, Long stock, Long barcode) 
	{
		super();
		this.name = name;
		this.description = description;
		this.brandID = brandID;
		
		this.categoryID = categoryID;
		this.price = price;
		this.stock = stock;
		this.barcode = barcode;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getBrandID() {
		return brandID;
	}

	public void setBrandID(Long brandID) {
		this.brandID = brandID;
	}

	public Long getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(Long categoryID) {
		this.categoryID = categoryID;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getStock() {
		return stock;
	}

	public void setStock(Long stock) {
		this.stock = stock;
	}

}
