package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import dao.ProductDAO;
import dto.ProductDTO;
import services.ProductService;
import services.ProductServiceImpl;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Vector;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SalePanel extends JPanel {
	private JTextField barcodeField;
	private JTextField nameField;
	private JTextField priceField;
	private JTextField quantityField;
	private JTable saleTable;
	private JTextField textField_4;
	private ProductService productService = new ProductServiceImpl();
	private DefaultTableModel tableModel;
	private JLabel totalLbl;
	
	
	private void clearFields() 
	{
		this.barcodeField.setText("");
		this.nameField.setText("");
		this.priceField.setText("");
		this.quantityField.setText("");
	}
	
	private Vector<Object> addItem() 
	{
		Long barcode = Long.parseLong(barcodeField.getText());
		String name = nameField.getText();
		Double price = Double.parseDouble(priceField.getText());
		Integer quantity = Integer.parseInt(quantityField.getText());
		Double total = price * quantity;
		Vector<Object> row = new Vector<Object>();
		row.add(barcode);
		row.add(name);
		row.add(price);
		row.add(quantity);
		row.add(total);		
		
		return row;
		
	}
	
	private void addSubTotal(Double itemTotal) 
	{
		double subtotal = 0.0;
		double currentTotal = Double.parseDouble(totalLbl.getText());
		
		totalLbl.setText(new String());
	}
	/**
	 * Create the panel.
	 */
	public SalePanel() {
		setLayout(null);
		
		JLabel barcodeLbl = new JLabel("Barcode:");
		barcodeLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		barcodeLbl.setBounds(30, 29, 61, 16);
		add(barcodeLbl);
		
		barcodeField = new JTextField();
		barcodeField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) 
				{
					
					int barcodeInt = Integer.parseInt(barcodeField.getText());
					
					
					Long barcode = (long) barcodeInt;
					
					ProductDTO productDTO = productService.getProductByBarcode(barcode);
					if(productDTO.getName() != null) 
					{
						nameField.setText(productDTO.getName());
						priceField.setText(productDTO.getPrice().toString());
					}
					else 
					{
						JOptionPane.showMessageDialog(null, "There is no product with this barcode", "No product", JOptionPane.ERROR_MESSAGE);
						clearFields();
					}
				}
				
			}
		});
		barcodeField.setBounds(103, 24, 130, 26);
		add(barcodeField);
		barcodeField.setColumns(10);
		
		nameField = new JTextField();
		nameField.setBounds(351, 24, 130, 26);
		add(nameField);
		nameField.setColumns(10);
		
		JLabel productNameLbl = new JLabel("Product Name:");
		productNameLbl.setBounds(245, 29, 94, 16);
		add(productNameLbl);
		
		JLabel priceLbl = new JLabel("Price:");
		priceLbl.setBounds(40, 57, 61, 16);
		add(priceLbl);
		
		priceField = new JTextField();
		priceField.setBounds(103, 52, 130, 26);
		add(priceField);
		priceField.setColumns(10);
		
		JLabel quantityLbl = new JLabel("Quantity:");
		quantityLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		quantityLbl.setBounds(269, 57, 61, 16);
		add(quantityLbl);
		
		quantityField = new JTextField();
		quantityField.setBounds(351, 52, 130, 26);
		add(quantityField);
		quantityField.setColumns(10);
		
		JButton addBtn = new JButton("Add");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableModel = (DefaultTableModel)saleTable.getModel();
				Vector<Object> row = addItem();
				tableModel.addRow(row);
//				addSubTotal()
				clearFields();
				
			}
		});
		addBtn.setBounds(493, 24, 117, 29);
		add(addBtn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 127, 633, 246);
		add(scrollPane);
		
		saleTable = new JTable();
		scrollPane.setViewportView(saleTable);
		saleTable.setModel(new DefaultTableModel(new Object[][] {

		}, new String[] { "Barcode", "Name","Price","Quantity","Total" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {

				return false;
			}
		});
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableModel = (DefaultTableModel)saleTable.getModel();
				int selectedIndex = saleTable.getSelectedRow();
				tableModel.removeRow(selectedIndex);
			}
		});
		btnDelete.setBounds(675, 216, 103, 29);
		add(btnDelete);
		
		JLabel subTotalLbl = new JLabel("Subtotal:");
		subTotalLbl.setBounds(30, 399, 61, 16);
		add(subTotalLbl);
		
		totalLbl = new JLabel("0");
		totalLbl.setBounds(103, 399, 61, 16);
		add(totalLbl);
		
		JLabel lblPay = new JLabel("Pay:");
		lblPay.setBounds(176, 399, 61, 16);
		add(lblPay);
		
		textField_4 = new JTextField();
		textField_4.setBounds(209, 394, 130, 26);
		add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblChange = new JLabel("Change:");
		lblChange.setBounds(351, 399, 61, 16);
		add(lblChange);
		
		JLabel label_1 = new JLabel("0");
		label_1.setBounds(424, 399, 61, 16);
		add(label_1);
		
		JButton btnPayInvoice = new JButton("Pay Invoice:");
		btnPayInvoice.setBounds(527, 394, 117, 29);
		add(btnPayInvoice);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearFields();
			}
		});
		btnClear.setBounds(493, 52, 117, 29);
		add(btnClear);

	}
}
