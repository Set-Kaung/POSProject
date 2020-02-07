package view;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import java.util.List;

import javax.swing.JComboBox;

import model.Brand;
import model.Category;
import model.Product;
import services.BrandService;
import services.BrandServiceImpl;
import services.CategoryService;
import services.CategoryServiceImpl;
import services.ProductService;
import services.ProductServiceImpl;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;

public class ProductPanel extends JPanel {
	private JTextField nameTextField;
	private static List<Brand> brands;
	private static List<Category> categories;
	private	static BrandService brandService = new BrandServiceImpl();
	private static CategoryService categoryService = new CategoryServiceImpl();
    static JComboBox brandCombo,categoryCombo;
    private JTextField priceTextField;
    private JTextField stockTextField;
    private JTable productTable;
    private DefaultTableModel tableModel;
    private ProductService productService = new ProductServiceImpl();
    private JTextField descriptionTextField;
    private JTextField barcodeField;
	/**
	 * Create the panel.
	 */
	
    
    public void clearTextFields() 
    {
    	nameTextField.setText("");
    	descriptionTextField.setText("");
    	priceTextField.setText("");
    	stockTextField.setText("");
    	barcodeField.setText("");
    }
	
	public static void loadBrandComboBox() 
	{
		if(! (brandCombo.getItemCount()==0)) 
		{
			brandCombo.removeAllItems();
			brands = brandService.getAllBrand();
			for(Brand brand: brands)
			{
				brandCombo.addItem(brand);
			}
		}
		else 
		{
			brands = brandService.getAllBrand();
			for(Brand brand: brands)
			{
				brandCombo.addItem(brand);
			}
		}
		
		
	}
	
	public static void loadCategoryComboBox() 
	{
		if(! (categoryCombo.getItemCount()==0)) 
		{
			categoryCombo.removeAllItems();
			categories = categoryService.getAllCategories();
			for(Category category: categories)
			{
				categoryCombo.addItem(category);
			}
		}
		else 
		{
			categories = categoryService.getAllCategories();
			for(Category category: categories)
			{
				categoryCombo.addItem(category);
				
			}
		}
		
		
	}
	
	public void loadProducts() 
	{
		List<Product> products = productService.getAllProduct();
		tableModel = (DefaultTableModel)this.productTable.getModel();
		
		for(Product product:products) 
		{
			Object row[] = new Object[8];
			row[0] = product.getID();
			row[1] = product.getName();
			row[2] = product.getDescription();
			row[3] = product.getCategoryID();
			row[4] = product.getBrandID();
			row[5] = product.getPrice();
			row[6] = product.getStock();
			row[7] = product.getBarcode();
			
			tableModel.addRow(row);
		}
	}
	
	public void reloadProducts() 
	{
		tableModel.setRowCount(0);
		this.loadProducts();
	}
	
	
	
	public ProductPanel() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				productTable.clearSelection();
				
			}
		});
		
		setLayout(null);
		
		nameTextField = new JTextField();
		nameTextField.setToolTipText("Fill product name here\n");
		nameTextField.setBounds(80, 45, 130, 26);
		add(nameTextField);
		nameTextField.setColumns(10);
		
		JLabel nameLbl = new JLabel("Name:");
		nameLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		nameLbl.setBounds(19, 50, 61, 16);
		add(nameLbl);
		
		brandCombo = new JComboBox();
		brandCombo.setBounds(80, 73, 130, 27);
		add(brandCombo);
		
		JLabel brandLbl = new JLabel("Brand:");
		brandLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		brandLbl.setBounds(19, 77, 61, 16);
		add(brandLbl);
		
		JLabel categoryLbl = new JLabel("Category:");
		categoryLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		categoryLbl.setBounds(19, 105, 61, 16);
		add(categoryLbl);
		
	    categoryCombo = new JComboBox();
		categoryCombo.setBounds(80, 101, 130, 27);
		add(categoryCombo);
		
		priceTextField = new JTextField();
		priceTextField.setBounds(576, 40, 130, 26);
		add(priceTextField);
		priceTextField.setColumns(10);
		
		JLabel priceLbl = new JLabel("Price:");
		priceLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		priceLbl.setBounds(513, 45, 61, 16);
		add(priceLbl);
		
		JLabel quantityLbl = new JLabel("Stock:");
		quantityLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		quantityLbl.setBounds(513, 77, 61, 16);
		add(quantityLbl);
		
		stockTextField = new JTextField();
		stockTextField.setBounds(576, 72, 130, 26);
		add(stockTextField);
		stockTextField.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameTextField.getText();
				String des = descriptionTextField.getText();
				Brand brand = (Brand) brandCombo.getSelectedItem();
				Category category = (Category) categoryCombo.getSelectedItem();
				Long brandID = brand.getID();
				Long categoryID = category.getId();
				Double price = Double.parseDouble(priceTextField.getText());
				Long stock = Long.parseLong(stockTextField.getText());
				Long barcode = Long.parseLong(barcodeField.getText());
				
				
				
				Product product = new Product(name,des,brandID,categoryID,price,stock,barcode);
				productService.addProduct(product);
				brandCombo.setSelectedIndex(0);
				categoryCombo.setSelectedIndex(0);
				reloadProducts();
				clearTextFields();
			
			}
				
				
			
		});
		btnAdd.setBounds(229, 142, 83, 29);
		add(btnAdd);
		
		JButton updateBtn = new JButton("Update");
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = productTable.getSelectedRow();

				
				if(selectedIndex == -1 ) 
				{
					JOptionPane.showMessageDialog(null, "Please select a category");
				}
				else 
				{
					
					
					
					Long ID = Long.parseLong(tableModel.getValueAt(selectedIndex, 0).toString());
					String name = nameTextField.getText();
					String des = descriptionTextField.getText();
					Brand brand = (Brand) brandCombo.getSelectedItem();
					Category category = (Category) categoryCombo.getSelectedItem();
					Long brandID = brand.getID();
					Long categoryID = category.getId();
					Double price = Double.parseDouble(priceTextField.getText());
					Long stock = Long.parseLong(stockTextField.getText());
					Long barcode = Long.parseLong(barcodeField.getText());
					
					
					
					Product product = new Product(ID,name,des,brandID,categoryID,price,stock,barcode);
					productService.updateProduct(product);
					brandCombo.setSelectedIndex(0);
					categoryCombo.setSelectedIndex(0);
					reloadProducts();
					}
				}
				
			
		});
		updateBtn.setBounds(311, 142, 95, 29);
		add(updateBtn);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = productTable.getSelectedRow();

				
				if(selectedIndex == -1 ) 
				{
					JOptionPane.showMessageDialog(null, "Please select a category");
				}
				else 
				{
					Long ID = Long.parseLong(tableModel.getValueAt(selectedIndex, 0).toString());
					int choice = JOptionPane.showConfirmDialog(null, "Do you want to delete the product "+ID,"Warning!", JOptionPane.YES_NO_OPTION);
					if(choice == JOptionPane.YES_OPTION) {
					productService.deleteProduct(ID);
					reloadProducts();
					}
				}
			}
		});
		btnDelete.setBounds(404, 142, 95, 29);
		add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		
		scrollPane.setBounds(80, 211, 626, 180);
		add(scrollPane);
		
		productTable = new JTable();
		productTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				int selectedIndex = productTable.getSelectedRow();
//				categoryTextField.setText(model.getValueAt(selectedIndex, 1).toString());
				nameTextField.setText(tableModel.getValueAt(selectedIndex,1).toString());
				descriptionTextField.setText(tableModel.getValueAt(selectedIndex, 2).toString());
				
				
				Long categoryIndex = Long.parseLong(tableModel.getValueAt(selectedIndex, 3).toString());
//				for(Category category: categories) 
//				{
//					if(categoryIndex == category.getId()) 
//					{
//						categoryCombo.setSelectedItem(category);
//					}
//				}
				
				categoryCombo.setSelectedItem(categories.stream().filter(category -> category.getId() == categoryIndex));
				
				Long brandIndex = Long.parseLong(tableModel.getValueAt(selectedIndex, 4).toString());
				for(Brand brand: brands) 
				{
					if(brandIndex == brand.getID()) 
					{
						brandCombo.setSelectedItem(brand);
					}
				}
				priceTextField.setText(tableModel.getValueAt(selectedIndex, 5).toString());
				stockTextField.setText(tableModel.getValueAt(selectedIndex, 6).toString());
				barcodeField.setText(tableModel.getValueAt(selectedIndex, 7).toString());
				
			}
		});
		scrollPane.setViewportView(productTable);
		productTable.setModel(new DefaultTableModel(new Object[][] {

		}, new String[] { "ID", "Name" ,"Description","Category","Brand","Price","Stock","Barcode"}) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {

				return false;
			}
		});
		
		JButton restockBtn = new JButton("Restock");
		restockBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = productTable.getSelectedRow();
				if(selectedIndex == -1)
				{
					JOptionPane.showMessageDialog(null, "Please select a product");
				}
				else 
				{
				String input= JOptionPane.showInputDialog(null,"Enter amount to add:",0);
				
					if(!(input == null)) 
					{
						Long addAmount = Long.parseLong(input);
						Long currentStock = Long.parseLong(tableModel.getValueAt(selectedIndex, 6).toString());
						Long stock = addAmount + currentStock;
						Long ID =  Long.parseLong(tableModel.getValueAt(selectedIndex, 0).toString());
						productService.restockProduct(ID, stock);
						reloadProducts();
						clearTextFields();
				
					}
				
				}
				clearTextFields();
			}
		});
		restockBtn.setToolTipText("Increase item's quantity");
		restockBtn.setBounds(500, 142, 117, 29);
		add(restockBtn);
		
		JLabel descriptionLbl = new JLabel("Description:");
		descriptionLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		descriptionLbl.setBounds(222, 50, 77, 16);
		add(descriptionLbl);
		
		descriptionTextField = new JTextField();
		descriptionTextField.setBounds(311, 45, 130, 26);
		add(descriptionTextField);
		descriptionTextField.setColumns(10);
		
		barcodeField = new JTextField();
		barcodeField.setBounds(311, 72, 130, 26);
		add(barcodeField);
		barcodeField.setColumns(10);
		
		JLabel barcodeLbl = new JLabel("Barcode:");
		barcodeLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		barcodeLbl.setBounds(232, 77, 61, 16);
		add(barcodeLbl);
		
		JButton clearBtn = new JButton("Clear");
		clearBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nameTextField.setText("");
				descriptionTextField.setText("");
				priceTextField.setText("");
				stockTextField.setText("");
				barcodeField.setText("");
				
			}
		});
		clearBtn.setBounds(615, 142, 117, 29);
		add(clearBtn);
		
		
		this.loadBrandComboBox();
		this.loadCategoryComboBox();
		this.loadProducts();

	}
}
