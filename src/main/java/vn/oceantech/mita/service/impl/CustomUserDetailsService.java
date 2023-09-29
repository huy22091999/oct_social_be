package vn.oceantech.mita.service.impl;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import vn.oceantech.mita.domain.User;
import vn.oceantech.mita.dto.UserDto;
import vn.oceantech.mita.service.UserService;
import vn.oceantech.mita.utils.CommonUtils;

import java.util.Collection;
import java.util.Iterator;

@Service
public class CustomUserDetailsService implements UserDetailsService, InitializingBean {
    @Autowired
    private UserService userService;

    public CustomUserDetailsService() {
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto userDto = null;



        try {
            userDto = this.userService.findByUsername(username);
        } catch (Exception var9) {
            var9.printStackTrace();
        }

        String logContent = "Login with username:" + username;
        if (!CommonUtils.isNull(userDto) && CommonUtils.isPositive(userDto.getId(), true)) {
            User user = userDto.toEntity();
            Collection<? extends GrantedAuthority> cols = user.getAuthorities();
            Iterator var7 = cols.iterator();

            while(var7.hasNext()) {
                GrantedAuthority col = (GrantedAuthority)var7.next();
                user.getAuthorities().add(col);
            }

            return user;
        } else {
            throw new UsernameNotFoundException("User with username (" + username + ") not found.");
        }
    }

    public void afterPropertiesSet() throws Exception {
        if (CommonUtils.isNull(this.userService)) {
            System.out.println("User Service not initialized!");
        }

    }
}
