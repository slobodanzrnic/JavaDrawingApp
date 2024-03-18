package drawing;

import java.awt.Color;
import java.awt.Graphics;

public class Point extends Shape {
	
	public int x;
	public int y;
	
	public Point() {
		
	}
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Point(int x, int y, boolean selected) {
		this(x, y);
		setSelected(selected);
	}
	
	public double distance (int x , int y) {
		int dx = this.x - x;
		int dy = this.y - y;
		double d = Math.sqrt(dx * dx + dy * dy);
		return d;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Point) {
			Point p = (Point)obj;
			if (p.x == this.x && p.y == this.y)
				return true;
			else
				return false;
		} else
			return false;
	}
	
	public String toString() {
		return "Point [x= " + x + " , y= " + y + " , selected= " + selected + "]";
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getOutline());
		g.drawLine(this.getX()-2, this.getY(), this.getX()+2, this.getY());
		g.drawLine(this.getX(), this.getY()-2, this.getX(), this.getY()+2);
		if(isSelected() == true) {
			g.setColor(Color.black);
			g.drawRect(this.getX()-3, this.getY()-3, 6, 6);
		}
	}
	

	@Override
	public boolean contains(int x, int y) {
		return this.distance(x , y) <= 3;
	}

	@Override
	public void move(int newX, int newY) {
		this.x = newX;
		this.y = newY;
	}

	@Override
	public void DialogEdit() {
		DlgPoint dlgPoint = new DlgPoint();
		for (Shape shape : PnlDrawing.shapesArrList) {
			if (shape.isSelected()) {
				String[] split = shape.toString().split(" ");
				dlgPoint.getxCoord().setText(split[2]);
				dlgPoint.getyCoord().setText(split[5]);
			}
		}
		dlgPoint.setVisible(true);
	}

	@Override
	public void AreaPainter(Graphics g) {
		
	}
	
}
