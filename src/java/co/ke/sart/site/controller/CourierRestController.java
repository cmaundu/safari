/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ke.sart.site.controller;

import co.ke.sart.site.model.Courier;
import co.ke.sart.site.service.CourierService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * *
 * This class provides control logic for rest calls /api/courier/...
 *
 * @author CMaundu
 */
@RestController
public class CourierRestController {

    @Autowired
    CourierService courierService;

    @RequestMapping(value = "api/courier/list")
    public List<Courier> getList() {
        try {
            return this.courierService.getAllCouriers();
        } catch (Exception e) {
        }

        return null;
    }

    @RequestMapping(value = "api/courier/search/{searchString}")
    public List<Courier> searchCourier(@PathVariable("searchString") String searchString) {
        try {
            return this.courierService.searchCourier(searchString);
        } catch (Exception e) {
        }

        return null;
    }

}
