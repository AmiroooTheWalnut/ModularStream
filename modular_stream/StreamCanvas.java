/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modular_stream;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author Amir72c
 */
public class StreamCanvas extends StreamNode {

    StreamPanel my_StreamPanel;
    StreamCanvas thisCanvasNode = this;
    JButton zoomin;
    JButton zoomout;

    StreamCanvas(StreamPanel parent, Point dragged_position, String name) {
        super(parent, dragged_position, name);
        this.nodeDialog=null;
        mySteamPanelParent = parent;
        my_StreamPanel = new StreamPanel();
        my_StreamPanel.myCanvasParent=thisCanvasNode;
        my_StreamPanel.isRootStreamPanel=false;
        my_StreamPanel.metaParent=parent.metaParent;
        my_StreamPanel.initStreamChannel();
        my_StreamPanel.mainStreamScrollPane = mySteamPanelParent.mainStreamScrollPane;
        my_StreamPanel.mainStreamShowcase = mySteamPanelParent.mainStreamShowcase;
        my_StreamPanel.mainToolBar = mySteamPanelParent.mainToolBar;
        my_StreamPanel.numInputStreamChannel = numInput;
        my_StreamPanel.numOutputStreamChannel = numOutput;
        my_StreamPanel.root_timer = mySteamPanelParent.root_timer;
        my_StreamPanel.setVisible(true);

        Component[] temp = mySteamPanelParent.mainToolBar.getComponents();
        for (int i = 0; i < temp.length; i++) {
            if ("zoomin".equals(temp[i].getName())) {
                zoomin = (JButton) temp[i];
            }
            if ("zoomout".equals(temp[i].getName())) {
                zoomout = (JButton) temp[i];
            }
        }


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
                zoomin.setEnabled(true);
            }

            @Override
            public void focusLost(FocusEvent e) {
                isselected = false;
                zoomin.setEnabled(false);
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

        InitChannels();
        my_StreamPanel.RegisterNodes();
    }

    @Override
    public void activateNode() {

        for (int i = 0; i < InputChannels.length; i++) {
            InputChannels[i].activateChannel();
            my_StreamPanel.inputStreamChannels[i].outputData[0] = inputData[i];
        }
        outputData = evaluateNode();
        for (int i = 0; i < OutputChannels.length; i++) {
            OutputChannels[i].activateChannel();
        }
    }

    @Override
    public double[] evaluateNode() {
        double output[] = new double[numOutput];
        my_StreamPanel.activateStream();

        for (int i = 0; i < output.length; i++) {
            //output[i]=run_kernel();***************************
            //output[i]=Math.random();
            output[i] = my_StreamPanel.outputStreamChannels[i].inputData[0];
        }
        return output;
    }

    @Override
    public void ShowDialog() {
        StreamCanvasDialog current_StreamCanvasDialog = new StreamCanvasDialog((JFrame) SwingUtilities.getWindowAncestor(thisNode), true, thisCanvasNode);
        current_StreamCanvasDialog.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        if (isselected == true) {
            g.setColor(Color.MAGENTA);
        } else {
            g.setColor(Color.green);
        }
        if (isactive == true) {
            g.setColor(Color.YELLOW);
        }
        this.setBounds(my_position.x, my_position.y, width, 20 + ((Math.max(numInput, numOutput) - 1) * 10) + Math.max(numInput, numOutput) * 10);
        g.fillRect(0, 0, width, 20 + ((Math.max(numInput, numOutput) - 1) * 10) + Math.max(numInput, numOutput) * 10);
        g.setColor(Color.BLACK);
        g.drawString(thisNodeName, 10, 10);
    }
}
