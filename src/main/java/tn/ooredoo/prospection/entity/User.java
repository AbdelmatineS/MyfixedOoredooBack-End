package tn.ooredoo.prospection.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users",
       uniqueConstraints = {
           @UniqueConstraint(columnNames = "username"),
           @UniqueConstraint(columnNames = "email")
       })
public class User implements Serializable {
	
	
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  protected Long id;

	  protected String fullName;
	  
	  protected String username;

	  protected String email;

	  protected String password;
	  
	  protected String notToken;
	  
	  protected Long numTel;
	  

	  
	  protected String zone;

	  
	  @ManyToMany(fetch = FetchType.LAZY)
	  @JoinTable(name = "user_roles", 
	             joinColumns = @JoinColumn(name = "user_id"),
	             inverseJoinColumns = @JoinColumn(name = "role_id"))
	  private Set<Role> roles = new HashSet<>();
	

	  public User(String fullName, String username, String email, String password, Long numTel, String zone) {
		  	this.fullName = fullName;
		    this.username = username;
		    this.email = email;
		    this.password = password;
		    this.numTel = numTel;
		    this.zone = zone;
		  }
	  
	  public User(Long id, String fullName, String username, String email, String password, Long numTel, String zone) {
		  	this.id = id;
		  	this.fullName = fullName;
		    this.username = username;
		    this.email = email;
		    this.password = password;
		    this.numTel = numTel;
		    this.zone = zone;
		  }
	  
	  
	  public User(String username, String email, String password) {
		    this.username = username;
		    this.email = email;
		    this.password = password;
		  }
	

}
