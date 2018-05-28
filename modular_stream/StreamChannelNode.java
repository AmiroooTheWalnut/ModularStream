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

/**
 *
 * @author Amir72c
 */
public class StreamChannelNode extends StreamNode {
    
    int height;
    String my_gender;
    
    StreamChannelNode(final StreamPanel meta_parent, StreamCanvas parent, String gender, int order,Point position,String name) {
        super(meta_parent,position,name);
        my_gender=gender;
        if("output".equals(gender))
        {
            numInput=0;
            numOutput=1;
        }else{
            numInput=1;
            numOutput=0;
        }
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        mySteamPanelParent=meta_parent;
        thisNodeName=name;
        width=10;
        height=10;
        my_position=position;
        this.setLayout(null);
        this.setBounds(my_position.x, my_position.y, width, height);
        this.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                isselected=true;
            }

            @Override
            public void focusLost(FocusEvent e) {
                isselected=false;
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
        outputData=new double[numOutput];
        for(int i=0;i<numInput;i++)
        {
            InputChannels[i]=new Channel(mySteamPanelParent,this,"input",i);
            InputChannels[i].setBounds(0, 0, width, height);
            InputChannels[i].setVisible(true);
            this.add(InputChannels[i]);
        }
        for(int i=0;i<numOutput;i++)
        {
            OutputChannels[i]=new Channel(mySteamPanelParent,this,"output",i);
            OutputChannels[i].setBounds(0, 0, width, height);
            OutputChannels[i].setVisible(true);
            this.add(OutputChannels[i]);
        }
    }
    
    @Override
    public void activateNode()
    {
        if("input".equals(my_gender))
        {
            //System.out.println(inputData[0]);
        }else{
            //System.out.println(outputData[0]);
        }
        
        for(int i=0;i<InputChannels.length;i++)
        {
            InputChannels[i].activateChannel();
        }
        for(int i=0;i<OutputChannels.length;i++)
        {
            OutputChannels[i].activateChannel();
        }
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        if (isselected==true)
        {
            
            g.setColor(Color.CYAN);
        }else{
            g.setColor(Color.blue);
        }
        g.fillRect(0, 0, width, height);
    }
    
}
