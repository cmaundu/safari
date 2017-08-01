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
import java.security.Principal;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author CMaundu
 */
@RestController("report")
public class ReportRestController {
     
    @Autowired
    ReportService reportService;
    
    @RequestMapping(name="list", method = RequestMethod.GET)
    public Map<String,Object> list(Model model) {
        
        Map<String,Object> pays = reportService.getPayments();
        return pays;
    }
    
}
