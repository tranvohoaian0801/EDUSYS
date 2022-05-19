/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dell
 */
public class XJdbc {
    public static String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver"; 
    public static String dburl="jdbc:sqlserver://localhost:1433;databaseName=EduSys";
    public static String username="sa";
    public static String password="1221";
    
     static{
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(XJdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static PreparedStatement preparedStatement(String sql,Object...args)throws SQLException{
        Connection con = DriverManager.getConnection(dburl,username,password);
        PreparedStatement pstm ;
        if(sql.trim().startsWith("{")){
            pstm = con.prepareCall(sql);
        }else{
            pstm = con.prepareStatement(sql);
        }
        for(int i = 0;i<args.length;i++){
            pstm.setObject(i+1,args[i]);
        }
        return pstm;
    }
    public static ResultSet query (String sql, Object...args){
        try {
            PreparedStatement stmt = XJdbc.preparedStatement(sql, args);
            return stmt.executeQuery();
        } 
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public static Object value(String sql,Object...args){
        try {
            ResultSet rs = XJdbc.query(sql, args);
            if(rs.next()){
                return rs.getObject(0);
            }
            rs.getStatement().getConnection().close();
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static int update (String sql,Object...args){
        try {
            PreparedStatement pstm = XJdbc.preparedStatement(sql, args);
            try {
                return pstm.executeUpdate();
            }
            finally{
                pstm.getConnection().close();   
            }
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
