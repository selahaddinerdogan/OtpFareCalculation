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
public class Metro {

    String route_id;
    int agency_id;
    int stops_count;

    public Metro(String route_id, int agency_id, int stops_count) {
        this.route_id = route_id;
        this.agency_id = agency_id;
        this.stops_count = stops_count;
    }

    public String getRoute_id() {
        return route_id;
    }

    public void setRoute_id(String route_id) {
        this.route_id = route_id;
    }

    public int getAgency_id() {
        return agency_id;
    }

    public void setAgency_id(int agency_id) {
        this.agency_id = agency_id;
    }

    public int getStops_count() {
        return stops_count;
    }

    public void setStops_count(int stops_count) {
        this.stops_count = stops_count;
    }
}
