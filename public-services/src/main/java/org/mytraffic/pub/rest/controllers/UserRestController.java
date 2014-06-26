package org.mytraffic.pub.rest.controllers;

import org.craftercms.profile.api.Profile;
import org.craftercms.profile.api.exceptions.ProfileException;
import org.craftercms.profile.api.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    public static final String FIRST_NAME_ATTRIBUTE = "firstName";
    public static final String LAST_NAME_ATTRIBUTE = "lastName";
    public static final String PHONE_NUMBER_ATTRIBUTE = "phoneNumber";

    @Value("${crafter.security.tenant.default.name}")
    private String tenantName;
    @Autowired
    private ProfileService profileService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Profile register(@RequestParam("username") String username, @RequestParam("password") String password,
                            @RequestParam("email") String email, @RequestParam("firstName") String firstName,
                            @RequestParam("lastName") String lastName, @RequestParam("phoneNumber") String phoneNumber)
            throws ProfileException {
        Map<String, Object> attributes = new HashMap<>(3);
        attributes.put(FIRST_NAME_ATTRIBUTE, firstName);
        attributes.put(LAST_NAME_ATTRIBUTE, lastName);
        attributes.put(PHONE_NUMBER_ATTRIBUTE, phoneNumber);

        return profileService.createProfile(tenantName, username, password, email, true, null, attributes, null);
    }

}
