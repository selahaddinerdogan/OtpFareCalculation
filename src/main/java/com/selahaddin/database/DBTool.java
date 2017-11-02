/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selahaddin.database;

import java.sql.*;

public class DBTool {

    public static void main(String[] args) {

    }
    private java.sql.Connection conn;

    public DBTool() {
        connect();
    }

    public final void connect() {
        try {
            /*
             * Load the JDBC driver and establish a connection.
             */

            //String ip = "193.255.124.98";
            String ip = "193.255.124.98";
            String usr = "postgres";
            String pass = "123456";
            String db = "gtfs_ekim";

            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://" + ip + "/" + db;

            conn = DriverManager.getConnection(url, usr, pass);
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        } catch (Exception e) {
            System.out.println("Veri Tabanı Hatası: " + e);
        }
    }

    public boolean isClosed() {
        try {
            return conn.isClosed();
        } catch (Exception e) {
            return true;
        }
    }

    public boolean execute(String query) {
        try {
            if (conn.isClosed()) {
                connect();
            }

            conn.createStatement().execute(query);
            conn.close();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getDataCount(String query) {

        try {
            if (conn.isClosed()) {
                connect();
            }

            Statement s = conn.createStatement();
            ResultSet r = s.executeQuery(query);
            ResultSetMetaData rsmd = r.getMetaData();
            int count = 0;
            while (r.next()) {
                count++;
            }
            s.close();
            conn.close();
            return count;
        } catch (Exception e) {
            return -1;
        }
    }
 
    public ResultSet getResultSet(String query) {
        try {
            if (conn.isClosed()) {
                connect();
            }
            Statement s = conn.createStatement();
            ResultSet r = s.executeQuery(query);
            conn.close();
            return r;
        } catch (Exception e) {
            return null;
        } finally {
        }
    }

    public Statement getConnStatement() {
        try {
             if (conn.isClosed()) {
                connect();
            }
            Statement s = conn.createStatement();
            return s;
        } catch (Exception e) {
            return null;
        }
    }

    public void closeConn() {

        try {
            conn.close();
        } catch (Exception e) {
        }
    }

    public PreparedStatement prepareStatement(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
