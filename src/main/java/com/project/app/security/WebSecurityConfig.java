package com.project.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.project.app.filter.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity

public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	@Qualifier("userDetailsService")
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	/* Authorization----------*/
	@Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable()
		    .authorizeRequests()
		      //    .antMatchers("/api/v1/**").permitAll()
		        .antMatchers(HttpMethod.POST,"/api/v1/login/").permitAll()
		        .antMatchers(HttpMethod.POST,"/api/v1/user/register/").permitAll()   //These two URL paths are not secured.
                .anyRequest().authenticated()               //All other paths must be authenticated.
                .and()
            .exceptionHandling()
                .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
            .logout().permitAll();
		
		/* We are using JWT to store roles, so we need to translate that into something that Spring Security can understand. 
		 * The JWT Token needs to be parsed to fetch roles that the SpringSecurityContext needs to
		 * become aware of before it goes on to check if the API’s permissions will allow it.
		 * Hence we pass along the JwtAuthenticationFilter.
		 */

        httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
	
	/* Authentication ----------*/
	
	//AuthenticationManager is the main strategy interface for authentication. It validates whether a given user has the right credentials. 
	//Has only one method "authenticate()".
	@Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
	//AuthenticationManager needs to know where the user’s username and password have been stored.
	//For that we need to override the configure method where Spring will pass an AuthenticationManagerBuilder.
	
	@Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
	
	/*The implementation of UserDetailsService will be used for configuring DaoAuthenticationProvider( Authentication based on data stored in database)
	 * by AuthenticationManagerBuilder.userDetailsService() method.
	 * We also need a PasswordEncoder for the DaoAuthenticationProvider. If we don’t specify, it will use plain text.
	 * 
	 */
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() { //Creates a PasswordEncoder bean used while authentication.
		return new BCryptPasswordEncoder();
	}
}
