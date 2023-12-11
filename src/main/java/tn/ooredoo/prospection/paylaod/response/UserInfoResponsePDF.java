package tn.ooredoo.prospection.paylaod.response;

import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import tn.ooredoo.prospection.entity.Role;

@Setter
@Getter
@AllArgsConstructor
public class UserInfoResponsePDF {
	
	  protected Long id;

	  protected String username;

	  protected String email;

	  protected String password;
	  
	  protected String notToken;
	  
	  protected Long numTel;
	  
	  protected String fullName;
	  
	  protected String zone;
	  
	  private Set<Role> roles;

}
