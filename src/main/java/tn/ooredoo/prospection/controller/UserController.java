package tn.ooredoo.prospection.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.ooredoo.prospection.entity.User;
import tn.ooredoo.prospection.service.IUserService;

@CrossOrigin(origins = {"http://localhost:8100","http://localhost:8101","http://localhost:4200","http://192.168.1.57:8100"}, maxAge = 3600,allowCredentials="true")
@RestController
@RequestMapping("/api/authUser")
public class UserController {
	
	
	@Autowired
	IUserService userService;
	
	
    @GetMapping("/search")
    public List<User> searchEntities(
            @RequestParam("attribute") String attribute,
            @RequestParam("query") String query
    ) {
        return userService.searchEntities(attribute, query);
    }
    
    
	@GetMapping("/retrieveallusers")
	@ResponseBody
	  @PreAuthorize("hasRole('ROLE_ST')")
	public List<User> retrieveAll() {
	List<User> listUser = userService.retrieveAll();
	return listUser;
	}

}
