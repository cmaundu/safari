/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ke.sart.site.model;

import java.util.List;
import java.util.stream.Collectors;

public class ChargeDisplay {

    private int chargeid;
    private String lovtype;
    private double amount;
    private String recordStatus;
    private String lovName;
    private String lovVal;
    private int paymentMode;
    private int lovID;

    private List<Integer> rows;

    private List<ChargeDisplay> cdList;

    public void generateRows() {
        this.rows = this.cdList.stream().map(p -> p.getLovID()).distinct().collect(Collectors.toList());
    }

    public String getLovName(int lovID) {
        String lovNameStr = this.cdList.stream().filter(p -> p.getLovID() == lovID).map(p -> p.getLovVal()).findFirst().orElse("");
        return lovNameStr;
    }

    public double getChargeAmount(int lovID, int paymentMode) {
        double chargeAmount = this.cdList.stream()
                .filter(p -> p.getLovID() == lovID && p.getPaymentMode() == paymentMode)
                .map(p -> p.getAmount()).findFirst().orElse(
                this.cdList.stream()
                        .filter(p -> p.getLovID() == lovID && p.getPaymentMode() == 0)
                        .map(p -> p.getAmount()).findFirst().orElse(-2.0)
        );
        return chargeAmount;
    }

    public int getChargeid() {
        return chargeid;
    }

    public void setChargeid(int chargeid) {
        this.chargeid = chargeid;
    }

    public String getLovtype() {
        return lovtype;
    }

    public void setLovtype(String lovtype) {
        this.lovtype = lovtype;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public String getLovName() {
        return lovName;
    }

    public void setLovName(String lovName) {
        this.lovName = lovName;
    }

    public String getLovVal() {
        return lovVal;
    }

    public void setLovVal(String lovVal) {
        this.lovVal = lovVal;
    }

    public int getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(int paymentMode) {
        this.paymentMode = paymentMode;
    }

    public int getLovID() {
        return lovID;
    }

    public void setLovID(int lovID) {
        this.lovID = lovID;
    }

    public List<ChargeDisplay> getCdList() {
        return cdList;
    }

    public void setCdList(List<ChargeDisplay> cdList) {
        this.cdList = cdList;
    }

    public List<Integer> getRows() {
        return rows;
    }

    public void setRows(List<Integer> rows) {
        this.rows = rows;
    }

}
