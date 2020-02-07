package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Category;
import services.CategoryService;
import services.CategoryServiceImpl;

import javax.swing.JTabbedPane;
import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainPage extends JFrame {

	private JPanel contentPane;
	CategoryService categoryService = new CategoryServiceImpl();
	DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPage frame = new MainPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	
	

	/**
	 * Create the frame.
	 */
	public MainPage() {
		setPreferredSize(new Dimension(800, 500));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 250, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		
		CategoryPanel categoryPanel = new CategoryPanel();
		tabbedPane.addTab("Category", categoryPanel);
		
		BrandPanel brandPanel = new BrandPanel();
		tabbedPane.addTab("Brand", brandPanel);
		
		ProductPanel productPanel = new ProductPanel();
		tabbedPane.addTab("Product", productPanel);
		
		SalePanel salePanel = new SalePanel();
		tabbedPane.addTab("Sale", salePanel);
		
		
	}
}
