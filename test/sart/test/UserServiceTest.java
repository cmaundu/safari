
package sart.test;
import co.ke.sart.site.entity.UserPrincipal;
import co.ke.sart.site.form.UserForm;
import co.ke.sart.site.service.UserService;
import co.ke.sart.site.utils.FormAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author CMaundu
 */
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public final void whenUserCreatedThenUserNameIsProvided() {
        UserForm userForm = userService.prepareRoleForm(0, FormAction.FULFIL);

        UserPrincipal user = new UserPrincipal();
        user.setDocNumber("9990000");

        Assert.isTrue(!userService.saveUser(user, userForm), "Username must be provided before saving user");

    }
}
