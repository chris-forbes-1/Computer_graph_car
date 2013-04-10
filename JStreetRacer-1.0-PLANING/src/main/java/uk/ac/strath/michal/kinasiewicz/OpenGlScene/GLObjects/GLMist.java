package uk.ac.strath.michal.kinasiewicz.OpenGlScene.GLObjects;
import static org.lwjgl.opengl.GL11.*;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import  org.lwjgl.opengl.GL11;
public class GLMist {
	private int fogMode[] = { GL_EXP, GL_EXP2, GL_LINEAR };  // Storage For Three Types Of Fog
    private int fogfilter = 0;                              // Which Fog Mode To Use
    private float fogColor[] = {0.5f, 0.5f, 0.5f, 1.0f};        // Fog Color
    
    
    
    public void initFog()
    {
    	ByteBuffer temp = ByteBuffer.allocateDirect(16);
        temp.order(ByteOrder.nativeOrder());
    	glFogi(GL11.GL_FOG_MODE, fogMode[fogfilter]);                  // Fog Mode
        temp.asFloatBuffer().put(fogColor).flip();
        glFog(GL_FOG_COLOR, temp.asFloatBuffer());                // Set Fog Color
        glFogf(GL11.GL_FOG_DENSITY, 0.05f);                            // How Dense Will The Fog Be
        glHint(GL11.GL_FOG_HINT, GL11.GL_NICEST);                   // Fog Hint Value
        GL11.glFogf(GL11.GL_FOG_START, -0.0f);                               // Fog Start Depth
        GL11.glFogf(GL11.GL_FOG_END, 1.0f);                                 // Fog End Depth
        GL11.glEnable(GL11.GL_FOG);                             
    }
    

}
