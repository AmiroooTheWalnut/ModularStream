/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulation.ContinuousSimulation;

import Simulation.ConstraintDialog;
import Simulation.OutputDialog;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import modular_stream.Kernel;
import modular_stream.StreamNode;
import modular_stream.TimerDialog;
import org.apache.commons.io.IOUtils;
import org.codehaus.groovy.control.CompilationFailedException;

/**
 *
 * @author Amir72c
 */
public class ContinuousOutput extends Kernel {

    Binding main_binding;
    GroovyShell main_shell;
    //String outputExp="(Time+(Math.random()-0.5)*Time)";
    String outputExp="";
    String constraintExp="";
    Script compiledConstriant;
    Script compiledOutput;
    double[] allData=new double[10];
    int storeIndex=0;
    TimerDialog myrootTimer;
    ConstraintDialog constraintDialog;
    OutputDialog outputDialog;
    StreamNode myParent;
    
    /**
     * Creates new form ContinuousOutput
     */
    public ContinuousOutput(StreamNode parent,double Inputs[]) {
        initComponents();
        plot.getData(allData);
        TimerDialog rootTimer = parent.mySteamPanelParent.root_timer;
        main_binding = new Binding();
        
        main_shell = new GroovyShell(main_binding);
        
        InputStream loadedKernel = OutputDialog.class.getResourceAsStream("main_functions");
        System.out.println(loadedKernel.toString());
        File tempFile;
        try {
            tempFile = File.createTempFile("temp", ".groovy");
            OutputStream outputStream;
            outputStream = new FileOutputStream(tempFile);
            IOUtils.copy(loadedKernel, outputStream);
            outputStream.close();
            Script scrpt = main_shell.parse(tempFile);
            main_binding.setVariable("dist",scrpt);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ContinuousOutput.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ContinuousOutput.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        //main_shell.evaluate(new File());
        main_binding.setVariable("Inputs", Inputs);
        main_binding.setVariable("Globals", rootTimer.Globals);
        myrootTimer=rootTimer;
        myParent=parent;
        constraintDialog=new ConstraintDialog((JFrame) SwingUtilities.getWindowAncestor(this),true,myParent);
        outputDialog=new OutputDialog((JFrame) SwingUtilities.getWindowAncestor(this),true,myParent);
    }

    @Override
    public void parseKernel()
    {
        Object[] globals = myrootTimer.Globals.names.toArray();
        outputExp=outputDialog.Output.getText();
        constraintExp=constraintDialog.Const.getText();
        String temp_string_output="import java.math.*;";
        if(outputExp.length()>0)
        {
            temp_string_output=temp_string_output+"def evalOutput"+"(){";
            for(int i=0;i<myrootTimer.Globals.names.size();i++)
            {
                //myrootTimer.Globals.get(i);
                temp_string_output=temp_string_output+globals[i].toString()+"=Globals.values.get("+i+")[0];";
            }
            temp_string_output=temp_string_output+"final_value="+outputExp+";return final_value;}";
        }else{
            temp_string_output=temp_string_output+"def evalOutput"+"(){final_value=0;return final_value;};";
        }
        System.out.println(temp_string_output);
        compiledOutput=main_shell.parse(temp_string_output);
        
        String temp_string_const="import java.math.*;";
        if(constraintExp.length()>0)
        {
            temp_string_const = temp_string_const + "def evalConstraint" + "(){";
            for (int i = 0; i < myrootTimer.Globals.names.size(); i++) {
                temp_string_const = temp_string_const + globals[i].toString() + "=Globals.values.get(" + i + ")[0];";
            }
            temp_string_const = temp_string_const + "final_value=(" + constraintExp + ");return final_value;};";
        } else {
            temp_string_const=temp_string_const+"def evalConstraint"+"(){final_value=true;return final_value;};";
        }
        System.out.println(temp_string_const);
        compiledConstriant=main_shell.parse(temp_string_const);
        
    }
    
    @Override
    public double runKernel()
    {
        Output[0]=Double.parseDouble(compiledOutput.invokeMethod("evalOutput", null).toString());
        boolean tempConstraint = Boolean.parseBoolean(compiledConstriant.invokeMethod("evalConstraint", null).toString());
        if(tempConstraint)
        {
            if(storeAllOut.isSelected())
            {
                if(storeIndex+1>allData.length)
                {
                    double[] tempData=new double[allData.length*2];
                    System.arraycopy(allData, 0, tempData, 0, allData.length);
                    allData=tempData;
                }
                allData[storeIndex]=Output[0];
            }
            storeIndex=storeIndex+1;
            return Output[0];
        }else{
            System.out.println("infeasible!");
            if(storeAllOut.isSelected())
            {
                if(storeIndex+1>allData.length)
                {
                    double[] tempData=new double[allData.length*2];
                    System.arraycopy(allData, 0, tempData, 0, allData.length);
                    allData=tempData;
                }
                allData[storeIndex]=0;
            }
            storeIndex=storeIndex+1;
            return 0;
        }
        
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        plot = new modular_stream.Plot();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        storeAllOut = new javax.swing.JCheckBox();
        storeAllIn = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();

        jButton1.setText("Edit output");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Edit constraints");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButton3.setText("Save data");

        javax.swing.GroupLayout plotLayout = new javax.swing.GroupLayout(plot);
        plot.setLayout(plotLayout);
        plotLayout.setHorizontalGroup(
            plotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        plotLayout.setVerticalGroup(
            plotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 176, Short.MAX_VALUE)
        );

        jButton4.setText("Edit plot");

        jButton5.setText("Refresh plot");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(plot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(plot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addContainerGap())
        );

        storeAllOut.setSelected(true);
        storeAllOut.setText("Store all outputs");

        storeAllIn.setText("Store all inputs");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("Show output data");

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Show input data");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton2)
                    .addComponent(jRadioButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jRadioButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jRadioButton2))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addComponent(jButton2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(storeAllOut)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(storeAllIn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 165, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(storeAllOut)
                                    .addComponent(storeAllIn))))
                        .addGap(7, 7, 7))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        plot.getData(allData);
        //System.out.println(allData.length);
        this.repaint();
        //plot.revalidate();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        constraintDialog.setVisible(true);
        constraintDialog.refreshLists();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        outputDialog.setVisible(true);
        outputDialog.refreshLists();
    }//GEN-LAST:event_jButton1ActionPerformed

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private modular_stream.Plot plot;
    private javax.swing.JCheckBox storeAllIn;
    private javax.swing.JCheckBox storeAllOut;
    // End of variables declaration//GEN-END:variables
}
