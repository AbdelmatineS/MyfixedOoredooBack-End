package tn.ooredoo.prospection.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import tn.ooredoo.prospection.entity.User;
import tn.ooredoo.prospection.repository.UserRepository;




@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	  @Autowired
	  UserRepository userRepository;

	  @Override
	  @Transactional
	  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    User user = userRepository.findByUsername(username)
	        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

	    return UserDetailsImpl.build(user);
	  }
	  
	    public List<User> findAll(){
	        return userRepository.findAll();
	    }
	    @Transactional
	    public User updateUser(User updatedUser) {
	        Optional<User> optionalUser = userRepository.findById(updatedUser.getId());
	        if (optionalUser.isPresent()) {
	            User user = optionalUser.get();
	            user.setUsername(updatedUser.getUsername());
	            user.setEmail(updatedUser.getEmail());
	            user.setRoles(updatedUser.getRoles());
	            //user.setTel(updatedUser.getTel());
	            //user.setRegion(updatedUser.getRegion());

	            return userRepository.save(user);
	        } else {
	            throw new RuntimeException("User not found with ID: " + updatedUser.getId());
	        }
	    }
	    public void deleteUser(Long userId) {
	    	userRepository.deleteById(userId);
	    }

}
