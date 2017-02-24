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
		
	}
	
	public void setupLayout()
	{
	
	}
	
	public void setupListeners()
	{
		
	}
}