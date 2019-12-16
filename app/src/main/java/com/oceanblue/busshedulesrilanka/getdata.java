package com.oceanblue.busshedulesrilanka;

public class getdata {

    String busId;
    String busNumber;
    String endPlace;
    String endTimee;
    String rootNumber;
    String startPlace;
    String startTime;

    public getdata() {
    }

    public getdata(String busId, String busNumber, String endPlace, String endTimee, String rootNumber, String startPlace, String startTime) {
        this.busId = busId;
        this.busNumber = busNumber;
        this.endPlace = endPlace;
        this.endTimee = endTimee;
        this.rootNumber = rootNumber;
        this.startPlace = startPlace;
        this.startTime = startTime;
    }

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public String getEndPlace() {
        return endPlace;
    }

    public void setEndPlace(String endPlace) {
        this.endPlace = endPlace;
    }

    public String getEndTimee() {
        return endTimee;
    }

    public void setEndTimee(String endTimee) {
        this.endTimee = endTimee;
    }

    public String getRootNumber() {
        return rootNumber;
    }

    public void setRootNumber(String rootNumber) {
        this.rootNumber = rootNumber;
    }

    public String getStartPlace() {
        return startPlace;
    }

    public void setStartPlace(String startPlace) {
        this.startPlace = startPlace;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    public String toString(){
        return this.busNumber + "\r\n Start Place : " + startPlace + "\r\n End Place : "+ endPlace + "\r\n Root Number : " + rootNumber + "\r\n Star tTime : " + startTime + "\r\n End Time : " +endTimee + "\r\n" ;

        /*busId;
        busNumber;
        endPlace;
         endTimee;
        rootNumber;
        startPlace;
        startTime;*/
    }
}
