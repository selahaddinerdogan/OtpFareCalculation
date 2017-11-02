/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selahaddin.model;

/**
 *
 * @author erman
 */
public class fareTransfer {

    int agency_id, transfer_id;
    double price;

    public int getAgency_id() {
        return agency_id;
    }

    public void setAgency_id(int agency_id) {
        this.agency_id = agency_id;
    }

    public int getTransfer_id() {
        return transfer_id;
    }

    public void setTransfer_id(int transfer_id) {
        this.transfer_id = transfer_id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public fareTransfer(int agency_id, int transfer_id, double price) {
        this.agency_id = agency_id;
        this.transfer_id = transfer_id;
        this.price = price;
    }

}
