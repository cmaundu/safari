/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ke.sart.site.service;

import co.ke.sart.site.model.Courier;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 *
 * @author CMaundu
 */
@Service
public class CourierService {

    private static String URL_SERVER = "https://api.aftership.com/";
    private static String VERSION_API = "v4";

    private String keyAPI;
    private String apiHost;
    private String apiVersion;

    public CourierService() {
        this.keyAPI = "8933eb4b-5a74-4051-948b-a4defbacd857";
        this.apiHost = URL_SERVER;
        this.apiVersion = VERSION_API;
    }

    /**
     * make a request to the HTTP API of Aftership
     *
     * @param method String with the method of the request: GET, POST, PUT,
     * DELETE
     * @param url String with the URL of the request
     * @param body JSONObject with the body of the request, if the request
     * doesn't need body "GET/DELETE", the body would be null
     * @return A JSONObject with the response of the request
     * @throws AftershipAPIException If the request response an error
     * @throws java.io.IOException If there is a problem with the connection
     * @throws java.text.ParseException If the response can not be parse to
     * JSONObject
     *
     */
    public JSONObject request(String method, String url, JSONObject body)
            throws IOException, ParseException, JSONException {

        BufferedReader rd;
        StringBuilder sb;
        OutputStreamWriter wr;

        HttpURLConnection connection;
        URL serverAddress = new URL(new URL(this.apiHost), this.apiVersion + url);

        connection = (HttpURLConnection) serverAddress.openConnection();
        connection.setRequestMethod(method);
        connection.setReadTimeout(10000);
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("aftership-api-key", keyAPI);

        if (body != null) {
            connection.setDoOutput(true);
        }//if there is information in body, doOutput true, to write

        connection.connect();
        if (body != null) {
            wr = new OutputStreamWriter(connection.getOutputStream());
            wr.write(body.toString());
            wr.flush();
        }

        this.checkAPIResponse(connection.getResponseCode(), connection);
        rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        sb = new StringBuilder();

        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line + '\n');
        }

        JSONObject response;
        response = new JSONObject(sb.toString());

        return response;
    }

    /**
     * Check the status of a http response and if the status is an error throws
     * an exception
     *
     * @param status Status of the connection response
     * @exception AftershipAPIException A customize exception with a message
     * depending of the status error
     *
     */
    public void checkAPIResponse(int status, HttpURLConnection connection)
            throws IOException, ParseException, JSONException {

        if (status > 201) {

            BufferedReader rd;
            StringBuilder sb;
            String message = "";
            String type = "";
            rd = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line + '\n');
            }
        }

    }

    /**
     * Return a list of couriers supported by AfterShip along with their names,
     * URLs and slugs
     *
     * @return A list of Object Courier, with all the couriers supported by the
     * API
     * @throws AftershipAPIException If the request response an error
     * @throws java.io.IOException If there is a problem with the connection
     * @throws java.text.ParseException If the response can not be parse to
     * JSONObject
     *
     */
    @Cacheable("couriers")
    public List<Courier> getAllCouriers() {
        try {
            JSONObject response = this.request("GET", "/couriers/all", null);

            JSONArray couriersJSON = response.getJSONObject("data").getJSONArray("couriers");
            List<Courier> couriers = new ArrayList<Courier>(couriersJSON.length());

            JSONObject element;

            for (int i = 0; i < couriersJSON.length(); i++) {
                element = couriersJSON.getJSONObject(i);

                Courier newCourier = new Courier(element);
                couriers.add(newCourier);
            }
            return couriers;
        } catch (IOException | ParseException | JSONException e) {
        }
        return new ArrayList<Courier>();
    }

    /**
     * Method used to search a Courier
     *
     * @param searchString - the value to search.
     * @return List of Couriers.
     */
    public List<Courier> searchCourier(String searchString) {
        List<Courier> couriers = this.getAllCouriers();
        
        couriers = couriers.stream().parallel()
                .filter(c -> c.getName().contains(searchString) 
                        || c.getOtherName().contains(searchString) || c.getSlug().contains(searchString))
                .collect(Collectors.toList());
        
        return couriers;
    }

}
