/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ke.sart.site.controller;

import co.ke.sart.site.service.CourierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 * This class provides control logic for rest calls /api/courier/...
 * @author CMaundu
 */
@RestController
public class CourierRestController {
    
    @Autowired
    CourierService courierService;    
    
    @RequestMapping(value = "api/courier/list")
    public String getList(Model model) {
        try {
            model.addAttribute("couriers", this.courierService.getAllCouriers());
        } catch (Exception e) {
        }

        return "couriers/list";
    }
}
