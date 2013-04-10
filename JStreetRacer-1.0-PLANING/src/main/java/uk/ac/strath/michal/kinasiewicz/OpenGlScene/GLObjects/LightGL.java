/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.strath.michal.kinasiewicz.OpenGlScene.GLObjects;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;

/**
 * 
 * @author mikiones
 */
public class LightGL {
	float lightPos[] = { 0.0f, 5.0f,-4.0f, 1.0f};           // Light Position
    float lightAmb[] = { 0.2f, 0.2f, 0.2f, 1.0f};           // Ambient Light Values
    float lightDif[] = { 0.6f, 0.6f, 0.6f, 1.0f};           // Diffuse Light Values
    float lightSpc[] = {-0.2f, -0.2f, -0.2f, 1.0f};         // Specular Light Values
    ByteBuffer byteBuffer;
    ByteBuffer floatBuffer;
    float matAmb[] = {0.4f, 0.4f, 0.4f, 1.0f};              // Material - Ambient Values
    float matDif[] = {0.2f, 0.6f, 0.9f, 1.0f};              // Material - Diffuse Values
    float matSpc[] = {0.0f, 0.0f, 0.0f, 1.0f};              // Material - Specular Values
    float matShn[] = {0.0f, 0.0f, 0.0f, 0.0f};                                // Material - Shininess


	public LightGL() {
		floatBuffer = ByteBuffer.allocateDirect(64);
		floatBuffer.order(ByteOrder.nativeOrder());
		byteBuffer = ByteBuffer.allocateDirect(16);
		byteBuffer.order(ByteOrder.nativeOrder());
	}

	public void initLights() {
		glLight(GL_LIGHT1, GL_POSITION, (FloatBuffer) byteBuffer
				.asFloatBuffer().put(lightPos).flip()); // Set Light1 Position
		glLight(GL_LIGHT1, GL_AMBIENT, (FloatBuffer) byteBuffer.asFloatBuffer()
				.put(lightAmb).flip()); // Set Light1 Ambience
		glLight(GL_LIGHT1, GL_DIFFUSE, (FloatBuffer) byteBuffer.asFloatBuffer()
				.put(lightDif).flip()); // Set Light1 Diffuse
		glLight(GL_LIGHT1, GL_SPECULAR, (FloatBuffer) byteBuffer
				.asFloatBuffer().put(lightSpc).flip()); // Set Light1 Specular
		glEnable(GL_LIGHT1); // Enable Light1
		glEnable(GL_LIGHTING); // Enable Lighting

		glMaterial(GL_FRONT, GL_AMBIENT, (FloatBuffer) byteBuffer
				.asFloatBuffer().put(matAmb).flip()); // Set Material Ambience
		glMaterial(GL_FRONT, GL_DIFFUSE, (FloatBuffer) byteBuffer
				.asFloatBuffer().put(matDif).flip()); // Set Material Diffuse
		glMaterial(GL_FRONT, GL_SPECULAR, (FloatBuffer) byteBuffer
				.asFloatBuffer().put(matSpc).flip()); // Set Material Specular
		glMaterial(GL_FRONT, GL_SHININESS, (FloatBuffer) byteBuffer
				.asFloatBuffer().put(matShn).flip()); // Set Material Shininess
	}

}
