/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.strath.michal.kinasiewicz.OpenGlScene.GLObjects;

import javax.vecmath.Vector2f;

/**
 * the circle object border 
 * @author mikiones
 */
public class CircleObjectBorder implements iObjectBorder {

    private float squerdradius; // radous form midle point
    private Vector2f mid; // midle point of cirlce

    public CircleObjectBorder(float rad, Vector2f mid) {
        this.squerdradius = rad * rad;
        this.mid = mid;
    }

    public float getSquerdDistance(Vector2f from) {
        float d = (float) (Math.pow((mid.x - from.x), 2) + Math.pow((mid.y - from.y), 2));
        return d - squerdradius;

    }
}
