package com.example.demo.AuthorizationServerApplication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.stereotype.Controller;

@Controller
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

	  	@Value("${user.oauth.clientId}")
	    private String ClientID;
	    @Value("${user.oauth.clientSecret}")
	    private String ClientSecret;
	    @Value("${user.oauth.redirectUris}")
	    private String RedirectURLs;

	    private final PasswordEncoder passwordEncoder;
	    
	    public AuthServerConfig(PasswordEncoder passwordEncoder) {
	        this.passwordEncoder = passwordEncoder;
	    }
	    @Override
	    public void configure(
	        AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
	        oauthServer.tokenKeyAccess("permitAll()")
	            .checkTokenAccess("isAuthenticated()")
	            .allowFormAuthenticationForClients();
	    }

	    @Override
	    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
 	        clients.inMemory()
	            .withClient(ClientID)
	            .secret(passwordEncoder.encode(ClientSecret))
	            .authorizedGrantTypes("authorization_code")
	            .scopes("user_info")
	            .autoApprove(true)
	            .redirectUris(RedirectURLs);
	    }
}
