/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MultiShapeTest;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import static MultiShapeTest.InternalWindow.DELAY;
/**
 * An icon that contains a moveable shape.
 * @author Marissa Bucaro
 */
public class ShapeIcon implements Icon
{
    private int width;
    private int height;
    private MoveableShape shape;
    private JLabel label;
    private boolean reverse;
    private Timer t;
    
    /**
     * Constructor assigns instance fields of the class.
     * @param _shape MoveableShape object that contains the shape's pattern.
     * @param _width Integer that contains the width of the ShapeIcon
     * @param _height Integer that contains the height of the ShapeIcon
     */
    public ShapeIcon(MoveableShape _shape, int _width, int _height)
    {
        shape = _shape;
        width = _width;
        height = _height;
        reverse = false;
    }
    
    /**
     * Sets the Timer of the class to move the the ShapeIcon across the display window.
     * @param _label JLabel that contains the ShapeIcon object.
     */
    public void setTimer(JLabel _label)
    {
        label = _label;
        t = new Timer(DELAY, new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                if(!reverse)
                {
                    shape.move(1, 0);                    
                    label.repaint();
                }
                else
                {
                    shape.move(-1, 0);
                    label.repaint();
                }
            }
        });
    }
    
    /**
     * Starts the timer of the ShapeIcon object.
     */
    public void startTimer()
    {
        t.start();
    }
    
    /**
     * Stops the timer of the ShapeIcon object.
     */
    public void stopTimer()
    {
        t.stop();
    }
    
    /**
     * Setter that indicates if the ShapeIcon is moving forward or backwards.
     */
    public void setReverse()
    {
        if(reverse == true)
            reverse = false;
        else
            reverse = true;
    }
    
    /**
     * Draws the MoveableShape by calling its draw method.
     * @param c 
     * @param g 
     * @param x
     * @param y 
     */
    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) 
    {
        Graphics2D g2 = (Graphics2D)g;
        shape.draw(g2);
    }
    
    /**
     * Retrieves the width of the ShapeIcon.
     * @return Integer indicating the width of the ShapeIcon
     */
    @Override
    public int getIconWidth() 
    {
        return width;
    }
    
    /**
     * Retrieves the width of the ShapeIcon
     * @return Integer indicating the height of the ShapeIcon
     */
    @Override
    public int getIconHeight() 
    {
        return height;
    }  

}
