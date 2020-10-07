package infotronic.sous.com.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import infotronic.sous.com.web.Urls;
@Configuration
public class AdminSecurity extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource dataSource ;
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		String queryUser="SELECT email as principal, password as credentials, true FROM users WHERE email= ?  ";
		String queryRole="SELECT user_email as  principal, roles_role as role   FROM users_roles  WHERE user_email=? ";
		auth.inMemoryAuthentication().withUser("admin")
		.password("1234").roles("ADMIN","USER");
		
		auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery(queryUser)
		.authoritiesByUsernameQuery(queryRole).passwordEncoder(passwordEncoder());
		
		
		
		String queryCostomer="SELECT email as principal, password as credentials, true FROM costomers WHERE email= ?  ";
		String queryRoleCostomer="SELECT email as principal, role_role as role FROM costomers WHERE email = ? " ;
		auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery(queryCostomer)
		.authoritiesByUsernameQuery(queryRoleCostomer)
		.passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.formLogin().loginPage("/login").defaultSuccessUrl(Urls.Home);
		http.authorizeRequests().antMatchers(Urls.MANAGE_PRODUCT+"/**").hasRole("ADMIN");
		http.authorizeRequests().antMatchers("/cart/").hasRole("ADMIN");
		http.authorizeRequests().antMatchers("/**").permitAll();
		http.exceptionHandling().accessDeniedPage("/403");
		http.logout().logoutUrl("/logout")
		.invalidateHttpSession(true)
		.clearAuthentication(true)
		
		.addLogoutHandler(new LogoutHandler() {
			@Override
			public void logout(HttpServletRequest request, HttpServletResponse response, Authentication auth) {
				System.out.println("My Autorities "+ auth.getAuthorities());
				if(auth!=null) {
					new SecurityContextLogoutHandler().logout(request, response, auth);
				}
			}
		}).logoutSuccessUrl(Urls.Home+"?message=User has successfully logged out !")
		  .invalidateHttpSession(true)
		  .clearAuthentication(true);
		  /*.logoutSuccessUrl(Urls.Home+"?message=User has successfully logged out !")
		 ;*/
		
		
	}
	

}
