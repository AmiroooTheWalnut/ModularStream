/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modular_stream;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import javax.swing.JPanel;

/**
 *
 * @author Amir72c
 */
public class Kernel extends JPanel implements Externalizable{
    
    public double Output[]=new double[1];
    
    public void parseKernel()
    {
        
    }
    
    public double runKernel()
    {
        
        return 0;
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
