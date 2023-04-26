package C;

import java.sql.*;

public class Connexion {
    public static Connection dbconnect() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://172.29.106.10:3306/mise_au_vert", "facture", "facture");
       } catch (Exception exception) {
            System.out.println("Erreur lors de la connexion à la base de données : " + exception.getMessage());
        }
        return con;
    }

    public static ResultSet requeteResultat(String prmRequete) {
        Connection con = dbconnect();
        Statement statement;
        ResultSet resultset = null;
        try {
            statement = con.createStatement();
            resultset = statement.executeQuery(prmRequete);
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'exécution de la requête : " + e.getMessage());
        }
        return resultset;
    }

    public static void requeteSansResultat(String prmRequete) {
        Connection con = dbconnect();
        Statement statement = null;
        boolean resultSet = false;
        try {
            statement = con.createStatement();
            resultSet = statement.execute(prmRequete);
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'exécution de la requête : " + e.getMessage());
        }
    }
}
