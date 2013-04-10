/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.strath.michal.kinasiewicz.OpenGlScene.GLApp;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.opengl.Display;

/**
 * 
 * @author mikiones
 */
public class GLApp {

	private boolean finishApp;

	public void RunApp() {
		try {
			init();
			while (!finishApp) {
				if (!Display.isVisible()) {
					Thread.sleep(300L);
				} else if (Display.isCloseRequested()) {
					finishApp = true;
				} else {
					Thread.sleep(1);
				}
				checkInput(); // cecking user inputs;
				update();
				draw();
				Display.update();

			}
		} catch (InterruptedException ex) {
			Logger.getLogger(GLApp.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * Handle user input- Keyboard and mouse
	 */
	private void checkInput() {
	}

	/**
	 * OPenGl update...
	 */
	private void update() {
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		// TODO code application logic here
	}

	/**
	 * Draw 3d Scene
	 */
	private void draw() {

	}

	/**
	 * init all elements of app- display , opengl, etc..
	 */
	private void init() {

	}
}
