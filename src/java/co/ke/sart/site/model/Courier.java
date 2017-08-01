/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ke.sart.site.model;

/**
 * This class defines courier Model and business Logic.
 *
 * @author CMaundu
 */
import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Courier {

    /**
     * ID of the courier
     */
    private String slug;
    /**
     * Name of courier
     */
    private String name;
    /**
     * Contact phone number of courier
     */
    private String phone;
    /**
     * Other name of courier, if several they will be separated by commas
     */
    private String otherName;
    /**
     * website url of  courier
     */
    private String url;
    /**
     * Require fields for this courier
     */
    private List<String> requireFields;
    
    
    
    /** Default constructor with all the fields of the class */
    public Courier(String web_url, String slug, String name, String phone, String other_name) {
        this.url = web_url;
        this.slug = slug;
        this.name = name;
        this.phone = phone;
        this.otherName = other_name;
    }

    /**
     * Constructor, creates a Courier from a JSONObject with the information of the Courier,
     * if any field is not specified it will be ""
     *
     * @param jsonCourier   A JSONObject with information of the Courier
     * by the API.
     **/
    public Courier(JSONObject jsonCourier) throws JSONException {
        this.url = jsonCourier.has("web_url")  && !jsonCourier.isNull("web_url")?jsonCourier.getString("web_url"):"";
        this.slug =  jsonCourier.has("slug")  && !jsonCourier.isNull("slug")?jsonCourier.getString("slug"):"";
        this.name = jsonCourier.has("name") && !jsonCourier.isNull("name")?jsonCourier.getString("name"):"";
        this.phone = jsonCourier.has("phone") && !jsonCourier.isNull("phone")?jsonCourier.getString("phone"):"";
        this.otherName = jsonCourier.has("other_name") && !jsonCourier.isNull("other_name")?jsonCourier.getString("other_name"):"";

        JSONArray requireFieldsArray =jsonCourier.isNull("required_fields")?null:jsonCourier.getJSONArray("required_fields");
        if(requireFieldsArray !=null && requireFieldsArray.length()!=0){
            this.requireFields = new ArrayList<String>();
            for (int i=0;i<requireFieldsArray.length();i++){
                this.requireFields.add(requireFieldsArray.get(i).toString());
            }
        }

    }

    public void addRequierField(String requierField) {

        if (this.getRequireFields() == null) {
            this.setRequireFields(new ArrayList<String>());
            this.getRequireFields().add(requierField);
        } else
            this.getRequireFields().add(requierField);
    }

    public void deleteRequierField(String requierField) {
        if (this.getRequireFields() != null) {
            this.getRequireFields().remove(requierField);
        }
    }

    public void deleteRequierFields() {
        this.setRequireFields(null);
    }    

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOther_name() {
        return otherName;
    }

    public void setOther_name(String other_name) {
        this.otherName = other_name;
    }

    public String getWeb_url() {
        return url;
    }

    public void setWeb_url(String web_url) {
        this.url = web_url;
    }

    public List<String> getRequireFields() {
        return requireFields;
    }

    public void setRequireFields(List<String> requireFields) {
        this.requireFields = requireFields;
    }
}
