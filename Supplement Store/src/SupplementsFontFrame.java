import javax.swing.*;
import java.awt.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*Sukhjinder Samra
 * Supplement Store Font Frame
 */
public class SupplementsFontFrame extends JFrame 
{
	private static final int FRAME_WIDTH=700;
	private static final int FRAME_HEIGHT=800;
	
	private JLabel label;
	private JCheckBox fivelbs;
	private JCheckBox twolbs;
	private JRadioButton Whey;
	private JRadioButton Casein;
	private JRadioButton Egg;
	private JComboBox brand;
	private JTextArea results;
	private JTextField field;
	private JButton done;
	private ActionListener listener;
	private String size=null;
	private String type=null;
	private String brandname=null;
	private String name=null;
	
	
	//This constructs the frame for the supplement store GUI
	public SupplementsFontFrame()
	{
		//This displays the title for the GUI
		label=new JLabel("Supplement Store");
		label.setFont(new Font("Arial Black",Font.BOLD,16));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		add(label, BorderLayout.CENTER);
		
		//This creates the menu bar
		JMenuBar menuBar=new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(createFileMenu());
		
		/*This is the listener that will be used among all
		  the components in the GUI
		 */
		listener=new ChoiceListener();
		
		createMainPanel();
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		
	}
	
	//This is the listener that will be used to exit the GUI.
	class ExitItemListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			System.exit(0);
		}
	}
	
	/*This creates the Exit button under the
	  File tab in the menu bar
	 */
	public JMenu createFileMenu()
	{
		JMenu menu=new JMenu("File");
		JMenuItem exitItem=new JMenuItem("EXIT");
		ActionListener listener=new ExitItemListener();
		exitItem.addActionListener(listener);
		menu.add(exitItem);
		return menu;
	}
	
	/*This listener converts the inputs and displays
	  them in the results text area
	 */
	class ChoiceListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if(event.getSource()==done)
			{
			giveResults();
			results.setText("Hi "+name+", you have selected the "+System.lineSeparator()+size+" tub(s) of the "+System.lineSeparator()+brandname+" "+type+" protein.");
			}
		}
	}
	
	/*This creates the main panel and adds all the
	  component panels into it
	 */
	public void createMainPanel()
	{
		JPanel brandPanel=createComboBox();
		JPanel sizePanel=createCheckBoxes();
		JPanel typePanel=createRadioButtons();
		JPanel fieldPanel=createTextField();
		JPanel resultsPanel=createTextArea();
		JPanel donePanel=createJButton();
		
		/*Orders the component panels to display them in
		  the correct order
		 */
		JPanel mainPanel=new JPanel();
		mainPanel.setLayout(new GridLayout(3,1));
		mainPanel.add(fieldPanel);
		mainPanel.add(brandPanel);
		mainPanel.add(sizePanel);
		mainPanel.add(typePanel);
		mainPanel.add(donePanel);
		mainPanel.add(resultsPanel);
		
		//Adds the main panel to the frame.
		add(mainPanel, BorderLayout.SOUTH);
	}
	
	//Creates the text field panel for the name of the user
	public JPanel createTextField()
	{
		field=new JTextField();
		field.addActionListener(listener);
		
		JPanel panel=new JPanel();
		field.setPreferredSize(new Dimension(100,20));
		panel.add(field);
		panel.setBorder(new TitledBorder(new EtchedBorder(),"Full Name"));
		
		return panel;
	}
	
	//Creates the combo box panel for selecting the brand
	public JPanel createComboBox()
	{
		brand=new JComboBox();
		brand.addItem("Gold Standard");
		brand.addItem("IsoFlex");
		brand.addItem("Syntha-6");
		brand.setEditable(false);
		brand.addActionListener(listener);
		
		JPanel panel=new JPanel();
		panel.add(brand);
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Protein Brand"));
		
		return panel;
		
	}
	
	/*Creates the check boxes panel for selecting the size
	  of the protein container
	 */
	public JPanel createCheckBoxes()
	{
		fivelbs=new JCheckBox("5 LBS");
		fivelbs.addActionListener(listener);
		
		twolbs=new JCheckBox("2 LBS");
		twolbs.addActionListener(listener);
		
		JPanel panel=new JPanel();
		panel.add(fivelbs);
		panel.add(twolbs);
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Protein Size"));
		
		return panel;
	}
	
	/*Creates the radio buttons panel for selecting the
	  type of protein.
	 */
	public JPanel createRadioButtons()
	{
		Whey=new JRadioButton("Whey");
		Whey.addActionListener(listener);
		
		Casein=new JRadioButton("Casein");
		Casein.addActionListener(listener);
		
		Egg=new JRadioButton("Egg");
		Egg.addActionListener(listener);
		
		/*Groups the buttons together to ensure that only
		  one button can be selected
		 */
		ButtonGroup group=new ButtonGroup();
		group.add(Whey);
		group.add(Casein);
		group.add(Egg);
		
		JPanel panel=new JPanel();
		panel.add(Whey);
		panel.add(Casein);
		panel.add(Egg);
		panel.setBorder(new TitledBorder(new EtchedBorder(),"Protein Type"));
		
		return panel;
	}
	
	/*Creates the "Done" button panel for submitting to get
	  the results
	 */
	public JPanel createJButton()
	{
		done=new JButton("Done");
		done.addActionListener(listener);
		
		JPanel panel=new JPanel();
		panel.add(done);
		
		return panel;
	}
	
	/*Creates the text area panel where the 
	  results will be displayed
	 */
	public JPanel createTextArea()
	{
		results=new JTextArea(10,20);
		results.setEditable(false);
		
		JScrollPane scroll = new JScrollPane(results);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		JPanel panel=new JPanel();
		panel.add(scroll);
		panel.setBorder(new TitledBorder(new EtchedBorder(),"Results"));
		
		return panel;
		
	}
	
	/*Gets the info that the user inputs and sends it to the listener
	  to be displayed in the results area.
	 */
	public void giveResults()
	{
		//Gets the selected brand
		brandname= (String) brand.getSelectedItem();
		
		//Gets the name that was input
		name=field.getText();
		
		//Gets the selected size(s) of protein.
		if(fivelbs.isSelected())
		{
			size="5 LBS";
		}
		if(twolbs.isSelected())
		{
			size="2 LBS";
		}
		
		if (twolbs.isSelected()||fivelbs.isSelected())
		{
			size="5 lbs and 2 lbs";
		}
		
		//Gets the selected type of protein.
		if(Whey.isSelected())
		{
			type="Whey";
		}
		else if(Casein.isSelected())
		{
			type="Casein";
		}
		else if(Egg.isSelected())
		{
			type="Egg";
		}
		
		
				
	}

}












