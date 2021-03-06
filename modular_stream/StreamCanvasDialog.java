/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modular_stream;

import java.awt.Frame;
import java.awt.Window;
import javax.swing.SwingUtilities;

/**
 *
 * @author Amir72c
 */
public class StreamCanvasDialog extends javax.swing.JDialog {

    /**
     * Creates new form StreamCanvasDialog
     */
    
    StreamCanvas my_parent;
    
    public StreamCanvasDialog(java.awt.Frame parentFrame, boolean modal,StreamCanvas parent) {
        super(parentFrame, modal);
        my_parent=parent;
        initComponents();
        InputNumber.setValue(my_parent.numInput);
        OutputNumber.setValue(my_parent.numOutput);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        enterCanvas = new javax.swing.JButton();
        confirm = new javax.swing.JButton();
        InputNumber = new javax.swing.JSpinner();
        OutputNumber = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cancel = new javax.swing.JButton();
        RenameButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        enterCanvas.setText("Enter the canvas");
        enterCanvas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterCanvasActionPerformed(evt);
            }
        });

        confirm.setText("Ok");
        confirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmActionPerformed(evt);
            }
        });

        InputNumber.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(0), null, Integer.valueOf(1)));

        OutputNumber.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(0), null, Integer.valueOf(1)));

        jLabel1.setText("Number of input channels:");

        jLabel2.setText("Number of output channels:");

        cancel.setText("Cancel");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });

        RenameButton.setText("Rename");
        RenameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RenameButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(OutputNumber, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                            .addComponent(InputNumber)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(enterCanvas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(RenameButton))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(confirm)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancel)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(InputNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(OutputNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(enterCanvas)
                    .addComponent(RenameButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirm)
                    .addComponent(cancel))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void enterCanvasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enterCanvasActionPerformed
        // TODO add your handling code here:
        //my_parent.my_meta_parent.setVisible(false);
        //my_parent.my_StreamPanel.setVisible(true);
        my_parent.mySteamPanelParent.mainStreamScrollPane.setViewportView(my_parent.my_StreamPanel);
        my_parent.my_StreamPanel.mainStreamShowcase.CurrentCanvas=my_parent.my_StreamPanel;
        
        this.dispose();
    }//GEN-LAST:event_enterCanvasActionPerformed

    private void confirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmActionPerformed
        // TODO add your handling code here:
        my_parent.numInput=(int)InputNumber.getValue();
        my_parent.numOutput=(int)OutputNumber.getValue();
        my_parent.InitChannels();
        my_parent.my_StreamPanel.initStreamChannel();
        this.dispose();
    }//GEN-LAST:event_confirmActionPerformed

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_cancelActionPerformed

    private void RenameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RenameButtonActionPerformed
        // TODO add your handling code here:
        Window parentWindow = SwingUtilities.windowForComponent(this);
        RenameDialog renameDialog = new RenameDialog((Frame) parentWindow, true, my_parent);
        renameDialog.setVisible(true);
    }//GEN-LAST:event_RenameButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner InputNumber;
    private javax.swing.JSpinner OutputNumber;
    private javax.swing.JButton RenameButton;
    private javax.swing.JButton cancel;
    private javax.swing.JButton confirm;
    private javax.swing.JButton enterCanvas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
