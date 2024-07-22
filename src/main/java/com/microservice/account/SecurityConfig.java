package com.microservice.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.microservice.account.service.UserInfoService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private UserInfoService userInfoService;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(authorize -> authorize
						.antMatchers(HttpMethod.GET, "/api/login").authenticated()
						.antMatchers(HttpMethod.GET, "/api/employee/getall").permitAll()
						.antMatchers(HttpMethod.POST, "/api/employee/add").hasAuthority("EMPLOYEE")
						.antMatchers(HttpMethod.POST, "/api/country/add").authenticated()
						.antMatchers(HttpMethod.POST, "/api/region/add/{countryId}").authenticated()
						.antMatchers(HttpMethod.POST, "/api/project/add/{regionId}").authenticated()
						.antMatchers(HttpMethod.GET, "/api/customer/getall").hasAuthority("CUSTOMER")
						.antMatchers(HttpMethod.POST, "/api/hr/add").permitAll()
						.antMatchers(HttpMethod.POST, "/api/manager/add").hasAuthority("HR")
						.antMatchers(HttpMethod.POST, "/api/employee/add/{managerId}").hasAuthority("HR")

						.anyRequest().denyAll())
				.httpBasic(Customizer.withDefaults());
		return http.build(); /* */
	}

	/* AuthenticationManager : in-memory / jdbc */

	@Bean
	public AuthenticationManager authenticationManager() {
		DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
		dao.setPasswordEncoder(getEncoder());
		dao.setUserDetailsService(userInfoService);
		ProviderManager manager = new ProviderManager(dao);
		return manager;
	}

	@Bean
	public PasswordEncoder getEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

}
