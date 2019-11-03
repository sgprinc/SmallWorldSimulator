import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Network {
	private List<Node> networkNodes = null;
	private int count = 0;
	private int radius = 0;
	private int centerX = 0;
	private int centerY = 0;
	private int height = 0;
	private int width = 0;
	private int connectionCoef = 0;
	private int entropyCoef = 0;
	private int connectionCount = 0;
	private final String CONNECTION_TYPE_SYMMETRIC = "symmetric";
	private final String CONNECTION_TYPE_LINEAR = "linear";

	public Network() {
	}

	public Network(int nodes, int connections, int entropy, int dimensionX, int dimensionY) {
		connectionCoef = connections;
		entropyCoef = entropy;
		height = dimensionY;
		width = dimensionX;
		generateNetwork(nodes, connections, entropy);
	}

	public void generateNetwork(int nodes, int connections, int entropy) {
		networkNodes = new ArrayList<Node>();
		count = 0;
		centerX = width / 2;
		centerY = height / 2;
		radius = (int) (Math.min(height, width) * 0.4);

		double step = Math.toRadians(360) / nodes;
		for (int i = 0; i < nodes; ++i) {
			networkNodes.add(generateNode(step * i));
			this.count++;
		}
		generateConnections(connections, CONNECTION_TYPE_SYMMETRIC);
		induceEntropy(entropy);
	}

	private Node generateNode(double angle) {
		int x = (int) Math.round((centerX + radius * Math.cos(angle) - 25));
		int y = (int) Math.round((centerY + radius * Math.sin(angle) - 25));

		int anx = (int) Math.round((centerX + (radius + 40) * Math.cos(angle)));
		int any = (int) Math.round((centerY + (radius + 40) * Math.sin(angle) + 6));

		Node temp = new Node(count, x, y);
		temp.setAnchorX(anx);
		temp.setAnchorY(any);

		return temp;
	}

	private void generateConnections(int connections, String connectionType) {
		connections = (connections > (count - 1)) ? count - 1 : connections;
		for (int i = 0; i < networkNodes.size(); ++i) {
			if (connectionType.equals(CONNECTION_TYPE_SYMMETRIC)) {
				for (int j = 0; j < Math.ceil(connections / 2.0); ++j) {
					// Following nodes
					setConnection(networkNodes.get(i), networkNodes.get(((i + j + 1) + count) % count));
					// Previous nodes
					setConnection(networkNodes.get(i), networkNodes.get(((i - j + 1) + count) % count));
					connectionCount += 2;
				}
			} else if (connectionType.equals(CONNECTION_TYPE_LINEAR)) {
				for (int j = 1; j <= connections; ++j) {
					setConnection(networkNodes.get(i), networkNodes.get((count + (i + j)) % count));
				}
			}
		}
	}

	private void induceEntropy(int randomness) {
		// Induce entropy proportional to the indicated randomness into a
		// regularly constructed lattice.
		if (randomness > 0) {
			Random random = new Random();

			for (int i = 0; i < networkNodes.size(); ++i) {
				Node currentNode = networkNodes.get(i);

				for (int k = 0; k < currentNode.getContacts().size(); ++k) {
					int r = random.nextInt(100 / randomness);
					if (r == 0) {
						// Select a node index randomly
						int randomIndex = random.nextInt(count);
						// Retrieve such node
						Node possibleNewNode = networkNodes.get(randomIndex);

						// In case the retrieved node produces a self-loops or
						// an edge collitions, offset the random index
						while (possibleNewNode.equals(currentNode) || currentNode.hasContact(possibleNewNode)) {
							randomIndex = (randomIndex + 1) % count;
							possibleNewNode = networkNodes.get(randomIndex);
							if (randomIndex == networkNodes.size() - 1) {
								break;
							}
						}

						editConnection(currentNode, networkNodes.get(k), possibleNewNode);
					}
				}
			}
		}
	}

	private void setConnection(Node node1, Node node2) {
		node1.addConnection(node2);
		node2.addConnection(node1);
	}

	private void removeConnection(Node node1, Node node2) {
		node1.removeConnection(node2);
		node2.removeConnection(node1);
	}

	private void editConnection(Node rootNode, Node oldNode, Node newNode) {
		removeConnection(rootNode, oldNode);
		setConnection(rootNode, newNode);
	}

	public List<Node> getNodes() {
		return networkNodes;
	}

	public void execute() {
		networkNodes.get(3).sendPackage();
	}

	public void rescaleNetwork(int dimensionX, int dimensionY) {
		this.height = dimensionY;
		this.width = dimensionX;
		generateNetwork(count, connectionCoef, entropyCoef);
	}
}
