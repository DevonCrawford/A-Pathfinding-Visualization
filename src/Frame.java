import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Frame extends JPanel
		implements ActionListener, MouseListener, MouseMotionListener, MouseWheelListener, KeyListener {

	private static final long serialVersionUID = -3862960871170293464L;
	JFrame window;
	APathfinding pathfinding;
	boolean noPath, showSteps;
	int xBorder, yBorder, size, scroll;
	double prevSize;
	char currentKey = (char) 0;
	Node startNode, endNode;
	String mode;
	JLabel modeText;
	Font bigText = new Font("airal", Font.BOLD, 32);
	Font REALBIGText = new Font("airal", Font.BOLD, 72);
	Font largeText = new Font("airal", Font.BOLD, 50);
	Font numbers = new Font("airal", Font.BOLD, 12);
	Font smallNumbers = new Font("airal", Font.PLAIN, 11);
	Color greenHighlight = new Color(132, 255, 138);
	Color redHighlight = new Color(253, 90, 90);
	Color blueHighlight = new Color(32, 233, 255);
	Timer timer = new Timer(100, this);
	int r = (int) (Math.random() * 255) + 1;
	int G = (int) (Math.random() * 255) + 1;
	int b = (int) (Math.random() * 255) + 1;

	public static void main(String[] args) {
		new Frame();
	}

	public Frame() {
		size = 25;
		prevSize = size;
		scroll = 3;
		mode = "Map Creation";
		noPath = false;
		showSteps = true;
		setLayout(null);
		addMouseListener(this);
		addMouseMotionListener(this);
		addMouseWheelListener(this);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);

		pathfinding = new APathfinding(this, size);
		pathfinding.setDiagonal(true);

		window = new JFrame();
		window.setContentPane(this);
		window.getContentPane().setPreferredSize(new Dimension(700, 600));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);

		modeText = new JLabel("Mode: " + mode);
		modeText.setFont(bigText);
		modeText.setVisible(true);
		this.add(modeText);
		this.revalidate();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if (pathfinding.isNoPath()) {
			timer.setDelay(100);
			timer.start();

			Color flicker = new Color(r, G, b);
			g.setColor(flicker);
			g.fillRect(0, 0, getWidth(), getHeight());
			
			JLabel wtf = new JLabel("WTF YOU FUCK");
			wtf.setForeground(Color.white);
			wtf.setFont(REALBIGText);
			Dimension ts = wtf.getPreferredSize();
			wtf.setBounds((int)((getWidth()/2)-(ts.getWidth()/2)), (int)((getHeight()/2)-(ts.getHeight()/2))-50, (int)ts.getWidth(), (int)ts.getHeight());
			this.add(wtf);
			wtf.setVisible(true);
			
			JLabel uKno = new JLabel("U KNo Ther WAS NO PATH");
			uKno.setForeground(Color.white);
			uKno.setFont(largeText);
			Dimension d = uKno.getPreferredSize();
			uKno.setBounds((int)((getWidth()/2)-(d.getWidth()/2)), (int)((getHeight()/2)-(d.getHeight()/2)) + 50, (int)d.getWidth(), (int)d.getHeight());
			this.add(uKno);
			uKno.setVisible(true);
			
			JLabel uStil = new JLabel("But U STil TRIed It");
			uStil.setForeground(Color.white);
			uStil.setFont(largeText);
			Dimension dd = uStil.getPreferredSize();
			uStil.setBounds((int)((getWidth()/2)-(dd.getWidth()/2)), (int)((getHeight()/2)-(dd.getHeight()/2)) + 150, (int)dd.getWidth(), (int)dd.getHeight());
			this.add(uStil);
			uStil.setVisible(true);
			
			this.revalidate();
		}

		if (pathfinding.isComplete()) {
			timer.setDelay(50);
			timer.start();

			Color flicker = new Color(r, G, b);
			g.setColor(flicker);
			g.fillRect(0, 0, getWidth(), getHeight());
		}

		// Draws grid
		g.setColor(Color.lightGray);
		for (int j = 0; j < this.getHeight(); j += size) {
			for (int i = 0; i < this.getWidth(); i += size) {
				g.drawRect(i, j, size, size);
			}
		}

		// Draws all borders
		g.setColor(Color.black);
		for (int i = 0; i < pathfinding.getBorderList().size(); i++) {
			g.fillRect(pathfinding.getBorderList().get(i).getX() + 1, pathfinding.getBorderList().get(i).getY() + 1,
					size - 1, size - 1);
		}

		// Draws open Nodes (path finding nodes)
		for (int i = 0; i < pathfinding.getOpenList().size(); i++) {
			Node current = pathfinding.getOpenList().get(i);

			// A STAR
			if (!pathfinding.checkClosedDuplicate(current)) {
				g.setColor(greenHighlight);
				g.fillRect(current.getX() + 1, current.getY() + 1, size - 1, size - 1);

				drawInfo(current, g);
			}
				g.setColor(greenHighlight);
				g.fillRect(current.getX() + 1, current.getY() + 1, size - 1, size - 1);

				drawInfo(current, g);
		}

		for (int i = 0; i < pathfinding.getClosedList().size(); i++) {
			Node current = pathfinding.getClosedList().get(i);

			g.setColor(redHighlight);
			g.fillRect(current.getX() + 1, current.getY() + 1, size - 1, size - 1);

			drawInfo(current, g);
		}

		// Draw final path
		for (int i = 0; i < pathfinding.getPathList().size(); i++) {
			Node current = pathfinding.getPathList().get(i);

			g.setColor(blueHighlight);
			g.fillRect(current.getX() + 1, current.getY() + 1, size - 1, size - 1);

			drawInfo(current, g);
		}

		// Draws start of path
		if (startNode != null) {
			g.setColor(Color.blue);
			g.fillRect(startNode.getX() + 1, startNode.getY() + 1, size - 1, size - 1);
		}
		// Draws end of path
		if (endNode != null) {
			g.setColor(Color.red);
			g.fillRect(endNode.getX() + 1, endNode.getY() + 1, size - 1, size - 1);
		}

		modeText.setText("Mode: " + mode);
		Dimension size = modeText.getPreferredSize();
		modeText.setBounds(18, this.getHeight() - 38, size.width, size.height);
	}

	public void drawInfo(Node current, Graphics g) {
		if (size > 50) {
			g.setFont(numbers);
			g.setColor(Color.black);
			g.drawString(Integer.toString(current.getF()), current.getX() + 4, current.getY() + 16);
			g.setFont(smallNumbers);
			g.drawString(Integer.toString(current.getG()), current.getX() + 4, current.getY() + size - 7);
			g.drawString(Integer.toString(current.getH()), current.getX() + size - 26, current.getY() + size - 7);
		}
	}

	public void MapCalculations(MouseEvent e) {
		if (SwingUtilities.isLeftMouseButton(e)) {
			if (currentKey == 's') {
				int xRollover = e.getX() % size;
				int yRollover = e.getY() % size;

				if (startNode == null) {
					startNode = new Node(e.getX() - xRollover, e.getY() - yRollover);
				} else {
					startNode.setXY(e.getX() - xRollover, e.getY() - yRollover);
				}
				repaint();
			} else if (currentKey == 'e') {
				int xRollover = e.getX() % size;
				int yRollover = e.getY() % size;

				if (endNode == null) {
					endNode = new Node(e.getX() - xRollover, e.getY() - yRollover);
				} else {
					endNode.setXY(e.getX() - xRollover, e.getY() - yRollover);
				}
				repaint();
			} else {
				int xRollover = e.getX() % size;
				int yRollover = e.getY() % size;
				xBorder = e.getX() - xRollover;
				yBorder = e.getY() - yRollover;

				Node newBorder = new Node(xBorder, yBorder);
				pathfinding.addBorder(newBorder);

				repaint();
			}
		} else if (SwingUtilities.isRightMouseButton(e)) {
			int mouseBoxX = e.getX() - (e.getX() % size);
			int mouseBoxY = e.getY() - (e.getY() % size);

			if (currentKey == 's') {
				if (startNode != null && mouseBoxX == startNode.getX() && startNode.getY() == mouseBoxY) {
					startNode = null;
					repaint();
				}
			} else if (currentKey == 'e') {
				if (endNode != null && mouseBoxX == endNode.getX() && endNode.getY() == mouseBoxY) {
					endNode = null;
					repaint();
				}
			} else {
				int Location = pathfinding.searchBorder(mouseBoxX, mouseBoxY);
				if (Location != -1) {
					pathfinding.removeBorder(Location);
				}
				repaint();
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		MapCalculations(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		MapCalculations(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		char key = e.getKeyChar();
		currentKey = key;

		if (currentKey == KeyEvent.VK_SPACE) {
			if (!showSteps && (pathfinding.getStart() == null | pathfinding.getEnd() == null)) {
				pathfinding.start(startNode, endNode);
			} else if (showSteps) {
				pathfinding.setup(startNode, endNode);
				timer.setDelay(25);
				timer.start();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		currentKey = (char) 0;
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent m) {
		int rotation = m.getWheelRotation();
		prevSize = size;

		if (rotation == -1 && size + scroll < 200) {
			size += scroll;
		} else if (rotation == 1 && size - scroll > 2) {
			size += -scroll;
		}
		pathfinding.setSize(size);
		double ratio = size / prevSize;

		// new X and Y values for Start
		if (startNode != null) {
			int sX = (int) Math.round(startNode.getX() * ratio);
			int sY = (int) Math.round(startNode.getY() * ratio);
			startNode.setXY(sX, sY);
		}

		// new X and Y values for End
		if (endNode != null) {
			int eX = (int) Math.round(endNode.getX() * ratio);
			int eY = (int) Math.round(endNode.getY() * ratio);
			endNode.setXY(eX, eY);
		}

		// new X and Y values for borders
		for (int i = 0; i < pathfinding.getBorderList().size(); i++) {
			int newX = (int) Math.round((pathfinding.getBorderList().get(i).getX() * ratio));
			int newY = (int) Math.round((pathfinding.getBorderList().get(i).getY() * ratio));
			pathfinding.getBorderList().get(i).setXY(newX, newY);
		}

		// New X and Y for Open nodes
		for (int i = 0; i < pathfinding.getOpenList().size(); i++) {
			int newX = (int) Math.round((pathfinding.getOpenList().get(i).getX() * ratio));
			int newY = (int) Math.round((pathfinding.getOpenList().get(i).getY() * ratio));
			pathfinding.getOpenList().get(i).setXY(newX, newY);
		}

		// New X and Y for Closed Nodes
		for (int i = 0; i < pathfinding.getClosedList().size(); i++) {
			if (!Node.isEqual(pathfinding.getClosedList().get(i), startNode)) {
				int newX = (int) Math.round((pathfinding.getClosedList().get(i).getX() * ratio));
				int newY = (int) Math.round((pathfinding.getClosedList().get(i).getY() * ratio));
				pathfinding.getClosedList().get(i).setXY(newX, newY);
			}
		}
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (pathfinding.isRunning()) {
			//System.out.println("UPDATE");
			pathfinding.update();
		}
		if (pathfinding.isComplete()) {
			if (r >= 240 | r <= 15) {
				r = (int) (Math.random() * 255) + 1;
			}
			if (G >= 240 | G <= 15) {
				G = (int) (Math.random() * 255) + 1;
			}
			if (b >= 240 | b <= 15) {
				b = (int) (Math.random() * 255) + 1;
			}
			r = (int) (Math.random() * ((r + 15) - (r - 15)) + (r - 15));
			G = (int) (Math.random() * ((G + 15) - (G - 15)) + (G - 15));
			b = (int) (Math.random() * ((b + 15) - (b - 15)) + (b - 15));
		}
		if (pathfinding.isNoPath()) {
			r = (int) (Math.random() * 255) + 1;
			G = (int) (Math.random() * 255) + 1;
			b = (int) (Math.random() * 255) + 1;
		}

		repaint();
	}

}
