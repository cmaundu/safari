/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.ke.sart.site.controller;

import co.ke.sart.config.annotation.WebController;
import co.ke.sart.site.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@WebController
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class DefaultController {
        @Autowired
        ReportService reportService;
        
    @RequestMapping(value = {"*", "/dashboard/*", "/dashboard/**"}, method = RequestMethod.GET)
    public String dashboard(Model model) {
        model.addAllAttributes(this.reportService.getDashboardDetails());

        return "dashboard";
    }
}
