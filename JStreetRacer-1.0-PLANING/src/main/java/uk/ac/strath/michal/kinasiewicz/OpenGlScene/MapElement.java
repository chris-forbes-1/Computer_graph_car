package uk.ac.strath.michal.kinasiewicz.OpenGlScene;

import org.lwjgl.util.vector.Vector3f;

import uk.ac.strath.michal.kinasiewicz.OpenGlScene.GLObjects.AbstractNamedGLObject;

/**
 * The each object on map is stored in two parts graphical model 'phisical part'
 * and logical Duo to several logical models can share this same graphical
 * model.
 * 
 * @author mikiones
 * 
 */
public class MapElement {
	/**
	 * Cordinates where the element is on map
	 */
	private Vector3f cors;
	/**
	 * Angles ax,ay,az for rotatings 
	 */
	private Vector3f angels;
	
	/**
	 * reference to GLObject 
	 */
	private AbstractNamedGLObject gptr;
	
	
	
	
	
}
