/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.strath.michal.kinasiewicz.OpenGlScene.GLObjects;

import javax.vecmath.Vector3f;
import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author mikiones
 */
public abstract class AbstractNamedGLObject extends AbstractGLObject {

    private String Name;

    String getName() {
        return Name;
    }

    public AbstractNamedGLObject(String Name) {
        this.Name = Name;
    }
    
    
}
