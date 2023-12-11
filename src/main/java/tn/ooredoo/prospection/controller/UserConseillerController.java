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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.ooredoo.prospection.entity.ERole;
import tn.ooredoo.prospection.entity.Role;
import tn.ooredoo.prospection.entity.UserConseiller;
import tn.ooredoo.prospection.paylaod.request.LoginRequest;
import tn.ooredoo.prospection.paylaod.request.SignUpCRequest;
import tn.ooredoo.prospection.paylaod.response.MessageResponse;
import tn.ooredoo.prospection.paylaod.response.UserInfoResponse;
import tn.ooredoo.prospection.repository.RoleRepository;
import tn.ooredoo.prospection.repository.UserConRepository;
import tn.ooredoo.prospection.security.JwtUtils;
import tn.ooredoo.prospection.service.UserDetailsImpl;

@CrossOrigin(origins = {"http://localhost:8100","http://localhost:8101","http://localhost:4200","http://192.168.1.57:8100"}, maxAge = 3600,allowCredentials="true")
@RestController
@RequestMapping("/api/authCon")
public class UserConseillerController {
	
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserConRepository userCRepository;
	
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
	  public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpCRequest signUpCRequest) {
	    if (userCRepository.existsByUsername(signUpCRequest.getUsername())) {
	      return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
	    }

	    if (userCRepository.existsByEmail(signUpCRequest.getEmail())) {
	      return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
	    }

	    // Create new user's account
	    UserConseiller userC = new UserConseiller(
	    		signUpCRequest.getFullName(),
	    		signUpCRequest.getUsername(),
	    		signUpCRequest.getEmail(),
	            encoder.encode(signUpCRequest.getPassword()),
	    		signUpCRequest.getNumTel(),
	    		signUpCRequest.getZone(),
	    		signUpCRequest.getNomB());
	    		//Set<String> strRoles = signUpCRequest.getRole();
	    		
	    		
	    		Set<Role> roles = new HashSet<>();

	      Role userRole = roleRepository.findByName(ERole.ROLE_USERCONSEILLER)
	          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	      roles.add(userRole);

	    userC.setRoles(roles);
	    userCRepository.save(userC);

	    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	  }

	  @PostMapping("/signout")
	  public ResponseEntity<?> logoutUser() {
	    ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
	    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
	        .body(new MessageResponse("You've been signed out!"));
	  }

}
