package fi.haagahelia.blogmanagment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

import fi.haagahelia.blogmanagment.web.UserDetailServiceImpl;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailServiceImpl userDetailsService;	
	
    protected void configure(HttpSecurity http, WebSecurity web) throws Exception {
        http
        .authorizeRequests().antMatchers("/","/css/**","/articles/**","/register","/article/**","/image/**","/h2_console/**","/console/**").permitAll() // Enable css when logged out
        .and()
        .authorizeRequests().anyRequest().authenticated()
        .and()
      .formLogin()
      		.loginPage("/login")
      		.defaultSuccessUrl("/articles", true)
      		.permitAll()
      		.and()
      .logout()
          .permitAll();
        
        http.csrf().disable();
        http.headers().frameOptions().disable();
        web.httpFirewall(allowUrlEncodedSlashHttpFirewall());
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    
    //to allow "//" into spring firewall (because of the encoded image in base64)
    @Bean
    public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowUrlEncodedSlash(true);
        firewall.setAllowUrlEncodedDoubleSlash(true);
        return firewall;
    }

    
}