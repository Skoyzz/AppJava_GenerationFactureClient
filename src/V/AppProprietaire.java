package V;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import C.Connexion;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class AppProprietaire extends JFrame {

	private JPanel contentPane;
	private JTextField textNom;
	private JTextField textPrenom;
	private JTextField textTelephone;
	private JTextField textAdresse;
	private JTextField textIdentifiant;
	private JTextField textPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppProprietaire frame = new AppProprietaire();
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
	public AppProprietaire() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 408, 328);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNom = new JLabel("Nom :");
		lblNom.setBounds(10, 10, 45, 13);
		contentPane.add(lblNom);
		
		JLabel lblPrenom = new JLabel("Prenom :");
		lblPrenom.setBounds(10, 42, 57, 13);
		contentPane.add(lblPrenom);
		
		JLabel lblAdresse = new JLabel("Adresse :");
		lblAdresse.setBounds(10, 80, 57, 13);
		contentPane.add(lblAdresse);
		
		JLabel lblTelephone = new JLabel("Téléphone :");
		lblTelephone.setBounds(10, 120, 74, 13);
		contentPane.add(lblTelephone);
		
		textNom = new JTextField();
		textNom.setBounds(98, 7, 287, 19);
		contentPane.add(textNom);
		textNom.setColumns(10);
		
		textPrenom = new JTextField();
		textPrenom.setColumns(10);
		textPrenom.setBounds(98, 39, 287, 19);
		contentPane.add(textPrenom);
		
		textTelephone = new JTextField();
		textTelephone.setColumns(10);
		textTelephone.setBounds(98, 117, 287, 19);
		contentPane.add(textTelephone);
		
		textAdresse = new JTextField();
		textAdresse.setColumns(10);
		textAdresse.setBounds(98, 77, 287, 19);
		contentPane.add(textAdresse);
		
		JButton btnSend = new JButton("Envoyer");
		btnSend.setBounds(124, 229, 85, 21);
		contentPane.add(btnSend);
		
		textIdentifiant = new JTextField();
		textIdentifiant.setColumns(10);
		textIdentifiant.setBounds(98, 157, 287, 19);
		contentPane.add(textIdentifiant);
		
		textPassword = new JTextField();
		textPassword.setColumns(10);
		textPassword.setBounds(98, 193, 287, 19);
		contentPane.add(textPassword);
		
		JLabel lblIdentifiant = new JLabel("Identifiant :");
		lblIdentifiant.setBounds(10, 160, 74, 13);
		contentPane.add(lblIdentifiant);
		
		JLabel lblMotDePasse = new JLabel("Mot de passe :");
		lblMotDePasse.setBounds(10, 196, 74, 13);
		contentPane.add(lblMotDePasse);
		
		btnSend.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              
                String nom = textNom.getText();
                String prenom = textPrenom.getText();
                String adresse	 = textAdresse.getText();
                String telephone = textTelephone.getText();
                String identifiant = textIdentifiant.getText();
                String password = textPassword.getText();


                

                // enregistrement dans la base de données
                Connexion.requeteSansResultat("CALL FaireProprietaire('"+nom+"' ,'"+ prenom+"','"+ adresse+"','"+telephone+"','"+identifiant+"','"+password+"')" );
                		// Connexion.requeteSansResultat("INSERT INTO facturation(nom, prenom, mail, numero, datedebut, datefin, type, titre, commentaire, prix)"

                    //+ " VALUES ('" + nom + "', '" + prenom + "', '"+ mail +"', "+ numero +", '" + datedebut + "', '" + datefin + "', '"+recupType +"', '"+ titre +"', '"+ commentaire +"', "+ prix +")");

                AppFacturation Facture = new AppFacturation();
                Facture.setVisible(true);
                dispose();
            }

        });
	}
}
