/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MultiShapeTest;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

/**
 * Handles program operations based on user commands.
 * @author Marissa Bucaro
 */
public class InternalWindow extends JInternalFrame
{
    public static final int DELAY = 10;
    private static final int ICON_WIDTH = 400;
    private static final int ICON_HEIGHT = 100;
    private static final int SHAPE_WIDTH = 100;
    private JInternalFrame in;
    private BoxLayout bl;
    private JPanel windowPanel;
    private JLayeredPane iconPanel;
    private JButton hideBtn;
    private JButton exitBtn;
    private ArrayList<JLabel> labelList;
    private ArrayList<ShapeIcon> shapesList;
    private boolean isHide;
    private boolean isExit;
    private Random gen;
    
    /**
     * Constructor initializes the instance fields of the class and assigns actions.
     */
    public InternalWindow()
    {
        gen = new Random();
        windowPanel = new JPanel();
        iconPanel = new JLayeredPane();
        iconPanel.setPreferredSize(new Dimension(400, 250));
        bl = new BoxLayout(windowPanel, bl.PAGE_AXIS);
        in = new JInternalFrame();
        in.setPreferredSize(new Dimension(400, 400));
        windowPanel.setLayout(bl);
        hideBtn = new JButton("Hide");
        exitBtn = new JButton("Exit");
        windowPanel.add(hideBtn);
        windowPanel.add(exitBtn);
        windowPanel.add(iconPanel);
        in.setContentPane(windowPanel);
        in.pack();     
        
        labelList = new ArrayList<JLabel>();
        shapesList = new ArrayList<ShapeIcon>();
        
        isHide = true;
        isExit = false;
        
        setButtonActions();
    }
    
    /**
     * Retrieves the internal window for the Main class to display.
     * @return a JInternalFrame containing the internal GUI
     */
    public JInternalFrame getIconWindow()
    {       
        return in;
    }
    
    /**
     * Sets actions/functionality for the buttons.
     */
    private void setButtonActions()
    {
        final InternalWindow iw = this;
        hideBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                isHide = true;
                iw.setInternalVisibility(false);
            }
        }); 
        
        exitBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                isExit = true;
            }
        }); 
    }
    
    /**
     * Sets visibility for all components in the class.
     * @param flag boolean true indicates visibility, false indicates invisibility.
     */
    public void setInternalVisibility(boolean flag)
    {
        windowPanel.setVisible(flag);
        iconPanel.setVisible(flag);
        in.setVisible(flag);
        this.setVisible(flag);
    }
    
    /**
     * Sets visibility for the JLayeredPane containing the moving Icons.
     * @param flag boolean true indicates visibility, false indicates invisibility.
     */
    public void setIconFieldVisibility(boolean flag)
    {
        iconPanel.setVisible(flag);
    }
    /**
     * Adds a UFO shape to the display area. First it adds a ShapeIcon to the arraylist
     * tracking the ShapeIcon objects, then calls addShape() to add it to the display area.
     */
    public void addUFO()
    {
        shapesList.add(new ShapeIcon(new UFO(0, 0, SHAPE_WIDTH), ICON_WIDTH, ICON_HEIGHT));
        addShape();      
    }
    
    /**
     * Adds a Rocket shape to the display area. First it adds a ShapeIcon to the arraylist
     * tracking the ShapeIcon objects, then calls addShape() to add it to the display area.
     */
    public void addRocket()
    {
        shapesList.add(new ShapeIcon(new Rocket(0, 0, SHAPE_WIDTH), ICON_WIDTH, ICON_HEIGHT));
        addShape();
    }
    
    /**
     * Adds a Bird shape to the display area. First it adds a ShapeIcon to the arraylist
     * tracking the ShapeIcon objects, then calls addShape() to add it to the display area.
     */
    public void addBird()
    {
        shapesList.add(new ShapeIcon(new Bird(0, 0, SHAPE_WIDTH), ICON_WIDTH, ICON_HEIGHT));
        addShape();
    }
    
    /**
     * Adds a JLabel, containing the most recently added IconShape, to the display area.
     * First the JLabel is added to an ArrayLis tracking the JLabels, then the JLabel is
     * added, and last it will begin moving if the InternalWindow is visible.
     */
    private void addShape()
    {
        labelList.add(new JLabel(getLastShapeIcon()));
        getLastLabel().setBounds(0, gen.nextInt(230), ICON_WIDTH, ICON_HEIGHT);
        iconPanel.add(getLastLabel(), new Integer(0));
        in.pack();
        
        getLastShapeIcon().setTimer(getLastLabel());
        
        if(isHide == false)
        {
            getLastShapeIcon().startTimer();
        }  
    }
    
    /**
     * Reverses the direction of the moving shapes.
     */
    public void changeDirection()
    {
        if(!shapesList.isEmpty() && isHide == false)
        {
            for(int i = 0; i < shapesList.size(); i++)
            {
                shapesList.get(i).setReverse();
            }
        }
    }
    
    /**
     * Starts all of the timers of the ShapeIcon objects in the arraylist.
     */
    public void startAllTimers()
    {
        if(!shapesList.isEmpty() && isHide == false)
        {
            for(int i = 0; i < shapesList.size() - 1; i++)
            {
                shapesList.get(i).startTimer();
            }
        }
    }
    
    /**
     * Stops all of the timers of the ShapeIcon objects in the arraylist.
     */
    public void stopAllTimers()
    {
        if(!shapesList.isEmpty() && isHide == false)
        {
            for(int i = 0; i < shapesList.size(); i++)
            {
                shapesList.get(i).stopTimer();
            }
        }
    }
    
    /**
     * Removes the most recently added IconShape from the arraylist.
     */
    public void removeShape()
    {
        if(!shapesList.isEmpty() && (!labelList.isEmpty()))
        {
            shapesList.remove(shapesList.size() - 1);
            labelList.remove(labelList.size() - 1); 
            iconPanel.remove(0);
            iconPanel.revalidate();
            iconPanel.repaint();
        }
    }
    
    /**
     * Retrieves the isHide boolean indicating if the InternalWindow should be visible.
     * @return boolean true indicates the InternalWindow should be invisible, false
     * indicates visibility.
     */
    public boolean getHide()
    {
        return isHide;
    }
    
    /**
     * Sets the isHide boolean indicating if the InternalWindow should be visible.
     * @param flag boolean true indicates the InternalWindow should be invisible, false
     * indicates visibility.
     */
    public void setHide(boolean flag)
    {
        isHide = flag;
    }
    
    /**
     * Retrieves the isExit boolean indicating if the InternalWindow should be destroyed.
     * @return boolean true indicates the InternalWindow should be destroyed, false
     * indicates it should not.
     */
    public boolean getExit()
    {
        return isExit;
    }
    
    /**
     * Retrieves the isExit boolean indicating if the InternalWindow should be destroyed.
     * @param flag boolean true indicates the InternalWindow should be destroyed, false
     * indicates it should not.
     */
    public void setExit(boolean flag)
    {
        isExit = flag;
    }
    
    /**
     * Retrieves the last object in the ArrayList of ShapeIcons.
     * @return ShapeIcon that was most recently added to the display area.
     */
    public ShapeIcon getLastShapeIcon()
    {
        return shapesList.get(shapesList.size() - 1);
    }
    
    /**
     * Retrieves the last object in the ArrayList of JLabels.
     * @return JLabel that was most recently added to the display area.
     */
    public JLabel getLastLabel()
    {
        return labelList.get(labelList.size() - 1);
    } 
}

