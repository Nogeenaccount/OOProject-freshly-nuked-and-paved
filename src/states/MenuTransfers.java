package states;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

@SuppressWarnings("serial")
//FINISHED WITH FILLER
public class MenuTransfers extends State {

    public MenuTransfers() {
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public void createGUI() {
	layout = new GridBagLayout();
	this.setLayout(layout);
	c = new GridBagConstraints();

        //!!!!!!
//        StateManager.getLeague().setOffersmade(new ArrayList<String>());
//        
//        for(int i = 0; i < 3; i ++) {
//            StateManager.getLeague().generateOffer();
//                    }
        //!!!!!
        
        String[] array2 = new String[StateManager.getLeague().getOffersMade().size()];
        for(int j = 0; j < StateManager.getLeague().getOffersMade().size(); j++) {
            System.out.println(StateManager.getLeague().getOffersMade().get(j));
            array2[j] = StateManager.getLeague().getOffersMade().get(j);
        }
        
	//Initialise
	String[] array1 = new String[20];
	rest.League league1 = rest.League.readResources("SaveGame.xml");
	for (int i = 0; i < league1.getTeams().size(); i++) {
	    array1[i] = league1.getTeams().get(i).getTeamName();
	}

	//Initialise images
	String userDir = System.getProperty("user.home");
	String buttonAttemptImage = "GUIFiles\\buttonAttempt.png";
	String buttonOfferAcceptImage = "GUIFiles\\buttonBuyPlayer.png";
	String buttonBackImage = "GUIFiles\\buttonDone.png";
	String panelPanelImage = "GUIFiles\\FootbalStadiumSize.png";

	//Initialise List Data
	System.out.println("Initialise Offers: use filler data");
	//String[] array2 = {"hey", "bye"};
	//Eventually empty alreadyTried and alreadyTried if next match takes place
	final ArrayList<String> playerArray = new ArrayList();
	String[] buyOrSell = {"Buy", "Sell"};
	final ArrayList<String> alreadyTried = new ArrayList();
        final ArrayList<String> tempOffersTried = new ArrayList ();

	//Initialise Components
	final JTextField teamPrompt = new JTextField();
	final JList playerList = new JList();
	final JTextField playerPrompt = new JTextField();
	final JTextField attemptPricePrompt = new JTextField();
	final JList buyOrSellList = new JList(buyOrSell);
	final JList teamList = new JList(array1);
	final JTextField attemptPrice = new JTextField();
	final JButton buttonAttempt = new JButton(new ImageIcon(buttonAttemptImage));
	final JList offerList = new JList(array2);
	final JButton offerAccept = new JButton(new ImageIcon(buttonOfferAcceptImage));
	final JTextField budgetDisplay = new JTextField();
        
	budgetDisplay.setOpaque(true);
	budgetDisplay.setPreferredSize(new Dimension(400, 20));
	budgetDisplay.setEditable(false);
	budgetDisplay.setText("Your budget: " + StateManager.getLeague().getTeamByName(StateManager.getLeague().getChosenTeam()).getBudget());
	budgetDisplay.setBackground(Color.decode("#525151"));
	budgetDisplay.setForeground(Color.white);
	budgetDisplay.setMinimumSize(new Dimension(400, 20));
	budgetDisplay.setFont(new Font("Arial", Font.PLAIN, 12));
	c.weightx = 0.5;
	c.gridx = 1;
	c.gridy = 1;
	layout.setConstraints(budgetDisplay, c);
	this.add(budgetDisplay);

	//Prompt Buying or selling
	JTextField buyOrSellPrompt = new JTextField();
	buyOrSellPrompt.setOpaque(true);
	buyOrSellPrompt.setPreferredSize(new Dimension(400, 20));
	buyOrSellPrompt.setEditable(false);
	buyOrSellPrompt.setText("Please choose whether to buy or sell a player: ");
	buyOrSellPrompt.setBackground(Color.decode("#525151"));
	buyOrSellPrompt.setForeground(Color.white);
	buyOrSellPrompt.setMinimumSize(new Dimension(400, 20));
	buyOrSellPrompt.setFont(new Font("Arial", Font.PLAIN, 12));
	c.weightx = 0.5;
	c.gridx = 1;
	c.gridy = 2;
	layout.setConstraints(buyOrSellPrompt, c);
	this.add(buyOrSellPrompt);

	//Buy or Sell list
	buyOrSellList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	buyOrSellList.setVisibleRowCount(-1);
	buyOrSellList.setPreferredSize(new Dimension(400, 40));
	buyOrSellList.setBackground(Color.decode("#525151"));
	buyOrSellList.setForeground(Color.white);
	buyOrSellList.setMinimumSize(new Dimension(400, 34));
	buyOrSellList.setFont(new Font("Arial", Font.PLAIN, 12));
	c.weightx = 0.5;
	c.gridx = 1;
	c.gridy = 3;
	layout.setConstraints(buyOrSellList, c);
	this.add(buyOrSellList);
	buyOrSellList.addListSelectionListener(new ListSelectionListener() {
	    @Override
	    public void valueChanged(ListSelectionEvent e) {
		if (buyOrSellList.getValueIsAdjusting() && buyOrSellList.getSelectedValue().equals("Buy") && teamList.isSelectionEmpty() == false) {
		    playerArray.clear();
		    for (int i = 0; i < StateManager.getLeague().getTeams().size(); i++) {
			if (teamList.getSelectedValue().equals(StateManager.getLeague().getTeams().get(i).getTeamName())) {
			    playerPrompt.setText("Please select a player from this team:");
			    for (int j = 0; j < StateManager.getLeague().getTeams().get(i).getPlayers().size(); j++) {
				playerArray.add(StateManager.getLeague().getTeams().get(i).getPlayers().get(j).getPlayerName());
				//System.out.println(StateManager.getLeague().getTeams().get(i).getPlayers().get(j).getPlayerName());
				String[] playerArrayNotList = new String[playerArray.size()];
				for (int m = 0; m < playerArray.size(); m++) {
				    playerArrayNotList[m] = playerArray.get(m);
				}
				playerList.setListData(playerArrayNotList);
				attemptPricePrompt.setText("---");
				attemptPrice.setEditable(false);
				buttonAttempt.setEnabled(false);
			    }
			}
		    }
		}

		if (buyOrSellList.getValueIsAdjusting() && buyOrSellList.getSelectedValue().equals("Sell") && teamList.isSelectionEmpty() == false) {
		    playerArray.clear();
		    for (int i = 0; i < StateManager.getLeague().getTeams().size(); i++) {
			if (StateManager.getLeague().getChosenTeam().equals(StateManager.getLeague().getTeams().get(i).getTeamName())) {
			    playerPrompt.setText("Please select a player from your team:");
			    for (int j = 0; j < StateManager.getLeague().getTeams().get(i).getPlayers().size(); j++) {
				playerArray.add(StateManager.getLeague().getTeams().get(i).getPlayers().get(j).getPlayerName());
				String[] playerArrayNotList = new String[playerArray.size()];
				for (int m = 0; m < playerArray.size(); m++) {
				    playerArrayNotList[m] = playerArray.get(m);
				}
				playerList.setListData(playerArrayNotList);
				attemptPricePrompt.setText("---");
				attemptPrice.setEditable(false);
				buttonAttempt.setEnabled(false);
			    }
			}
		    }
		}
		if (buyOrSellList.getSelectedIndex() == 0 && buyOrSellList.getValueIsAdjusting()) {
		    teamPrompt.setText("Please choose a team to buy a player from: ");
		    System.out.println(buyOrSellList.getSelectedValue());
		}
		if (buyOrSellList.getSelectedIndex() == 1 && buyOrSellList.getValueIsAdjusting()) {
		    teamPrompt.setText("Please choose a team to sell a player to: ");
		    System.out.println(buyOrSellList.getSelectedValue());
		}
		if (teamList.isSelectionEmpty() == false && buyOrSellList.isSelectionEmpty() == false && playerList.isSelectionEmpty() == false) {
		    attemptPricePrompt.setText("For what price would you like to try this?");
		    attemptPrice.setEditable(true);
		    buttonAttempt.setEnabled(true);
		}
	    }
	});

	//Prompt choosing a team
	teamPrompt.setOpaque(true);
	teamPrompt.setPreferredSize(new Dimension(400, 20));
	teamPrompt.setEditable(false);
	teamPrompt.setText("---");
	teamPrompt.setBackground(Color.decode("#525151"));
	teamPrompt.setForeground(Color.white);
	teamPrompt.setMinimumSize(new Dimension(400, 20));
	teamPrompt.setFont(new Font("Arial", Font.PLAIN, 12));
	c.weightx = 0.5;
	c.gridx = 1;
	c.gridy = 4;
	layout.setConstraints(teamPrompt, c);
	this.add(teamPrompt);

	//Choosing a team to buy/sell with
	teamList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	teamList.setVisibleRowCount(-1);
	teamList.setBackground(Color.decode("#525151"));
	teamList.setForeground(Color.white);
	teamList.setMinimumSize(new Dimension(400, 200));
	teamList.setFont(new Font("Arial", Font.PLAIN, 12));
	JScrollPane teamScroller = new JScrollPane(teamList);
	teamScroller.setPreferredSize(new Dimension(400, 200));
	teamScroller.setMinimumSize(new Dimension(400, 200));
	c.gridx = 1;
	c.gridy = 5;
	layout.setConstraints(teamScroller, c);
	this.add(teamScroller);
	teamList.addListSelectionListener(new ListSelectionListener() {
	    @Override
	    public void valueChanged(ListSelectionEvent e) {
		//exception occurs here
		if (teamList.getValueIsAdjusting() && buyOrSellList.getSelectedValue().equals("Buy")) {
		    playerArray.clear();
		    for (int i = 0; i < StateManager.getLeague().getTeams().size(); i++) {
			if (teamList.getSelectedValue().equals(StateManager.getLeague().getTeams().get(i).getTeamName())) {
			    playerPrompt.setText("Please select a player from this team:");
			    for (int j = 0; j < StateManager.getLeague().getTeams().get(i).getPlayers().size(); j++) {
				playerArray.add(StateManager.getLeague().getTeams().get(i).getPlayers().get(j).getPlayerName());
				//System.out.println(StateManager.getLeague().getTeams().get(i).getPlayers().get(j).getPlayerName());
				String[] playerArrayNotList = new String[playerArray.size()];
				for (int m = 0; m < playerArray.size(); m++) {
				    playerArrayNotList[m] = playerArray.get(m);
				}
				playerList.setListData(playerArrayNotList);
				attemptPricePrompt.setText("---");
				attemptPrice.setEditable(false);
				buttonAttempt.setEnabled(false);
			    }
			}
		    }
		}
		if (teamList.getValueIsAdjusting() && buyOrSellList.getSelectedValue().equals("Sell")) {
		    playerArray.clear();
		    for (int i = 0; i < StateManager.getLeague().getTeams().size(); i++) {
			if (StateManager.getLeague().getChosenTeam().equals(StateManager.getLeague().getTeams().get(i).getTeamName())) {
			    playerPrompt.setText("Please select a player from your team:");
			    for (int j = 0; j < StateManager.getLeague().getTeams().get(i).getPlayers().size(); j++) {
				playerArray.add(StateManager.getLeague().getTeams().get(i).getPlayers().get(j).getPlayerName());
				String[] playerArrayNotList = new String[playerArray.size()];
				for (int m = 0; m < playerArray.size(); m++) {
				    playerArrayNotList[m] = playerArray.get(m);
				}
				playerList.setListData(playerArrayNotList);
				attemptPricePrompt.setText("---");
				attemptPrice.setEditable(false);
				buttonAttempt.setEnabled(false);
			    }
			}
		    }
		}
		if (teamList.isSelectionEmpty() == false && buyOrSellList.isSelectionEmpty() == false && playerList.isSelectionEmpty() == false) {
		    attemptPricePrompt.setText("For what price would you like to try this?");
		    attemptPrice.setEditable(true);
		    buttonAttempt.setEnabled(true);
		}
	    }
	});

	//Prompt choosing a player
	playerPrompt.setOpaque(true);
	playerPrompt.setPreferredSize(new Dimension(400, 20));
	playerPrompt.setEditable(false);
	playerPrompt.setText("---");
	playerPrompt.setBackground(Color.decode("#525151"));
	playerPrompt.setForeground(Color.white);
	playerPrompt.setMinimumSize(new Dimension(400, 20));
	playerPrompt.setFont(new Font("Arial", Font.PLAIN, 12));
	c.weightx = 0.5;
	c.gridx = 1;
	c.gridy = 6;
	layout.setConstraints(playerPrompt, c);
	this.add(playerPrompt);

	//Choosing a player to buy/sell
	playerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	playerList.setVisibleRowCount(-1);
	playerList.setBackground(Color.decode("#525151"));
	playerList.setForeground(Color.white);
	playerList.setMinimumSize(new Dimension(400, 200));
	playerList.setFont(new Font("Arial", Font.PLAIN, 12));
	JScrollPane playerScroller = new JScrollPane(playerList);
	playerScroller.setPreferredSize(new Dimension(400, 200));
	playerScroller.setMinimumSize(new Dimension(400, 200));
	c.weightx = 0.5;
	c.gridx = 1;
	c.gridy = 7;
	c.gridheight = 2;
	layout.setConstraints(playerScroller, c);
	this.add(playerScroller);
	playerList.addListSelectionListener(new ListSelectionListener() {
	    @Override
	    public void valueChanged(ListSelectionEvent e) {
		if (teamList.isSelectionEmpty() == false && buyOrSellList.isSelectionEmpty() == false && playerList.isSelectionEmpty() == false && alreadyTried.contains(playerList.getSelectedValue()) == false) {
		    attemptPricePrompt.setText("For what price would you like to try this?");
		    attemptPrice.setEditable(true);
		    buttonAttempt.setEnabled(true);
		} else {
		    attemptPricePrompt.setText("---");
		    attemptPrice.setEditable(false);
		    buttonAttempt.setEnabled(false);
		}
	    }
	});

	c.gridheight = 1;
	//Prompt price attempt
	attemptPricePrompt.setOpaque(true);
	attemptPricePrompt.setPreferredSize(new Dimension(400, 20));
	attemptPricePrompt.setEditable(false);
	attemptPricePrompt.setText("---");
	attemptPricePrompt.setBackground(Color.decode("#525151"));
	attemptPricePrompt.setForeground(Color.white);
	attemptPricePrompt.setMinimumSize(new Dimension(400, 20));
	attemptPricePrompt.setFont(new Font("Arial", Font.PLAIN, 12));
	c.weightx = 0.5;
	c.gridx = 1;
	c.gridy = 9;
	layout.setConstraints(attemptPricePrompt, c);
	this.add(attemptPricePrompt);

	//Price for attempt
	attemptPrice.setOpaque(true);
	attemptPrice.setPreferredSize(new Dimension(400, 20));
	attemptPrice.setEditable(false);
	attemptPrice.setBackground(Color.decode("#525151"));
	attemptPrice.setForeground(Color.white);
	attemptPrice.setMinimumSize(new Dimension(400, 20));
	attemptPrice.setFont(new Font("Arial", Font.PLAIN, 12));
	c.weightx = 0.5;
	c.gridx = 1;
	c.gridy = 10;
	layout.setConstraints(attemptPrice, c);
	this.add(attemptPrice);

	//Attempt action
	c.weightx = 0.5;
	c.gridx = 1;
	c.gridy = 11;
	createButton(buttonAttempt, "", c, layout);
	buttonAttempt.setEnabled(false);
	buttonAttempt.setPreferredSize(new Dimension(400, 50));
	buttonAttempt.setMinimumSize(new Dimension(400, 50));
	buttonAttempt.setMargin(new Insets(0, 0, 0, 0));
	buttonAttempt.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		if (alreadyTried.contains((String)playerList.getSelectedValue()) == false) {
		    System.out.println("Attempt to buy or sell made: " + buyOrSellList.getSelectedValue() + ", " + playerList.getSelectedValue() + " from/to " + teamList.getSelectedValue());
		    alreadyTried.add((String) playerList.getSelectedValue());
		    System.out.println(alreadyTried);
                    
                    String soortTransactie = (String) buyOrSellList.getSelectedValue();
                    String offerFormat = (String) teamList.getSelectedValue() + " " + (String) attemptPrice.getText() + " " + (String) playerList.getSelectedValue();
                    System.out.println(soortTransactie + offerFormat);
                    StateManager.getLeague().Transfer(soortTransactie, offerFormat);
                    budgetDisplay.setText("Your budget: " + StateManager.getLeague().getTeamByName(StateManager.getLeague().getChosenTeam()).getBudget());
		}
	    }
	});

	//Go back
	JButton buttonBack = new JButton(new ImageIcon(buttonBackImage));
	c.weightx = 0.5;
	c.gridx = 2;
	c.gridy = 8;
	c.gridheight = 4;
	buttonBack.setPreferredSize(new Dimension(400, 240));
        buttonBack.setMinimumSize(new Dimension(400, 240));
	buttonBack.setMargin(new Insets(0, 0, 0, 0));
	createButton(buttonBack, "", c, layout);
	
	attachStateChanger(buttonBack, new MenuBetweenRounds());
	c.gridheight = 1;
        buttonBack.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
                for(int i = 0; i < tempOffersTried.size(); i++) {
                    System.out.println("Offers in store: " + StateManager.getLeague().getOffersMade());
                    StateManager.getLeague().getOffersMade().remove(tempOffersTried.get(i));
                    System.out.println("Removing offer: " + tempOffersTried.get(i));
                }
                
	    }
	});
        
	//Prompt Offers
	JTextField lookAtOffersPrompt = new JTextField();
	lookAtOffersPrompt.setOpaque(true);
	lookAtOffersPrompt.setPreferredSize(new Dimension(400, 20));
	lookAtOffersPrompt.setEditable(false);
	lookAtOffersPrompt.setText("Here are offers from other teams: ");
	lookAtOffersPrompt.setBackground(Color.decode("#525151"));
	lookAtOffersPrompt.setForeground(Color.white);
	lookAtOffersPrompt.setMinimumSize(new Dimension(400, 20));
	lookAtOffersPrompt.setFont(new Font("Arial", Font.PLAIN, 12));
	c.weightx = 0.5;
	c.gridx = 2;
	c.gridy = 2;
	layout.setConstraints(lookAtOffersPrompt, c);
	this.add(lookAtOffersPrompt);

	//Accept Offers
	c.weightx = 0.5;
	c.gridx = 2;
	c.gridy = 7;
	createButton(offerAccept, "", c, layout);
	offerAccept.setEnabled(false);
	offerAccept.setPreferredSize(new Dimension(400, 50));
	offerAccept.setMinimumSize(new Dimension(400, 50));
	offerAccept.setMargin(new Insets(0, 0, 0, 0));
	offerAccept.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
                if (tempOffersTried.contains((String)offerList.getSelectedValue()) == false) {
                    String offer = (String) offerList.getSelectedValue();
                    //StateManager.getLeague().getOffersMade().remove(offerList.getSelectedIndex());
                    //offerList.removeSelectionInterval(offerList.getSelectedIndex(), offerList.getSelectedIndex());
                    StateManager.getLeague().TransferOffer("Sell", offer);
                    tempOffersTried.add((String) offerList.getSelectedValue());
                    System.out.println("Offer accepted: " + offer + " New arrayofaccepted: " + tempOffersTried);
                    budgetDisplay.setText("Your budget: " + StateManager.getLeague().getTeamByName(StateManager.getLeague().getChosenTeam()).getBudget());
		}
	    }
	});

	//Offer List
	offerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	offerList.setVisibleRowCount(-1);
	offerList.setBackground(Color.decode("#525151"));
	offerList.setForeground(Color.white);
	offerList.setMinimumSize(new Dimension(400, 278));
	offerList.setFont(new Font("Arial", Font.PLAIN, 12));
	JScrollPane offerScroller = new JScrollPane(offerList);
	offerScroller.setPreferredSize(new Dimension(400, 278));
	offerScroller.setMinimumSize(new Dimension(400, 278));
	c.weightx = 0.5;
	c.gridx = 2;
	c.gridy = 3;
	c.gridheight = 4;
	layout.setConstraints(offerScroller, c);
	this.add(offerScroller);
	offerList.addListSelectionListener(new ListSelectionListener() {
	    @Override
	    public void valueChanged(ListSelectionEvent e) {
		if (offerList.isSelectionEmpty() == false && tempOffersTried.contains((String) offerList.getSelectedValue()) == false) {
                    //System.out.println("Enable that button");
		    offerAccept.setEnabled(true);
		} else {
		    offerAccept.setEnabled(false);
		}
	    }
	});

	c.gridheight = 1;

	//Padding
	JTextArea invisi2 = new JTextArea();
	c.weightx = 0.5;
	c.gridx = 1;
	c.gridy = 0;
	layout.setConstraints(invisi2, c);
	invisi2.setPreferredSize(new Dimension(100, 100));
	invisi2.setOpaque(false);
	invisi2.setEditable(false);
	invisi2.setMargin(new Insets(50, 0, 0, 0));
	this.add(invisi2);

//	JTextArea invisi3 = new JTextArea();
//	c.weightx = 0.5;
//	c.gridx = 1;
//	c.gridy = 2;
//	c.gridwidth = 2;
//	layout.setConstraints(invisi3, c);
//	invisi3.setPreferredSize(new Dimension(1000, 10));
//	invisi3.setOpaque(true);
//	invisi3.setEditable(false);
//	invisi3.setMargin(new Insets(0, 300, 0, 300));
//	this.add(invisi3);
//	JTextArea invisi4 = new JTextArea();
//	c.weightx = 0.5;
//	c.gridx = 3;
//	c.gridy = 5;
//	layout.setConstraints(invisi4, c);
//	invisi4.setPreferredSize(new Dimension(300, 300));
//	invisi4.setOpaque(true);
//	invisi4.setEditable(false);
//	invisi4.setMargin(new Insets(0, 0, 300, 0));
//	this.add(invisi4);
	c.weightx = 0.5;
	c.gridheight = 13;
	c.gridwidth = 4;
	c.gridx = 0;
	c.gridy = 0;
	ImagePanel panel = new ImagePanel(new ImageIcon(panelPanelImage).getImage(), c, layout);
	this.add(panel);
    }
}
