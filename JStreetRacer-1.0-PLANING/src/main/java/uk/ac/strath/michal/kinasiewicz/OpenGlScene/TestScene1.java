/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.strath.michal.kinasiewicz.OpenGlScene;

import com.obj.Vertex;
import com.obj.WavefrontObject;

import java.io.File;
import java.nio.*; // Native IO buffers: LWJGL uses these to efficiently exchange data with system memory

import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.*;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.glu.Cylinder;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.vector.Vector3f;
import uk.ac.strath.michal.kinasiewicz.OpenGlScene.GLObjects.CameraG3d;
import uk.ac.strath.michal.kinasiewicz.OpenGlScene.GLObjects.GLMist;
import uk.ac.strath.michal.kinasiewicz.OpenGlScene.GLObjects.LightGL;
import uk.ac.strath.michal.kinasiewicz.OpenGlScene.GLObjects.ObjectG3d;
import static org.lwjgl.opengl.GL11.*;

/**
 * 
 * @author kevin
 */
public class TestScene1 {

	ObjectG3d p = new ObjectG3d(0.0f, 0.0f, 0.0f);
	CameraG3d cam = new CameraG3d(new Vector3f(0, -5, 0));
	WavefrontObject loader;
	WavefrontObject tur;
	WavefrontObject church;
	WavefrontObject road;
	WavefrontObject tower;
	WavefrontObject bigHouse;
	WavefrontObject bungalo;
	WavefrontObject houseWithGarage;
	WavefrontObject corner;
	WavefrontObject petrol;
	WavefrontObject townHall;
	WavefrontObject bin;
	WavefrontObject suburb;
	WavefrontObject bench;
	LightGL lightModel = new LightGL();
	GLMist mist = new GLMist();
	float camSpeed = 0.5f;
	
	private float rotation = 0;

	public TestScene1() {
		File t = new File("TEst");
		t.mkdir();
		try {
			church = new WavefrontObject("resources/newChurch1.obj");
			loader = new WavefrontObject("resources/test.obj");
			tur = new WavefrontObject("resources/tur.obj");
			road = new WavefrontObject("resources/Road.obj");
			tower = new WavefrontObject("resources/towerBlock1.obj");
			bigHouse = new WavefrontObject("resources/Hospital.obj");
			bungalo = new WavefrontObject("resources/Bungalo1.obj");
			houseWithGarage = new WavefrontObject(
					"resources/houseWithGarage.obj");
			corner = new WavefrontObject("resources/actualcorner.obj");
			petrol = new WavefrontObject("resources/PetrolStation.obj");
			townHall = new WavefrontObject("resources/TownHall.obj");
			bin = new WavefrontObject("resources/bin.obj");
			suburb = new WavefrontObject("resources/suburb_house.obj");
			bench = new WavefrontObject("resources/bench.obj");

		} catch (NullPointerException n1) {
			Display.destroy();
			System.exit(1);
		}
	}

	public void start() {
		InitWindow(); // Init Display window
		InitOpenGL(); // init opengl flags
		while (!Display.isCloseRequested()
				&& !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) // while window
																// exxistas
		{
			try {
				Thread.sleep(1);

				// render1();
				Display(); // render opengl
				KeysListener(); // listen for keys
				Display.update(); // update window;
			} catch (InterruptedException ex) {
				Logger.getLogger(TestScene1.class.getName()).log(Level.SEVERE,
						null, ex);
			}
		}
		Display.destroy();
	}

	public void InitWindow() {
		try {

			Display.setDisplayMode(new DisplayMode(800, 600));
			Display.create();
			Mouse.setCursorPosition(Display.getWidth() / 2,
					Display.getHeight() / 2);
			Mouse.setGrabbed(true);
			// Mouse.setClipMouseCoordinatesToWindow(true);
		} catch (LWJGLException ex) {
			Logger.getLogger(TestScene1.class.getName()).log(Level.SEVERE,
					null, ex);
			System.exit(0);
		}

	}

	public void InitOpenGL() {
		// GL11.glClearColor(0.0f, 0.0f, 1.0f, 0.5f); // Blue sky
		// glEnable(GL_TEXTURE_2D);
		// glBlendFunc(GL_SRC_ALPHA, GL_ONE);
		//
		//
		//
		//
		//
		// GL11.glEnable(GL11.GL_NORMALIZE); // force normal lengths to 1
		// GL11.glEnable(GL11.GL_CULL_FACE); // don't render hidden faces
		// GL11.glEnable(GL11.GL_ALPHA_TEST);
		// GL11.glEnable(GL11.GL_BLEND);
		// GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		// GL11.glAlphaFunc(GL11.GL_GREATER, 0f);
		// GL11.glLightModeli(GL12.GL_LIGHT_MODEL_COLOR_CONTROL,
		// GL12.GL_SEPARATE_SPECULAR_COLOR);
		// GL11.glClearDepth(10.0f);
		// GL11.glEnable(GL11.GL_DEPTH_TEST); // checking overlapings
		// GL11.glShadeModel(GL11.GL_SMOOTH); // smooth shading
		// GL11.glDepthFunc(GL11.GL_LEQUAL); // Less or equal to show
		//
		// GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_NICEST);
		//
		// GL11.glViewport(0, 0, Display.getDisplayMode().getWidth(), Display
		// .getDisplayMode().getHeight()); // seting the viewpoint to full
		// // window size
		// GL11.glMatrixMode(GL11.GL_PROJECTION);
		// GL11.glLoadIdentity();
		// GLU.gluPerspective(60.0f, (float) Display.getDisplayMode().getWidth()
		// / (float) Display.getDisplayMode().getHeight(), 0.1f, 1000.0f); //
		// setting
		// // perspective
		// GL11.glMatrixMode(GL11.GL_MODELVIEW);
		// GL11.glLoadIdentity(); // populate matricxes

		GL11.glShadeModel(GL11.GL_SMOOTH); // Enable Smooth Shading
		GL11.glClearColor(0.0f, 0.0f, 0.6f, 0.5f); // Black Background
		GL11.glClearDepth(100.0f); // Depth Buffer Setup
		GL11.glClearStencil(0); // Stencil Buffer Setup
		GL11.glEnable(GL11.GL_DEPTH_TEST); // Enables Depth Testing
		GL11.glDepthFunc(GL11.GL_LEQUAL); // The Type Of Depth Testing To Do
		GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_NICEST); // Really
																			// Nice
																			// Perspective
																			// Calculations
		lightModel.initLights();
		mist.initFog();
		GL11.glCullFace(GL11.GL_BACK); // Set Culling Face To Back Face
		GL11.glEnable(GL11.GL_CULL_FACE);

		// GL11.glClearColor(0.0f, 0.0f, 1.0f, 0.8f); // Set Clear Color
		// (Greenish Color)

		GL11.glViewport(0, 0, Display.getDisplayMode().getWidth(), Display
				.getDisplayMode().getHeight()); // seting the viewpoint to full
												// window size
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GLU.gluPerspective(60.0f, (float) Display.getDisplayMode().getWidth()
				/ (float) Display.getDisplayMode().getHeight(), 0.1f, 1000.0f); // setting
																				// perspective
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity(); // populate matricxes
		glEnable(GL_COLOR_MATERIAL);
		glColorMaterial(GL_FRONT_AND_BACK, GL_AMBIENT_AND_DIFFUSE);

	}

	public void Display() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glLoadIdentity();
		cam.CamKeysListener( camSpeed);
		cam.UpdateCamera();

		// GL11.glTranslatef(0, -5.0f, 0f);
		glPushMatrix();
		glColor3f(0, 1, 0);
		GL11.glBegin(GL11.GL_QUADS);

		{

			GL11.glVertex3f(-1000.0f, 0, -1000.0f);
			GL11.glVertex3f(-1000.0f, 0, 1000.0f);
			GL11.glVertex3f(1000.0f, 0, 1000.0f);
			GL11.glVertex3f(1000.0f, 0, -1000.0f);
		}
		GL11.glEnd();
		glPopMatrix();

		// glPushMatrix();
		// {
		// GL11.glTranslatef(0, 1, -10);
		// GL11.glRotatef(90, 0, 1, 0);

		// loader.render();
		// }
		// glPopMatrix();

		/*
		 * glPushMatrix(); {
		 * 
		 * GL11.glTranslatef(5, 1.0f, -10f); GL11.glRotatef(-90, 0, 1, 0);
		 * loader.render(); }
		 */
		/*
		 * glPushMatrix(); { GL11.glTranslatef(10, 1.0f, 0f);
		 * GL11.glRotatef(rotation, 1f, 1f, 0f); } glPopMatrix();
		 * glPushMatrix(); { rotation += 1f; if (rotation > 360) { rotation -=
		 * 360; } tur.render(); } glPopMatrix(); // GL11.glRotatef(-rotation,
		 * 1f, 1f, 0f);
		 */
		glPushMatrix();

		{
			GL11.glTranslatef(-2, 0.0f, 0f);

			testrecTree(new Vector3f(0, 0, 0), new Vector3f(0, 5, 0),
					new Vector3f(0, 0, 0), 15, 3, 3, 0.5f, false);
		}
		
		
		glPopMatrix();

		
		glPushMatrix();
		{
			GL11.glTranslatef(-30, 0.0f, 0f);
			testrecTree(new Vector3f(0, 0, 0), new Vector3f(0, 5, 0),
					new Vector3f(0, 0, 0), 15, 3, 3, 0.5f, false);
		}
		glPopMatrix();

		
		glPushMatrix();
		{
			GL11.glTranslatef(-30, 0.0f, 0f);
			glRotatef(108, 0, 1, 0);
			
			testrecTree(new Vector3f(0, 0, 0), new Vector3f(0, 5, 0),
					new Vector3f(0, 0, 0), 15, 3, 3, 0.5f, false);
		}
		glPopMatrix();

		glPushMatrix();
		{
			GL11.glTranslatef(0, 0.0f, 55f);
			glRotatef(90, 0, 1, 0);
			testrecTree(new Vector3f(0, 0, 0), new Vector3f(0, 5, 0),
					new Vector3f(0, 0, 0), 15, 3, 3, 0.5f, false);
		}		
		glPopMatrix();
		
		glPushMatrix();
		{
			GL11.glTranslatef(-40, 0.0f, 53f);
			glRotatef(63, 0, 1, 0);
			testrecTree(new Vector3f(0, 0, 0), new Vector3f(0, 5, 0),
					new Vector3f(0, 0, 0), 15, 3, 3, 0.5f, false);
		}		
		glPopMatrix();
		
		glPushMatrix();
		{
			GL11.glTranslatef(0, 0.0f, 55f);
			testrecTree(new Vector3f(0, 0, 0), new Vector3f(0, 5, 0),
					new Vector3f(0, 0, 0), 15, 3, 3, 0.5f, false);
		}		
		glPopMatrix();

		// glPushMatrix();
		// {
		// glTranslatef(0, 3, 0);
		//
		// c.draw(0.2f, 0.3f, 1, 10, 2);
		// }
		// glPopMatrix();

		glPushMatrix();
		{
			// glColor3f(1, 1, 0);
			GL11.glTranslatef(7.45f, 0, 20);
			glRotatef(180, 0.0f, 1.0f, 0f);
			church.getGroups().get(0).getMaterial()
					.setKa(new Vertex(1.0f, 1.0f, 1.0f));
			church.render();
		}
		glPopMatrix();

		glPushMatrix();
		{
			GL11.glTranslatef(3.9f, 0, 0);
			glRotatef(90, 0.0f, 1.0f, 0f);
			tower.render();
		}
		glPopMatrix();

		glPushMatrix();
		{
			GL11.glTranslatef(3.5f, 0, -4.5f);
			glRotatef(90, 0.0f, 1.0f, 0f);
			bin.render();
		}
		glPopMatrix();

		glPushMatrix();
		{
			GL11.glTranslatef(3.9f, 0, 10);
			glRotatef(90, 0.0f, 1.0f, 0f);
			tower.render();
		}
		glPopMatrix();

		glPushMatrix();
		{
			GL11.glTranslatef(7.78f, 0, 30);
			glRotatef(180, 0.0f, 1.0f, 0f);
			bigHouse.render();
		}
		glPopMatrix();

		glPushMatrix();
		{
			GL11.glTranslatef(7.38f, 0, 40);
			glRotatef(180, 0.0f, 1.0f, 0f);
			bungalo.render();
		}
		glPopMatrix();

		glPushMatrix();
		{
			GL11.glTranslatef(-9f, 0, 40);
			glRotatef(90, 0.0f, 1.0f, 0f);
			suburb.render();
		}
		glPopMatrix();

		glPushMatrix();
		{
			GL11.glTranslatef(-33.5f, 0, 40);
			glRotatef(270, 0.0f, 1.0f, 0f);
			suburb.render();
		}
		glPopMatrix();

		glPushMatrix();
		{
			GL11.glTranslatef(0, 20, 0);
			glRotatef(270, 0.0f, 1.0f, 0f);
			loader.render();
		}
		glPopMatrix();

		glPushMatrix();
		{
			GL11.glTranslatef(-7.2f, 0, -2.5f);
			glRotatef(-180, 0.0f, 1.0f, 0f);
			suburb.render();
		}
		glPopMatrix();

		glPushMatrix();
		{
			GL11.glTranslatef(-35, 0, -2.5f);
			glRotatef(-180, 0.0f, 1.0f, 0f);
			suburb.render();
		}
		glPopMatrix();

		glPushMatrix();
		{
			GL11.glTranslatef(-34.7f, 0, 18.5f);
			// glRotatef(-90, 0.0f, 1.0f, 0f);
			suburb.render();
		}
		glPopMatrix();

		glPushMatrix();
		{
			GL11.glTranslatef(-6.6f, 0, 18.5f);
			suburb.render();
		}
		glPopMatrix();

		/*
		 * glPushMatrix(); { GL11.glTranslatef(-7.38f, 0, 0);
		 * houseWithGarage.render(); } glPopMatrix();
		 */

		glPushMatrix();
		{
			glTranslatef(0, 0, 0);
			road.render();
		}
		glPopMatrix();

		glPushMatrix();
		{
			glTranslatef(0, 0, 10);
			road.render();
		}
		glPopMatrix();

		glPushMatrix();
		{
			glTranslatef(0, 0, 20);
			road.render();
		}
		glPopMatrix();

		glPushMatrix();
		{
			glTranslatef(0, 0, 30);
			road.render();
		}
		glPopMatrix();

		glPushMatrix();
		{
			glTranslatef(0, 0, 40);
			glRotatef(180, 0.0f, 1.0f, 0f);

			road.render();
		}
		glPopMatrix();

		glPushMatrix();
		{
			glTranslatef(-1, 0, 52);
			glScalef(2, 1, 2);
			// glRotatef(90, 0.0f, 1.0f, 0f);
			corner.render();
		}
		glPopMatrix();

		glPushMatrix();
		{
			glTranslatef(-11, 0, 50f);
			glRotatef(90, 0.0f, 1.0f, 0f);
			road.render();
		}
		glPopMatrix();

		glPushMatrix();
		{
			GL11.glTranslatef(-9, 0, 53.8f);
			tower.render();
		}
		glPopMatrix();

		glPushMatrix();
		{
			GL11.glTranslatef(-18, 0, 56.6f);
			glRotatef(90, 0.0f, 1.0f, 0f);
			bigHouse.render();
		}
		glPopMatrix();

		glPushMatrix();
		{
			GL11.glTranslatef(-25, 0, 57f);
			glRotatef(90, 0.0f, 1.0f, 0f);
			bungalo.render();
		}
		glPopMatrix();

		glPushMatrix();
		{
			GL11.glTranslatef(-35, 0, 53.8f);
			tower.render();
		}
		glPopMatrix();

		glPushMatrix();
		{
			glTranslatef(-21, 0, 50f);
			glRotatef(90, 0.0f, 1.0f, 0f);
			road.render();
		}
		glPopMatrix();

		glPushMatrix();
		{
			glTranslatef(-31, 0, 50f);
			glRotatef(270, 0.0f, 1.0f, 0f);
			road.render();
		}
		glPopMatrix();

		glPushMatrix();
		{
			glTranslatef(-11, 0, 25);
			glRotatef(90, 0.0f, 1.0f, 0f);
			glScalef(0.5f, 1, 1f);
			road.render();
		}
		glPopMatrix();

		glPushMatrix();
		{
			glTranslatef(-21, 0, 25);
			glRotatef(90, 0.0f, 1.0f, 0f);
			glScalef(0.5f, 1, 1f);
			road.render();
		}
		glPopMatrix();

		glPushMatrix();
		{
			glTranslatef(-31, 0, 25);
			glRotatef(90, 0.0f, 1.0f, 0f);
			glScalef(0.5f, 1, 1f);
			road.render();
		}
		glPopMatrix();

		glPushMatrix();
		{
			glTranslatef(-43.5f, 0, 49.3f);
			glScalef(2, 1, 2);
			glRotatef(270, 0.0f, 1.0f, 0f);
			corner.render();
		}
		glPopMatrix();

		glPushMatrix();
		{
			GL11.glTranslatef(-44f, 0, 51.5f);
			glRotatef(90, 0.0f, 1.0f, 0f);
			bin.render();
		}
		glPopMatrix();

		/*
		 * glPushMatrix(); { GL11.glTranslatef(0, 0, 0); glRotatef(90, 0.0f,
		 * 1.0f, 0f); glScalef(10, 501, 10); bench.render(); } glPopMatrix();
		 */

		glPushMatrix();
		{
			glTranslatef(-42, 0, 39.8f);
			glRotatef(180, 0.0f, 1.0f, 0f);
			road.render();
		}
		glPopMatrix();

		glPushMatrix();
		{
			glTranslatef(-42, 0, 29.8f);
			glRotatef(180, 0.0f, 1.0f, 0f);
			road.render();
		}
		glPopMatrix();

		glPushMatrix();
		{
			glTranslatef(-42, 0, 19f);
			glRotatef(180, 0.0f, 1.0f, 0f);
			road.render();
		}
		glPopMatrix();

		glPushMatrix();
		{
			glTranslatef(-42, 0, 9);
			glRotatef(180, 0.0f, 1.0f, 0f);
			road.render();
		}
		glPopMatrix();

		glPushMatrix();
		{
			glTranslatef(-42, 0, -1);
			road.render();
		}
		glPopMatrix();

		glPushMatrix();
		{
			glTranslatef(-55.5f, 0, -1);
			glRotatef(270, 0.0f, 1.0f, 0f);
			townHall.render();
		}
		glPopMatrix();

		glPushMatrix();
		{
			glTranslatef(-46f, 0, 30);
			glRotatef(270, 0.0f, 1.0f, 0f);
			tower.render();
		}
		glPopMatrix();

		glPushMatrix();
		{
			glTranslatef(-46f, 0, 40);
			glRotatef(270, 0.0f, 1.0f, 0f);
			tower.render();
		}
		glPopMatrix();

		glPushMatrix();
		{
			glTranslatef(-41.2f, 0, -12.8f);
			glScalef(2, 1, 2);
			glRotatef(180, 0.0f, 1.0f, 0f);
			corner.render();
		}
		glPopMatrix();
		glPushMatrix();
		{
			glTranslatef(-32, 0, -11);
			glRotatef(90, 0.0f, 1.0f, 0f);
			road.render();
		}
		glPopMatrix();

		glPushMatrix();
		{
			glTranslatef(-22, 0, -11);
			glRotatef(90, 0.0f, 1.0f, 0f);
			road.render();
		}
		glPopMatrix();

		glPushMatrix();
		{
			glTranslatef(-22, 0, -11);
			glRotatef(90, 0.0f, 1.0f, 0f);
			road.render();
		}
		glPopMatrix();

		glPushMatrix();
		{
			glTranslatef(-12, 0, -11);
			glRotatef(90, 0.0f, 1.0f, 0f);
			road.render();
		}

		glPushMatrix();
		{
			GL11.glTranslatef(7, 0, -22);
			glRotatef(180, 0.0f, 1.0f, 0f);
			houseWithGarage.render();
		}

		glPushMatrix();
		{
			GL11.glTranslatef(-2.6f, 0, -29);
			petrol.render();
		}

		glPopMatrix();
		glPopMatrix();

		glPushMatrix();
		{
			glTranslatef(2f, 0, -11.2f);
			glScalef(2, 1, 2);
			glRotatef(90, 0.0f, 1.0f, 0f);
			corner.render();
		}
		glPopMatrix();

		GL11.glFlush();

	}

	private void render1() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glLoadIdentity();

		GL11.glRotatef(rotation, 0f, 1f, 0f);
		GL11.glTranslated(0, 0, -10);
		rotation += 0.35f;
		if (rotation > 360) {
			rotation -= 360;
		}
		loader.render();

		GL11.glTranslated(0, 0, -10);
		loader.render();

		GL11.glFlush();
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	boolean a_pressed = false;
	boolean s_pressed = false;
	boolean w_pressed = false;
	boolean d_pressed = false;

	private void KeysListener() {

		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState()) {

				if (Keyboard.getEventKey() == Keyboard.KEY_F) {

					setDisplayMode(800, 600, !Display.isFullscreen());
					Mouse.setGrabbed(false);
					Mouse.setCursorPosition(Display.getWidth() / 2,
							Display.getHeight() / 2);
					Mouse.setGrabbed(true);

				}
				if (Keyboard.getEventKey() == Keyboard.KEY_A) {
					System.out.println("A Key Pressed");
					cam.left = true;

				}
				if (Keyboard.getEventKey() == Keyboard.KEY_S) {
					cam.backward = true;
					System.out.println("S Key Pressed");

				}
				
				if (Keyboard.getEventKey() == Keyboard.KEY_Q) {
					cam.speedDown = true;
					
				}
				
				if (Keyboard.getEventKey() == Keyboard.KEY_E) {
				cam.speedup = true;	
				}

				if (Keyboard.getEventKey() == Keyboard.KEY_W) {
					System.out.println("W Key Pressed");
					cam.forward = true;

				}
				if (Keyboard.getEventKey() == Keyboard.KEY_D) {
					System.out.println("D Key Pressed");
					cam.right = true;

				}
			} else {
				if (Keyboard.getEventKey() == Keyboard.KEY_A) {
					System.out.println("A Key Released");
					cam.left = false;
				}
				
				if (Keyboard.getEventKey() == Keyboard.KEY_Q) {
					System.out.println("Q Key Released");
					cam.speedDown = false;
					
				}

				
				if (Keyboard.getEventKey() == Keyboard.KEY_S) {
					System.out.println("S Key Released");
					cam.backward = false;
				}

				if (Keyboard.getEventKey() == Keyboard.KEY_W) {
					System.out.println("W Key relased");
					cam.forward = false;
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_D) {
					System.out.println("D Key Released");
					cam.right = false;
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_E) {
					System.out.println("E Key Released");
					cam.speedup = false;
				}

			}

		}
	}

	public void setDisplayMode(int width, int height, boolean fullscreen) {

		// return if requested DisplayMode is already set
		if ((Display.getDisplayMode().getWidth() == width)
				&& (Display.getDisplayMode().getHeight() == height)
				&& (Display.isFullscreen() == fullscreen)) {
			return;
		}

		try {
			DisplayMode targetDisplayMode = null;

			if (fullscreen) {
				DisplayMode[] modes = Display.getAvailableDisplayModes();
				int freq = 0;

				for (int i = 0; i < modes.length; i++) {
					DisplayMode current = modes[i];

					if ((current.getWidth() == width)
							&& (current.getHeight() == height)) {
						if ((targetDisplayMode == null)
								|| (current.getFrequency() >= freq)) {
							if ((targetDisplayMode == null)
									|| (current.getBitsPerPixel() > targetDisplayMode
											.getBitsPerPixel())) {
								targetDisplayMode = current;
								freq = targetDisplayMode.getFrequency();
							}
						}

						// if we've found a match for bpp and frequence against
						// the
						// original display mode then it's probably best to go
						// for this one
						// since it's most likely compatible with the monitor
						if ((current.getBitsPerPixel() == Display
								.getDesktopDisplayMode().getBitsPerPixel())
								&& (current.getFrequency() == Display
										.getDesktopDisplayMode().getFrequency())) {
							targetDisplayMode = current;
							break;
						}
					}
				}
			} else {
				targetDisplayMode = new DisplayMode(width, height);
			}

			if (targetDisplayMode == null) {
				System.out.println("Failed to find value mode: " + width + "x"
						+ height + " fs=" + fullscreen);
				return;
			}

			Display.setDisplayMode(targetDisplayMode);
			Display.setFullscreen(fullscreen);

		} catch (LWJGLException e) {
			System.out.println("Unable to setup mode " + width + "x" + height
					+ " fullscreen=" + fullscreen + e);
		}
	}

	org.lwjgl.util.glu.Cylinder c = new Cylinder();

	public void testrecTree(Vector3f beg, Vector3f end, Vector3f angles,
			float angle, int n, int maxn, float ticknes, boolean f) {

		Vector3f oldbeg = beg;
		Vector3f oldEnd = end;
		Vector3f newEnd = new Vector3f();
		Vector3f newBEg = new Vector3f();

		// glPushMatrix();
		//
		// glTranslatef(beg.x, beg.y, beg.z);
		// glRotatef(-90, 1, 0, 0);
		// glRotatef(angles.x, 1, 0, 0);
		// glRotatef(angles.y, 0, 1, 0);
		// glRotatef(angles.z, 0, 0, 1);
		// c.draw(ticknes, ticknes/2, len, 20, 1);
		//
		// glPopMatrix();

		// c.draw(rotation, rotation, angle, n, n)

		float a = 0.5f;
		for (int i = 0; i < 15; i++) {
			a++;
			newBEg = new Vector3f((float) (oldbeg.getX() + ticknes
					* Math.cos(a)), oldbeg.getY(),
			(float) (oldbeg.getZ() + ticknes * Math.sin(a)));
			
			newEnd = new Vector3f((float) (oldEnd.getX() + (ticknes/2)
					* Math.cos(a)), oldEnd.getY(),
			(float) (oldEnd.getZ() + (ticknes/2) * Math.sin(a)));
			
			glColor3ub((byte)139, (byte)71, (byte)38);
			glBegin(GL_QUAD_STRIP);
			glVertex3f(oldbeg.x, oldbeg.y, oldbeg.z);
			glVertex3f(oldEnd.x, oldEnd.y, oldEnd.z);

			
			
			
			glVertex3f(newBEg.x, newBEg.y, newBEg.z);
			glVertex3f(newEnd.x, newEnd.y, newEnd.z);
			glEnd();
			oldEnd = newEnd;
			oldbeg = newBEg;
			

		}

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
		Vector3f v1 = new Vector3f(angles.x, angles.y + angle, angles.z);
		Vector3f v2 = new Vector3f(angles.x, angles.y - angle, angles.z);
		testrecTree(end, new Vector3f(bendx1, bendy1, end.z), v1, angle, n,
				maxn, ticknes / 2, true);
		testrecTree(end, new Vector3f(bendx2, bendy2, end.z), v2, -angle, n,
				maxn, ticknes / 2, true);

	}

	public static void main(String[] args) {
		// FBOExample e = new FBOExample();
		// e.start();
		TestScene1 scene = new TestScene1();
		scene.start();

	}
}