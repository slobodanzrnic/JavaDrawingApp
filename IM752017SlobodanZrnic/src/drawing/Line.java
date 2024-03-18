package drawing;

import java.awt.Color;
import java.awt.Graphics;

public class Line extends Shape {

	private Point startPoint;
	private Point endPoint;

	public Line() {

	}

	public Line(Point startPoint, Point endPoint) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}

	public Line(Point startPoint, Point endPoint, boolean selected) {
		this(startPoint, endPoint);
		setSelected(selected);
	}

	public double length() {
		return this.startPoint.distance(endPoint.getX(), endPoint.getY());
	}

	public boolean equals(Object obj) {
		if (obj instanceof Line) {
			Line l = (Line) obj;
			if (l.getStartPoint().equals(this.getStartPoint()) && l.getEndPoint().equals(this.getEndPoint()))
				return true;
			else
				return false;
		} else
			return false;
	}

	public String toString() {
		return "Line [start= " + startPoint + " , end= " + endPoint + " , selected= " + isSelected() + "]";
	}

	public Point middleOfLine() {
		int middleX = (this.getStartPoint().getX() + this.getEndPoint().getX()) / 2;
		int middleY = (this.getStartPoint().getY() + this.getEndPoint().getY()) / 2;
		Point middle = new Point(middleX, middleY);
		return middle;
	}

	public Point getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}

	public Point getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getOutline());
		g.drawLine(this.getStartPoint().getX(), this.getStartPoint().getY(), this.getEndPoint().getX(),
				this.getEndPoint().getY());
		if (isSelected() == true) {
			g.setColor(Color.black);
			g.drawRect(this.getStartPoint().getX() - 3, this.getStartPoint().getY() - 3, 6, 6);
			g.drawRect(this.getEndPoint().getX() - 3, this.getEndPoint().getY() - 3, 6, 6);
			g.drawRect(this.middleOfLine().getX() - 3, this.middleOfLine().getY() - 3, 6, 6);
		}
	}

	@Override
	public boolean contains(int x, int y) {
		return (startPoint.distance(x, y) + endPoint.distance(x, y)) - length() < 0.1;
	}

	@Override
	public void move(int newX, int newY) {
		// TODO Auto-generated method stub

	}

	@Override
	public void DialogEdit() {
		DlgLine dlgLine = new DlgLine();
		for (Shape shape : PnlDrawing.shapesArrList) {
			if (shape.isSelected()) {
				String[] split = shape.toString().split(" ");
				dlgLine.getSxValue().setText(split[4]);
				dlgLine.getSyValue().setText(split[7]);
				dlgLine.getExValue().setText(split[15]);
				dlgLine.getEyValue().setText(split[18]);
			}
		}
		dlgLine.setVisible(true);
	}

	@Override
	public void AreaPainter(Graphics g) {

	}
}
