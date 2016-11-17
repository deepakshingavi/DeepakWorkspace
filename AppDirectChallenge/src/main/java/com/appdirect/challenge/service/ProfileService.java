package com.appdirect.challenge.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appdirect.challenge.domain.myapp.Profile;
import com.appdirect.challenge.repository.BaseRepository;
import com.appdirect.challenge.repository.ProfileRepository;

@Service
public class ProfileService extends BaseCrudService<Profile, Long> {

	private ProfileRepository repository;
	
	@Autowired
    public ProfileService(ProfileRepository repository) {
		super();
		this.repository = repository;
	}

    public Profile getByOpenID(String openId) {
        return repository.findByOpenId(openId);
    }

    public Profile getByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    protected BaseRepository<Profile, Serializable> getRepository() {
        return repository;
    }
}
