	package V;
	
	import C.Connexion;
	
	import java.awt.EventQueue;
	import java.awt.Font;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	
	import java.sql.SQLException;
	import java.util.ArrayList;
	
	import javax.swing.JButton;
	import javax.swing.JComboBox;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JPanel;
	import javax.swing.JTextField;
	import javax.swing.border.EmptyBorder;
	
	public class AppFacturation extends JFrame {
	
	    private JPanel contentPane;
	    private JTextField textIntitule;
	    private JTextField textCommentaire;
	    private JTextField textPrix;
	    private JComboBox<String> comboBoxType;
	    private JComboBox<String> comboBoxPension;
	    private JTextField textDateDebut;
	    private JTextField textDateFin;
	    
	    
	    //Declaration des variables
	
	    /**
	     * Launch the application.
	     */
	    public static void main(String[] args) {
	        EventQueue.invokeLater(new Runnable() {
	            public void run() {
	                try {
	                	//on teste dabord si la base de donnée est connectée
						Connection testbdd = Connexion.dbconnect();
						if(testbdd != null) {
	                    AppFacturation frame = new AppFacturation();
	                    frame.setVisible(true);
						}
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        });
	    }
	
	    /**
	     * Create the frame.
	     */
	    public AppFacturation() {
	        setTitle("App Facturation");
	        setBounds(100, 100, 488, 419);
	        contentPane = new JPanel();
	        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	
	        setContentPane(contentPane);
	        contentPane.setLayout(null);
	
	        JLabel lblNewLabel = new JLabel("Application de facturation - Mise au vert");
	        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
	        lblNewLabel.setBounds(100, 10, 268, 19);
	        contentPane.add(lblNewLabel);
	
	        		
			JButton btnlisting = new JButton("Listing facturation");
			btnlisting.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					AppListingFacturation mise_au_vert =new AppListingFacturation();
					mise_au_vert.setVisible(true);
					dispose();
				}
			});
			btnlisting.setBounds(119, 315, 165, 21);
			contentPane.add(btnlisting);
			
			textIntitule = new JTextField();
			textIntitule.setColumns(10);
			textIntitule.setBounds(154, 228, 219, 19);
			contentPane.add(textIntitule);
			
			textCommentaire = new JTextField();
			textCommentaire.setColumns(10);
			textCommentaire.setBounds(154, 257, 219, 19);
			contentPane.add(textCommentaire);
			
			textPrix = new JTextField();
			textPrix.setColumns(10);
			textPrix.setBounds(154, 286, 219, 19);
			contentPane.add(textPrix);
			
			JLabel lblTitre = new JLabel("Titre :");
			lblTitre.setBounds(10, 231, 127, 13);
			contentPane.add(lblTitre);
			
			JLabel lblCommentaire = new JLabel("Commentaire :");
			lblCommentaire.setBounds(10, 260, 127, 13);
			contentPane.add(lblCommentaire);
			
			JLabel lblPrix = new JLabel("Prix :");
			lblPrix.setBounds(10, 289, 127, 13);
			contentPane.add(lblPrix);
			
			JLabel lblType = new JLabel("Type de gardiennage :");
			lblType.setBounds(10, 169, 142, 19);
			contentPane.add(lblType);
			
			JComboBox comboBoxType = new JComboBox();
			ArrayList<String> listeFacturation = new ArrayList<String>();
			ResultSet resultatFacturation = Connexion.requeteResultat("CALL AfficherTypeGardiennage");
			try {
				while (resultatFacturation.next()) {
				    String idLibelle = resultatFacturation.getString("idType")+ " - " + resultatFacturation.getString("libelle");
				    			    
				    listeFacturation.add(idLibelle);
				    comboBoxType.addItem(idLibelle);
				}
	
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String[] facturation = new String[listeFacturation.size()];
			facturation = listeFacturation.toArray(facturation);
			comboBoxType.setBounds(154, 168, 219, 21);
			contentPane.add(comboBoxType);
			
			
			
			JLabel lblPension = new JLabel("Pension :");
			lblPension.setBounds(10, 58, 59, 19);
			contentPane.add(lblPension);
			
			
			JComboBox comboBoxPension = new JComboBox();
			ArrayList<String> listepension = new ArrayList<String>();
			ResultSet resultatpension = Connexion.requeteResultat("CALL AfficherPension");
			try {
			    while (resultatpension.next()) {
			        String idVille = resultatpension.getString("idPension")+ " - " + resultatpension.getString("Ville");
			        listepension.add(idVille);
			        comboBoxPension.addItem(idVille);
			    }
			} catch (SQLException e1) {
			    e1.printStackTrace();
			}
			comboBoxPension.setBounds(154, 57, 214, 21);
			contentPane.add(comboBoxPension);
			
			JLabel lblDateDebut = new JLabel("Date début : (yyyy-mm-jj)");
			lblDateDebut.setBounds(10, 123, 142, 13);
			contentPane.add(lblDateDebut);
			
			JLabel lblDateFin = new JLabel("Date fin :    (yyyy-mm-jj)");
			lblDateFin.setBounds(10, 146, 127, 13);
			contentPane.add(lblDateFin);
			
			textDateDebut = new JTextField();
			textDateDebut.setBounds(154, 120, 214, 19);
			contentPane.add(textDateDebut);
			textDateDebut.setColumns(10);
			
			textDateFin = new JTextField();
			textDateFin.setColumns(10);
			textDateFin.setBounds(154, 143, 214, 19);
			contentPane.add(textDateFin);
			
			
			
			JButton btnAjouter = new JButton("Ajouter");
			btnAjouter.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					AppProprietaire propri =new AppProprietaire();
					propri.setVisible(true);
					dispose();
				}
			});
			btnAjouter.setBounds(378, 88, 85, 21);
			contentPane.add(btnAjouter);
			
			JComboBox comboBoxProprietaire = new JComboBox();
			ArrayList<String> listeProprietaire = new ArrayList<String>();
			ResultSet resultatProprietaire = Connexion.requeteResultat("CALL AfficherProprietaire");
			try {
			    while (resultatProprietaire.next()) {
			        String idproprietaire =  resultatProprietaire.getString("idProprietaire")+  " -"+ " Nom :  " + resultatProprietaire.getString("nom");
			        listeProprietaire.add(idproprietaire); 
			        comboBoxProprietaire.addItem(idproprietaire);
			    }
			} catch (SQLException e1) {
			    e1.printStackTrace();
			}
			comboBoxProprietaire.setBounds(154, 88, 214, 21);
			contentPane.add(comboBoxProprietaire);
			

			JComboBox comboBoxAnimaux = new JComboBox();
			ArrayList<String> listeAnimaux = new ArrayList<String>();
			ResultSet resultatanimaux = Connexion.requeteResultat("CALL AfficherAnimaux");
			try {
			    while (resultatanimaux.next()) {
			        String idAnimauaux =  resultatanimaux.getString("idAnimal")+  " -"+ " Nom :  " + resultatanimaux.getString("nomAnimal");
			        listeAnimaux.add(idAnimauaux); 
			        comboBoxAnimaux.addItem(idAnimauaux);
			    }
			} catch (SQLException e1) {
			    e1.printStackTrace();
			}
			comboBoxAnimaux.setBounds(154, 199, 219, 21);
			contentPane.add(comboBoxAnimaux);
			
			JLabel lblAnimaux = new JLabel("Animal :");
			lblAnimaux.setBounds(10, 202, 142, 19);
			contentPane.add(lblAnimaux);
			
			
			
			JButton btnSave = new JButton("Enregistrement");
	        btnSave.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                String pension = comboBoxPension.getSelectedItem().toString();
	                String recupPension = pension.substring(0, 1);
	                
	                String proprietaire = comboBoxProprietaire.getSelectedItem().toString();
	                String recupPropri = proprietaire.substring(0, 1);
	                
	                String datedebut = textDateDebut.getText();
	                String datefin = textDateFin.getText();
	                
	                String type = comboBoxType.getSelectedItem().toString();
	                String recupType = type.substring(0, 1);
	                
	                String animaux = comboBoxAnimaux.getSelectedItem().toString();
	                String recupAnimaux = animaux.substring(0, 1);
	                
	                
	                String titre = textIntitule.getText();
	                String commentaire = textCommentaire.getText();
	                String prix = textPrix.getText();
	
	                // enregistrement dans la base de données
	                Connexion.requeteSansResultat("CALL Fairefacturation('"+recupPension+"' ,'"+ recupPropri+"','"+datedebut+"','"+datefin+"','	"+recupType+"','"+recupAnimaux+"','"+titre+"','"+commentaire+"','"+prix+"')" );
	                		// Connexion.requeteSansResultat("INSERT INTO facturation(nom, prenom, mail, numero, datedebut, datefin, type, titre, commentaire, prix)"

	                    //+ " VALUES ('" + nom + "', '" + prenom + "', '"+ mail +"', "+ numero +", '" + datedebut + "', '" + datefin + "', '"+recupType +"', '"+ titre +"', '"+ commentaire +"', "+ prix +")");

	                AppListingFacturation modifFacture = new AppListingFacturation();
	                modifFacture.setVisible(true);
	                dispose();
	            }

	        });
	
			btnSave.setBounds(10, 346, 373, 21);
			contentPane.add(btnSave);
			
			JLabel lblPropri = new JLabel("Propriétaire :");
			lblPropri.setBounds(10, 87, 95, 19);
			contentPane.add(lblPropri);
			
			
		}
	}
