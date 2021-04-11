/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MultiShapeTest;
import java.awt.*;

/**
 * A shape that can be moved around
 * @author Marissa Bucaro
 */
public interface MoveableShape 
{
    /**
     * Renders various geometrical shapes and lines.
     * @param g2 Graphics2D object to render lines and shapes.
     */
    void draw(Graphics2D g2);
    
    /**
     * Adjusts the X and Y coordinates of the an object.
     * @param dx The distance to move across the x-axis.
     * @param dy The distance to move across the y-axis.
     */
    void move(int dx, int dy);
}