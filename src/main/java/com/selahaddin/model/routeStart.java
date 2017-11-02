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
public class routeStart {

    int route_id;
    long startTime;
    int agency_id;

    public routeStart(int route_id, long startTime, int agency_id) {
        this.route_id = route_id;
        this.startTime = startTime;
        this.agency_id = agency_id;
    }

    public int getRoute_id() {
        return route_id;
    }

    public void setRoute_id(int route_id) {
        this.route_id = route_id;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public int getAgency_id() {
        return agency_id;
    }

    public void setAgency_id(int agency_id) {
        this.agency_id = agency_id;
    }

}
