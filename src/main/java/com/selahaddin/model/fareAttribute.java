/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.selahaddin.model;

/**
 *
 * @author lenova
 */
public class fareAttribute {

    int route_id, agency_id, transfers, transfer_duration;
    double price;
    String currency_type;

    public int getRoute_id() {
        return route_id;
    }

    public void setRoute_id(int route_id) {
        this.route_id = route_id;
    }

    public int getAgency_id() {
        return agency_id;
    }

    public void setAgency_id(int agency_id) {
        this.agency_id = agency_id;
    }

    public int getTransfers() {
        return transfers;
    }

    public void setTransfers(int transfers) {
        this.transfers = transfers;
    }

    public int getTransfer_duration() {
        return transfer_duration;
    }

    public void setTransfer_duration(int transfer_duration) {
        this.transfer_duration = transfer_duration;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCurrency_type() {
        return currency_type;
    }

    public void setCurrency_type(String currency_type) {
        this.currency_type = currency_type;
    }

    public fareAttribute(int route_id, int agency_id, int transfers, int transfer_duration, double price, String currency_type) {
        this.route_id = route_id;
        this.agency_id = agency_id;
        this.transfers = transfers;
        this.transfer_duration = transfer_duration;
        this.price = price;
        this.currency_type = currency_type;
    }

}
