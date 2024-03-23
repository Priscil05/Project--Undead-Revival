package main;

import java.sql.*;

public class DataBase {

    GamePanel gp;

    public DataBase(GamePanel gp)
    {
        this.gp = gp;
    }

    /*public void write(int value)
    {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:Project_UndeadRevival.db");
            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS PRJ_UNDEADREVIV " +

                    "(Health   INT NOT NULL)";
            stmt.execute(sql);

            sql = "INSERT INTO PRJ_UNDEADREVIV (Health) " +
                    "VALUES(vlaue);";
            stmt.executeUpdate(sql);

            stmt.close();
            c.close();

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }


    }*/

    public void write(int value) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:Project_UndeadRevival.db");
            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS PRJ_UNDEADREVIVAL " +
                    "(Health   INT NOT NULL)";
            stmt.execute(sql);

            sql = "INSERT INTO PRJ_UNDEADREVIVAL (Health) " +
                    "VALUES(" + value + ");";
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
                if (c != null)
                    c.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
