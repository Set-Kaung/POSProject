package ui;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.Category;
import services.CategoryService;
import services.CategoryServiceImpl;

public class CategoryPanel extends JPanel {
	private JTextField categoryTextField;
	private JTable categoryTable;
	private DefaultTableModel model;
	CategoryService categoryService = new CategoryServiceImpl();
	private ProductPanel productPanel = new ProductPanel();
	Image img;
	Icon icon;

	public void loadCategories() {
		List<Category> categories = this.categoryService.getAllCategories();
		model = (DefaultTableModel) this.categoryTable.getModel();

		for (Category category : categories) {
			Object row[] = new Object[3];
			row[0] = category.getId();
			row[1] = category.getName();

			model.addRow(row);

		}

	}

	public void reloadCategories() {
		model.setRowCount(0);
		this.loadCategories();
		productPanel.loadCategoryComboBox();
	}

	/**
	 * Create the panel.
	 */
	public CategoryPanel() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				categoryTable.clearSelection();
			}
		});

		setLayout(null);

		categoryTextField = new JTextField();
		categoryTextField.setText("");
		categoryTextField.setBounds(130, 65, 130, 26);
		add(categoryTextField);
		categoryTextField.setColumns(10);

		JLabel lblCategory = new JLabel("Category:");
		lblCategory.setBounds(40, 70, 61, 16);
		add(lblCategory);

		JButton addBtn = new JButton("Add");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String categoryName = categoryTextField.getText();
				if (categoryName.isBlank()) {
					JOptionPane.showMessageDialog(null, "Please enter category name.");
				} else {
					categoryService.addCategory(categoryName);
					JOptionPane.showMessageDialog(null, "Category added.");
					categoryTextField.setText("");
					reloadCategories();
				}
			}
		});
		addBtn.setBounds(40, 120, 80, 30);
		add(addBtn);

		JButton updateBtn = new JButton("Update");
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				int selectedIndex = categoryTable.getSelectedRow();

				
				if(selectedIndex == -1 ) 
				{
					JOptionPane.showMessageDialog(null, "Please select a category");
				}
				else
				{
					Long ID = Long.parseLong(model.getValueAt(selectedIndex, 0).toString());
					String name = categoryTextField.getText();
					
					if(name.isBlank()) 
					{
						int choice = JOptionPane.showConfirmDialog(null, "Do you want to update this category as blank?");
						if(choice == JOptionPane.YES_OPTION) 
						{
							categoryService.updateCategory(ID, name);
							reloadCategories();
						}
					}
					else 
					{
						categoryService.updateCategory(ID, name);
						reloadCategories();
					}
				}
				
			}
		});
		updateBtn.setBounds(112, 121, 100, 29);
		add(updateBtn);

		JButton deleteBtn = new JButton("Delete");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = categoryTable.getSelectedRow();

				
				if(selectedIndex == -1 ) 
				{
					JOptionPane.showMessageDialog(null, "Please select a category");
				}
				else 
				{
					Long ID = Long.parseLong(model.getValueAt(selectedIndex, 0).toString());
					int choice = JOptionPane.showConfirmDialog(null, "Do you want to delete the category "+ID,"Warning!", JOptionPane.YES_NO_OPTION);
					if(choice == JOptionPane.YES_OPTION) {
					categoryService.deleteCategory(ID);
					reloadCategories();
					}
				}
				
			}
		});
		deleteBtn.setBounds(205, 121, 100, 29);
		add(deleteBtn);

		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setBounds(344, 27, 406, 281);
		add(scrollPane);

		categoryTable = new JTable();
		categoryTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedIndex = categoryTable.getSelectedRow();
				categoryTextField.setText(model.getValueAt(selectedIndex, 1).toString());
				categoryTextField.requestFocus();
			}
		});
		scrollPane.setViewportView(categoryTable);

		categoryTable.setModel(new DefaultTableModel(new Object[][] {

		}, new String[] { "ID", "Name" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {

				return false;
			}
		});

		loadCategories();

	}
}
