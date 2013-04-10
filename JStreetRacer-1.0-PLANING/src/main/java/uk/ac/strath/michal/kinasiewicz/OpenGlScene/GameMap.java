package uk.ac.strath.michal.kinasiewicz.OpenGlScene;

import java.util.ArrayList;

import uk.ac.strath.michal.kinasiewicz.OpenGlScene.GLObjects.AbstractNamedGLObject;

public class GameMap {
	private ArrayList<MapElement> elements = new ArrayList<MapElement>();
	
	public void placeElementOnMap(MapElement e)
	{
		elements.add(e);
	}

	

}
