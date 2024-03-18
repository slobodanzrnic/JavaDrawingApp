package sort;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import drawing.Circle;
import drawing.Point;
import stack.StackDialog;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.ActionEvent;

public class SortFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	DefaultListModel<String> dlm = new DefaultListModel<String>();
	ArrayList<Circle> arrayCircle = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SortFrame frame = new SortFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SortFrame() {
		setTitle("Zrnic Slobodan IM75-2017");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 475, 375);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel pnlCenter = new JPanel();
		contentPane.add(pnlCenter, BorderLayout.CENTER);

		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_pnlCenter = new GroupLayout(pnlCenter);
		gl_pnlCenter.setHorizontalGroup(gl_pnlCenter.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlCenter.createSequentialGroup().addContainerGap()
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE).addContainerGap()));
		gl_pnlCenter.setVerticalGroup(gl_pnlCenter.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlCenter.createSequentialGroup().addContainerGap()
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE).addContainerGap()));

		JList<String> list = new JList<String>();
		scrollPane.setViewportView(list);
		pnlCenter.setLayout(gl_pnlCenter);
		list.setModel(dlm);

		JPanel pnlDown = new JPanel();
		contentPane.add(pnlDown, BorderLayout.SOUTH);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SortDialog sortDialog = new SortDialog();
				sortDialog.setVisible(true);
				if (sortDialog.isOk == true) {
					Circle c = new Circle(
							new Point(Integer.parseInt(sortDialog.getxCoord().getText()),
									(Integer.parseInt(sortDialog.getyCoord().getText()))),
							Integer.parseInt(sortDialog.getrValue().getText()));
					arrayCircle.add(c);
					Collections.sort(arrayCircle, Collections.reverseOrder());
					dlm.add(arrayCircle.indexOf(c), "X: " + c.getCenter().getX() + " , Y: " + c.getCenter().getY()
							+ " , Radius " + c.getRadius());
				}
			}
		});

		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dlm.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Nothing to remove", "Error", JOptionPane.ERROR_MESSAGE);
					getToolkit().beep();
				}
				StackDialog stackDlgDelete = new StackDialog();
				String[] split = dlm.firstElement().toString().split(" ");
				stackDlgDelete.getxCoord().setText(split[1]);
				stackDlgDelete.getyCoord().setText(split[4]);
				stackDlgDelete.getrValue().setText(split[7]);
				stackDlgDelete.getxCoord().setEditable(false);
				stackDlgDelete.getyCoord().setEditable(false);
				stackDlgDelete.getrValue().setEditable(false);
				stackDlgDelete.setVisible(true);
				if (stackDlgDelete.isDelete() == true) {
					arrayCircle.remove(0);
					dlm.removeElementAt(0);
				}
			}
		});
		GroupLayout gl_pnlDown = new GroupLayout(pnlDown);
		gl_pnlDown.setHorizontalGroup(gl_pnlDown.createParallelGroup(Alignment.LEADING).addGroup(
				gl_pnlDown.createSequentialGroup().addGap(162).addComponent(btnAdd).addGap(5).addComponent(btnRemove)));
		gl_pnlDown.setVerticalGroup(gl_pnlDown.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlDown.createSequentialGroup().addGap(5).addGroup(gl_pnlDown
						.createParallelGroup(Alignment.LEADING).addComponent(btnAdd).addComponent(btnRemove))));
		pnlDown.setLayout(gl_pnlDown);
	}
}
