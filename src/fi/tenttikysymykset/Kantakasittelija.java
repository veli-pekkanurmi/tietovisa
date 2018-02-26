package fi.tenttikysymykset;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Kantakasittelija {

    protected static Connection avaaYhteys(String kanta) throws SQLException, ClassNotFoundException {
        String connstr = String.format("jdbc:mysql://localhost:3306/%s?useSSL=false", kanta);
        tarkistaAjuriluokka();

        return DriverManager.getConnection(connstr, "root", "salasana");
    }

    private static void tarkistaAjuriluokka() throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
    }


}
