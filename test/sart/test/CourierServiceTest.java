/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sart.test;

import co.ke.sart.site.entity.UserPrincipal;
import co.ke.sart.site.form.UserForm;
import co.ke.sart.site.service.CourierService;
import co.ke.sart.site.service.UserService;
import co.ke.sart.site.utils.FormAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

/***
 * CourierServiceTest a unit test class for testing implementation of courier 
 * Service
 * @author CMaundu
 */
public class CourierServiceTest {
        @Autowired
        CourierService courierService;
        
        
    /***
     * Test Rest API call returns atleast one Courier.
     */
    public final void whenListCourierServiceThenReturnAtLeastOne() {
        Assert.isTrue(courierService.getAllCouriers().size() > 0, "At least one courer service should be returned.");
    }        
}
