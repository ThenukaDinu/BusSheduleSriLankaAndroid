package com.oceanblue.busshedulesrilanka;

public class Bus {

    private String busId,startPlace,endPlace,startTime,endTimee,rootNumber,busNumber;

    public Bus() {
    }

    public Bus(String busId, String startPlace, String endPlace, String startTime, String endTimee, String rootNumber, String busNumber) {
        this.busId = busId;
        this.startPlace = startPlace;
        this.endPlace = endPlace;
        this.startTime = startTime;
        this.endTimee = endTimee;
        this.rootNumber = rootNumber;
        this.busNumber = busNumber;
    }

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public String getStartPlace() {
        return startPlace;
    }

    public void setStartPlace(String startPlace) {
        this.startPlace = startPlace;
    }

    public String getEndPlace() {
        return endPlace;
    }

    public void setEndPlace(String endPlace) {
        this.endPlace = endPlace;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
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

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }
}
