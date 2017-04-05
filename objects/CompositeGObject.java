package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import main.*;

public class CompositeGObject extends GObject {

	private List<GObject> gObjects;
	

	public CompositeGObject() {
		super(0, 0, 0, 0);
		gObjects = new ArrayList<GObject>();
	}

	public void add(GObject gObject) {
		// TODO: Implement this method.
		gObjects.add(gObject);
	}

	public void remove(GObject gObject) {
		// TODO: Implement this method.
		gObjects.remove(gObject);
	}

	@Override
	public void move(int dX, int dY) {
		// TODO: Implement this method.
		int posX = dX - this.x;
		int posY = dY - this.y;
		for(GObject go : gObjects){
			go.move(go.x + posX ,go.y + posY);
		}
		this.x = dX;
		this.y = dY;
				
	}
	
	public void recalculateRegion() {
		// TODO: Implement this method.
		DrawingBoard db = new DrawingBoard();
		int xOfRegion = db.heightOfBoard ,yOfRegion = db.widthOfBoard ,newWidth = 0, newHeigh = 0;
		
		for(GObject go : gObjects){
			if(go.x < xOfRegion)
				xOfRegion = go.x;
			if(go.y < yOfRegion) 
				yOfRegion = go.y;
			if(go.x + go.width > newWidth) 
				newWidth = go.x + go.width;
			if(go.y + go.height > newHeigh)	
				newHeigh = go.y + go.height;
		}
		this.x = xOfRegion;
		this.y = yOfRegion;
		this.width = newWidth - xOfRegion;
		this.height = newHeigh - yOfRegion;
	}

	@Override
	public void paintObject(Graphics g) {
		// TODO: Implement this method.
		for(GObject go : gObjects){
			go.paintObject(g);
		}
	}

	@Override
	public void paintLabel(Graphics g) {
		// TODO: Implement this method.
		g.drawString("New Group", x, y+height+15);
	}
	
}
