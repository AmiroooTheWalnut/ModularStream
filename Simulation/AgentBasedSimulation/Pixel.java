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
public class Pixel extends JButton {

    boolean is_alive = false;
    int my_order;
    int size = 10;
    int x;
    int y;
    int num_columns;

    Pixel(final Main_frame my_parent, int order) {
        num_columns=my_parent.num_columns;
        my_order = order - 1;
        this.setBounds(((order % num_columns) * 11), (int) (Math.floor(order / num_columns)) * (size + 1), size, size);
        x = order % num_columns;
        y = (int) (Math.floor(order / num_columns));
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (my_parent.slider.getValue() == 1) {
                    if (is_alive == true) {
                        is_alive = false;
                    } else {
                        is_alive = true;
                    }
                    my_parent.is_state_inialized=false;
                }else{
                    my_parent.status.setText("Go to first frame by slider or set this state as init state!");
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
        if (is_alive == true) {
            g.setColor(Color.red);
            g.fillRect(0, 0, size, size);
        } else {
            g.setColor(Color.BLUE);
            g.fillRect(0, 0, size, size);
        }

    }
}
