package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;

public class AddItem {

	private JFrame frame;
	private JTextField textField_ID;
	private JTextField textField_Type;
	private JTextField textField_Description;
	private JTextField textField_Price;
	private JTextField textField_Quality;
	private JTextField textField_Amount;
	private JTextField textField_Blueprint;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddItem window = new AddItem();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AddItem() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 345);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel idLabel = new JLabel("id:");
		idLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		idLabel.setBounds(27, 11, 46, 14);
		frame.getContentPane().add(idLabel);
		
		JLabel typeLabel = new JLabel("Type:");
		typeLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		typeLabel.setBounds(27, 36, 46, 14);
		frame.getContentPane().add(typeLabel);
		
		JLabel descriptionLabel = new JLabel("Description:");
		descriptionLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		descriptionLabel.setBounds(27, 61, 83, 14);
		frame.getContentPane().add(descriptionLabel);
		
		JLabel priceLabel = new JLabel("Price:");
		priceLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		priceLabel.setBounds(27, 86, 46, 14);
		frame.getContentPane().add(priceLabel);
		
		JLabel labelItem = new JLabel("Item:");
		labelItem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelItem.setBounds(27, 152, 46, 14);
		frame.getContentPane().add(labelItem);
		
		textField_ID = new JTextField();
		textField_ID.setBounds(107, 10, 265, 20);
		frame.getContentPane().add(textField_ID);
		textField_ID.setColumns(10);
		
		textField_Type = new JTextField();
		textField_Type.setBounds(107, 36, 265, 20);
		frame.getContentPane().add(textField_Type);
		textField_Type.setColumns(10);
		
		textField_Description = new JTextField();
		textField_Description.setBounds(107, 60, 265, 20);
		frame.getContentPane().add(textField_Description);
		textField_Description.setColumns(10);
		
		textField_Price = new JTextField();
		textField_Price.setBounds(107, 86, 265, 20);
		frame.getContentPane().add(textField_Price);
		textField_Price.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Quality:");
		lblNewLabel.setBounds(51, 177, 46, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JCheckBox chckbxForceBlueprint = new JCheckBox("Force Blueprint");
		chckbxForceBlueprint.setBounds(199, 173, 97, 23);
		frame.getContentPane().add(chckbxForceBlueprint);
		
		textField_Quality = new JTextField();
		textField_Quality.setBounds(107, 174, 86, 20);
		frame.getContentPane().add(textField_Quality);
		textField_Quality.setColumns(10);
		
		JLabel lblAmount = new JLabel("Amount:");
		lblAmount.setBounds(51, 207, 46, 14);
		frame.getContentPane().add(lblAmount);
		
		JLabel lblBlueprint = new JLabel("Blueprint:");
		lblBlueprint.setBounds(51, 232, 46, 14);
		frame.getContentPane().add(lblBlueprint);
		
		textField_Amount = new JTextField();
		textField_Amount.setBounds(107, 202, 86, 20);
		frame.getContentPane().add(textField_Amount);
		textField_Amount.setColumns(10);
		
		textField_Blueprint = new JTextField();
		textField_Blueprint.setBounds(107, 229, 265, 20);
		frame.getContentPane().add(textField_Blueprint);
		textField_Blueprint.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(10, 260, 414, 35);
		frame.getContentPane().add(btnAdd);
	}
}
