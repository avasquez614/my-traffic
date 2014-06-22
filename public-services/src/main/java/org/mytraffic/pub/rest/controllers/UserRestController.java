package org.mytraffic.pub.rest.controllers;

import org.craftercms.profile.api.exceptions.ProfileException;
import org.craftercms.profile.api.services.ProfileService;
import org.mytraffic.pub.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

/**
 * REST controller for user related operations.
 *
 * @author avasquez
 * @author mariobarque
 */
@Controller
@RequestMapping("/api/1/users")
public class UserRestController {

    @Value("${crafter.security.tenant.default.name}")
    private String tenantName;
    @Autowired
    private ProfileService profileService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public User register(@RequestParam("username") String username, @RequestParam("password") String password,
                         @RequestParam("email") String email, @RequestParam("firstName") String firstName,
                         @RequestParam("lastName") String lastName, @RequestParam("phoneNumber") String phoneNumber)
            throws ProfileException {
        Map<String, Object> attributes = new HashMap<>(3);
        attributes.put(User.FIRST_NAME_ATTRIBUTE, firstName);
        attributes.put(User.LAST_NAME_ATTRIBUTE, lastName);
        attributes.put(User.PHONE_NUMBER_ATTRIBUTE, phoneNumber);

        return new User(profileService.createProfile(tenantName, username, password, email, true, null, attributes, null));
    }

}
