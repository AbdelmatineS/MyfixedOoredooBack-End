package tn.ooredoo.prospection.service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;


import tn.ooredoo.prospection.entity.ERole;
import tn.ooredoo.prospection.entity.Role;
import tn.ooredoo.prospection.entity.User;
import tn.ooredoo.prospection.paylaod.request.SignUpRequest;
import tn.ooredoo.prospection.paylaod.response.MessageResponse;
import tn.ooredoo.prospection.repository.RoleRepository;
import tn.ooredoo.prospection.repository.UserRepository;

@Service
public class UserServiceImpl implements IUserService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	  @Autowired
	  PasswordEncoder encoder;

	@Override
	 public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
	    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
	      return ResponseEntity
	          .badRequest()
	          .body(new MessageResponse("Error: Username is already taken!"));
	    }

	    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
	      return ResponseEntity
	          .badRequest()
	          .body(new MessageResponse("Error: Email is already in use!"));
	    }

	    // Create new user's account
	    User user = new User(signUpRequest.getUsername(), 
	               signUpRequest.getEmail(),
	               encoder.encode(signUpRequest.getPassword()));

	    Set<String> strRoles = signUpRequest.getRole();
	    Set<Role> roles = new HashSet<>();

	    if (strRoles == null) {
	      Role userRole = roleRepository.findByName(ERole.ROLE_USERCONSEILLER)
	          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	      roles.add(userRole);
	    } else {
	      strRoles.forEach(role -> {
	        switch (role) {
	        case "admin":
	          Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
	              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	          roles.add(adminRole);

	          break;
	        case "ss":
	          Role ssRole = roleRepository.findByName(ERole.ROLE_ST)
	              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	          roles.add(ssRole);

	          break;
	        default:
	          Role userRole = roleRepository.findByName(ERole.ROLE_PUBLIC)
	              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	          roles.add(userRole);
	        }
	      });
	    }

	    user.setRoles(roles);
	    userRepository.save(user);

	    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	  }

	  @Override
	  @Transactional
	  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    User user = userRepository.findByUsername(username)
	        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

	    return UserDetailsImpl.build(user);
	  }
	
	    public List<User> getAllUsers() {
	        return userRepository.findAll();
	    }

		@Override
		public List<User> retrieveAll() {
			return (List<User>) userRepository.findAll();
		}

		@Override
		public List<User> searchEntities(String attribute, String query) {
	        if ("fullName".equals(attribute)) {
	            return userRepository.findByFullNameContainingIgnoreCase(query);
	        }  else if ("zone".equals(attribute)) {
	            return userRepository.findByZoneContainingIgnoreCase(query);
	        } else if ("numID".equals(attribute)) {
	            //return pRepo.findByNumIDContaining(Long.parseLong(query));
	        }

	        return Collections.emptyList();
		}

}
