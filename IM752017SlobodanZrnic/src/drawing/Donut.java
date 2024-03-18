package drawing;

import java.awt.Color;
import java.awt.Graphics;

public class Donut extends Circle {

	private int innerRadius;

	public Donut() {

	}

	public Donut(Point center, int radius, int innerRadius) {
		super(center, radius);
		this.innerRadius = innerRadius;
	}

	public Donut(Point center, int radius, int innerRadius, boolean selected) {
		this(center, radius, innerRadius);
		setSelected(selected);
	}

	public double area() {
		return super.area() - Math.PI * innerRadius * innerRadius;
	}

	public String toString() {
		return "Donut [ innerRadius= " + innerRadius + " , [Outer " + super.toString() + "]]";
	}

	public int getInnerRadius() {
		return innerRadius;
	}

	public void setInnerRadius(int innerRadius) {
		this.innerRadius = innerRadius;
	}

	public void draw(Graphics g) {
		super.draw(g);
		g.drawOval(this.getCenter().getX() - this.getInnerRadius(), this.getCenter().getY() - this.getInnerRadius(),
				this.getInnerRadius() * 2, this.getInnerRadius() * 2);
	}

	public boolean contains(int x, int y) {
		return super.contains(x, y) && this.getCenter().distance(x, y) >= this.getInnerRadius();
	}

	public void DialogEdit() {
		DlgDonut dlgDonut = new DlgDonut();
		for (Shape shape : PnlDrawing.shapesArrList) {
			if (shape.isSelected()) {
				String[] split = shape.toString().split(" ");
				dlgDonut.getxCoord().setText(split[10]);
				dlgDonut.getyCoord().setText(split[13]);
				dlgDonut.getrValue().setText(split[19]);
				dlgDonut.getIrValue().setText(split[3]);
			}
		}
		dlgDonut.setVisible(true);
	}

	@Override
	public void AreaPainter(Graphics g) {
		super.AreaPainter(g);
		g.setColor(getFill());
		g.drawOval(this.getCenter().getX() - this.getInnerRadius(), this.getCenter().getY() - this.getInnerRadius(),
				this.getInnerRadius() * 2, this.getInnerRadius() * 2);
		g.setColor(Color.WHITE);
		g.fillOval(this.getCenter().getX() - this.getInnerRadius(), this.getCenter().getY() - this.getInnerRadius(),
				this.getInnerRadius() * 2, this.getInnerRadius() * 2);
	}
}
