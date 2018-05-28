/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modular_stream;

/**
 *
 * @author Amir72c
 */

import com.jme3.app.SimpleApplication;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;

public class View3D extends SimpleApplication{

    public void getting_data(String[] passed_names,Vector3f[] passed_positions){
        objects_names=passed_names;
        objects_init_positions=passed_positions;
    }
    

    @Override
    public void simpleInitApp() {
        inputManager.addMapping("focus", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        inputManager.addListener(actionListener,"focus");
        //all_models=new Spatial[objects_names.length];
        Geometry[] boxes=new Geometry[objects_names.length];
        Material defaultMat = new Material( assetManager, "Common/MatDefs/Misc/ShowNormals.j3md");
        String default_string = "Models/";
        for(int i=0;i<objects_names.length;i++)
        {
            //all_models[i]=assetManager.loadModel(default_string.concat(objects_names[i]));
            //all_models[i].setMaterial(defaultMat);
            //all_models[i].setLocalTranslation(objects_init_positions[i].x, objects_init_positions[i].y, objects_init_positions[i].z);
            //rootNode.attachChild(all_models[i]);
            boxes[i] = new Geometry("Box", new Box(1, 1, 1));
            boxes[i].setMaterial(defaultMat);
            boxes[i].setLocalTranslation(objects_init_positions[i].x/10, objects_init_positions[i].y/10, objects_init_positions[i].z/10);
            rootNode.attachChild(boxes[i]);
        }
        
        /*
        Box b = new Box(1, 1, 1);
        Geometry geom = new Geometry("Box", b);

        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        geom.setMaterial(mat);
        
        rootNode.attachChild(geom);

        
        teapot = assetManager.loadModel("Models/testing.j3o");
        Material defaultMat = new Material( assetManager, "Common/MatDefs/Misc/ShowNormals.j3md");
        teapot.setMaterial(defaultMat);
        teapot.setLocalScale((float)0.1);
        teapot.setLocalTranslation(10, 10, 10);
        rootNode.attachChild(teapot);
        
        Spatial move = assetManager.loadModel("Models/untitled.j3o");
        Material movemat = new Material( assetManager, "Common/MatDefs/Misc/ShowNormals.j3md");
        move.setMaterial(movemat);
        move.setLocalTranslation(5, 5, 5);
        rootNode.attachChild(move);
        
        
        Spatial anim = assetManager.loadModel("Models/newtpl_anim.j3o");
        Material animmat = new Material( assetManager, "Common/MatDefs/Misc/ShowNormals.j3md");
        anim.setMaterial(animmat);
        rootNode.attachChild(anim);
        
        */
        
        flyCam.setMoveSpeed(70);
        
    }
    
    public void my_simpleInitApp()
    {
        rootNode.detachAllChildren();
        Geometry[] boxes=new Geometry[objects_names.length];
        Material defaultMat = new Material( assetManager, "Common/MatDefs/Misc/ShowNormals.j3md");
        String default_string = "Models/";
        for(int i=0;i<objects_names.length;i++)
        {
            //all_models[i]=assetManager.loadModel(default_string.concat(objects_names[i]));
            //all_models[i].setMaterial(defaultMat);
            //all_models[i].setLocalTranslation(objects_init_positions[i].x, objects_init_positions[i].y, objects_init_positions[i].z);
            //rootNode.attachChild(all_models[i]);
            boxes[i] = new Geometry("Box", new Box(1, 1, 1));
            boxes[i].setMaterial(defaultMat);
            boxes[i].setLocalTranslation(objects_init_positions[i].x/10, objects_init_positions[i].y/10, objects_init_positions[i].z/10);
            rootNode.attachChild(boxes[i]);
        }
    }
    
private ActionListener actionListener = new ActionListener() {
  public void onAction(String name, boolean keyPressed, float tpf) {
     if(name.equals("BUTTON_LEFT") || keyPressed==false)
     {
         if(inputManager.isCursorVisible()==false)
         {
             inputManager.setCursorVisible(true);
             flyCam.setEnabled(false);
         }
         else
         {
             inputManager.setCursorVisible(false);
             flyCam.setEnabled(true);
             my_simpleInitApp();
         }
         
     }
  }
};

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
        //for(int i=0;i<objects_names.length;i++)
        //{
            //all_models[i].setLocalTranslation(myGUI.dispatch_jSlider1_data(),myGUI.dispatch_location()[i][1],myGUI.dispatch_location()[i][0]);
        //} 
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
        
    }
    
    
public Spatial teapot;
public static View3D app;
public Spatial[] all_models;
public String[] objects_names;
public Vector3f[] objects_init_positions;

    
}
