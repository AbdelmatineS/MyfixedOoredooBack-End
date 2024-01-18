package tn.ooredoo.prospection.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import tn.ooredoo.prospection.entity.ERole;
import tn.ooredoo.prospection.entity.Role;
import tn.ooredoo.prospection.entity.UserSousTraitant;
import tn.ooredoo.prospection.paylaod.request.LoginRequest;
import tn.ooredoo.prospection.paylaod.request.SignUpStRequest;
import tn.ooredoo.prospection.paylaod.response.MessageResponse;
import tn.ooredoo.prospection.paylaod.response.UserInfoResponse;
import tn.ooredoo.prospection.repository.RoleRepository;
import tn.ooredoo.prospection.repository.UserStRepository;
import tn.ooredoo.prospection.security.JwtUtils;
import tn.ooredoo.prospection.service.UserDetailsImpl;

//@CrossOrigin(origins = {"http://localhost:8100","http://localhost:8101","http://localhost:4200","http://192.168.29.1:8100","http://10.255.255.204:8100"}, maxAge = 3600,allowCredentials="true")
@RestController
@RequestMapping("/api/authSt")
public class UserStController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserStRepository userStRepository;
	
	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	JwtUtils jwtUtils;
	
	
	  @PostMapping("/signin")
	  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

	    Authentication authentication = authenticationManager
	        .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

	    SecurityContextHolder.getContext().setAuthentication(authentication);

	    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

	    ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

	    List<String> roles = userDetails.getAuthorities().stream()
	        .map(item -> item.getAuthority())
	        .collect(Collectors.toList());

	    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
	        .body(new UserInfoResponse(userDetails.getId(),
                    userDetails.getUsername(),
                    userDetails.getEmail(),
                    roles));
	  }

	  @PostMapping("/signup")
	  public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpStRequest signUpStRequest) {
	    if (userStRepository.existsByUsername(signUpStRequest.getUsername())) {
	      return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
	    }

	    if (userStRepository.existsByEmail(signUpStRequest.getEmail())) {
	      return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
	    }

	    // Create new user's account
	    UserSousTraitant userSt = new UserSousTraitant(
	    		signUpStRequest.getFullName(),
	    		signUpStRequest.getUsername(),
	    		signUpStRequest.getEmail(),
	            encoder.encode(signUpStRequest.getPassword()),
	            signUpStRequest.getNumTel(),
	            signUpStRequest.getZone(),
	            signUpStRequest.getNomSt());
	    		//Set<String> strRoles = signUpCRequest.getRole();
	    		
	    		
	    		Set<Role> roles = new HashSet<>();

	      Role userRole = roleRepository.findByName(ERole.ROLE_ST)
	          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	      roles.add(userRole);

	      userSt.setRoles(roles);
	    userStRepository.save(userSt);

	    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	  }

	  @PostMapping("/signout")
	  public ResponseEntity<?> logoutUser() {
	    ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
	    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
	        .body(new MessageResponse("You've been signed out!"));
	  }
}
