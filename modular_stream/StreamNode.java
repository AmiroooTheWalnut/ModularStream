/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modular_stream;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author Amir72c
 */
public class StreamNode extends JButton implements Externalizable{

    Channel InputChannels[];
    Channel OutputChannels[];
    boolean isPrallel = false;
    boolean isselected = false;
    boolean isactive = false;
    public int numInput = 1;
    public int numOutput = 1;
    String thisNodeName;
    Point my_position;
    int width;
    StreamNode thisNode = this;
    public StreamPanel mySteamPanelParent;
    double inputData[];
    double outputData[];
    boolean isRealNode = true;
    public StreamNodeDialog nodeDialog;

    StreamNode(StreamPanel meta_parent, final Point dragged_position, String name) {
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        mySteamPanelParent = meta_parent;
        thisNodeName = name;
        width = (int) new FontMetrics(new Font("SansSerif", Font.PLAIN, 12)) {
        }.getStringBounds(thisNodeName, null).getWidth() + 20;
        my_position = dragged_position;
        this.setLayout(null);
        this.setBounds(my_position.x, my_position.y, width, 20 + ((Math.max(numInput, numOutput) - 1) * 10) + Math.max(numInput, numOutput) * 10);
        this.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                isselected = true;
            }

            @Override
            public void focusLost(FocusEvent e) {
                isselected = false;
            }
        });

        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (mySteamPanelParent.getMousePosition() != null) {
                    thisNode.setLocation(mySteamPanelParent.getMousePosition().x - (width / 2), mySteamPanelParent.getMousePosition().y - 10);
                    dragged_position.x = mySteamPanelParent.getMousePosition().x - (width / 2);
                    dragged_position.y = mySteamPanelParent.getMousePosition().y - 10;
                }
                mySteamPanelParent.refresh_canvas_bounds();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && !e.isConsumed()) {
                    e.consume();
                    ShowDialog();
                }
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

        this.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_DELETE && isselected == true) {
                    mySteamPanelParent.remove(thisNode);
                    mySteamPanelParent.repaint();
                }
            }

            public void keyReleased(KeyEvent e) {
            }
        });

        InitChannels();
        nodeDialog = new StreamNodeDialog((JFrame) SwingUtilities.getWindowAncestor(thisNode),thisNode, true);
    }

    public void ShowDialog() {
        nodeDialog.setVisible(true);
    }

    public void InitChannels() {
        this.removeAll();
        InputChannels = new Channel[numInput];
        OutputChannels = new Channel[numOutput];
        inputData = new double[numInput];
        outputData = new double[numOutput];
        for (int i = 0; i < numInput; i++) {
            InputChannels[i] = new Channel(mySteamPanelParent, this, "input", i);
            InputChannels[i].setBounds(0, 10 + (i) * 20, 10, 10);
            InputChannels[i].setVisible(true);
            this.add(InputChannels[i]);
        }
        for (int i = 0; i < numOutput; i++) {
            OutputChannels[i] = new Channel(mySteamPanelParent, this, "output", i);
            OutputChannels[i].setBounds(width - OutputChannels[i].width, 10 + (i) * 20, 10, 10);
            OutputChannels[i].setVisible(true);
            this.add(OutputChannels[i]);
        }
    }

    public void activateNode() {
        for (int i = 0; i < InputChannels.length; i++) {
            InputChannels[i].activateChannel();
        }
        outputData = evaluateNode();
        for (int i = 0; i < OutputChannels.length; i++) {
            OutputChannels[i].activateChannel();
        }
    }

    public double[] evaluateNode() {
        double output[] = new double[numOutput];
        for (int i = 0; i < output.length; i++) {
            if(((OutputPanel)nodeDialog.AllOutputs.getComponent(i)).myKernel==null)
            {
                output[i]=0;
            }else{
                output[i]=((OutputPanel)nodeDialog.AllOutputs.getComponent(i)).myKernel.runKernel();
            }
            //if(((OutputPanel)nodeDialog.AllOutputs.getComponent(i)).IsGlobal.isSelected())
            //{
                //mySteamPanelParent.root_timer.Globals.put(thisNodeName+"_Output_"+i, output[i]);
            //}
            //output[i] = Math.random();
        }
        return output;
    }

    @Override
    public void paintComponent(Graphics g) {
        if (isselected == true) {
            g.setColor(Color.ORANGE);
        } else {
            g.setColor(Color.RED);
        }
        if (isactive == true) {
            g.setColor(Color.YELLOW);
        }
        this.setBounds(my_position.x, my_position.y, width, 20 + ((Math.max(numInput, numOutput) - 1) * 10) + Math.max(numInput, numOutput) * 10);
        g.fillRect(0, 0, width, 20 + ((Math.max(numInput, numOutput) - 1) * 10) + Math.max(numInput, numOutput) * 10);
        g.setColor(Color.BLACK);
        g.drawString(thisNodeName, 10, 10);
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
