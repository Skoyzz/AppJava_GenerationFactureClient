		package V;
		
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;


import C.Connexion;
		
		public class AppGenPDF extends JFrame {
			private int id;
			
			
		    private JPanel contentPane;
		    private JComboBox<String> comboBoxTypeGardiennage;
		    private JTextField textDateDebut;
		    private JTextField textDateFin;
		    private JLabel lblType;
		    private JLabel lblDateDebut;
		    private JTextField textTitre;
		    private JTextField textCommentaire;
		    private JLabel lblTitre;
		    private JLabel lblCommentaire;
		    private JLabel lblPrix;
		    private JTextField textPrix;
		    private JButton btnModifier;
		    private JComboBox<String> comboBoxPension;
		    private JLabel lblPension;
		    private JLabel lblPropritaire;
		    private JComboBox<String> comboBoxProprietaire;
		    private JComboBox<String> comboBoxAnimal;
		
		    /**
		     * Create the frame.
		     */
		    public AppGenPDF(int id) {
		    	id = AppListingFacturation.idFacture;
		        // Initialiser les composants de l'interface utilisateur
		        initComponents(id);
		
		        // Utiliser l'identifiant de la facture sélectionnée pour charger les données de la facture dans les champs correspondants
		        try {
		        	ResultSet resultat = Connexion.requeteResultat("CALL ModificationID('" + id+"')");
	
		        	//ResultSet resultat = Connexion.requeteResultat("SELECT nom, prenom, mail, numero, datedebut, datefin, libelle, titre, commentaire, prix FROM facturation INNER JOIN typegardiennage ON typegardiennage.idType = facturation.type WHERE id = " + id);
		            
		
		            if (resultat.next()) {
		            	
		            	
		            	
		            	// Récupérer pension
		                ResultSet resultTypesPension = Connexion.requeteResultat("CALL AfficherPension");
		                // Ajouter chaque pension à la liste déroulante
		                while (resultTypesPension.next()) {
		                	comboBoxPension.setSelectedItem(resultat.getString("Ville"));
		                }
		                // Sélectionner la ville de la pnesion pour la facture
		                comboBoxPension.addItem(resultat.getString("Ville"));
		     	            	
		                	
		                // Récupérer tous les types de gardiennage
		                ResultSet resultTypes = Connexion.requeteResultat("CALL AfficherTypeGardiennage");
		                // Ajouter chaque type de gardiennage à la liste déroulante
		                while (resultTypes.next()) {
		                    comboBoxTypeGardiennage.setSelectedItem(resultTypes.getString("idType")+ " - " + resultTypes.getString("libelle"));
		                }
		                // Sélectionner le type de gardiennage de la facture
		                comboBoxTypeGardiennage.addItem(resultat.getString("libelle"));
		                
		                
		             // Récupérer du proprio
		                ResultSet resultPropri = Connexion.requeteResultat("CALL AfficherProprietaire");
		                // Ajouter proprioà la liste déroulante
		                while (resultPropri.next()) {
		                	comboBoxProprietaire.setSelectedItem(resultPropri.getString("idProprietaire")+ " - " + resultPropri.getString("nom"));
		                }
		                // Sélectionner le type de gardiennage de la facture
		                comboBoxProprietaire.addItem(resultat.getString("nom"));
		                
		                
		                
		                // Récupérer de l'animal
		                ResultSet resultAnimal = Connexion.requeteResultat("CALL AfficherAnimaux");
		                // Ajouter proprioà la liste déroulante
		                while (resultAnimal.next()) {
		                	comboBoxAnimal.setSelectedItem(resultAnimal.getString("idAnimal")+ " - " + resultAnimal.getString("nomAnimal"));
		                }
		                // Sélectionner le type de gardiennage de la facture
		                comboBoxAnimal.addItem(resultat.getString("nomAnimal"));
		                
		                
		                
		               
		                textDateDebut.setText(resultat.getString("date_debut"));
		                textDateFin.setText(resultat.getString("date_fin"));
		                textTitre.setText(resultat.getString("titre"));
		                textCommentaire.setText(resultat.getString("commentaire"));
		                textPrix.setText(resultat.getString("prix"));
		                
		                
		    	        }
		
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }  
		
		    /**
		     * Méthode pour initialiser les composants de l'interface utilisateur.
		     */
		    private void initComponents(int id) {
		        // Titre de la fenêtre
		        setTitle("Facture n°" + id);
		
		        // Définir les dimensions de la fenêtre
		        setBounds(100, 100, 352, 468);
		
		        // Créer le JPanel
		        contentPane = new JPanel();
		        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		        setContentPane(contentPane);
		        contentPane.setLayout(null);
		
		        // Ajouter le champ de saisie pour la date de début
		        textDateDebut = new JTextField();
		        textDateDebut.setEditable(false);
		        textDateDebut.setBounds(173, 81, 150, 20);
		        contentPane.add(textDateDebut);
		
		        // Ajouter le label "Date de fin"
		        JLabel lblDateFin = new JLabel("Date de fin :");
		        lblDateFin.setBounds(10, 127, 201, 14);
		        contentPane.add(lblDateFin);
		
		        // Ajouter le champ de saisie pour la date de fin
		        textDateFin = new JTextField();
		        textDateFin.setEditable(false);
		        textDateFin.setBounds(173, 125, 150, 20);
		        contentPane.add(textDateFin);
		
		        // Ajouter la liste déroulante pour le type de gardiennage
		        comboBoxTypeGardiennage = new JComboBox<String>();
		        comboBoxTypeGardiennage.setBounds(173, 174, 150, 20);
		        contentPane.add(comboBoxTypeGardiennage);
		        
		        lblType = new JLabel("Type de gardiennage : ");
		        lblType.setBounds(10, 178, 153, 13);
		        contentPane.add(lblType);
		        
		        lblDateDebut = new JLabel("Date de debut :");
		        lblDateDebut.setBounds(10, 83, 201, 14);
		        contentPane.add(lblDateDebut);
		        
		        textTitre = new JTextField();
		        textTitre.setEditable(false);
		        textTitre.setBounds(173, 253, 150, 20);
		        contentPane.add(textTitre);
		        
		        textCommentaire = new JTextField();
		        textCommentaire.setEditable(false);
		        textCommentaire.setBounds(173, 283, 150, 20);
		        contentPane.add(textCommentaire);
		        
		        lblTitre = new JLabel("Titre :");
		        lblTitre.setBounds(10, 256, 201, 14);
		        contentPane.add(lblTitre);
		        
		        lblCommentaire = new JLabel("Commentaire : ");
		        lblCommentaire.setBounds(10, 286, 201, 14);
		        contentPane.add(lblCommentaire);
		        
		        lblPrix = new JLabel("Prix :");
		        lblPrix.setBounds(10, 316, 201, 14);
		        contentPane.add(lblPrix);
		        
		        textPrix = new JTextField();
		        textPrix.setEditable(false);
		        textPrix.setBounds(173, 314, 150, 20);
		        contentPane.add(textPrix);
	
		        
		        lblPropritaire = new JLabel("Propriétaire :");
		        lblPropritaire.setBounds(10, 46, 95, 14);
		        contentPane.add(lblPropritaire);
		        
		        JButton btnPDF = new JButton("Générer un PDF");
		        btnPDF.setBounds(23, 395, 283, 21);
		        contentPane.add(btnPDF);
		        
		        comboBoxPension = new JComboBox<String>();
		        comboBoxPension.setBounds(173, 7, 150, 20);
		        contentPane.add(comboBoxPension);
		        
		        lblPension = new JLabel("Pension :");
		        lblPension.setBounds(10, 11, 95, 14);
		        contentPane.add(lblPension);
		        
		        comboBoxAnimal = new JComboBox();
		        comboBoxAnimal.setBounds(173, 222, 150, 21);
		        contentPane.add(comboBoxAnimal);
		        
		        JLabel lblAnimal = new JLabel("Animal");
		        lblAnimal.setBounds(10, 226, 45, 13);
		        contentPane.add(lblAnimal);
		        
		        comboBoxProprietaire = new JComboBox();
		        comboBoxProprietaire.setBounds(173, 43, 155, 21);
		        contentPane.add(comboBoxProprietaire);
		        
		
		        btnPDF.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e) {
		                // Récupérer les données à partir des champs de texte et de la liste déroulante
		                		                   
		                 // créer et enregistrer un fichier PDF
		            	try {
		            	    String nomFichier =  "facture-id-n°"+id; 
		            	    File tempFile = File.createTempFile(nomFichier, ".pdf"); 
		            	    String user = "root"; 
		            	    String host = "172.29.106.10"; 
		            	    int port = 22; 
		            	    String password = "123"; 
		            	    String localFilePath = tempFile.getAbsolutePath();
		            	    String remoteFilePath = "/var/www/html/facture/"+nomFichier+".pdf";         
		            	    
		            	              	    
		            	    Document document = new Document();
							PdfWriter.getInstance(document, new FileOutputStream(tempFile));
		            	    document.open();

		            	    Paragraph titre = new Paragraph("Facture #" + id);
		            	    titre.setAlignment(Element.ALIGN_CENTER);
		            	    document.add(titre);
		            	    
		            	    Paragraph typeGardiennage = new Paragraph("Type de gardiennage: " + comboBoxTypeGardiennage.getSelectedItem().toString());
		            	    document.add(typeGardiennage);
		            	    
		            	    Paragraph pension = new Paragraph("Pension: " + comboBoxPension.getSelectedItem().toString());
		            	    document.add(pension);
		            	    
		            	    Paragraph dateDebut = new Paragraph("Date de début: " + textDateDebut.getText());
		            	    document.add(dateDebut);
		            	    
		            	    Paragraph dateFin = new Paragraph("Date de fin: " + textDateFin.getText());
		            	    document.add(dateFin);
		            	    
		            	    Paragraph proprietaire = new Paragraph("Propriétaire: " + comboBoxProprietaire.getSelectedItem().toString());
		            	    document.add(proprietaire);
		            	    
		            	    Paragraph animal = new Paragraph("Animal: " + comboBoxAnimal.getSelectedItem().toString());
		            	    document.add(animal);
		            	    	
		            	    Paragraph titreGardiennage = new Paragraph("Titre: " + textTitre.getText());
		            	    document.add(titreGardiennage);
		            	    
		            	    Paragraph commentaire = new Paragraph("Commentaire: " + textCommentaire.getText());
		            	    document.add(commentaire);
		            	    
		            	    Paragraph prix = new Paragraph("Prix: " + textPrix.getText() + "€");
		            	    document.add(prix);
		            	     
		            	    document.close();
		            	
		            	    JSch jsch = new JSch();
		            	    Session session = jsch.getSession(user, host, port);
		            	    session.setPassword(password);
		            	    session.setConfig("StrictHostKeyChecking", "no");
		            	    session.connect();

		            	    ChannelSftp channel = (ChannelSftp) session.openChannel("sftp");
		            	    channel.connect();
		            	    channel.put(localFilePath, remoteFilePath);

		            	    channel.disconnect();
		            	    session.disconnect();   
		            	    tempFile.delete();
		            	    
		            	    Connexion.requeteSansResultat("UPDATE facturation SET PDF ='" + nomFichier + ".pdf' WHERE id =" + id);
		            	    
		                    JOptionPane.showMessageDialog(null, "Vous avez générer une facture");
		                    
		                    AppListingFacturation appListing = new AppListingFacturation();
		                    appListing.setVisible(true);
		                    dispose();

		            	    
		            	} catch (IOException | DocumentException | JSchException | SftpException e1) {
		                    JOptionPane.showMessageDialog(null, "Vous avez eu une erreur de génerationfacture");

		            	    System.out.println(e1);
		            	}	
		            }
		        });
		    }	
		}