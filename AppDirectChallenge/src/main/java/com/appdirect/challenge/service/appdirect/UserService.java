package com.appdirect.challenge.service.appdirect;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appdirect.challenge.domain.appdirect.User;
import com.appdirect.challenge.domain.myapp.Profile;
import com.appdirect.challenge.service.ProfileService;

/**
 * AppDirect user service
 */
@Service
public class UserService {

    private ProfileService profileService;
    
    @Autowired
    public UserService(ProfileService profileService) {
    	this.profileService = profileService;
	}

    /**
     * Creates a profile from an AppDirect User
     *
     * @param user an AppDirect user
     * @return
     */
    public Profile createProfile(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User can not be null");
        }

        if (StringUtils.isEmpty(user.getOpenId())) {
            throw new IllegalArgumentException("User's OpenId can not be empty");
        }

        Profile profile = profileService.getByOpenID(user.getOpenId());

        if (profile == null) {
            profile = new Profile();
            profile.setFirstName(user.getFirstName());
            profile.setLastName(user.getLastName());
            profile.setEmail(user.getEmail());
            profile.setOpenId(user.getOpenId());

            profile = profileService.create(profile);
        }

        return profile;
    }
}
