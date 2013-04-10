/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.strath.michal.kinasiewicz.OpenGlScene.GLObjects;

import javax.vecmath.Vector3f;
import org.lwjgl.util.Dimension;
import org.lwjgl.util.glu.Cylinder;
import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author mikiones
 */
public class GLO_Tree1 extends AbstractNamedGLObject {

    public GLO_Tree1() {
        super("Tree");
    }

    @Override
    public void buildObject() {
        testrecTree(new Vector3f(0, 0, 0), new Vector3f(0, 20, 0), 75, 5);

    }

    public void testrecTree(Vector3f beg, Vector3f end, float angle, int n) {
        
        
        
       
        glBegin(GL_LINE_STRIP);
        
        glVertex3f(beg.x, beg.y, beg.z);
        glVertex3f(end.x, end.y, end.z);
        glEnd();
        if (n < 0) {
            return;
        }
        n--;
        float nx = (float) ((end.x - beg.x) * 0.5 + beg.x - end.x);
        float ny = (float) ((end.y - beg.y) * 0.5 + beg.y - end.y);
        float bendx1 = (float) (nx * Math.cos(angle) + ny * Math.sin(angle) + end.x);
        float bendy1 = (float) (-nx * Math.sin(angle) + ny * Math.cos(angle) + end.y);
        float bendx2 = (float) (nx * Math.cos(-angle) + ny * Math.sin(-angle) + end.x);
        float bendy2 = (float) (-nx * Math.sin(-angle) + ny * Math.cos(-angle) + end.y);

        testrecTree(end, new Vector3f(bendx1, bendy1, end.z), angle, n);
        testrecTree(end, new Vector3f(bendx2, bendy2, end.z), angle, n);


    }
}
