package tn.ooredoo.prospection.security;


import java.io.InputStream;
import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import tn.ooredoo.prospection.service.UserDetailsServiceImpl;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig implements Serializable{
	  @Autowired
	  UserDetailsServiceImpl userDetailsService;

	  @Autowired
	  private AuthEntryPointJwt unauthorizedHandler;

	  @Bean
	  public AuthTokenFilter authenticationJwtTokenFilter() {
	    return new AuthTokenFilter();
	  }

	  @Bean
	  public DaoAuthenticationProvider authenticationProvider() {
	      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	       
	      authProvider.setUserDetailsService(userDetailsService);
	      authProvider.setPasswordEncoder(passwordEncoder());
	   
	      return authProvider;
	  }
	  
	  @Bean
	  public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
	    return authConfig.getAuthenticationManager();
	  }

	  @Bean
	  public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	  }
	  
	  @Bean
	  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http.cors().and().csrf().disable()
	        .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
	        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
	        .authorizeRequests()
	        /*
	        .antMatchers("/api/auth/**").permitAll()
	        .antMatchers("/api/test/**").permitAll()
	        .antMatchers("/api/authSt/**").permitAll()
	        .antMatchers("/api/authUser/**").permitAll()
	        .antMatchers("/api/authCon/**").permitAll()
	        .antMatchers("/api/DemandeInter/**").permitAll()
	        .antMatchers("/api/authAdmin/**").permitAll()
	        .antMatchers("/api/Prospection/**").permitAll()*/
	        .antMatchers("/api/Prospection/*/*").permitAll()
	        .antMatchers("/api/*/**").permitAll()


	        .anyRequest().authenticated();
	    
	    http.authenticationProvider(authenticationProvider());

	    http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	    
	    return http.build();
	  }
	  
	  @Bean
	  WebMvcConfigurer corsConfigurer() {
		  return new WebMvcConfigurer() {
			    @Override
			    public void addCorsMappings(CorsRegistry registry) {
			        registry.addMapping("/**")
			                .allowedOrigins("http://localhost:8100"
			                		,"http://172.19.3.136:8101"
			                		,"http://localhost:8101"
			                		,"http://localhost:4200"
			                		,"http://192.168.1.117:45779"
			                		,"http://192.168.1.33:8100")
			                		
			                .allowedMethods("GET", "POST", "PUT", "DELETE")
			                .allowedHeaders("*")
			                .allowCredentials(true)
			                .maxAge(3600);
			        		
			    }
		  };
	  }
	  



}

/*
 * "http://localhost:8100",
 * "http://localhost:8101",
 * "http://localhost:4200",
 * "http://192.168.1.57:8100"
 */
