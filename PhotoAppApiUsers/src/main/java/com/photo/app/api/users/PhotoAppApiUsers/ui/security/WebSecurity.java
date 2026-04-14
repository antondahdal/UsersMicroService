package com.photo.app.api.users.PhotoAppApiUsers.ui.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.authentication.AuthenticationFilter;

import com.photo.app.api.users.PhotoAppApiUsers.ui.Service.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurity {
	private Environment environment;


	@Autowired
	public WebSecurity(Environment environment) {
		super();
		this.environment = environment;

	}

	@Bean
	protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
		

		http
		.cors(cors -> {})
		.csrf((csrf) -> csrf.disable())
				.authorizeHttpRequests((authz) -> authz.requestMatchers("/users").access(new WebExpressionAuthorizationManager("hasIpAddress('"+environment.getProperty("gateway.ip")+"')"))
				.requestMatchers("/h2-console/**").permitAll())
				.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.headers((headers) -> headers.frameOptions((frameOptions) -> frameOptions.sameOrigin()));

		return http.build();

	}


}
