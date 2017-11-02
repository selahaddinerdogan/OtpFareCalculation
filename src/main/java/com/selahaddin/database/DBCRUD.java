/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selahaddin.database;

import com.selahaddin.model.fareAttribute;
import com.selahaddin.model.fareTransfer;
import com.selahaddin.model.tranferDuration;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lenova
 */
public class DBCRUD {

    public List getFareAttributes() {
        DBTool db = new DBTool();
        List<fareAttribute> listfare = new ArrayList<fareAttribute>();
        String query = "SELECT * FROM public.fare_attributes";
        ResultSet rs = db.getResultSet(query);
        try {
            while (rs.next()) {
                int route_id = rs.getInt("route_id");
                int agency_id = rs.getInt("agency_id");
                int transfers = rs.getInt("transfer");
                int transfer_duration = rs.getInt("transfer_duration");
                String currency_type = rs.getString("currency_type");
                double price = rs.getDouble("price");
                fareAttribute f1 = new fareAttribute(route_id, agency_id, transfers, transfer_duration, price, currency_type);
                listfare.add(f1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBCRUD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DBCRUD.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return listfare;
    }

    public List getFareTransfer() {
        DBTool db = new DBTool();
        List<fareTransfer> listfare = new ArrayList<fareTransfer>();
        String query = "SELECT * FROM public.fare_transfer";
        ResultSet rs = db.getResultSet(query);
        try {
            while (rs.next()) {
                int agency_id = rs.getInt("agency_id");
                int transfer_id = rs.getInt("transfer_id");
                double price = rs.getDouble("price");
                fareTransfer ft = new fareTransfer(agency_id, transfer_id, price);
                listfare.add(ft);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBCRUD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DBCRUD.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return listfare;
    }

    public List getTransferDuration() {
        DBTool db = new DBTool();
        List<tranferDuration> listfare = new ArrayList<tranferDuration>();
        String query = "SELECT * FROM public.transfer_duration";
        ResultSet rs = db.getResultSet(query);
        try {
            while (rs.next()) {
                int agency_id = rs.getInt("agency_id");
                int transfer_id = rs.getInt("transfer_duration");
                tranferDuration td = new tranferDuration(agency_id, transfer_id);
                listfare.add(td);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBCRUD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DBCRUD.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return listfare;
    }

}
