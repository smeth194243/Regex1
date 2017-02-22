package regex.view;

import javax.swing.JFrame;
import java.awt.Dimension;
import regex.controller.RegexController;

public class RegexFrame extends JFrame
{
	private RegexController baseController;
	private RegexPanel basePanel;
	
	public RegexFrame(RegexController baseController)
	{
		super();
		this.baseController = baseController;
		this.basePanel = new RegexPanel(baseController);
		this.setupFrame();
	}
	
	public void setupFrame()
	{
		this.setContentPane(basePanel);
		this.setTitle("Regex");
		this.setSize(new Dimension(300, 300));
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
