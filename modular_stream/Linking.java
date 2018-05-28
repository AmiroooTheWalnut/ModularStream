/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modular_stream;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author Amir72c
 */
public class Linking extends JButton {

    StreamPanel myParent;
    String my_outputNode;
    String my_inputNode;
    int my_outputChannel;
    int my_inputChannel;
    int tail_length = 10;
    Polygon contains_polygon;
    boolean isselected = false;
    Linking thisLink=this;

    Linking(StreamPanel parent, String outputNode, String inputNode, int outputChannel, int inputChannel) {
        thisLink.setBounds(0, 0, 10, 10);

        myParent = parent;
        my_outputNode = outputNode;
        my_inputNode = inputNode;
        my_outputChannel = outputChannel;
        my_inputChannel = inputChannel;
        thisLink.setBorderPainted(false);
        thisLink.setBorder(null);
        thisLink.setOpaque(false);
        thisLink.setContentAreaFilled(false);
        thisLink.setBorderPainted(false);
        Image add_image = new ImageIcon(getClass().getResource("/images/link_hand_pointer.png")).getImage();
        Cursor add_cursor = Toolkit.getDefaultToolkit().createCustomCursor(
                add_image,
                new Point(2, 2), "link selection cursor");
        thisLink.setCursor(add_cursor);
        thisLink.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                isselected = true;
            }

            @Override
            public void focusLost(FocusEvent e) {
                isselected = false;
            }
        });
        thisLink.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_DELETE && isselected == true) {
                    myParent.remove(thisLink);
                    myParent.repaint();
                    StreamNode grandFather = myParent.FindNode(my_inputNode);
                    grandFather.InputChannels[my_inputChannel].myData=0;
                    grandFather.InputChannels[my_inputChannel].isfed = false;
                    grandFather.InputChannels[my_inputChannel].myLink = null;
                    grandFather.remove(thisLink);
                }
            }
            

            public void keyReleased(KeyEvent e) {
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isselected == true) {
            g.setColor(Color.YELLOW);
        } else {
            g.setColor(Color.black);
        }
        StreamNode outputNode = myParent.FindNode(my_outputNode);
        StreamNode inputNode = myParent.FindNode(my_inputNode);
        int start_x = 0;
        int start_y = 0;
        int width = 0;
        int height = 0;
        try {
            start_x = Math.min(outputNode.getX() + outputNode.OutputChannels[my_outputChannel].getX(), inputNode.getX() + inputNode.InputChannels[my_inputChannel].getX()) + outputNode.OutputChannels[my_outputChannel].width / 2;
            start_y = Math.min(outputNode.getY() + outputNode.OutputChannels[my_outputChannel].getY(), inputNode.getY() + inputNode.InputChannels[my_inputChannel].getY()) + outputNode.OutputChannels[my_outputChannel].height / 2;
            width = Math.abs(outputNode.getX() + outputNode.OutputChannels[my_outputChannel].getX() - (inputNode.getX() + inputNode.InputChannels[my_inputChannel].getX()));
            height = Math.abs(outputNode.getY() + outputNode.OutputChannels[my_outputChannel].getY() - (inputNode.getY() + inputNode.InputChannels[my_inputChannel].getY()));
            if (width < 10) {
                width = 10;
            }
            if (height < 10) {
                height = 10;
            }

            thisLink.setBounds(start_x, start_y, width, height);
            if (start_x == outputNode.getX() + outputNode.OutputChannels[my_outputChannel].getX() + outputNode.OutputChannels[my_outputChannel].width / 2 && start_y == outputNode.getY() + outputNode.OutputChannels[my_outputChannel].getY() + outputNode.OutputChannels[my_outputChannel].height / 2
                    || start_x == inputNode.getX() + inputNode.InputChannels[my_inputChannel].getX() + inputNode.InputChannels[my_inputChannel].width / 2 && start_y == inputNode.getY() + inputNode.InputChannels[my_inputChannel].getY() + inputNode.InputChannels[my_inputChannel].height / 2) {
                g.drawLine(0, 0, tail_length, 0);
                g.drawLine(tail_length, 0, width - tail_length, height - 1);
                g.drawLine(width - tail_length, height - 1, width, height - 1);
                contains_polygon = new Polygon();
                //contains_polygon.addPoint(-1, -1);
                contains_polygon.addPoint(tail_length + 5, -1);
                contains_polygon.addPoint(width - tail_length, height - 5);
                //contains_polygon.addPoint(width, height-5);
                //contains_polygon.addPoint(width, height+2);
                contains_polygon.addPoint(width - tail_length, height + 2);
                contains_polygon.addPoint(tail_length, 5);
                //contains_polygon.addPoint(-1, 5);
            } else {
                g.drawLine(0, height - 1, tail_length, height - 1);
                g.drawLine(tail_length, height - 1, width - tail_length, 0);
                g.drawLine(width - tail_length, 0, width, 0);
                contains_polygon = new Polygon();
                //contains_polygon.addPoint(-1, height+1);
                contains_polygon.addPoint(tail_length + 5, height + 1);
                contains_polygon.addPoint(width - tail_length, 5);
                //contains_polygon.addPoint(width+1, 5);
                //contains_polygon.addPoint(width+1, -1);
                contains_polygon.addPoint(width - tail_length, -1);
                contains_polygon.addPoint(tail_length, height - 5);
                //contains_polygon.addPoint(-1, height-5);
            }
        } catch (Exception ex) {
            myParent.remove(thisLink);
            try {
                StreamNode grandFather = myParent.FindNode(my_inputNode);
                grandFather.InputChannels[my_inputChannel].isfed = false;
                grandFather.InputChannels[my_inputChannel].myLink = null;
                grandFather.remove(thisLink);
            } catch (Exception ex1) {
            }
        }
    }

    public double activateLinking() {
        StreamNode grandFather = myParent.FindNode(my_outputNode);
        return grandFather.OutputChannels[my_outputChannel].myData;
    }

    @Override
    public boolean contains(int x, int y) {
        //    if (thisLink.getGraphics().getClip().contains(x,y)==true)
        //        System.out.println("true");
        //    return thisLink.getGraphics().getClip().contains(x,y);//triangle.contains(x, y);
        if (contains_polygon != null) {
            return contains_polygon.contains(x, y);
        } else {
            return false;
        }

    }
}
