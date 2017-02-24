package regex.view;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import regex.controller.RegexController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import org.json.simple.JsonObject;
import org.json.simple.Jsoner;

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
		this.doesTalking.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RegexController regexController = regexFrame.getRegexController();
				
				boolean matchedFirstName = baseController.testFirstName(firstName.getText());
				boolean matchedLastName = baseController.testLastName(lastName.getText());
				
				String phoneNumber = phoneNumber.getText().replaceAll("\\s+", "");
				if(!phoneNumber.startsWith("1")){
					phoneNumber = "1" + phoneNumber;
				}
				boolean validPhone = false;
				JsonObject phoneJSONObject = null;
				if(phoneNumber.length() > 8){
					String phoneURL = "http://apilayer.net/api/validate?access_key=96d4141049100f8413bc5d3438c6e1f4&number=" + phoneNumber + "&country_code=&format=1";
					String phoneJSON = getJSONFromURL(phoneURL);
					phoneJSONObject = Jsoner.deserialize(phoneJSON, new JsonObject());
					validPhone = phoneJSONObject.getBoolean("valid") != null && phoneJSONObject.getBoolean("valid");
				}
				
				String email = emailField.getText().replaceAll("\\s+", "");
				String emailURL = "http://apilayer.net/api/check?access_key=db2e54d32073ef1ae2585fd7a7799d1c&email=" + email +"&smtp=1&format=1";
				String emailJSON = getJSONFromURL(emailURL);
				JsonObject emailJSONObject = Jsoner.deserialize(emailJSON, new JsonObject());
				boolean validEmail = emailJSONObject.getBoolean("format_valid") != null && emailJSONObject.getBoolean("format_valid");
				
				String shownString = "First Name:    " + matchedFirstName + "\n\n";
				shownString += "Last Name:    " + matchedLastName + "\n\n";
				if(validPhone){
					shownString += "Phone Number:    " + true + "\n";
					shownString += "Location of Phone:    " + phoneJSONObject.getString("location") + "\n\n";
				}else{
					shownString += "Phone Number:    " + false + "\n\n";
				}
				if(validEmail){
					shownString += "Email:    valid \n";
					shownString += "Mail Exchange record found:    " + emailJSONObject.getBoolean("mx_found") + "\n";
					shownString += "Simple Mail Transfer Protocol check:    " + emailJSONObject.getBoolean("smtp_check") + "\n";
					shownString += "Score (Higher is more legitimate. Max of 80):    " + emailJSONObject.getDouble("score") * 100;
				}else{
					shownString += "Email: invalid \n";
					shownString += "Did you mean " + emailJSONObject.getString("did_you_mean");
				}
				
				JOptionPane.showMessageDialog(regexFrame, shownString);
			}
		});
	}
	private String getJSONFromURL(String url){
		String json = "";
		try (InputStream input = new URL(url).openStream()){
			BufferedReader reader = new BufferedReader(new InputStreamReader(input, Charset.forName("UTF-8")));
			int cp;
			while((cp = reader.read()) != -1){
				json += (char) cp;
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		return json;
	}
}