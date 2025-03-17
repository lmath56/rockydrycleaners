/*
Programmer: Lucas Mathews 12070730
Course: Programming Fundamentals COIT11222
File: RockyDryCleanersGUI.java
Purpose: Assignment Two -- Rocky Dry Cleaners windowed application
Date: 19 May 2019
*/

import java.util.Scanner;

import java.awt.FlowLayout;
import java.awt.Font;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;


public class RockyDryCleanersGUI extends JFrame implements ActionListener
{

	private final int MAX_ORDERS = 10; 							//declare maximum orders constant
	private Order [] orderArray = new Order[MAX_ORDERS]; 		//declare Order object array
	private int currentOrder = 0;								//declare current order variable


	private JLabel titleLabel = new JLabel("Rocky Dry Cleaners Management System"); // program title
	private JLabel nameLabel = new JLabel("Customer name: ");// for prompt
	private JTextField nameField = new JTextField(26);      // for name entry

	private JLabel garmentsLabel = new JLabel("Number of plain garments: ");// for prompt
	private JTextField garmentsField = new JTextField(4);      // for number of garments entry

	private JTextArea displayTextArea = new JTextArea("", 16, 56); // declare text area
	private JScrollPane scrollPane; // scroll pane for the text area

	//  declare all of the buttons
	private JButton enterButton = new JButton("Enter"); // buttons
	private JButton displayButton = new JButton("Display All");
	private JButton searchButton = new JButton("Search");
	private JButton exitButton = new JButton("Exit");


	public RockyDryCleanersGUI()
	{ // constructor create the Gui
		this.setLayout(new FlowLayout());			// set JFrame to FlowLayout

		titleLabel.setFont(new Font("Ariel", Font.BOLD, 22));
		add(titleLabel);
		add(nameLabel);
		add(nameField);
		add(garmentsLabel);
		add(garmentsField);

		// set text area to a monospaced font so the columns can be aligned using a format string
		displayTextArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
		displayTextArea.setEditable(false); 			// make text area read only
		scrollPane = new JScrollPane(displayTextArea); 	// add text area to the scroll pane
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // just need vertical scrolling

		add(scrollPane);

		add(enterButton);
		add(displayButton);
		add(searchButton);
		add(exitButton);

		enterButton.addActionListener(this);		// add the action listener to the buttons
		displayButton.addActionListener(this);
		searchButton.addActionListener(this);
		exitButton.addActionListener(this);



		// when the user pushes the system close (X top right corner)
		addWindowListener( // override window closing method
			new WindowAdapter()
			{
				public void windowClosing(WindowEvent e)
				{
					exit();				// Attempt to exit application
				}
			}
		);
	}



	public void actionPerformed(ActionEvent e)
	{ // process the clicks on all of the buttons
		String command = e.getActionCommand();

		if (command.compareTo("Enter") == 0)
			enter();
		else if (command.compareTo("Display All") == 0)
			displayAll();
		else if (command.compareTo("Search") == 0)
			search();
		else if (command.compareTo("Exit") == 0)
			exit();
	}




	private void enter()
	{
		if (currentOrder == 10) //error message code to show max orders has been reached
			{
				JOptionPane.showMessageDialog(null, "Maximum number of orders has been reached", "Rocky Dry Cleaners Management System", JOptionPane.ERROR_MESSAGE);
				nameField.requestFocus();
				return;
			}

		if (nameField.getText().compareTo("") == 0) //error message code to prompt for name to be entered
			{
				JOptionPane.showMessageDialog(null, "You must enter a customer name", "Rocky Dry Cleaners Management System", JOptionPane.ERROR_MESSAGE);
				nameField.requestFocus();
				return;
			}


		if (garmentsField.getText().compareTo("") == 0) //error message code to prompt for number of garments to be entered
			{
				JOptionPane.showMessageDialog(null, "You must enter the number of garments", "Rocky Dry Cleaners Management System", JOptionPane.ERROR_MESSAGE);
				garmentsField.requestFocus();
				return;
			}

		String customerName = nameField.getText(); //Read in customer name and number of garments information and add to the Order array
		int garments = Integer.parseInt(garmentsField.getText());

		orderArray[currentOrder] = new Order(customerName, garments);
		double charge = orderArray[currentOrder].calculateCharge();

		displayHeading(); //Display customer name, number of garments and the order charge to the text area
		displayTextArea.append(String.format("%-30s%-17s%5.2f", customerName, garments, charge));

		//Clear input fields and return focus to the customer name field and increment current booking variable
		nameField.setText("");
		garmentsField.setText("");
		nameField.requestFocus();
		currentOrder ++;

	}

	private void displayHeading()
		{
			displayTextArea.setText(String.format("%-30s%-17s%-6s\n", "Customer Name", "Garments", "Charge"));
			appendLine();
		}

	private void appendLine()
		{
			displayTextArea.append("-------------------------------------------------------\n");
		}






	private void displayAll()
	{

		displayTextArea.setText(String.format("%-30s%-17s%-6s\n", "Customer Name", "Garments", "Charge"));
		displayTextArea.append("-------------------------------------------------------\n");

		for (int i = 0; i < currentOrder; i++) //displays all of the entries in the array
			{
				displayTextArea.append(String.format("%-30s%-17s%5.2f\n", orderArray[i].getCustomerName(), orderArray[i].getGarments(), orderArray[i].calculateCharge()));
			}

		displayTextArea.append("-------------------------------------------------------\n");


		double totalGarments = 0;
		double totalCharge = 0;


		for(int i = 0; i < currentOrder; i++) //adds up total garments and charge stored in array
			{
				totalGarments = totalGarments + orderArray[i].getGarments();
			    totalCharge = totalCharge + orderArray[i].calculateCharge();
        	}

        double average = totalGarments / currentOrder; //calculates average garments

		displayTextArea.append(String.format("Average garments per order: %.2f\nTotal charges: $%.2f\n", average, totalCharge));


		if (currentOrder == 0) //error message for no order entered when pressing the Display All button
			{
				displayTextArea.setText("");
				JOptionPane.showMessageDialog(null, "No order entered", "Rocky Dry Cleaners Management System", JOptionPane.ERROR_MESSAGE);
				nameField.requestFocus();
				return;
			}
	}


	private void search()
	{
		if (currentOrder == 0)
			{
				displayTextArea.setText(""); //error message for no order entered when pressing the Search button
				JOptionPane.showMessageDialog(null, "No order entered", "Rocky Dry Cleaners Management System", JOptionPane.ERROR_MESSAGE);
				nameField.requestFocus();
				return;
			}

			String searchName;
			searchName = JOptionPane.showInputDialog(null, "Enter a customer name to search", "Search", JOptionPane.PLAIN_MESSAGE); //Read search name

			boolean found = false;
			int searchNum = 0;

			for(int i = 0; i < currentOrder ; i++) //iterate through array to search for the search key
				{
					if(searchName.equalsIgnoreCase(orderArray[i].getCustomerName()))
					{
						found=true;
						searchNum = i;
					}
				}

			if(found == true) //display search entry
				{
					displayTextArea.setText(String.format("%-30s%-17s%-6s\n", "Customer Name", "Garments", "Charge"));
					displayTextArea.append("-------------------------------------------------------\n");
					displayTextArea.append(String.format("%-30s%-17s%5.2f\n", orderArray[searchNum].getCustomerName(), orderArray[searchNum].getGarments(), orderArray[searchNum].calculateCharge()));
				}

			else if(searchName.length() == 0) //error message for entering nothing in the search field
				{
					displayTextArea.setText("");
					JOptionPane.showMessageDialog(null, "You must enter a search name", "Rocky Dry Cleaners Management System", JOptionPane.ERROR_MESSAGE);
					nameField.requestFocus();
				}

			else //error message for not finding search
				{
					displayTextArea.setText("");
					JOptionPane.showMessageDialog(null, searchName + " not found", "Rocky Dry Cleaners Management System", JOptionPane.ERROR_MESSAGE);
					nameField.requestFocus();
				}
	}


	private void exit()
	{
		//display exit message here
		JOptionPane.showMessageDialog(null, "Thank you for using the Rocky Dry Cleaners Management System", "Rocky Dry Cleaners Management System", JOptionPane.PLAIN_MESSAGE);

		System.exit(0);
	} // exit




	// Main method create instance of class
	public static void main(String[] args)
	{
		RockyDryCleanersGUI f = new RockyDryCleanersGUI();			// Create instance of class
		f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);	// allow the code to close the program
		f.setBounds(200, 100, 470, 440);						// Define position and size of app
		f.setTitle("Rocky Dry Cleaners Management System");		// Set the title of the app
		f.setVisible(true);										// Make the application visible
		f.setResizable(false);									// Make the window not resizable
	} // main

}