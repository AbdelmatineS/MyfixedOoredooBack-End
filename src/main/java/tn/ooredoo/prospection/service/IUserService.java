package tn.ooredoo.prospection.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;

import tn.ooredoo.prospection.entity.User;
import tn.ooredoo.prospection.paylaod.request.SignUpRequest;



public interface IUserService {
	  public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest);

	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
	
	public List<User> retrieveAll();
	 public List<User> searchEntities(String attribute, String query);
	 
	 public void saveNotToken(Long userId, String notToken);
	 public void updateNotToken(Long userId, String oldToken, String newToken);
}
