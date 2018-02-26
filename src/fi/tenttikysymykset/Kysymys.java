package fi.tenttikysymykset;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Kysymys {

    int id;
    String kysymysteksti;
    int tyyppiId;
    int kategoriaId;


    Kysymys(ResultSet rs) throws SQLException {
        this.id = rs.getInt("id");
        this.kysymysteksti = rs.getString("kysymysteksti");
        this.tyyppiId = rs.getInt("tyyppiId");
        this.kategoriaId = rs.getInt("kategoriaId");
    }

    public int getId() {
        return id;
    }

    public String getKysymysteksti() {
        return kysymysteksti;
    }

    public int getTyyppiId() {
        return tyyppiId;
    }

    public int getKategoriaId() {
        return kategoriaId;
    }

    @Override
    public String toString() {
        return this.getKysymysteksti();
    }


}
