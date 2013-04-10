/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.strath.michal.kinasiewicz.OpenGlScene.GLObjects;

import java.util.ArrayList;
import java.util.HashMap;
import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author mikiones
 */
public class MapObjectsManager {

    /**
     * Global list of registred objects
     */
    ArrayList<AbstractGLObject> Objects = new ArrayList<AbstractGLObject>();
    /**
     * set of named objects 
     */
    HashMap<String, AbstractNamedGLObject> namedObjects = new HashMap<String, AbstractNamedGLObject>();
    /**
     * registred objects with opengl
     */
    int registred = 0;

    /**
     * Store new object-o into list
     * @param o 
     */
    public void AddObject(AbstractGLObject o) {
        Objects.add(o);

    }

    public void AddNamedObject(AbstractNamedGLObject o) {
        Objects.add(o);
        namedObjects.put(o.getName(), o);

    }

    public void RegisterIntoGL() {
        glGenLists(Objects.size() - 1);
        for (int i = 0; i < Objects.size(); i++) {
            Objects.get(i).LoadIntoOpengl(i);
            registred++;
        }
    }

    private MapObjectsManager() {
    }
    private static MapObjectsManager _instance = null;

    public static MapObjectsManager getInstance() {
        if (_instance == null) {
            _instance = new MapObjectsManager();
        }
        return _instance;
    }
    
    AbstractNamedGLObject GetGLObjectByName(String name)
    {
        return namedObjects.get(name);
    }
}
