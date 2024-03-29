package com.mercantil.andina.pizzeria.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
	@Autowired
	UserDetailsService usuarioDetailsService;

	@Autowired
    PasswordEncoder passwordEncoder;	
	
	 @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.cors().and()
	            .csrf().disable()
	            .authorizeRequests()
	            .antMatchers(SecurityConstants.URL_PATH+"/api/public/**").permitAll()
	            .antMatchers("/docs/**").permitAll()
	            .anyRequest().authenticated()
	            .and()
	            .addFilter(new JwtAuthenticationFilter(authenticationManager()))
	            .addFilter(new JwtAuthorizationFilter(authenticationManager()))
	            .sessionManagement()
	            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	    }
	 
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception
		{
			super.configure(auth);
			auth.userDetailsService(usuarioDetailsService).passwordEncoder(passwordEncoder);
		}

	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	    @Bean
	    public CorsConfigurationSource corsConfigurationSource() {
	        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());

	        return source;
	    }
}
