/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package states;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author user
 */
public abstract class State extends JPanel {
    
    GridBagConstraints c = null;
    GridBagLayout layout = null;
    
    protected void createSpace(){
        JTextArea invisi2 = new JTextArea();
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 2;
        layout.setConstraints(invisi2, c);
        invisi2.setPreferredSize(new Dimension(200, 200));
        invisi2.setOpaque(false);
        invisi2.setEditable(false);
        invisi2.setMargin(new Insets(200, 0, 0, 0));
        this.add(invisi2);
    }
    
    protected void createButton(JButton button, String label,
                                GridBagConstraints constraints, GridBagLayout layout){
        button.setText(label);
        button.setPreferredSize(new Dimension(400,80));
        button.setMinimumSize(new Dimension(400,80));
        layout.setConstraints(button, constraints);
        
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enableButtons();
            }
        });
        
        this.add(button);
    }
    
    protected void attachStateChanger(JButton button, final State newState) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StateManager.ChangeState(newState);
            }
        });
    }
    
    protected void createLabel(JLabel label, String text,
                               GridBagConstraints constraints, GridBagLayout layout){
        label.setText(text);
        label.setPreferredSize(new Dimension(400, 40));
        label.setMinimumSize(new Dimension(400, 40));
        layout.setConstraints(label, constraints);
        this.add(label);
    }
    
    protected void createSmallLabel(JLabel label, String text,
                               GridBagConstraints constraints, GridBagLayout layout){
        label.setText(text);
        label.setPreferredSize(new Dimension(200, 35));
        label.setMinimumSize(new Dimension(200, 35));
        layout.setConstraints(label, constraints);
        this.add(label);
    }
    
    protected void createInput(JTextField input,
                               GridBagConstraints constraints, GridBagLayout layout){
        input.setOpaque(true);
        input.setPreferredSize(new Dimension(400, 40));
        input.setMinimumSize(new Dimension(400, 40));
        input.setBackground(Color.decode("#525151"));
        input.setForeground(Color.white);
        input.setFont(new Font("Arial", Font.PLAIN, 20));
        layout.setConstraints(input, c);
        this.add(input);
    }
    
    
    protected void setBackground(String path){
        GridBagConstraints back = new GridBagConstraints();
        back.weightx = 0.5;
        back.gridheight = 10;
        back.gridwidth = 5;
        back.gridx = 0;
        back.gridy = 0;
        ImagePanel panel = new ImagePanel(new ImageIcon(path).getImage(), back, layout);
        this.add(panel);
    }
    
    abstract void createGUI();
    
    public void enableButtons() {
        
    }
}
