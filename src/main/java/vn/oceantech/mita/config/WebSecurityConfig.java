package vn.oceantech.mita.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import vn.oceantech.mita.service.impl.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
//@Order(SecurityProperties.DEFAULT_FILTER_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userServiceDetails;

    @Autowired
    private CorsFilter corsFilter;

    public WebSecurityConfig() {
    }

    @Autowired
    public void globalUserDetails(final AuthenticationManagerBuilder auth) throws Exception {

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        provider.setUserDetailsService(userServiceDetails);

        auth.authenticationProvider(provider);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/public/**");
		web.ignoring().antMatchers("/swagger-ui/**");
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {

        // Filters
        http.addFilterBefore(corsFilter, ChannelProcessingFilter.class);
        http.httpBasic().disable();
        http.authorizeRequests()

                .antMatchers("/login").permitAll()

                .antMatchers("/api/**").permitAll()
                .antMatchers("/oauth/**").permitAll()

                .antMatchers("/admin/**").fullyAuthenticated()

                .antMatchers("/api/**").permitAll()

                .anyRequest().authenticated()

                .and().formLogin().permitAll()

                .and().csrf().disable();

    }

}
