package V;

import C.Connexion;
import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;



public class AppListingFacturation extends JFrame {

	private JPanel contentPane;
    private JTable table;
    public static int idFacture;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AppListingFacturation frame = new AppListingFacturation();
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
    public AppListingFacturation() {
        setTitle("Facture mise au vert");
        setBounds(100, 100, 959, 479);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

      

        JButton btnAddFacture = new JButton("Ajouter");
        btnAddFacture.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AppFacturation mise_au_vert = new AppFacturation();
                mise_au_vert.setVisible(true);
                dispose();
            }
        });
        btnAddFacture.setBounds(859, 140, 82, 66);    
        contentPane.add(btnAddFacture);

        // Définir les noms de colonnes
        String[] columnNames = {"Numéro de facture", "Pension", "Propriétaire",  "Date de début", "Date de fin", "Type de gardiennagec", "Animal", "Titre de la facture", "Commentaire", "Prix"};

        // Créer un modèle de tableau avec les noms de colonnes
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        try {
            ResultSet resultat = Connexion.requeteResultat("CALL AfficherFacturation");

            while (resultat.next()) {
                // Ajouter chaque ligne de la base de données dans le modèle de tableau
                Object[] rowData = {resultat.getString("id"), resultat.getString("Ville"), resultat.getString("nom"), 
                							resultat.getString("date_debut"), resultat.getString("date_fin"), resultat.getString("libelle"), resultat.getString("nomAnimal"),
                									resultat.getString("titre"), resultat.getString("commentaire"), 
                										resultat.getString("prix")+("€")};
                tableModel.addRow(rowData);
                	// C'est une implémentation standard de l'interface TableModel. addRow est une méthode de cette classe qui permet d'ajouter une ligne à un modèle de tableau
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Créer un JTable à partir du modèle de tableau
        JTable tableau = new JTable(tableModel);
        tableau.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Ajouter le JTable au JPanel
        JScrollPane scrollPane = new JScrollPane(tableau);
        scrollPane.setBounds(0, 0, 849, 426);
        contentPane.add(scrollPane);

        // Ajouter le bouton "Modifier"
        JButton btnPDF = new JButton("PDF");
        btnPDF.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Récupération de l'identifiant de la facture sélectionnée dans le tableau
                int row = tableau.getSelectedRow();
                if (row == -1) {
                    // Aucune ligne n'a été sélectionnée dans le tableau
                    JOptionPane.showMessageDialog(null, "Veuillez sélectionner une facture.");
                    return;
                }   
                idFacture = Integer.parseInt(tableau.getValueAt(row, 0).toString());

                // Ouverture de la fenêtre de modification de la facture correspondante à l'identifiant
                AppGenPDF modifFacture = new AppGenPDF(idFacture);
                modifFacture.setVisible(true);
                dispose();
            }
        });

        btnPDF.setBounds(859, 236, 82, 66);
        contentPane.add(btnPDF);


    }
}
