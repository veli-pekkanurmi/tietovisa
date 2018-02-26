package fi.tenttikysymykset;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Kysymysvaihtoehdot {

    int id;
    int kysymysId;
    String vastausvaihtoehtoteksti;
    boolean oikeaVastaus;

    Kysymysvaihtoehdot(ResultSet rs) throws SQLException {
        this.id = rs.getInt("id");
        this.kysymysId = rs.getInt("kysymysId");
        this.vastausvaihtoehtoteksti = rs.getString("vastausVaihtoehtoTeksti");
        this.oikeaVastaus = (rs.getInt("oikeaVastaus")) != 1 ? true : false;
    }

    public int getId() {
        return id;
    }

    public int getKysymysId() {
        return kysymysId;
    }

    public String getVastausvaihtoehtoteksti() {
        return vastausvaihtoehtoteksti;
    }

    public boolean isOikeaVastaus() {
        return oikeaVastaus;
    }

    @Override
    public String toString() {
        return this.getVastausvaihtoehtoteksti();
    }

}
