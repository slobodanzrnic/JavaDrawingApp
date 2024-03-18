package drawing;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends Shape {

	private Point upperLeft;
	private int width;
	private int height;
	
	public Rectangle() {
		
	}
	
	public Rectangle(Point upperLeft, int width, int height) {
		this.upperLeft = upperLeft;
		this.width = width;
		this.height = height;
	}
	
	public Rectangle(Point upperLeft, int width, int height, boolean selected) {
		this(upperLeft, width, height);
		setSelected(selected);
	}
	
	public Point getCenter() {
		return new Point(upperLeft.getX() + width/2, upperLeft.getY() + width/2);
	}
	
	public String toString() {
		return "Rectangle [upl= " + upperLeft + " , width= " + width + " , height= " + height + " , selected= " +
	isSelected() + "]";
	}
	
	public int area() {
		return width * height;
	}
	
	public Point getUpperLeft() {
		return upperLeft;
	}
	public void setUpperLeft(Point upperLeft) {
		this.upperLeft = upperLeft;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getOutline());
		g.drawRect(this.getUpperLeft().getX(), this.getUpperLeft().getY(),
				this.getWidth(), this.getHeight());
		if(isSelected() == true) {
			g.setColor(Color.black);
			g.drawRect(this.getUpperLeft().getX() - 3, this.getUpperLeft().getY() - 3, 6, 6);
			g.drawRect(this.getUpperLeft().getX() + this.getWidth() - 3, this.getUpperLeft().getY() - 3 ,
					   6 , 6);
			g.drawRect(this.getUpperLeft().getX() - 3, this.getUpperLeft().getY() + this.getHeight() - 3, 6, 6);
			g.drawRect(this.getUpperLeft().getX() + this.getWidth() - 3, this.getUpperLeft().getY() + this.getHeight() - 3 , 6, 6);
		}
	}

	@Override
	public boolean contains(int x, int y) {
		return ( this.getUpperLeft().getX() <= x && x <= this.getUpperLeft().getX() + width &&
				this.getUpperLeft().getY() <= y && y <= this.getUpperLeft().getY() + height);
	}

	@Override
	public void move(int newX, int newY) {
		upperLeft.move(newX, newY);		
	}

	@Override
	public void DialogEdit() {
		DlgRectangle dlgRectangle = new DlgRectangle();
		for (Shape shape : PnlDrawing.shapesArrList) {
			if (shape.isSelected()) {
				String[] split = shape.toString().split(" ");
				dlgRectangle.getxCoord().setText(split[4]);
				dlgRectangle.getyCoord().setText(split[7]);
				dlgRectangle.getrWidth().setText(split[13]);
				dlgRectangle.getrHeight().setText(split[16]);
			}
		}
		dlgRectangle.setVisible(true);
	}

	@Override
	public void AreaPainter(Graphics g) {
		g.setColor(getFill());
		g.fillRect(this.getUpperLeft().getX(), this.getUpperLeft().getY(), 
				this.getWidth(), this.getHeight());
	}

}
