    package TicTacToe;

import java.sql.*;

public class SQLiteWork {

    private static Connection conn;
    private static Statement statmt;
    private static ResultSet resSet;

    public static void Connection() throws ClassNotFoundException, SQLException
    {
        conn = null;
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:TicTac.s3db");
        System.out.println("SQLite OK!");
    }


    public static void CreateDB() throws SQLException
    {
        statmt = conn.createStatement();
        statmt.execute("CREATE TABLE if not exists 'TicTac' ('id' INTEGER PRIMARY KEY " +
                "AUTOINCREMENT, 'Winner' text);");

        System.out.println("TAB CREATE");
    }


    public static void WriteDB() throws SQLException
    {
        statmt.execute("INSERT INTO 'TicTac' ('Winner') VALUES ( 0 ); ");

       System.out.println("TAB INSERT");
    }

    public static void updateDB(String s) throws SQLException
    {
        statmt.execute("UPDATE 'TicTac' SET 'Winner' = '"+ s +"' WHERE id = 1;");

        System.out.println(s);
    }


    public static void ReadDB() throws SQLException
    {
        statmt = conn.createStatement();
        resSet = statmt.executeQuery("SELECT * FROM TicTac");

        while(resSet.next())
        {
            int id = resSet.getInt("id");
            String  Winner = resSet.getString("Winner");
            System.out.println( "ID = " + id );
            System.out.println( "Winner = " + Winner );

            System.out.println();
        }

        System.out.println("TAB Read");
    }

    public static void CloseDB() throws SQLException
    {
        conn.close();
        statmt.close();
        resSet.close();

        System.out.println("SQLite Close");
    }

}
