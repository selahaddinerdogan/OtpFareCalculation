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
public class tranferDuration {

    int agency_id, tranfer_duration;

    public int getAgency_id() {
        return agency_id;
    }

    public void setAgency_id(int agency_id) {
        this.agency_id = agency_id;
    }

    public int getTranfer_duration() {
        return tranfer_duration;
    }

    public void setTranfer_duration(int tranfer_duration) {
        this.tranfer_duration = tranfer_duration;
    }

    public tranferDuration(int agency_id, int tranfer_duration) {
        this.agency_id = agency_id;
        this.tranfer_duration = tranfer_duration;
    }

}
