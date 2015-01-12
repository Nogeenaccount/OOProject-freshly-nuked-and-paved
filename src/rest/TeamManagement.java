//package rest;
//
//import java.awt.*;
//import java.awt.event.*;
//import java.text.*;
//import java.util.Objects;
//
//import javax.swing.*;
//import javax.swing.border.*;
//import javax.swing.event.ListSelectionEvent;
//import javax.swing.event.ListSelectionListener;
//
//
//public class TeamManagement extends JFrame implements ActionListener, ListSelectionListener {
//
//	private static JFrame frame;
//	private Objects teams;
//	private JButton backButton = new JButton("Back to between Rounds");
//	private JButton saveAndQuitButton = new JButton("Save and Quit");
//	private JTextField nameField = new JTextField(20);
////	private JTextArea playerInfo = new JTextArea(8, 25);
//	private JList playerinfo;
//	private JButton sub = new JButton("substitube");
//	private JButton sell = new JButton("sell");
///**
//public TeamManagement(String title) {
//		//JFrame basicframe = new JFrame(title);
//		//basicframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		//frame = basicframe;
//}
//**/
//	
//public TeamManagement(JFrame basicframe)
//{
//	//int amountOfPlayers = numberIn;
//	setLayout(new FlowLayout());
//	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	setTitle("Teammanagement");
//	setSize(480,640);
//	setLocation(400,100);
//	getContentPane().setBackground(Color.gray);
//	add(nameField);
//	nameField.setBorder(new TitledBorder("Team Name"));
//	add(backButton);
//	add(saveAndQuitButton);
//	saveAndQuitButton.setActionCommand("exit");
//	saveAndQuitButton.addActionListener(this);
//	//setVisible(true);
//	
//	//LIST OBJECT
//	Object[] array1 = new Object[20];
//	for(int i = 0; i < 20; i++) {
//		array1[i] = "player  " + i +"   \t    End: 00" + "\t   Off: 00" + "   \tDef: 00" ;
//	}
//	playerinfo = new JList(array1);
//	playerinfo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//	//playerinfo.setLayoutOrientation(JList.HORIZONTAL_WRAP);
//	playerinfo.setVisibleRowCount(-1);
//	
//	playerinfo.addListSelectionListener(this);
//	this.playerinfo = playerinfo;
//	
//	
//	JScrollPane listscroller1 = new JScrollPane(playerinfo);
//	listscroller1.setPreferredSize(new Dimension(400, 400));
//	
//	JTextField textfield1 = new JTextField();
//	textfield1.setOpaque(true);
//	textfield1.setPreferredSize(new Dimension(200, 200));
//	add(playerinfo);
//	add(sub);
//	add(sell);
//	setVisible(true);
//}
//
//public static void main(String[]args){
//	TeamManagement tm = new TeamManagement(frame);
//	//tm.createAndShowGUI();
//}
//
//
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		// TODO Auto-generated method stub
//		if ("exit".equals(e.getActionCommand())) {
//			//Press Exit on HomeScreen
//		System.exit(EXIT_ON_CLOSE);
//		}
//	}
//
//	@Override
//	public void valueChanged(ListSelectionEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//	
//	
//}
