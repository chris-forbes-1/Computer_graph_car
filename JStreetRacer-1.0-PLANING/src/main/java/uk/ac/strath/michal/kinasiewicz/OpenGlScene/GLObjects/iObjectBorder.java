/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.strath.michal.kinasiewicz.OpenGlScene.GLObjects;

import javax.vecmath.Vector2f;

/**
 * All borders are 2D on map
 * @author mikiones
 */
public interface iObjectBorder {
    
    /**
     * return the squert distance from border 
     * @param from
     * @return 
     */
    public float getSquerdDistance(Vector2f from);
    
    
    
}
