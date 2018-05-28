/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulation.AgentBasedSimulation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;

/**
 *
 * @author Amir72c
 */
public class PixelAdjacent extends JButton {
    
    int size=10;
    boolean isSelected=false;
    boolean isCenter=false;
    PixelAdjacent()
    {
        
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (isSelected==false)
                {
                    isSelected=true;
                }else{
                    isSelected=false;
                }
                //System.out.println("x:"+x+"y:"+y+"order:"+my_order);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isCenter==true)
        {
            g.setColor(Color.GREEN);
            g.fillRect(0, 0, size, size);
        }else if (isSelected == true) {
            g.setColor(Color.RED);
            g.fillRect(0, 0, size, size);
        } else {
            g.setColor(Color.BLUE);
            g.fillRect(0, 0, size, size);
        }

    }
}
