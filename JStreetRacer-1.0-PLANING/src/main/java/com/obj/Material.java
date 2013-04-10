package com.obj;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import com.obj.Texture;
import com.obj.Vertex;
import static org.lwjgl.opengl.GL11.*;


public class Material 
{
	private Texture texture;
	private Vertex Ka;
	private Vertex Kd;
	private Vertex Ks;
	private float _shininess;
	private String name;
	public String texName;
	ByteBuffer floatBuffer;
	ByteBuffer byteBuffer;
	
	public Material(String name)
	{
		Ka = null;
		Kd = null;
		Ks = null;
		texture = null;
		name = null;
		texName = null;
		_shininess = 0;
		
		this.name = name;
		floatBuffer = ByteBuffer.allocateDirect(64);
		floatBuffer.order(ByteOrder.nativeOrder());
		byteBuffer = ByteBuffer.allocateDirect(16);
		byteBuffer.order(ByteOrder.nativeOrder());
	}

	public void setName( String name ) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public Vertex getKa() 
	{
		return Ka;
	}
	
	public Vertex getKd() {
		return Kd;
	}

	public Vertex getKs() {
		return Ks;
	}
	
	public float getShininess()
	{
		return _shininess;
	}

	public void setKa(Vertex ka) {
		Ka = ka;
	}
	
	public void setKd(Vertex kd) {
		Kd = kd;
	}
	
	public void setKs(Vertex ks) {
		Ks = ks;
	}
	
	public void setShininess( float s )
	{
		_shininess = s;
	}
	public void useMaterial()
	{
		glDisable(GL_COLOR_MATERIAL);
		
		glMaterial(GL_FRONT, GL_AMBIENT, (FloatBuffer) byteBuffer
				.asFloatBuffer().put(Ka.getAsVector()).flip()); // Set Material Ambience
		
		glMaterial(GL_FRONT, GL_DIFFUSE, (FloatBuffer) byteBuffer
				.asFloatBuffer().put(Kd.getAsVector()).flip()); // Set Material Diffuse
		
		glMaterial(GL_FRONT, GL_SPECULAR, (FloatBuffer) byteBuffer
				.asFloatBuffer().put(Ks.getAsVector()).flip()); // Set Material Specular
//		
//		glMaterial(GL_FRONT, GL_SHININESS, (FloatBuffer) byteBuffer
//				.asFloatBuffer().put(new float[] {_shininess,0,0,0}).flip()); // Set Material Shininess
		
		
		
		glEnable(GL_COLOR_MATERIAL); 
		glColorMaterial ( GL_FRONT_AND_BACK, GL_AMBIENT_AND_DIFFUSE ) ;
		
	}
	
	
	
}
