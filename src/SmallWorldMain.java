import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;

public class SmallWorldMain extends JFrame {

	private NetworkViewer networkViewer;

	public static void main(String[] args) throws InterruptedException {
		new SmallWorldMain();
	}

	private SmallWorldMain() {
		this.setTitle("Small World Simulator");
		this.setSize(2000, 1200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel container = new JPanel();
		container.setLayout(new BorderLayout());

		JPanel controlPanel = createControlPanel();
		networkViewer = createNetworkViewer();

		container.add(networkViewer, BorderLayout.CENTER);
		container.add(controlPanel, BorderLayout.EAST);

		this.setContentPane(container);
		this.pack();
		this.setVisible(true);

	}

	private JPanel createControlPanel() {
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new GridLayout(5, 1));
		controlPanel.setPreferredSize(new Dimension(300, 1000));
		controlPanel.setMinimumSize(new Dimension(300, 1000));
		controlPanel.setBackground(Color.GRAY);

		JSlider nodeSlider = new JSlider(SwingConstants.HORIZONTAL, 0, 50, 0);
		nodeSlider.setMajorTickSpacing(5);
		nodeSlider.setMinorTickSpacing(1);
		nodeSlider.setPaintTicks(true);
		nodeSlider.setPaintLabels(true);
		nodeSlider.setPreferredSize(new Dimension(400, 200));

		JSlider connectionSlider = new JSlider(SwingConstants.HORIZONTAL, 0, 50, 0);
		connectionSlider.setMajorTickSpacing(10);
		connectionSlider.setMinorTickSpacing(2);
		connectionSlider.setPaintTicks(true);
		connectionSlider.setPaintLabels(true);
		connectionSlider.setPreferredSize(new Dimension(400, 200));

		JSlider entropySlider = new JSlider(SwingConstants.HORIZONTAL, 0, 100, 0);
		entropySlider.setMajorTickSpacing(10);
		entropySlider.setMinorTickSpacing(1);
		entropySlider.setPaintTicks(true);
		entropySlider.setPaintLabels(true);
		entropySlider.setPreferredSize(new Dimension(400, 200));

		JButton createBtn = new JButton("Create Network");
		JButton entropyBtn = new JButton("Induce Entropy");
		JButton executeBtn = new JButton("Execute");

		createBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				createNetwork(nodeSlider.getValue(), connectionSlider.getValue(), entropySlider.getValue());
			}
		});

		entropyBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				induceEntropy(entropySlider.getValue());
			}
		});

		executeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			}
		});

		controlPanel.add(nodeSlider);
		controlPanel.add(connectionSlider);
		controlPanel.add(entropySlider);
		controlPanel.add(createBtn);
		controlPanel.add(entropyBtn);
		controlPanel.add(executeBtn);

		return controlPanel;
	}

	private NetworkViewer createNetworkViewer() {
		NetworkViewer networkViewer = new NetworkViewer();
		networkViewer.setMinimumSize(new Dimension(500, 1000));
		networkViewer.setPreferredSize(new Dimension(1000, 1000));
		networkViewer.addComponentListener(new ComponentListener() {
			@Override
			public void componentResized(ComponentEvent e) {
				networkViewer.refresh();
				repaint();
			}

			@Override
			public void componentHidden(ComponentEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void componentMoved(ComponentEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void componentShown(ComponentEvent e) {
				// TODO Auto-generated method stub

			}
		});
		return networkViewer;
	}

	private void createNetwork(int nodes, int connections, int entropy) {
		System.out.println("Generating new network");
		Network network = new Network(nodes, connections, entropy, networkViewer.getWidth(), networkViewer.getHeight());
		networkViewer.setNetworkModel(network);
		repaint();
	}

	private void induceEntropy(int entropy) {
		System.out.println("Inducing entropy in the network");
		repaint();
	}
}
