package drawing;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JPanel;

public class PnlDrawing extends JPanel implements MouseListener {

	static int obj = 0;
	public int mx;
	public int my;
	static ArrayList<Shape> shapesArrList = new ArrayList<Shape>();
	private Point startLine = null;

	public PnlDrawing() {
		addMouseListener(this);
	}

	public void paint(Graphics g) {
		super.paint(g);
		for (Shape shape : shapesArrList) {
			shape.AreaPainter(g);
			shape.draw(g);
		}
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		mx = e.getX();
		my = e.getY();

		switch (obj) {
		case 1:
			Point p = new Point(mx, my, false);
			p.setOutline(DrawingFrm1.outline);
			shapesArrList.add(p);
			System.out.println(shapesArrList);
			break;
		case 2:
			if (startLine == null) {
				startLine = new Point(mx, my, false);
			} else {
				Point endLine = new Point(mx, my);
				Line line = new Line(startLine, endLine, false);
				line.setOutline(DrawingFrm1.outline);
				shapesArrList.add(line);
				startLine = null;
			}
			break;
		case 3:
			DlgRectangle dlgRectangle = new DlgRectangle();
			dlgRectangle.getxCoord().setText(String.valueOf(mx));
			dlgRectangle.getxCoord().setEditable(false);
			dlgRectangle.getyCoord().setText(String.valueOf(my));
			dlgRectangle.getyCoord().setEditable(false);
			dlgRectangle.setVisible(true);
			if (dlgRectangle.isOk == true) {
				Rectangle r = new Rectangle(new Point(mx, my), Integer.parseInt(dlgRectangle.getrWidth().getText()),
						Integer.parseInt(dlgRectangle.getrHeight().getText()), false);
				r.setOutline(DrawingFrm1.outline);
				r.setFill(DrawingFrm1.area);
				System.out.println(r);
				shapesArrList.add(r);
			}
			break;
		case 4:
			DlgCircle dlgCircle = new DlgCircle();
			dlgCircle.getxCoord().setText(String.valueOf(mx));
			dlgCircle.getyCoord().setText(String.valueOf(my));
			dlgCircle.getxCoord().setEditable(false);
			dlgCircle.getyCoord().setEditable(false);
			dlgCircle.setVisible(true);
			if (dlgCircle.isOk == true) {
				Circle c = new Circle(new Point(mx, my), Integer.parseInt(dlgCircle.getrValue().getText()), false);
				c.setOutline(DrawingFrm1.outline);
				c.setFill(DrawingFrm1.area);
				shapesArrList.add(c);
			}
			break;
		case 5:
			DlgDonut dlgDonut = new DlgDonut();
			dlgDonut.getxCoord().setText(String.valueOf(mx));
			dlgDonut.getxCoord().setEditable(false);
			dlgDonut.getyCoord().setText(String.valueOf(my));
			dlgDonut.getyCoord().setEditable(false);
			dlgDonut.setVisible(true);
			if (dlgDonut.isOk == true) {
				Donut d = new Donut(new Point(mx, my), Integer.parseInt(dlgDonut.getrValue().getText()),
						Integer.parseInt(dlgDonut.getIrValue().getText()), false);
				d.setOutline(DrawingFrm1.outline);
				d.setFill(DrawingFrm1.area);
				shapesArrList.add(d);
			}
			break;
		case 6:
			boolean match = false;
			Collections.reverse(shapesArrList);
			for (Shape shape : shapesArrList) {
				shape.setSelected(false);
				if (match == false) {
					if (shape.contains(mx, my)) {
						shape.setSelected(true);
						match = true;
					}
				}
			}
			Collections.reverse(shapesArrList);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
