package uk.ac.strath.michal.kinasiewicz.OpenGlScene.GLObjects;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

public class CameraG3d extends ObjectG3d {

    /**
     * X axes rotation
     */
    private float pitch = 0;
    /**
     * 
     * Y axes rotation 
     */
    private float yawl;
    public boolean forward = false;
    public boolean backward = false;
    public boolean right = false;
    public boolean left = false;
	public boolean speedup= false;
	public boolean speedDown = false;
	public float speed = 0.5f;

    public CameraG3d(Vector3f poo) {
        super(poo);
    }

    /**
     * calculate position of camera after moving in directon distance
     * @param distance
     * @param direction 
     */
    public void moveCamera(float distance, float direction) {
        float rad = (float) ((yawl + direction) * Math.PI / 180.0f);
        poo.x -= Math.sin(rad) * distance;
        poo.z -= Math.cos(rad) * distance;

    }

    public void moveCameraUp(float distance, float direction) {
        float rad = (float) ((pitch + direction) * Math.PI / 180.0f);
        poo.y -= Math.sin(rad) * distance;
    }

    /**
     * Lock yawl between 360 0 and pitch -90 - 90 degrees
     */
    private void lockCameraAngels() {
        if (pitch > 90.0f) {
            pitch = 90.0f;
        } else if (pitch < -90.0f) {
            pitch = -90.0f;
        }

        if (yawl < 0.0f) {
            yawl += 360;
        } else if (yawl > 360.0f) {
            yawl -= 360;
        }

    }
    /**
     * Calculate the position of camarea and angels, by steplenght and factor
     * @param steplenght
     * @param mousesens 
     */
    public void CamKeysListener( float mousesens) {
        if (true) {
            //Mouse.setGrabbed(true);
            int midX = Display.getWidth() / 2;
            int midY = Display.getHeight() / 2;
            int mx = Mouse.getDX();
            int my = Mouse.getDY();
           
            yawl -= mousesens * ( mx);
            pitch += mousesens * ( my);
            lockCameraAngels();
            //Mouse.setCursorPosition(midX, midY);
      
            if(speedup)
            {
            	speed+=0.02;
            }
            else
            	if(speedDown)
            	{
            		speed-=0.02;
            		if(speed < 0f)
            			speed = 0;
            				
            	}
            
            if (backward) {
                if (pitch != 90 && pitch != -90) {
                    moveCamera(speed, 180.0f);
                    moveCameraUp(speed, 180.0f);
                }//forward
            } else if (forward) {
                if (pitch != 90 && pitch != -90) {
                    moveCamera(speed, 0.0f);
                    moveCameraUp(speed, 0.0f);
                }
            }//backward


            if (left) {
                moveCamera(speed, 90.0f);
            } //left
            else if (right) {
                moveCamera(speed, 270.0f);
            }//roight
            GL11.glRotatef(-pitch, 1.0f, 0.0f, 0.0f);
            GL11.glRotatef(-yawl, 0.0f, 1.0f, 0.0f);
            
        }
        
    }
    /**
     * update position of scene to new camera location
     */
    public void UpdateCamera()
    {
        GL11.glTranslatef(-poo.x, -0.60f, -poo.z);
    }
}
