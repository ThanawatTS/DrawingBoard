package main;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import objects.*;

public class DrawingBoard extends JPanel {

	private MouseAdapter mouseAdapter; 
	private List<GObject> gObjects;
	private GObject target;
	
	private int gridSize = 10;
	public int heightOfBoard = 800;
	public int widthOfBoard = 600;
	
	public DrawingBoard() {
		gObjects = new ArrayList<GObject>();
		mouseAdapter = new MAdapter();
		addMouseListener(mouseAdapter);
		addMouseMotionListener(mouseAdapter);
		setPreferredSize(new Dimension(heightOfBoard, widthOfBoard));
	}
	
	public void addGObject(GObject gObject) {
		// TODO: Implement this method.
		gObjects.add(gObject);
		repaint();
	}
	
	public void groupAll() {
		// TODO: Implement this method.
		CompositeGObject group = new CompositeGObject();
		for(GObject go: gObjects){
			group.add(go);
		}
		clear();
		group.recalculateRegion();
		gObjects.add(group);
		repaint();
	}

	public void deleteSelected() {
		// TODO: Implement this method.
		gObjects.remove(target);
		repaint();
	}
	
	public void clear() {
		// TODO: Implement this method.
		gObjects.clear();
		repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		paintBackground(g);
		paintGrids(g);
		paintObjects(g);
	}

	private void paintBackground(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), getHeight());
	}

	private void paintGrids(Graphics g) {
		g.setColor(Color.lightGray);
		int gridCountX = getWidth() / gridSize;
		int gridCountY = getHeight() / gridSize;
		for (int i = 0; i < gridCountX; i++) {
			g.drawLine(gridSize * i, 0, gridSize * i, getHeight());
		}
		for (int i = 0; i < gridCountY; i++) {
			g.drawLine(0, gridSize * i, getWidth(), gridSize * i);
		}
	}

	private void paintObjects(Graphics g) {
		for (GObject go : gObjects) {
			go.paint(g);
		}
	}

	class MAdapter extends MouseAdapter {

		// TODO: You need some variables here
		int xmouse,ymouse;
		
		boolean check;
		private void deselectAll() {
			// TODO: Implement this method.
			for(GObject go : gObjects){
				go.deselected();
				target = null;
			}
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			for(int i = gObjects.size()-1 ; i >= 0; i--){
				GObject go = gObjects.get(i);
				if(go.pointerHit(e.getX(), e.getY())){
					deselectAll();
					go.selected();
					target = go;
					xmouse = e.getX();
					ymouse = e.getY();
					break;
				}else{
					deselectAll();
				}
			}
			repaint();
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO: Implement this method.
			int posX = e.getX() - xmouse;
			int posY = e.getY() - ymouse;
			if(target != null){
				target.move(target.xGobject + posX ,target.yGobject + posY);
			}
			repaint();
			
		}
	}
	
}