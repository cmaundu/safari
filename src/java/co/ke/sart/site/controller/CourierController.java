/*
 * This Controller class listens to http requests from client and 
 * Retrieves courier information.
 */
package co.ke.sart.site.controller;

import co.ke.sart.config.annotation.WebController;
import co.ke.sart.site.entity.UserPrincipal;
import co.ke.sart.site.service.CourierService;
import co.ke.sart.site.utils.PrintType;
import java.io.IOException;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@WebController
@RequestMapping("courier")
public class CourierController {

    @Autowired
    CourierService courierService;

    @RequestMapping(value = "list")
    public String getList(Model model) {
        try {
            model.addAttribute("couriers", this.courierService.getAllCouriers());
        } catch (Exception e) {
        }

        return "couriers/list";
    }
}
