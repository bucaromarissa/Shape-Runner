/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MultiShapeTest;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * Class containing the graphical information of Bird shapes.
 * @author Marissa Bucaro
 */
public class Bird implements MoveableShape
{
    private int x;
    private int y;
    private int width;
    private Random gen;
    private int color;
    
    /**
     * Constructs a Bird MoveableShape based on the Integers passed in. X and y
     * represent coordinates, width is the size of the Bird.
     * @param _x Integer Represents the x coordinate where the Bird will be drawn
     * @param _y Integer Represents the y coordinate there the Bird will be drawn
     * @param _width Integer Represents the width of the Bird object
     */
    public Bird(int _x, int _y, int _width)
    {
        x = _x;
        y = _y;
        width = _width;
        gen = new Random();
        color = gen.nextInt(3);
    }
    
    /**
     * Recolors the Bird object with a random color
     * @param g2 Graphics2D object to render lines and shapes.
     */
    private void recolor(Graphics2D g2)
    {
        if(color == 0)
            g2.setColor(Color.red);
        else if(color == 1)
            g2.setColor(Color.blue);
        else
            g2.setColor(Color.black);
    }
    
    /**
     * Draws a Bird shape on a coordinate based on the Bird object's instance data.
     * @param g2 Graphics2D object to render lines and shapes.
     */
    @Override
    public void draw(Graphics2D g2) 
    {
        Ellipse2D.Double body = new Ellipse2D.Double(x + width / 6, y + width / 3, width / 3, width / 3);
        Ellipse2D.Double eye = new Ellipse2D.Double(x + width / 2.8, y + width / 2.4, width / 13, width / 13);

        Point2D.Double r1 = new Point2D.Double(x + width / 2.1, y + width / 2.3);
        Point2D.Double r2 = new Point2D.Double(x + width - 40, y + width / 2);
        Point2D.Double r3 = new Point2D.Double(x + width / 2.1, y + width / 1.8);
        
        Line2D.Double topBeak = new Line2D.Double(r1, r2);
        Line2D.Double bottomBeak = new Line2D.Double(r3, r2);
        
        recolor(g2);
        g2.draw(body);
        g2.draw(eye);
        g2.draw(topBeak);
        g2.draw(bottomBeak);
    }    
    
    /**
     * Adjusts the X and Y coordinates of the Bird object.
     * @param dx Integer representing the distance for the Bird to move across the X-axis
     * @param dy Integer representing the distance for the Bird to move across the Y-axis
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
