/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modular_stream;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author Amir72c
 */
public class PlotHist extends StreamNode {

    double data[] = new double[10];
    int time_data[] = new int[10];
    int plot_index = 0;
    //int width;
    int height = 80;
    int column_width = 7;

    PlotHist(StreamPanel meta_parent, final Point dragged_position, String name) {
        super(meta_parent, dragged_position, name);
        numInput = 1;
        numOutput = 0;
        //InputChannels[0] = new Channel(my_meta_parent, this, "input", 0);

        //InputChannels[0].setBounds(1, 1, 10, 10);
        //InputChannels[0].setVisible(true);
        //this.add(InputChannels[0]);

        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        mySteamPanelParent = meta_parent;
        thisNodeName = name;
        //width=(int)new FontMetrics(new Font("SansSerif", Font.PLAIN, 12)) {}.getStringBounds(thisNodeName, null).getWidth()+20;
        my_position = dragged_position;
        this.setLayout(null);
        this.setBounds(my_position.x, my_position.y, 80, 80);
        width = 80;
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

    }
    
    @Override
    public void InitChannels()
    {
        this.removeAll();
        InputChannels=new Channel[numInput];
        OutputChannels=new Channel[numOutput];
        inputData=new double[numInput];
        //outputData=new double[numOutput];
        //for(int i=0;i<numInput;i++)
        //{
            InputChannels[0]=new Channel(mySteamPanelParent,this,"input",0);
            InputChannels[0].setBounds(0, 5, 10, 10);
            InputChannels[0].setVisible(true);
            this.add(InputChannels[0]);
        //}
        //for(int i=0;i<numOutput;i++)
        //{
        //    OutputChannels[i]=new Channel(my_meta_parent,this,"output",i);
        //    OutputChannels[i].setBounds(width-OutputChannels[i].width, 10+(i)*20, 10, 10);
        //    OutputChannels[i].setVisible(true);
        //    this.add(OutputChannels[i]);
        //}
    }

    @Override
    public void activateNode() {
        InputChannels[0].activateChannel();
        active_plot_hist(mySteamPanelParent.root_timer.current_time[0]);
    }

    public void active_plot_hist(int time) {
        if (plot_index < 10) {
            data[plot_index] = inputData[0];
            time_data[plot_index] = time;
            plot_index = plot_index + 1;
        } else {
            System.arraycopy(data, 1, data, 0, data.length - 1);
            System.arraycopy(time_data, 1, time_data, 0, time_data.length - 1);
            data[plot_index - 1] = inputData[0];
            time_data[plot_index - 1] = time;
        }
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        //super.paintComponent(g);
        this.setBounds(my_position.x, my_position.y, 80, 80);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        if (isselected == true) {
            g.setColor(Color.ORANGE);
        } else {
            g.setColor(Color.BLACK);
        }
        g.drawLine(1, height - 12, width, height - 12);
        g.drawLine(12, 1, 12, height);
        double max_value = 0;
        for (int i = 0; i < 10; i++) {
            if (data[i] > max_value) {
                max_value = data[i];
            }
        }
        for (int i = 0; i < 10; i++) {
            g.setColor(new Color((i * 24) + 1, 255 - (i * 24), 5));
            g.fillRect(i * column_width + 13, height - (int) ((data[i] / max_value) * (height - 22)) - 12, column_width, (int) ((data[i] / max_value) * (height - 22)));
        }
        g.setColor(Color.BLUE);
        g.drawString(String.valueOf(time_data[0]), 0 * column_width + 15, height - 1);
        g.drawString(String.valueOf((float) max_value), 20, 10);
        //g.drawString(String.valueOf(time_data[9]), 9*column_width+3, height-1);
    }

    public void prepare_data() {
        /*
         mySteamPanelParent.temp_main_frame.Generated_file.append("(plot_hist)");
         mySteamPanelParent.temp_main_frame.Generated_file.append("this_plot_hist_order:"+this_plot_hist_order+"/");
         mySteamPanelParent.temp_main_frame.Generated_file.append("this_plot_hist_name:"+this_plot_hist_name+"/");
         mySteamPanelParent.temp_main_frame.Generated_file.append("width:"+width+"/");
         mySteamPanelParent.temp_main_frame.Generated_file.append("height:"+height+"/");
         mySteamPanelParent.temp_main_frame.Generated_file.append("column_width:"+column_width+"/");
         mySteamPanelParent.temp_main_frame.Generated_file.append("my_dragged_position:"+my_dragged_position.toString()+"/");
         mySteamPanelParent.temp_main_frame.Generated_file.append("\n");
         mySteamPanelParent.temp_main_frame.Generated_file.append("||"+"\n");//To children
         */
        //input_channel.prepare_data();
    }
}
