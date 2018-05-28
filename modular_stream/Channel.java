/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modular_stream;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JButton;
import javax.swing.JToolTip;

/**
 *
 * @author Amir72c
 */
public class Channel extends JButton{
    
    boolean isselected=false;
    boolean isactive=false;
    
    boolean isfed=false;
    
    int width=10;
    int height=10;
    
    int my_order;
    
    double myData;
    
    String my_gender;
    StreamNode my_parent;
    Linking myLink;
    
    Channel thisChannel=this;
    
    Channel(final StreamPanel meta_parent,final StreamNode parent,String gender,int order)
    {
        my_gender=gender;
        my_parent=parent;
        my_order=order;
        final JToolTip dataTooltip=new JToolTip();
        
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        this.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                if(meta_parent.waslinked==false)
                {
                    isselected=true;
                    meta_parent.islinking=true;
                }else{
                    meta_parent.requestFocus();
                    meta_parent.waslinked=false;
                }
                
            }

            @Override
            public void focusLost(FocusEvent e) {
                isselected=false;
                meta_parent.islinking=false;
                Component temp = e.getOppositeComponent();
                //if(a.equals(a))
                try{
                    Channel other_channel = (Channel)temp;
                    if(!other_channel.my_gender.equals(my_gender) && !other_channel.my_parent.thisNodeName.equals(my_parent.thisNodeName) && other_channel.isfed==false && isfed==false)
                    {
                        meta_parent.waslinked=true;
                        if("input".equals(my_gender))
                        {
                            Linking tempo=new Linking(meta_parent,other_channel.my_parent.thisNodeName,my_parent.thisNodeName,other_channel.my_order,my_order);
                            tempo.setVisible(true);
                            meta_parent.add(tempo);
                            meta_parent.setComponentZOrder(tempo, 0);
                            meta_parent.repaint();
                            myLink=tempo;//only mother knows her own child
                            isfed=true;
                            
                        }else{
                            Linking tempo=new Linking(meta_parent,my_parent.thisNodeName,other_channel.my_parent.thisNodeName,my_order,other_channel.my_order);
                            tempo.setVisible(true);
                            meta_parent.add(tempo);
                            meta_parent.setComponentZOrder(tempo, 0);
                            meta_parent.repaint();
                            other_channel.myLink=tempo;
                            other_channel.isfed=true;
                            //System.out.println("added");
                        }
                        
                    }
                    
                }catch(Exception ex)
                {
                    //System.out.println(ex);
                }
            }
        });
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                //dataTooltip.setTipText(String.valueOf(myData));
                //dataTooltip
                thisChannel.setToolTipText(String.valueOf(myData));
            }
        });
    }
    
    public void activateChannel()
    {
        //System.out.println(myLink);
        if("input".equals(my_gender))
        {
            if(myLink!=null)
            {
                double retrievedData=myLink.activateLinking();
                my_parent.inputData[my_order]=retrievedData;
                myData=retrievedData;
            }
        }else{
            myData=my_parent.outputData[my_order];
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
