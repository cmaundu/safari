/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ke.sart.site.model;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author CMaundu
 */
public class PrescriptionReport {

    private List<PrescriptionReport> RxReportList;
    private List<String> rows;
    private List<String> cols;
    private String drugName;
    private Date created;
    private int quantity;
    private double chargeAmount;
    private boolean dispensed;

    public int getQuantity(String row) {
        return this.RxReportList.stream().filter(p -> p.getDrugName().equals(row))
                .mapToInt(p -> p.getQuantity()).sum();
    }

    public int getQuantity(String row, String col) {
        DateFormat df = new SimpleDateFormat("dd-MM-YYYY");
        return this.RxReportList.stream()
                .filter(p -> p.getDrugName().equals(row) && df.format(p.getCreated()).equals(col))
                .mapToInt(p -> p.getQuantity()).sum();
    }

    public double getAmount(String row) {
        return this.RxReportList.stream().filter(p -> p.getDrugName().equals(row))
                .mapToDouble(p -> p.getChargeAmount()).sum();
    }

    public double getAmount(String row, String col) {
        DateFormat df = new SimpleDateFormat("dd-MM-YYYY");
        return this.RxReportList.stream()
                .filter(p -> p.getDrugName().equals(row) && df.format(p.getCreated()).equals(col))
                .mapToDouble(p -> p.getChargeAmount()).sum();
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(double chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    public boolean isDispensed() {
        return dispensed;
    }

    public void setDispensed(boolean dispensed) {
        this.dispensed = dispensed;
    }

    public List<PrescriptionReport> getRxReportList() {
        return RxReportList;
    }

    public void setRxReportList(List<PrescriptionReport> RxReportList) {
        this.RxReportList = RxReportList;
    }

    public List<String> getRows() {
        return rows;
    }

    public void setRows(List<String> rows) {
        this.rows = rows;
    }

    public List<String> getCols() {
        return cols;
    }

    public void setCols(List<String> cols) {
        this.cols = cols;
    }

}
