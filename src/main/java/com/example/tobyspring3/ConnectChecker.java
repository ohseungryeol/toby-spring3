package com.example.tobyspring3;

import java.sql.*;
import java.util.Map;

import static java.lang.System.getenv;

public class ConnectChecker {
    public void check() throws SQLException, ClassNotFoundException {

        Map<String, String> env = getenv();
        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(
                dbHost,
                dbUser,
                dbPassword
        );

        Statement st = con.createStatement(); //Statement는 완성된 쿼리를 넘겨야한다.
        ResultSet rs = st.executeQuery("SHOW DATABASES");
        rs = st.getResultSet();
        while (rs.next()) {
            String str = rs.getString(1);
            System.out.println(str);
        }
    }

    public void add() throws ClassNotFoundException, SQLException {

        Map<String, String> env = getenv();
        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(
                dbHost,
                dbUser,
                dbPassword
        );
        PreparedStatement pstmt = con.prepareStatement("insert into user(id, name, password) values(?, ?, ?)");
        pstmt.setString(1, "3"); //1열
        pstmt.setString(2, "경록"); //2열
        pstmt.setString(3, "1234"); //3열
        pstmt.executeUpdate();

    }

    public void select() throws SQLException, ClassNotFoundException {
        Map<String, String> env = getenv();
        String dbHost = env.get("DB_HOST");
        String dbUser = env.get("DB_USER");
        String dbPassword = env.get("DB_PASSWORD");

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(
                dbHost,
                dbUser,
                dbPassword
        );

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from user");
        rs = st.getResultSet();
        while (rs.next()) {
            String str = rs.getString(1);
            String str2 = rs.getString(2);
            String str3 = rs.getString(3);

            System.out.printf("id = %s, name = %s password = %s\n",str,str2,str3);
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ConnectChecker cc = new ConnectChecker();
        cc.check();
    }
}
