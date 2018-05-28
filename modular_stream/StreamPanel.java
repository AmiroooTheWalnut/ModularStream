/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modular_stream;

import com.jme3.math.Vector3f;
import com.jme3.system.AppSettings;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

/**
 *
 * @author Amir72c
 */
public final class StreamPanel extends JPanel implements Externalizable{
    //Channel RegisteredInputChannels[];
    //Channel RegisteredOutputChannels[];

    StreamNode allStreamNodes[];
    StreamCanvas allStreamCanvases[];
    StreamNode allNodes[];
    int numInputStreamChannel;
    int numOutputStreamChannel;
    StreamChannelNode inputStreamChannels[];
    StreamChannelNode outputStreamChannels[];
    JScrollPane mainStreamScrollPane;
    Showcase mainStreamShowcase;
    JToolBar mainToolBar;
    StreamPackage metaParent;
    StreamCanvas myCanvasParent;
    boolean isselected = false;
    boolean isRootStreamPanel = true;
    //ONLY ROOT STREAM PANEL
    public TimerDialog root_timer;
    //ONLY ROOT STREAM PANEL
    boolean isParallel = false;
    int num_StreamNode = 0;
    int num_StreamCanvas = 0;
    //CURSOR STATUS
    boolean islinking = false;
    boolean waslinked = false;
    //CURSOR STATUS
    Cursor add_cursor;
    StreamPanel thisStreamPanel = this;
    String[] objects_names;
    Vector3f[] objects_positions;

    public StreamPanel() {
        this.setLayout(null);
        this.setFocusable(true);
        final StreamPanel this_panel = this;

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                this_panel.requestFocus();
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
        Image add_image = new ImageIcon(getClass().getResource("/images/add_link.png")).getImage();
        add_cursor = Toolkit.getDefaultToolkit().createCustomCursor(
                add_image,
                new Point(16, 16), "add link cursor");
        this.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                isselected = true;
                if (isRootStreamPanel == false) {
                    JButton zoomout = new JButton();
                    Component[] temp = mainToolBar.getComponents();
                    for (int i = 0; i < temp.length; i++) {
                        if ("zoomout".equals(temp[i].getName())) {
                            zoomout = (JButton) temp[i];
                        }
                    }
                    zoomout.setEnabled(true);
                }
                thisStreamPanel.repaint();
            }

            @Override
            public void focusLost(FocusEvent e) {
                isselected = false;
                JButton zoomout = new JButton();
                Component[] temp = mainToolBar.getComponents();
                for (int i = 0; i < temp.length; i++) {
                    if ("zoomout".equals(temp[i].getName())) {
                        zoomout = (JButton) temp[i];
                    }
                }
                zoomout.setEnabled(false);
                thisStreamPanel.repaint();
            }
        });
    }

    public void initStreamChannel() {
        if (isRootStreamPanel == false) {
            for (int i = 0; i < numInputStreamChannel; i++) {
                this.remove(inputStreamChannels[i]);
            }
            for (int i = 0; i < numOutputStreamChannel; i++) {
                this.remove(outputStreamChannels[i]);
            }
            numInputStreamChannel = myCanvasParent.numInput;
            numOutputStreamChannel = myCanvasParent.numOutput;
            inputStreamChannels = new StreamChannelNode[numInputStreamChannel];
            outputStreamChannels = new StreamChannelNode[numOutputStreamChannel];
            for (int i = 0; i < inputStreamChannels.length; i++) {
                inputStreamChannels[i] = new StreamChannelNode(this, myCanvasParent, "output", i, new Point(0, 10 + (i) * 20), "inputStreamChannels[" + i + "]");//output in sub-stream
                inputStreamChannels[i].setBounds(0, 10 + (i) * 20, 10, 10);
                inputStreamChannels[i].isRealNode = false;
                inputStreamChannels[i].setVisible(true);
                this.add(inputStreamChannels[i]);
            }
            for (int i = 0; i < outputStreamChannels.length; i++) {
                outputStreamChannels[i] = new StreamChannelNode(this, myCanvasParent, "input", i, new Point(this.getWidth() - 10, 10 + (i) * 20), "outputStreamChannels[" + i + "]");//input in sub-stream
                outputStreamChannels[i].setBounds(this.getWidth() - outputStreamChannels[i].width, 10 + (i) * 20, 10, 10);
                outputStreamChannels[i].isRealNode = false;
                outputStreamChannels[i].setVisible(true);
                this.add(outputStreamChannels[i]);
            }
        }
    }

    public void RegisterNodes() {
        Component[] all_components = this.getComponents();
        //System.out.println(a.length);
        num_StreamNode = 0;
        num_StreamCanvas = 0;
        for (int i = 0; i < all_components.length; i++) {
            try {
                StreamNode temp = (StreamNode) all_components[i];
                num_StreamNode = num_StreamNode + 1;
                //System.out.println(a.length);
            } catch (Exception ex) {
                //System.out.println(ex);
            }
            try {
                StreamCanvas temp = (StreamCanvas) all_components[i];
                num_StreamCanvas = num_StreamCanvas + 1;
            } catch (Exception ex) {
                //System.out.println(ex);
            }
        }
        allStreamNodes = new StreamNode[num_StreamNode];
        allStreamCanvases = new StreamCanvas[num_StreamCanvas];
        num_StreamNode = 0;
        num_StreamCanvas = 0;
        for (int i = 0; i < all_components.length; i++) {
            try {
                allStreamCanvases[num_StreamCanvas] = (StreamCanvas) all_components[i];
                num_StreamCanvas = num_StreamCanvas + 1;
            } catch (Exception ex) {
                //System.out.println(ex);
                try {
                    allStreamNodes[num_StreamNode] = (StreamNode) all_components[i];
                    num_StreamNode = num_StreamNode + 1;
                    //System.out.println(a.length);
                } catch (Exception ex1) {
                    //System.out.println(ex);
                }
            }
        }
        //System.out.println(num_StreamCanvas);
        allNodes = new StreamNode[num_StreamNode + num_StreamCanvas];
        for (int i = 0; i < allStreamNodes.length; i++) {
            allNodes[i] = allStreamNodes[i];
        }
        for (int j = 0; j < allStreamCanvases.length; j++) {
            allNodes[num_StreamNode + j] = allStreamCanvases[j];
        }

        //System.out.println(allStreamNodes.length);
        //System.out.println(allStreamCanvases.length);
    }

    public void RegisterChannels() {
    }
    //public void ReorderStreamNodes()
    //{
    //    
    //}

    public void RefreshConnections() {
    }

    public void CheckNameDuplicate(String myName) {
    }

    public StreamNode FindNode(String name) {
        int found = -1;
        if (name == null) {
            return null;
        } else {
            for (int i = 0; i < allStreamNodes.length; i++) {
                if (allNodes[i].thisNodeName.equals(name)) {
                    found = i;
                    return allNodes[i];
                }
            }
        }

        return null;
    }

    public void start_view3d() {
        if (metaParent.my_view3d_thread != null) {
            //System.out.println(my_view3d_thread.isAlive());
            metaParent.app.stop();
            metaParent.app.destroy();
            //System.out.println(my_view3d_thread.isAlive());
            metaParent.my_view3d_thread = null;
        } else {
            view3d_data_generation();
            metaParent.my_view3d_thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    //myGUI=new NewJFrame(number_of_objects);
                    //myGUI.setVisible(true);
                    metaParent.app = new View3D();
                    metaParent.app.getting_data(objects_names, objects_positions);
                    metaParent.app.setShowSettings(false);
                    AppSettings settings = new AppSettings(true);
                    settings.setResolution(800, 600);
                    settings.setBitsPerPixel(24);
                    metaParent.app.setSettings(settings);
                    metaParent.app.setPauseOnLostFocus(false);
                    metaParent.app.start();
                }
            });
            metaParent.my_view3d_thread.start();
            //System.out.println(my_view3d_thread.isAlive());
        }
    }

    public void view3d_data_generation() {
        RegisterNodes();
        objects_names = new String[allNodes.length];
        objects_positions = new Vector3f[allNodes.length];
        for (int i = 0; i < allNodes.length; i++) {
            objects_names[i] = allNodes[i].thisNodeName;
            objects_positions[i] = new Vector3f();
            objects_positions[i].x = allNodes[i].getX();
            objects_positions[i].y = allNodes[i].getY();
            objects_positions[i].z = 10;
        }
    }

    public void refresh_canvas_bounds() {
        int max_x = 0;
        int max_y = 0;
        for (int i = 0; i < allNodes.length; i++) {
            if (allNodes[i].getLocation().x > max_x && allNodes[i].isRealNode == true) {
                max_x = allNodes[i].getLocation().x;
            }
            if (allNodes[i].getLocation().y > max_y && allNodes[i].isRealNode == true) {
                max_y = allNodes[i].getLocation().y;
            }
        }
        this.repaint();
        this.setPreferredSize(new Dimension(max_x + 300, max_y + 200));
        this.revalidate();
    }

    public void parseStreamPanel() {
        for (int i = 0; i < allNodes.length; i++) {
            for (int j = 0; j < allNodes[i].numOutput; j++) {
                if (allNodes[i].nodeDialog != null) {
                    if (((OutputPanel) allNodes[i].nodeDialog.AllOutputs.getComponent(j)).myKernel != null) {
                        ((OutputPanel) allNodes[i].nodeDialog.AllOutputs.getComponent(j)).myKernel.parseKernel();
                    }
                } else {
                    ((StreamCanvas) allNodes[i]).my_StreamPanel.parseStreamPanel();
                }
            }
        }
    }
    
    public void refreshGlobals()
    {
        root_timer.Globals.names.clear();
        root_timer.Globals.values.clear();
        root_timer.Globals.names.add("Time");
        root_timer.Globals.values.add(root_timer.current_time);
        assignGlobals();
    }
    
    public void assignGlobals()
    {
        for (int i = 0; i < allNodes.length; i++) {
            for (int j = 0; j < allNodes[i].numOutput; j++) {
                if (allNodes[i].nodeDialog != null) {
                    if (((OutputPanel) allNodes[i].nodeDialog.AllOutputs.getComponent(j)).myKernel != null && ((OutputPanel) allNodes[i].nodeDialog.AllOutputs.getComponent(j)).isGlobal==true) {
                        root_timer.Globals.names.add(allNodes[i].thisNodeName+"_output_"+j);
                        root_timer.Globals.values.add(((OutputPanel) allNodes[i].nodeDialog.AllOutputs.getComponent(j)).myKernel.Output);
                    }
                } else {
                    ((StreamCanvas) allNodes[i]).my_StreamPanel.assignGlobals();
                }
            }
        }
    }

    public void activateStream() {
        //System.out.println(allNodes.length);
        for (int i = 0; i < allNodes.length; i++) {
            allNodes[i].activateNode();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        if (isselected == false) {
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
        } else {
            g.setColor(new Color(1, 0.98f, 0.9f, 1));
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
        }

        if (islinking == true) {
            this.setCursor(add_cursor);
        } else {
            this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
        RegisterNodes();
        if (thisStreamPanel.isVisible() && isRootStreamPanel == false) {
            for (int i = 0; i < outputStreamChannels.length; i++) {
                outputStreamChannels[i].setBounds(this.getWidth() - outputStreamChannels[i].width, 10 + (i) * 20, 10, 10);
            }
        }

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
