/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ke.sart.site.controller;

import co.ke.sart.site.entity.UserPrincipal;
import co.ke.sart.site.service.PrintService;
import co.ke.sart.site.service.ReportService;
import co.ke.sart.site.utils.PrintType;
import java.io.IOException;
import java.net.MalformedURLException;
import java.security.Principal;
import java.util.Map;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController("api")
public class ReportRestController {

    @Autowired
    ReportService reportService;

    @RequestMapping(value = "report/list", method = RequestMethod.GET)
    public Map<String, Object> list(Model model) {

        Map<String, Object> pays = reportService.getPayments();
        return pays;
    }
    
    @RequestMapping(value = "location/list", method = RequestMethod.GET)
    public String getLocation() {
        try {
            Client client = ClientBuilder.newClient();
            WebTarget myResource = client.target("https://api.aftership.com/v4");
            MultivaluedMap<String, Object> myHeaders = new MultivaluedHashMap<>();
            myHeaders.add("aftership-api-key", "8933eb4b-5a74-4051-948b-a4defbacd857");
            String response = myResource.request(MediaType.APPLICATION_JSON)
                    .headers(myHeaders)
                    .get(String.class);
            return response;
        } catch (Exception e) {

        }
        return "";
    }    
}
