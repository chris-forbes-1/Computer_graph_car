/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.strath.michal.kinasiewicz.ObjLoader;

import com.obj.WavefrontObject;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.opengl.GL21;
import uk.ac.strath.michal.kinasiewicz.OpenGlScene.GLObjects.ObjectG3d;

/**
 *
 * @author mikiones
 */
public class testLoader {
    
      

    public void start() {
        float angle = 0;
        WavefrontObject w = new WavefrontObject("src/main/resources/test.obj");

        try {
            Display.setDisplayMode(new DisplayMode(800, 600));
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(0);
        }

        // init OpenGL here
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_LIGHT0);
        GL11.glEnable(GL11.GL_DEPTH);

        while (!Display.isCloseRequested()) {
            GL11.glMatrixMode(GL11.GL_PROJECTION);
            GL11.glLoadIdentity();
            GL11.glOrtho(0, 800, 600, 0, 800, -600);
            GL11.glMatrixMode(GL11.GL_MODELVIEW);
            GL11.glPushMatrix();
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
            GL11.glLoadIdentity();
            GL11.glTranslatef(400, 300, 0.0f);
            GL11.glRotatef(angle, 0.0f, 0.5f, 0.0f);
            //GL11.glRotatef(50, 0.0f, 0.0f, 1.0f);
            GL11.glScalef(100, 100, 100);
            

            w.render();

            GL11.glPopMatrix();
            angle += 0.5f;
            Display.update();
        }

        Display.destroy();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        testLoader t = new testLoader();
        t.start();;
    }
}
