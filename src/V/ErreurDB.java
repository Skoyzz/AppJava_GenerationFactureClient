package V;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ErreurDB extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String erreur) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ErreurDB frame = new ErreurDB(erreur);
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
	public ErreurDB(String erreur) {
		setTitle("Erreur");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 580, 170);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblConnexionLa = new JLabel(erreur);
		lblConnexionLa.setHorizontalAlignment(SwingConstants.CENTER);
		lblConnexionLa.setBounds(10, 64, 546, 13);
		contentPane.add(lblConnexionLa);
	}

}
