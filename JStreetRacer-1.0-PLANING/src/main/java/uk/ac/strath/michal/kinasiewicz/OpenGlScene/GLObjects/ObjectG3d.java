/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.strath.michal.kinasiewicz.OpenGlScene.GLObjects;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author mikiones
 */
public class ObjectG3d {
    
   

   public Vector3f poo; // point of orgin

    /**
     * Change the curent point of orgint tp p3d
     * @param p3d 
     */
    public void setPointOfOrgin(Vector3f p3d) {
        poo = p3d;
    }

    ;
    
    /**
     * Create new ObjectG3d with point of orgin poo
     * @param poo 
     */
    public ObjectG3d(Vector3f poo) {
        this.poo = poo;
    }

    public ObjectG3d(float x, float y, float z) {
        poo = new Vector3f(x, y, z);
    }

    public void setPointOfOrgin(float x, float y, float z) {
        poo = new Vector3f(x, y, z);
    }

    /**
     * add reneder sequence to show point of orgin in curent 3d view
     */
    public void renderPofO() {
        GL11.glPointSize(4);
        GL11.glColor3f(1, 0, 0);
        GL11.glBegin(GL11.GL_POINTS);
        {
            GL11.glVertex3f(poo.x, poo.y, poo.z);
        }
        GL11.glEnd();
        GL11.glBegin(GL11.GL_LINES);
        {
            GL11.glColor3f(1, 0, 0);
            GL11.glVertex3f(poo.x, poo.y, poo.z + 4f);
            GL11.glVertex3f(poo.x, poo.y, poo.z - 4f);
            GL11.glColor3f(0, 1, 0);
            GL11.glVertex3f(poo.x, poo.y - 4f, poo.z);
            GL11.glVertex3f(poo.x, poo.y + 4, poo.z);
            GL11.glColor3f(0, 0, 1);
            GL11.glVertex3f(poo.x - 4f, poo.y, poo.z);
            GL11.glVertex3f(poo.x + 4f, poo.y, poo.z);

        }
        GL11.glEnd();

    }

    public void render() {
    }

    ;
    public Vector3f getPointOfOrgin() {
        return poo;
    }
}
