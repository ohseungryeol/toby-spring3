package com.example.tobyspring3.dao;

import com.example.tobyspring3.domain.User;

import java.sql.*;
import java.util.Map;

import static java.lang.System.getenv;

public class UserDAO { // 사용자 정보를 DB에 넣고 관리하는 클래스
    ConnectionMaker connectionMaker;

    public UserDAO() {
        this.connectionMaker = new DConnectionMaker();
    }

    public void add(User user) throws ClassNotFoundException, SQLException {

        Connection con = connectionMaker.makeConnection();
        PreparedStatement pstmt = con.prepareStatement("insert into users(id, name, password) values(?, ?, ?)");
        pstmt.setString(1, user.getId()); //1열
        pstmt.setString(2, user.getName()); //2열
        pstmt.setString(3, user.getPassword()); //3열
        pstmt.executeUpdate(); // 쿼리문 실행

        pstmt.close();
        con.close();

    }

    /*public User get(String id) throws ClassNotFoundException, SQLException {
        Connection con = getConnection();

        PreparedStatement pstmt = con.prepareStatement("select id, name, password from users where id = ?");        pstmt.setString(1,id);
        ResultSet rs = pstmt.executeQuery(); //select 결과값을 저장
        rs.next();
        User user = new User();
        user.setId(rs.getString(1));  //1열
        user.setName(rs.getString(2)); //2열
        user.setPassword(rs.getString(3)); //3열
        return user;
    }*/

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        User user3 = new User();
        user3.setId("12");
        user3.setName("형형");
        user3.setPassword("23123");

        UserDAO userDAO = new UserDAO();
        userDAO.add(user3);

    }
}
