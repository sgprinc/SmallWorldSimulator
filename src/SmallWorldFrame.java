import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JSlider;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SmallWorldFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					SmallWorldFrame frame = new SmallWorldFrame();
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
	public SmallWorldFrame() {
		setTitle("Small World Simulator");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panel = new JPanel();

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);

		JPanel panel_2 = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 650, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 318, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup().addGap(715).addComponent(panel_1,
								GroupLayout.PREFERRED_SIZE, 279, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE))
				.addGap(57).addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 510, GroupLayout.PREFERRED_SIZE)
				.addGap(171)));

		JLabel lblNewLabel = new JLabel("Nodes in Network:");
		panel_2.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JSlider slider = new JSlider();
		panel_2.add(slider);
		slider.setValue(20);
		slider.setPaintLabels(true);
		slider.setFont(new Font("Verdana", Font.PLAIN, 14));
		slider.setMinorTickSpacing(2);
		slider.setSnapToTicks(true);
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing(10);

		JSlider slider_1 = new JSlider();
		panel_2.add(slider_1);
		slider_1.setSnapToTicks(true);
		slider_1.setPaintLabels(true);
		slider_1.setPaintTicks(true);
		slider_1.setMaximum(50);
		slider_1.setValue(20);
		slider_1.setMinorTickSpacing(2);
		slider_1.setMajorTickSpacing(10);
		slider_1.setFont(new Font("Verdana", Font.PLAIN, 14));

		JSlider slider_2 = new JSlider();
		panel_2.add(slider_2);
		slider_2.setValue(20);
		slider_2.setSnapToTicks(true);
		slider_2.setPaintTicks(true);
		slider_2.setPaintLabels(true);
		slider_2.setMinorTickSpacing(10);
		slider_2.setMajorTickSpacing(25);
		slider_2.setFont(new Font("Verdana", Font.PLAIN, 14));

		JLabel lblConnectionsPerNode = new JLabel("Connections per Node");
		panel_2.add(lblConnectionsPerNode);
		lblConnectionsPerNode.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel lblEntropy = new JLabel("Entropy:");
		panel_2.add(lblEntropy);
		lblEntropy.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JButton btnNewButton = new JButton("Execute");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panel_2.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Recreate");
		panel_2.add(btnNewButton_1);
		panel.setLayout(null);
		contentPane.setLayout(gl_contentPane);
	}
}
