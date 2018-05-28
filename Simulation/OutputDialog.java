/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Simulation;

import java.awt.Cursor;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import modular_stream.StreamNode;

/**
 *
 * @author Amir72c
 */
public class OutputDialog extends javax.swing.JDialog {

    String dragging_text;
    int drop_index = -1;
    StreamNode myNodeParent;
    
    /**
     * Creates new form OutputDialog
     */
    public OutputDialog(java.awt.Frame parent, boolean modal, StreamNode nodeParent) {
        super(parent, modal);
        initComponents();
        myNodeParent=nodeParent;
        refreshLists();
    }
    
    
    
    public void refreshLists() {
        ListModel varModel = new ListModel() {
            @Override
            public int getSize() {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                return myNodeParent.numInput;
            }

            @Override
            public Object getElementAt(int index) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                String[] vars = new String[myNodeParent.numInput];
                for (int i = 0; i < myNodeParent.numInput; i++) {
                    vars[i] = "Inputs[" + i + "]";
                }
                return vars[index];
            }

            @Override
            public void addListDataListener(ListDataListener l) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void removeListDataListener(ListDataListener l) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        ListModel globalModel = new ListModel() {
            @Override
            public int getSize() {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                return myNodeParent.mySteamPanelParent.root_timer.Globals.names.size();
            }

            @Override
            public Object getElementAt(int index) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                String[] vars = new String[myNodeParent.mySteamPanelParent.root_timer.Globals.names.size()];
                for (int i = 0; i < myNodeParent.mySteamPanelParent.root_timer.Globals.names.size(); i++) {
                    vars[i] = (String) myNodeParent.mySteamPanelParent.root_timer.Globals.names.get(i);
                }
                return vars[index];
            }

            @Override
            public void addListDataListener(ListDataListener l) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void removeListDataListener(ListDataListener l) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        VariablesList.setModel(varModel);
        GlobalsList.setModel(globalModel);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        VariablesList = new javax.swing.JList();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        GlobalsList = new javax.swing.JList();
        jScrollPane3 = new javax.swing.JScrollPane();
        Output = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        Funcs = new javax.swing.JList();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Local Variables"));

        VariablesList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        VariablesList.setFocusable(false);
        VariablesList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                VariablesListMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                VariablesListMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(VariablesList);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Global Variables"));

        GlobalsList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        GlobalsList.setFocusable(false);
        GlobalsList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                GlobalsListMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                GlobalsListMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(GlobalsList);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );

        Output.setColumns(20);
        Output.setRows(5);
        Output.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                OutputMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                OutputMouseExited(evt);
            }
        });
        jScrollPane3.setViewportView(Output);

        jLabel1.setText("Output:");

        Funcs.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "And", "Or", "Not equal to", "Less than", "Less equal than", "More than", "More equal than", "Equal to" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        Funcs.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        Funcs.setFocusable(false);
        Funcs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                FuncsMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                FuncsMouseReleased(evt);
            }
        });
        jScrollPane4.setViewportView(Funcs);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 185, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void VariablesListMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VariablesListMousePressed
        // TODO add your handling code here:
        VariablesList.setEnabled(false);
        this.setCursor(new Cursor(Cursor.MOVE_CURSOR));
        dragging_text = VariablesList.getSelectedValue().toString();
        if (evt.getClickCount() == 2 && !evt.isConsumed()) {
            evt.consume();
            //VariablesList.setEnabled(false);
            //this.setCursor(new Cursor(Cursor.MOVE_CURSOR));
        }
    }//GEN-LAST:event_VariablesListMousePressed

    private void VariablesListMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VariablesListMouseReleased
        // TODO add your handling code here:
        VariablesList.setEnabled(true);
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        Document temp_doc = Output.getDocument();
        if (drop_index > 0) {
            if (Output.hasFocus()) {
                try {
                    temp_doc.insertString(Output.getCaretPosition(), dragging_text, null);
                } catch (BadLocationException ex) {
                    Logger.getLogger(ConstraintDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
                Output.setDocument(temp_doc);
            } else {
                try {
                    temp_doc.insertString(temp_doc.getLength(), dragging_text, null);
                } catch (BadLocationException ex) {
                    Logger.getLogger(ConstraintDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
                Output.setDocument(temp_doc);
            }
        }
    }//GEN-LAST:event_VariablesListMouseReleased

    private void GlobalsListMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GlobalsListMousePressed
        // TODO add your handling code here:
        GlobalsList.setEnabled(false);
        this.setCursor(new Cursor(Cursor.MOVE_CURSOR));
        dragging_text = GlobalsList.getSelectedValue().toString();
    }//GEN-LAST:event_GlobalsListMousePressed

    private void GlobalsListMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GlobalsListMouseReleased
        // TODO add your handling code here:
        GlobalsList.setEnabled(true);
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        Document temp_doc = Output.getDocument();
        if (drop_index > 0) {
            if (Output.hasFocus()) {
                try {
                    temp_doc.insertString(Output.getCaretPosition(), dragging_text, null);
                } catch (BadLocationException ex) {
                    Logger.getLogger(ConstraintDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
                Output.setDocument(temp_doc);
            } else {
                try {
                    temp_doc.insertString(temp_doc.getLength(), dragging_text, null);
                } catch (BadLocationException ex) {
                    Logger.getLogger(ConstraintDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
                Output.setDocument(temp_doc);
            }
        }
    }//GEN-LAST:event_GlobalsListMouseReleased

    private void OutputMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OutputMouseEntered
        // TODO add your handling code here:
        drop_index = 1;
    }//GEN-LAST:event_OutputMouseEntered

    private void OutputMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OutputMouseExited
        // TODO add your handling code here:
        drop_index = -1;
    }//GEN-LAST:event_OutputMouseExited

    private void FuncsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FuncsMousePressed
        // TODO add your handling code here:
        Funcs.setEnabled(false);
        this.setCursor(new Cursor(Cursor.MOVE_CURSOR));
        //System.out.println(jList3.getSelectedValue().toString());
        String temp = Funcs.getSelectedValue().toString();
        switch (temp) {
            case "And":
            dragging_text = "&&";
            break;
            case "Or":
            dragging_text = "||";
            break;
            case "Not equal to":
            dragging_text = "!=";
            break;
            case "Less than":
            dragging_text = "<";
            break;
            case "Less equal than":
            dragging_text = "<=";
            break;
            case "More than":
            dragging_text = ">";
            break;
            case "More equal than":
            dragging_text = ">=";
            break;
            case "Equal to":
            dragging_text = "=";
            break;
        }
    }//GEN-LAST:event_FuncsMousePressed

    private void FuncsMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FuncsMouseReleased
        // TODO add your handling code here:
        Funcs.setEnabled(true);
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        Document temp_doc = Output.getDocument();
        if (drop_index > 0) {
            if (Output.hasFocus()) {
                try {
                    temp_doc.insertString(Output.getCaretPosition(), dragging_text, null);
                } catch (BadLocationException ex) {
                    Logger.getLogger(ConstraintDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
                Output.setDocument(temp_doc);
            } else {
                try {
                    temp_doc.insertString(temp_doc.getLength(), dragging_text, null);
                } catch (BadLocationException ex) {
                    Logger.getLogger(ConstraintDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
                Output.setDocument(temp_doc);
            }
        }
    }//GEN-LAST:event_FuncsMouseReleased


    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList Funcs;
    private javax.swing.JList GlobalsList;
    public javax.swing.JTextArea Output;
    private javax.swing.JList VariablesList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    // End of variables declaration//GEN-END:variables
}
