package stack;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import drawing.Circle;
import drawing.Point;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.util.Stack;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class StackFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	DefaultListModel<String> dlm = new DefaultListModel<String>();
	Stack<Circle> stack = new Stack<Circle>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StackFrame frame = new StackFrame();
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
	public StackFrame() {
		setTitle("Zrnic Slobodan IM75-2017");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 478, 338);
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
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE).addContainerGap()));
		gl_pnlCenter.setVerticalGroup(gl_pnlCenter.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlCenter.createSequentialGroup().addContainerGap()
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE).addContainerGap()));

		JList<String> list = new JList<String>();
		scrollPane.setViewportView(list);
		pnlCenter.setLayout(gl_pnlCenter);
		list.setModel(dlm);

		JPanel pnlDown = new JPanel();
		contentPane.add(pnlDown, BorderLayout.SOUTH);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StackDialog stackDialog = new StackDialog();
				stackDialog.setVisible(true);
				if (stackDialog.isOk == true) {
					Circle c = new Circle(
							new Point(Integer.parseInt(stackDialog.getxCoord().getText()),
									(Integer.parseInt(stackDialog.getyCoord().getText()))),
							Integer.parseInt(stackDialog.getrValue().getText()));
					stack.push(c);
					dlm.add(0, "X: " + c.getCenter().getX() + " , Y: " + c.getCenter().getY() + " , Radius: "
							+ c.getRadius());
					System.out.println(stack);
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
					stack.pop();
					dlm.removeElementAt(0);
				}
			}
		});

		GroupLayout gl_pnlDown = new GroupLayout(pnlDown);
		gl_pnlDown.setHorizontalGroup(gl_pnlDown.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlDown.createSequentialGroup().addGap(88).addComponent(btnAdd)
						.addPreferredGap(ComponentPlacement.RELATED, 100, Short.MAX_VALUE).addComponent(btnRemove)
						.addGap(86)));
		gl_pnlDown.setVerticalGroup(gl_pnlDown.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				gl_pnlDown.createSequentialGroup().addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_pnlDown.createParallelGroup(Alignment.BASELINE).addComponent(btnAdd)
								.addComponent(btnRemove))));
		pnlDown.setLayout(gl_pnlDown);
	}
}
