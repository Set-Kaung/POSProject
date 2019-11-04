package ui;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import java.util.List;

import javax.swing.JComboBox;

import model.Brand;
import model.Category;
import services.BrandService;
import services.BrandServiceImpl;
import services.CategoryService;
import services.CategoryServiceImpl;


import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProductPanel extends JPanel {
	private JTextField nameTextField;
	private static List<Brand> brands;
	private static List<Category> categories;
	private	static BrandService brandService = new BrandServiceImpl();
	private static CategoryService categoryService = new CategoryServiceImpl();
    static JComboBox brandCombo,categoryCombo;
    private JTextField priceTextFIeld;
    private JTextField quantityTextField;
    private JTable productTable;
    private DefaultTableModel tableModel;
	/**
	 * Create the panel.
	 */
	
	
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
	
	
	
	public ProductPanel() {
		setLayout(null);
		
		nameTextField = new JTextField();
		nameTextField.setToolTipText("Fill product name here\n");
		nameTextField.setBounds(80, 50, 130, 26);
		add(nameTextField);
		nameTextField.setColumns(10);
		
		JLabel nameLbl = new JLabel("Name:");
		nameLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		nameLbl.setBounds(19, 55, 61, 16);
		add(nameLbl);
		
		brandCombo = new JComboBox();
		brandCombo.setBounds(80, 88, 130, 27);
		add(brandCombo);
		
		JLabel brandLbl = new JLabel("Brand:");
		brandLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		brandLbl.setBounds(19, 92, 61, 16);
		add(brandLbl);
		
		JLabel categoryLbl = new JLabel("Category:");
		categoryLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		categoryLbl.setBounds(19, 127, 61, 16);
		add(categoryLbl);
		
	    categoryCombo = new JComboBox();
		categoryCombo.setBounds(80, 123, 130, 27);
		add(categoryCombo);
		
		priceTextFIeld = new JTextField();
		priceTextFIeld.setBounds(576, 50, 130, 26);
		add(priceTextFIeld);
		priceTextFIeld.setColumns(10);
		
		JLabel priceLbl = new JLabel("Price:");
		priceLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		priceLbl.setBounds(513, 55, 61, 16);
		add(priceLbl);
		
		JLabel quantityLbl = new JLabel("Stock:");
		quantityLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		quantityLbl.setBounds(513, 92, 61, 16);
		add(quantityLbl);
		
		quantityTextField = new JTextField();
		quantityTextField.setBounds(576, 87, 130, 26);
		add(quantityTextField);
		quantityTextField.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(425, 142, 83, 29);
		add(btnAdd);
		
		JButton updateBtn = new JButton("Update");
		updateBtn.setBounds(511, 142, 95, 29);
		add(updateBtn);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(606, 142, 95, 29);
		add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(100, 211, 600, 180);
		add(scrollPane);
		
		productTable = new JTable();
		scrollPane.setViewportView(productTable);
		productTable.setModel(new DefaultTableModel(new Object[][] {

		}, new String[] { "ID", "Name" ,"Description","Category","Brand","Price","Stock"}) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {

				return false;
			}
		});
		
		
		this.loadBrandComboBox();
		this.loadCategoryComboBox();

	}
}
