package com.zxc.roomkotlin.tylo;

/**
 * Created by india on 1/9/2019.
 */

public class ShiftModel {


    private String Userid;
    private String Vehicle_id;
    private String vehicle_no;
    private String shift_id;
    private String shift_timing;
    private String shift;
    private String childname;
    private String childclass;
    private String school;
    private String shift_status;

    public ShiftModel() {
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    private String parentid;

    public String getParentname() {
        return parentname;
    }

    public void setParentname(String parentname) {
        this.parentname = parentname;
    }

    private String childid;
    private String parentname;

    public String getChildname() {
        return childname;
    }

    public void setChildname(String childname) {
        this.childname = childname;
    }

    public String getChildclass() {
        return childclass;
    }

    public void setChildclass(String childclass) {
        this.childclass = childclass;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getChildid() {
        return childid;
    }

    public void setChildid(String childid) {
        this.childid = childid;
    }

    public String getUserid() {
        return Userid;
    }

    public void setUserid(String userid) {
        Userid = userid;
    }

    public String getVehicle_id() {
        return Vehicle_id;
    }

    public void setVehicle_id(String vehicle_id) {
        Vehicle_id = vehicle_id;
    }

    public String getVehicle_no() {
        return vehicle_no;
    }

    public void setVehicle_no(String vehicle_no) {
        this.vehicle_no = vehicle_no;
    }

    public String getShift_id() {
        return shift_id;
    }

    public void setShift_id(String shift_id) {
        this.shift_id = shift_id;
    }

    public String getShift_timing() {
        return shift_timing;
    }

    public void setShift_timing(String shift_timing) {
        this.shift_timing = shift_timing;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }


    public String getShift_status() {
        return shift_status;
    }

    public void setShift_status(String shift_status) {
        this.shift_status = shift_status;
    }
}
