/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.strath.michal.kinasiewicz.OpenGlScene.GLObjects;

import javax.vecmath.Vector3f;
import static org.lwjgl.opengl.GL11.*;

/**
 * Abstract bridge between OpenGL and 3d opjects defined in java
 * The openGl seqwence is stored into Opengl List buffor;
 * This is graphical representation of obkect on map, the actual borders are stored in 2d shapes cus 
 * we move only x-z axes 
 * @author mikiones
 */
public abstract class AbstractGLObject {

    /**
     * if object was registerd int List Menager 
     */
    private boolean registred = false;
    /**
     * List ID opengl
     */
    private int glListRef = -1;

    public void LoadIntoOpengl(int ListNb) {

        glNewList(ListNb, GL_COMPILE);
        buildObject();
        glEndList();
        glListRef = ListNb;
    }

    private void privRender() {

        /**
         * if we registred with menager and we heve GL ref number to list
         */
        if (registred && (glListRef != -1)) {

            glCallList(glListRef); // call list
        } else {
            buildObject();
        }

    }

    public void render(Vector3f cords) {
        //Move to desired cords
        glTranslatef(cords.x, cords.y, cords.z);
        privRender(); // render from list if regstred atherwise call buildObject
        //MOve back to keep cords sound
        glTranslatef(-cords.x, -cords.y, -cords.z);
    }

    public abstract void buildObject();
}
