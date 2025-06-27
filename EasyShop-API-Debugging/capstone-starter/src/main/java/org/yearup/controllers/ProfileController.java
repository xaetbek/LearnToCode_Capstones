package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.data.ProfileDao;
import org.yearup.data.UserDao;
import org.yearup.models.Profile;
import org.yearup.models.User;

import java.security.Principal;

@RestController
@RequestMapping("profile")
@CrossOrigin
public class ProfileController
{
    private ProfileDao profileDao;
    private UserDao userDao;

    @Autowired
    public ProfileController(ProfileDao profileDao, UserDao userDao)
    {
        this.profileDao = profileDao;
        this.userDao = userDao;
    }

    @GetMapping("")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Profile getProfile(Principal principal)
    {
        try
        {
            // Get the currently logged-in user
            String userName = principal.getName();
            User user = userDao.getByUserName(userName);

            if (user == null)
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");

            // Get the user's profile
            Profile profile = profileDao.getByUserId(user.getId());

            if (profile == null)
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile not found");

            return profile;
        }
        catch (ResponseStatusException ex)
        {
            throw ex;
        }
        catch (Exception ex)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }

    @PutMapping("")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void updateProfile(@RequestBody Profile profile, Principal principal)
    {
        try
        {
            // Get the currently logged-in user
            String userName = principal.getName();
            User user = userDao.getByUserName(userName);

            if (user == null)
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");

            // Ensure the profile belongs to the current user
            profile.setUserId(user.getId());

            // Update the profile
            profileDao.update(user.getId(), profile);
        }
        catch (ResponseStatusException ex)
        {
            throw ex;
        }
        catch (Exception ex)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Oops... our bad.");
        }
    }
}