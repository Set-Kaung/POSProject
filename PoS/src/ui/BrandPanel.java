package ui;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.Brand;
import services.BrandService;
import services.BrandServiceImpl;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.util.List;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BrandPanel extends JPanel {
	private JTable brandTable;
	private JTextField nameTextField;
	private DefaultTableModel tableModel;
	private BrandService brandService = new BrandServiceImpl();
	private ProductPanel productPanel = new ProductPanel();

	
	public void loadBrands() 
	{
		List<Brand> brands = this.brandService.getAllBrand();
		tableModel = (DefaultTableModel)this.brandTable.getModel();
		
		for(Brand brand: brands) 
		{
			Object[] rows = new Object[2];
			rows[0] = brand.getID();
			rows[1] = brand.getName();
			
			tableModel.addRow(rows);
			
		}
	}
	
	private void reloadBrands() 
	{
		tableModel.setRowCount(0);
		loadBrands();
		
		productPanel.loadBrandComboBox();
		
	}
	/**
	 * Create the panel.
	 */
	public BrandPanel() {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(368, 49, 233, 219);
		add(scrollPane);
		
		brandTable = new JTable();
		brandTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedIndex = brandTable.getSelectedRow();
				nameTextField.setText(tableModel.getValueAt(selectedIndex, 1).toString());
				nameTextField.requestFocus();
			}
		});
		scrollPane.setViewportView(brandTable);
		brandTable.setModel(new DefaultTableModel(new Object[][] {

		}, new String[] { "ID", "Name" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {

				return false;
			}
		});
		
		nameTextField = new JTextField();
		nameTextField.setBounds(100, 89, 130, 26);
		add(nameTextField);
		nameTextField.setColumns(10);
		
		JLabel nameLabel = new JLabel("Name:");
		nameLabel.setBounds(17, 94, 61, 16);
		add(nameLabel);
		
		JButton addBtn = new JButton("Add");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameTextField.getText();
				brandService.addBrand(name);
				reloadBrands();
			}
		});
		addBtn.setBounds(17, 140, 75, 29);
		add(addBtn);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = brandTable.getSelectedRow();
				
				if(selectedIndex == -1) 
				{
					JOptionPane.showMessageDialog(null, "Please select a brand.");
				}
				else 
				{
					Long ID = Long.parseLong(tableModel.getValueAt(selectedIndex, 0).toString());
					String updatedName = nameTextField.getText();
					if(updatedName.isBlank()) 
					{
						int choice = JOptionPane.showConfirmDialog(null, "Do you want to update this brand as blank?");
						if(choice == JOptionPane.YES_OPTION) 
						{
							brandService.updateBrand(ID, updatedName);
							reloadBrands();
						}
					}
					else 
					{
						brandService.updateBrand(ID, updatedName);
						reloadBrands();
					}
				}
				
				
				
			}	
		});
		btnUpdate.setBounds(100, 140, 80, 29);
		add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = brandTable.getSelectedRow();

				
				if(selectedIndex == -1 ) 
				{
					JOptionPane.showMessageDialog(null, "Please select a category");
				}
				else 
				{
					Long ID = Long.parseLong(tableModel.getValueAt(selectedIndex, 0).toString());
					int choice = JOptionPane.showConfirmDialog(null, "Do you want to delete the brand "+ID,"Warning!", JOptionPane.YES_NO_OPTION);
					if(choice == JOptionPane.YES_OPTION) {
					brandService.deleteBrand(ID);
					reloadBrands();
					}
				}
				nameTextField.setText("");
				
			}
			
		});
		btnDelete.setBounds(192, 140, 94, 29);
		add(btnDelete);

		
		loadBrands();
	}
}
