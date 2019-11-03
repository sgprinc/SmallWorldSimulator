import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Node {
	private int ID;
	private List<Node> connections = null;
	private boolean state = false;
	private int positionX;
	private int positionY;
	private int anchorX;
	private int anchorY;

	public Node() {

	}

	public Node(int id, int positionX, int positionY) {
		this.ID = id;
		this.connections = new ArrayList<Node>();
		this.positionX = positionX;
		this.positionY = positionY;
	}

	public void setActive() {
		state = true;
	}

	public void setInactive() {
		state = false;
	}

	public boolean getState() {
		return state;
	}

	public void addConnection(Node newContact) {
		if (!connections.contains(newContact)) {
			connections.add(newContact);
		}
	}

	public void removeConnection(Node node) {
		connections.remove(node);
	}

	public boolean hasContact(Node node) {
		return connections.contains(node);
	}

	public List<Node> getContacts() {
		return connections;
	}

	public boolean hasContacts() {
		if (this.connections == null || this.connections.isEmpty()) {
			return false;
		}
		return true;
	}

	public int getX() {
		return positionX;
	}

	public int getY() {
		return positionY;
	}

	public int getID() {
		return ID;
	}

	public int getAnchorY() {
		return anchorY;
	}

	public void setAnchorY(int anchorY) {
		this.anchorY = anchorY;
	}

	public int getAnchorX() {
		return anchorX;
	}

	public void setAnchorX(int anchorX) {
		this.anchorX = anchorX;
	}

	public Node sendPackage() {
		Random r = new Random();
		int Low = 0;
		int High = connections.size();
		Node tempNode = connections.get(r.nextInt(High - Low) + Low);
		tempNode.setActive();
		this.setInactive();

		return tempNode;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Node) {
			if (((Node) o).getID() == this.getID()) {
				return true;
			}
		}
		return false;
	}
}
