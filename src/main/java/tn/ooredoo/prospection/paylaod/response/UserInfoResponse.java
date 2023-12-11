package tn.ooredoo.prospection.paylaod.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
@AllArgsConstructor
public class UserInfoResponse {
	
	private Long id;
	private String username;
	private String email;
	private List<String> roles;

}
