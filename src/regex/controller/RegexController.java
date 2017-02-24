package regex.controller;

import regex.view.RegexPanel;

public class RegexController 
{
	public void start()
	{
		
	}
	
	public boolean testFirstName(String firstName)
	{
		if(firstName.matches(".*[a-zA-Z-]{2,30}"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean testLastName(String lastName)
	{
		if(lastName.matches(".*[a-zA-Z-,.]{2,40}"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
