/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MultiShapeTest;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.*;
import java.util.Random;

/**
 * Class containing the graphical information of Rocket shapes.
 * @author Marissa Bucaro
 */
public class Rocket implements MoveableShape
{
    private int x;
    private int y;
    private int width;
    private Random gen;
    private int color;
    
    /**
     * Constructs a Rocket MoveableShape based on the Integers passed in. X and y
     * represent coordinates, width is the size of the rocket.
     * @param _x Integer Represents the x coordinate where the Rocket will be drawn
     * @param _y Integer Represents the y coordinate where the Rocket will be drawn
     * @param _width Integer Represents the width of the Rocket object
     */
    public Rocket(int _x, int _y, int _width)
    {
        x = _x;
        y = _y;
        width = _width;
        gen = new Random();
        color = gen.nextInt(3);
    }
    
    /**
     * Recolors the UFO object with a random color
     * @param g2 Graphics2D object to render lines and shapes.
     */
    private void recolor(Graphics2D g2)
    {
        if(color == 0)
        {
            g2.setColor(Color.red);
        }
        else if(color == 1)
        {
            g2.setColor(Color.blue);
        }
        else
            g2.setColor(Color.black);
    }
    
    /**
     * Draws a Rocket shape on a coordinate based on the Rocket object's instance data.
     * @param g2 Graphics2D object to render lines and shapes.
     */
    @Override
    public void draw(Graphics2D g2) 
    {
        Rectangle2D.Double body = new Rectangle2D.Double(x, y + width / 6, width - 1, width / 6);

        Point2D.Double r1 = new Point2D.Double(x + width + 20, y + width / 4);
        Point2D.Double r2 = new Point2D.Double(x + width, y + width / 6);
        Point2D.Double r4 = new Point2D.Double(x + width, y + width / 3);

        Line2D.Double topPoint = new Line2D.Double(r1, r2);
        Line2D.Double bottomPoint = new Line2D.Double(r1, r4);
        
        recolor(g2);
        g2.draw(body);
        g2.draw(topPoint);
        g2.draw(bottomPoint);    
    }    
    
    /**
     * Adjusts the X and Y coordinates of the Rocket object.
     * @param dx Integer representing the distance for the Rocket to move across the X-axis
     * @param dy Integer representing the distance for the Rocket to move across the Y-axis
     */
    @Override
    public void move(int dx, int dy) 
    {
        if((x += dx) > 400 || (y+= dy) > 400)
        {
            x = 0;
            y = 0;
        }
        else if((y += dy) < 0 || (x += dx) < 0)
        {
            x = 400;
            y = 0;
        }
        x += dx;
        y += dy;
    }
    
}
