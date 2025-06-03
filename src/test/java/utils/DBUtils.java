
package utils;

import java.sql.*;

public class DBUtils {
    public static void executarQuery(String query) {
        try {
            Connection con = DriverManager.getConnection(
                System.getenv("DB_URL"),
                System.getenv("DB_USER"),
                System.getenv("DB_PASS"));
            Statement stmt = con.createStatement();
            stmt.execute(query);
            con.close();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao executar query: " + e.getMessage());
        }
    }
}
