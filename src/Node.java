
/* Node.java is an object used for every node (or block)
 * on the grid for path finding. Including walls, open, closed
 * path, start and end nodes. This class allows each node to
 * store its position, calculations (f, g, h), parents, and
 * determine equalities to other nodes.
 * by Devon Crawford
 */
public class Node {
	private int x, y, g, h, f;
	private Node parent;
	
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getG() {
		return g;
	}

	public int getH() {
		return h;
	}

	public int getF() {
		return f;
	}

	public Node getNode() {
		return parent;
	}
	
	public Node getParent() {
		return parent;
	}

	public void setXY(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void setG(int g) {
		this.g = g;
	}

	public void setH(int h) {
		this.h = h;
	}

	public void setF(int f) {
		this.f = f;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public static boolean isEqual(Node s, Node e) {
		if (s.getX() == e.getX() && s.getY() == e.getY()) {
			return true;
		}
		return false;
	}
}
