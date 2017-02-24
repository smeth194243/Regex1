package regex.view;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import regex.controller.RegexController;

public class RegexPanel extends JPanel
{
	//4 text feilds
	// a button
	// 
	private RegexController baseController;
	private SpringLayout baseLayout;
	private JTextField firstName;
	private JTextField lastName;
	private JTextField phoneNumber;
	private JTextField validEmail;
	private JLabel fNLabel;
	private JLabel lNLabel;
	private JLabel pNLabel;
	private JLabel vELabel;
	private JButton doesTalking;	


	public RegexPanel(RegexController baseController)
	{
		super();
		this.baseController = baseController;
		baseLayout = new SpringLayout();
		firstName = new JTextField(25);
		lastName = new JTextField(25);
		phoneNumber = new JTextField(25);
		
		validEmail = new JTextField(25);
		fNLabel = new JLabel("First Name");
		
		lNLabel = new JLabel("Last Name");
		
		pNLabel = new JLabel("Phone number");
		
		vELabel = new JLabel("Valid Email");
		
		doesTalking = new JButton("Validate information");
	
		
		this.setupPanel();
		this.setupLayout();
		this.setupListeners();
	}
	
	public void setupPanel()
	{
		this.setLayout(baseLayout);
		this.add(doesTalking);
		this.add(fNLabel);
		this.add(firstName);
		this.add(lNLabel);
		this.add(lastName);
		this.add(pNLabel);
		this.add(phoneNumber);
		this.add(vELabel);
		this.add(validEmail);
	}
	
	public void setupLayout()
	{
		baseLayout.putConstraint(SpringLayout.EAST, phoneNumber, 0, SpringLayout.EAST, firstName);
		baseLayout.putConstraint(SpringLayout.WEST, validEmail, 0, SpringLayout.WEST, fNLabel);
		baseLayout.putConstraint(SpringLayout.WEST, fNLabel, 234, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.EAST, fNLabel, -153, SpringLayout.EAST, this);
		baseLayout.putConstraint(SpringLayout.WEST, lastName, 0, SpringLayout.WEST, fNLabel);
		baseLayout.putConstraint(SpringLayout.NORTH, firstName, 6, SpringLayout.SOUTH, fNLabel);
		baseLayout.putConstraint(SpringLayout.WEST, firstName, 0, SpringLayout.WEST, fNLabel);
		baseLayout.putConstraint(SpringLayout.NORTH, fNLabel, 10, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.NORTH, lastName, 6, SpringLayout.SOUTH, lNLabel);
		baseLayout.putConstraint(SpringLayout.NORTH, lNLabel, 6, SpringLayout.SOUTH, firstName);
		baseLayout.putConstraint(SpringLayout.WEST, lNLabel, 0, SpringLayout.WEST, fNLabel);
		baseLayout.putConstraint(SpringLayout.NORTH, phoneNumber, 6, SpringLayout.SOUTH, pNLabel);
		baseLayout.putConstraint(SpringLayout.WEST, pNLabel, 0, SpringLayout.WEST, fNLabel);
		baseLayout.putConstraint(SpringLayout.NORTH, validEmail, 6, SpringLayout.SOUTH, vELabel);
		baseLayout.putConstraint(SpringLayout.NORTH, vELabel, 10, SpringLayout.SOUTH, phoneNumber);
		baseLayout.putConstraint(SpringLayout.EAST, vELabel, 0, SpringLayout.EAST, lNLabel);
		baseLayout.putConstraint(SpringLayout.NORTH, doesTalking, 95, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.NORTH, pNLabel, 4, SpringLayout.NORTH, doesTalking);
		baseLayout.putConstraint(SpringLayout.WEST, doesTalking, 22, SpringLayout.WEST, this);
	}
	
	public void setupListeners()
	{
		
	}
}