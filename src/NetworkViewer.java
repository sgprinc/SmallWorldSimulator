import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class NetworkViewer extends JPanel {

	private static Network networkModel = null;

	// Graphic Resources
	private static BufferedImage activeNode = null;
	private static BufferedImage inactiveNode = null;

	public NetworkViewer() {
		try {
			activeNode = ImageIO.read(SmallWorldMain.class.getResourceAsStream("/node-active.png"));
			inactiveNode = ImageIO.read(SmallWorldMain.class.getResourceAsStream("/node-inactive.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setNetworkModel(Network network) {
		networkModel = network;
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		if (networkModel != null && !networkModel.getNodes().isEmpty()) {
			for (Node node : networkModel.getNodes()) {

				if (node.hasContacts()) {
					g2d.setStroke(new BasicStroke(2));
					for (Node contact : node.getContacts()) {
						if (contact != null) {
							g2d.drawLine(node.getX() + 25, node.getY() + 25, contact.getX() + 25, contact.getY() + 25);
						}
					}
				}

				if (node.getState()) {
					g2d.drawImage(activeNode, node.getX(), node.getY(), null);
				} else {
					g2d.drawImage(inactiveNode, node.getX(), node.getY(), null);
				}
				g2d.setFont(new Font("default", Font.BOLD, 16));
				g.drawString("" + node.getID(), node.getAnchorX(), node.getAnchorY());
			}
		}
	}

	public void refresh() {
		if (networkModel != null) {
			networkModel.rescaleNetwork(this.getWidth(), this.getHeight());
		}
	}
}
