package tn.ooredoo.prospection.paylaod.request;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class SignUpStRequest {

    @NotBlank
    @Size(min = 3, max = 20)
    private String fullName;
    
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;
 
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    
    private Set<String> role;
    
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
	
    @NotNull
    private Long numTel;
    
    @NotBlank
    @Size(min = 2, max = 40)
    private String zone;
    
    @NotBlank
    @Size(min = 2, max = 40)
    private String nomSt;

}
