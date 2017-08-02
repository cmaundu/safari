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
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@WebController
@RequestMapping("courier")
public class CourierController {

    @Autowired
    CourierService courierService;

    /* 
     * Default Controller listing all courier Services. Invokes CourierService 
     * to retrieve records.
     */
    @RequestMapping(value = "list")
    @PreAuthorize("hasRole('ROLE_COURIER_SEARCH')")  //Ensure user has Courier list authority
    public String getList(Model model) {
        try {
            model.addAttribute("couriers", this.courierService.getAllCouriers());
        } catch (Exception e) {
        }

        return "couriers/list";
    }

    
    /**
     * Controller method for couriers/search.
     * @param model - model for display
     * @param form - search form.
     * @param errors - if any errors.
     * @return - jsp mapping
     */
    @RequestMapping(value = "search")
    @PreAuthorize("hasRole('ROLE_COURIER_SEARCH')") //Ensure user has Courier Search authority
    public String search(Model model, @Valid CourierController.SearchForm form, Errors errors) {
        if (form == null || errors.hasErrors()) {

        } else if (form.getQuery() == null || form.getQuery().isEmpty()) {

        } else {

            model.addAttribute("couriers", this.courierService.searchCourier(form.getQuery()));
        }

        return "couriers/search";
    }

    // Search class used to store values for seach form
    public static class SearchForm {

        @NotNull(message = "{validate.courier.search.query}")
        private String query;

        public String getQuery() {
            return query;
        }

        public void setQuery(String query) {
            this.query = query;
        }

    }
}
