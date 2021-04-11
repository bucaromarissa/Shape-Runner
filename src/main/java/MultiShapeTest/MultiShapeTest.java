/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MultiShapeTest;

import java.awt.Dimension;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Main class creates the user GUI and handles the program loop.
 * @author Marissa Bucaro
 */
public class MultiShapeTest extends JFrame
{
    private InternalWindow internalWindow; 
    private JFrame mainFrame;
    private JPanel checklist;
    private JButton showBtn;
    private JButton addBtn;    
    private JButton removeBtn;       
    private JButton pauseBtn;      
    private JButton changeDirectionBtn;       
    private JButton exitBtn;
    private JCheckBox ufoCheck;
    private JCheckBox rocketCheck;
    private JCheckBox birdCheck;
    private GridBagConstraints gbc;
    private boolean exitApp;
    
    /**
     * Constructor initializes the instance fields of the class and assigns actions.
     */
    public MultiShapeTest()
    {
        internalWindow = new InternalWindow();
        mainFrame = new JFrame();
        gbc = new GridBagConstraints();
        checklist = new JPanel();
        showBtn = new JButton("Show");
        addBtn = new JButton("Add");     
        removeBtn = new JButton("Remove");        
        pauseBtn = new JButton("Pause");       
        changeDirectionBtn = new JButton("Change Direction");        
        exitBtn = new JButton("Exit");
        ufoCheck = new JCheckBox("UFO");
        rocketCheck = new JCheckBox("Rocket");
        birdCheck = new JCheckBox("Bird");
        exitApp = false;
        setButtonActions();
        setGUI();
    }
    
    /**
     * Main class starts the program and handles the program loop.
     * @param args 
     */
    public static void main(String[] args)
    {
        final MultiShapeTest iconApp = new MultiShapeTest();
        while(iconApp.internalWindow.getExit() == false)
        {
        
        }
        iconApp.destroyInternalWindow();  
    }
    
    /**
     * Setter for the gridbag container.
     * @param x the x coordinate of the gridbag
     * @param y the y coordinate of the gridbag
     */
    private void setGBC(int x, int y)
    {
        gbc.gridx = x;
        gbc.gridy = y;
    }
    
    /**
     * Setter assigns actions to the user GUI buttons.
     */
    private void setButtonActions()
    {
        showBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                showInternalWindow();
            }
        });
        
        addBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
               if(ufoCheck.isSelected())
               {
                   internalWindow.addUFO();
                   showInternalWindow();
               }
               if(rocketCheck.isSelected())
               {
                   internalWindow.addRocket();
                   showInternalWindow();
                   //System.out.println("Rocket is selected");
               }
               if(birdCheck.isSelected())
               {
                   internalWindow.addBird();
                   showInternalWindow();
               }
            }
        });
        
        removeBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                internalWindow.removeShape();
                //in.setVisible(true);
            }
        });
        
        pauseBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                internalWindow.stopAllTimers();
            }
        });
        
        changeDirectionBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {           
                internalWindow.changeDirection();
            }
        });
        
        exitBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                exitApp = true;
                System.exit(1);
            }
        }); 
    }
    
    /**
     * Makes the internal window visible and notifies its internal trackers.
     */
    private void showInternalWindow()
    {
        internalWindow.setInternalVisibility(true);
        internalWindow.setHide(false);
        internalWindow.setExit(false);
    }
    
    /**
     * Replaces the old InternalWindow object with a new one by reassigning its pointer
     */
    private void destroyInternalWindow()
    {
        mainFrame.remove(internalWindow);
        internalWindow = new InternalWindow();
        mainFrame.add(internalWindow.getIconWindow(), gbc);
        mainFrame.revalidate();
        mainFrame.repaint();
    }
    
    /**
     * Organizes the GUI components into the GridBag layout
     */
    private void setGUI()
    {
        mainFrame.setLayout(new GridBagLayout());
        gbc.fill = GridBagConstraints.HORIZONTAL;
        setGBC(0, 0);
        mainFrame.add(showBtn, gbc);
        setGBC(1, 0);
        mainFrame.add(addBtn, gbc);
        setGBC(2, 0);
        mainFrame.add(removeBtn, gbc);
        setGBC(3, 0);
        mainFrame.add(pauseBtn, gbc);
        setGBC(4, 0);
        mainFrame.add(changeDirectionBtn, gbc);
        setGBC(5, 0);
        mainFrame.add(exitBtn, gbc);
        setGBC(0, 1);
        checklist.add(ufoCheck);
        checklist.add(rocketCheck);
        checklist.add(birdCheck);
        mainFrame.add(checklist, gbc);
        setGBC(0, 2);
        mainFrame.add(internalWindow.getIconWindow(), gbc);
        
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setPreferredSize(new Dimension(1000, 1000));
        mainFrame.pack();
        mainFrame.setVisible(true);
    }   
}
