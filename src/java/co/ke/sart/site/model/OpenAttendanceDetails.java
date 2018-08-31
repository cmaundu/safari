/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ke.sart.site.model;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author CMaundu
 */
public class OpenAttendanceDetails {

    private List<OpenAttendanceDetails> openAttendances;

    private int attendanceID;
    private int patientID;
    private String attendanceNumber;
    private String patientName;
    private String attendanceType;
    private String paymentMode;
    private String attendanceStatus;
    private String insuranceNumber;
    private Date created;

    public int getAttendanceID() {
        return attendanceID;
    }

    public void setAttendanceID(int attendanceID) {
        this.attendanceID = attendanceID;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public String getAttendanceNumber() {
        return attendanceNumber;
    }

    public void setAttendanceNumber(String attendanceNumber) {
        this.attendanceNumber = attendanceNumber;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getAttendanceType() {
        return attendanceType;
    }

    public void setAttendanceType(String attendanceType) {
        this.attendanceType = attendanceType;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(String attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }

    public String getInsuranceNumber() {
        return insuranceNumber;
    }

    public void setInsuranceNumber(String insuranceNumber) {
        this.insuranceNumber = insuranceNumber;
    }

    public List<OpenAttendanceDetails> getOpenAttendances() {
        return openAttendances;
    }

    public void setOpenAttendances(List<OpenAttendanceDetails> openAttendances) {
        this.openAttendances = openAttendances;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

}
