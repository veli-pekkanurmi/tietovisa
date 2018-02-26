package fi.tenttikysymykset;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // HAETAAN KYSYMYKSET JA VASTAUKSET
        Main olio = new Main();
        List<Kysymys> kysymykset = olio.suoritaKysymykset();
        List<Kysymysvaihtoehdot> vastaukset = olio.suoritaVastaukset();

        // ALOITETAAN KYSELY
        Scanner sc = new Scanner(System.in);

        int pisteita =0;

        int moneskoKysymys = 0; // Laskee kysymykset
        do {
            System.out.println("");
            Kysymys kysytty = kysymykset.get(moneskoKysymys); // hae kysymys
            System.out.println(kysytty); // printtaa kysymys

            //hae vastausvaihtoehdot
            List<Kysymysvaihtoehdot> kysytynVaihtoehdot = new ArrayList();


            System.out.println("Vastausvaihtoehdot ovat:");
            for (int i = 0; i < vastaukset.size(); i++) {
                if (vastaukset.get(i).getKysymysId() == kysytty.getId()) {
                    System.out.println(vastaukset.get(i));
                    kysytynVaihtoehdot.add(vastaukset.get(i));
                }
            }

            System.out.println("Vastaa 1-4!");
            int vastaus = 0;
            vastaus = sc.nextInt() - 1;

            if (kysytynVaihtoehdot.get(vastaus).oikeaVastaus) {
                System.out.println("Väärä vastaus!");
            } else {
                System.out.println("Oikea vastaus!");
                pisteita++;
            }
            moneskoKysymys++;
        } while (moneskoKysymys < 2);

        System.out.println("");
        System.out.println("Peli päättyi :) Pisteitä kerrytit yhteensä: " + pisteita);
    }

    public List<Kysymys> suoritaKysymykset() {

        List<Kysymys> kysymykset = new ArrayList<>();
        try (Connection con = Kantakasittelija.avaaYhteys("tenttikysymykset")) {

            // HAE KYSYMYKSET KANNASTA
            kysymykset = haeKysymykset(con);

            // POIKKEUSKÄSITTELYT
        } catch (SQLException e) {
            System.err.println("Ongelma: " + e);
            StackTraceElement[] rivit = e.getStackTrace();
            System.err.println(rivit[rivit.length - 2]);
        } catch (ClassNotFoundException e) {
            System.err.println("Ajuria ei löydy: " + e);
        }

        return kysymykset;
    }

    public static List<Kysymys> haeKysymykset(Connection con) {

        // SQL kysely
        String kysely = "SELECT * FROM kysymys";
        List<Kysymys> lista = new ArrayList<>();

        try (PreparedStatement pstmt = con.prepareStatement(kysely)) {
            ResultSet rs = pstmt.executeQuery();

            // käydään läpi kysymykset
            // luodaan Kysymys-olioita
            // lisätään oli listaan
            while (rs.next()) {
                Kysymys uusi = new Kysymys(rs);
                lista.add(uusi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista; // kysymyslista
    }

    public List<Kysymysvaihtoehdot> suoritaVastaukset() {

        List<Kysymysvaihtoehdot> vastaukset = new ArrayList<>();

        try (Connection con = Kantakasittelija.avaaYhteys("tenttikysymykset")) {

            // HAE VASTAUKSET KANNASTA
            vastaukset = haeVastaukset(con);

            // POIKKEUSKÄSITTELYT
        } catch (SQLException e) {
            System.err.println("Ongelma: " + e);
            StackTraceElement[] rivit = e.getStackTrace();
            System.err.println(rivit[rivit.length - 2]);
        } catch (ClassNotFoundException e) {
            System.err.println("Ajuria ei löydy: " + e);
        }

        return vastaukset;
    }


    public static List<Kysymysvaihtoehdot> haeVastaukset(Connection con) {

        // SQL kysely
        String kysely = "SELECT * FROM kysymysvaihtoehdot";
        List<Kysymysvaihtoehdot> lista = new ArrayList<>();

        try (PreparedStatement pstmt = con.prepareStatement(kysely)) {
            ResultSet rs = pstmt.executeQuery();

            // käydään läpi vastaukset
            // luodaan vastaus-olioita
            // lisätään olio listaan
            while (rs.next()) {
                Kysymysvaihtoehdot uusi = new Kysymysvaihtoehdot(rs);
                lista.add(uusi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;

    }

}
